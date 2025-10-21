#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <ctype.h>
#include <sys/wait.h>

void convertirMayusculas(char *texto) {
    for (int i = 0; texto[i]; i++) {
        texto[i] = toupper((unsigned char) texto[i]);
    }
}

int main() {
    int pipePadreHijo[2];
    int pipeHijoPadre[2];

    // Crear los dos pipes
    if (pipe(pipePadreHijo) == -1 || pipe(pipeHijoPadre) == -1) {
        perror("Error al crear las tuberías");
        return 1;
    }

    pid_t pid = fork();

    if (pid < 0) {
        perror("Error en fork");
        return 1;
    }

    if (pid == 0) {
        // --- PROCESO HIJO ---
        close(pipePadreHijo[1]); // Cierra escritura del pipe padre→hijo
        close(pipeHijoPadre[0]); // Cierra lectura del pipe hijo→padre

        char mensaje[100];
        read(pipePadreHijo[0], mensaje, sizeof(mensaje));
        printf("[HIJO] Mensaje recibido: %s\n", mensaje);

        convertirMayusculas(mensaje);
        printf("[HIJO] Enviando mensaje transformado...\n");

        write(pipeHijoPadre[1], mensaje, strlen(mensaje) + 1);

        // Cerrar descriptores usados
        close(pipePadreHijo[0]);
        close(pipeHijoPadre[1]);
    } 
    else {
        // --- PROCESO PADRE ---
        close(pipePadreHijo[0]); // Cierra lectura del pipe padre→hijo
        close(pipeHijoPadre[1]); // Cierra escritura del pipe hijo→padre

        char mensaje[100];
        printf("Introduce un mensaje: ");
        fgets(mensaje, sizeof(mensaje), stdin);
        mensaje[strcspn(mensaje, "\n")] = '\0'; // Quitar salto de línea

        printf("[PADRE] Enviando mensaje al hijo...\n");
        write(pipePadreHijo[1], mensaje, strlen(mensaje) + 1);

        char respuesta[100];
        read(pipeHijoPadre[0], respuesta, sizeof(respuesta));
        printf("[PADRE] Respuesta del hijo: %s\n", respuesta);

        // Cerrar descriptores usados
        close(pipePadreHijo[1]);
        close(pipeHijoPadre[0]);

        // Esperar a que el hijo termine
        wait(NULL);
    }

    return 0;
}
