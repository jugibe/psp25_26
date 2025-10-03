
/*
#include <sys/wait.h>

pid_t wait(int *status);
El proceso padre se bloquea hasta que uno de sus hijos termina.

status guarda cómo terminó el hijo (opcional → puedes pasar NULL).

Devuelve el PID del hijo que terminó.

Si no hay hijos que esperar → devuelve -1.

*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();//duplicamos

    if (pid < 0) {
        perror("Error en fork");  // imprimir mensaje de error
        exit(1);
    } else if (pid == 0) {
        // Hijo
        printf("Soy el hijo, PID=%d\n", getpid());
        sleep(2);
        printf("Hijo termina\n");
        exit(0);
    } else {
        usleep(10);
        // Padre
        printf("Soy el padre, esperando al hijo...\n");
        wait(NULL);  // espera a que el hijo termine
        printf("Padre: el hijo ya terminó\n");
    }

    return 0;
}
