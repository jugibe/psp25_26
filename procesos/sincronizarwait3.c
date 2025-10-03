#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();

    if (pid < 0) {
        perror("Error en fork");
        exit(1);
    } else if (pid == 0) {
        printf("Hijo PID=%d termina con exit(42)\n", getpid());
        exit(42);
    } else {
        int status;
        wait(&status);
        if (WIFEXITED(status)) {
            printf("Padre: hijo terminó con código %d\n", WEXITSTATUS(status));
        }
    }

    return 0;
}
