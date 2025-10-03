/*
programa que cmpara el tiempo de sumar numeros grandes entre un proceso de forma secuencial
y dos procesos paralelos
*/
#define _POSIX_C_SOURCE 199309L

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>


#define N 30000000  // Número total de números a sumar
#define HIJOS 2     // Número de procesos hijos

long suma_rango(long inicio, long fin) {
    long suma = 0;
    for (long i = inicio; i <= fin; i++) {
        suma += i;
    }
    return suma;
}

double tiempo_en_segundos(struct timespec inicio, struct timespec fin) {
    return (fin.tv_sec - inicio.tv_sec) + (fin.tv_nsec - inicio.tv_nsec) / 1e9;
}

int main() {
    struct timespec start, end;

    // --------------------
    // Suma secuencial
    clock_gettime(CLOCK_MONOTONIC, &start);
    long suma_total = suma_rango(1, N);
    clock_gettime(CLOCK_MONOTONIC, &end);
    printf("Secuencial: suma=%ld, tiempo=%.6f s\n", suma_total, tiempo_en_segundos(start, end));

    // --------------------
    // Suma paralela con HIJOS
    int pipes[HIJOS][2];

    for (int i = 0; i < HIJOS; i++) {
        if (pipe(pipes[i]) == -1) { perror("pipe"); exit(1); }
    }

    clock_gettime(CLOCK_MONOTONIC, &start);

    for (int i = 0; i < HIJOS; i++) {
        pid_t pid = fork();
        if (pid < 0) { perror("fork"); exit(1); }

        if (pid == 0) {
            // Código del hijo
            close(pipes[i][0]); // cerramos lectura

            long inicio = i * (N / HIJOS) + 1;
            long fin = (i == HIJOS - 1) ? N : (i + 1) * (N / HIJOS);
            long suma = suma_rango(inicio, fin);

            write(pipes[i][1], &suma, sizeof(suma));
            close(pipes[i][1]);
            _exit(0);
        }
    }

    // Código del padre: leer resultados
    long suma_total_paralela = 0;
    for (int i = 0; i < HIJOS; i++) {
        close(pipes[i][1]); // cerramos escritura
        long suma_hijo;
        read(pipes[i][0], &suma_hijo, sizeof(suma_hijo));
        close(pipes[i][0]);
        suma_total_paralela += suma_hijo;
    }

    // Esperamos a todos los hijos
    while (wait(NULL) > 0);

    clock_gettime(CLOCK_MONOTONIC, &end);
    printf("Paralelo (%d hijos): suma=%ld, tiempo=%.6f s\n", HIJOS, suma_total_paralela, tiempo_en_segundos(start, end));

    return 0;
}
