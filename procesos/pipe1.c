/*
Ejemplo de uso de pipes para que un proceso se comunicque con otro
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int fd[2];  // fd[0] = lectura, fd[1] = escritura
    char mensaje[] = "Hola desde el hijo!";
    char buffer[100];

    // Creamos el pipe
    // fd[0] será usado para leer, fd[1] para escribir
    if (pipe(fd) == -1) {
        perror("pipe");
        exit(1);
    }

    pid_t pid = fork();  // Creamos un hijo

    if (pid < 0) {
        perror("fork");
        exit(1);
    } 
    else if (pid == 0) {
        // ----------------------------
        // Código del hijo
        // ----------------------------
        close(fd[0]); // Cerramos el extremo de lectura del hijo
        write(fd[1], mensaje, sizeof(mensaje)); // Escribimos en el pipe
        close(fd[1]); // Cerramos el extremo de escritura
        _exit(0);     // Terminamos el hijo
    } 
    else {
        // ----------------------------
        // Código del padre
        // ----------------------------
        close(fd[1]); // Cerramos el extremo de escritura del padre
        read(fd[0], buffer, sizeof(buffer));  // Leemos lo que escribió el hijo
        close(fd[0]); // Cerramos la lectura
        wait(NULL);   // Esperamos que el hijo termine

        printf("Padre recibió: %s\n", buffer); // Mostramos el mensaje
    }

    return 0;
}
