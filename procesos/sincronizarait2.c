/*
Crear varios hijos en un fork

*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    for (int i = 0; i < 3; i++) {
        pid_t pid = fork();

        if (pid < 0) {
            perror("Error en fork");  // fallo al crear hijo
            exit(1);
        } else if (pid == 0) {
            printf("Hijo %d, PID=%d\n", i, getpid());
            sleep(1 + i);
            exit(0);//evita que el hijo vuelva a crear otro hijo, nieto. 
        }
    }

    // Padre espera a todos los hijos
    for (int i = 0; i < 3; i++) {
        pid_t pid = wait(NULL);
        printf("Padre: terminó el hijo con PID=%d\n", pid);
    }

    return 0;
}
