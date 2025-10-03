#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define N 1000  // Número total de números a sumar
#define HIJOS 2 // Dos hijos
/*
El programa suma 1000 numeros
divide la tarea entre dos hijos, que comunican el resultado al padre que calcula la suma total
*/


//Funcion suma
long suma_rango(long inicio, long fin) {
    long suma = 0;
    for (long i = inicio; i <= fin; i++) {
        suma += i;
    }
    return suma;
}

int main() {
    int pipes[HIJOS][2];  // Un pipe por hijo
    pid_t pid;

    // Creamos los pipes
    for (int i = 0; i < HIJOS; i++) {
        if (pipe(pipes[i]) == -1) { perror("pipe"); exit(1); }
    }

    // Creamos los hijos
    for (int i = 0; i < HIJOS; i++) {
        pid = fork();
        if (pid < 0) { perror("fork"); exit(1); }

        if (pid == 0) {
            // Código del hijo
            close(pipes[i][0]); // cerramos lectura

            long inicio = i * (N / HIJOS) + 1;//hijo i=0, i=1 se dividen los numeros a sumar inicio y fin
            long fin = (i == HIJOS - 1) ? N : (i + 1) * (N / HIJOS);
            long suma = suma_rango(inicio, fin);

            write(pipes[i][1], &suma, sizeof(suma));
            close(pipes[i][1]);
            _exit(0);
        }
    }

    // Código del padre: leer resultados
    long suma_total = 0;
    for (int i = 0; i < HIJOS; i++) {
        close(pipes[i][1]); // cerramos escritura
        long suma_hijo;
        read(pipes[i][0], &suma_hijo, sizeof(suma_hijo)); //no lee en paralelo, hasta que no escribe el primero no lee el 2
        printf("Padre lee suma del hijo %d valor %d\n",i,suma_hijo);
        close(pipes[i][0]);
        suma_total += suma_hijo;
    }

    // Esperamos a todos los hijos con while
    while (wait(NULL) > 0);

    printf("Suma total: %ld\n", suma_total);

    return 0;
}
