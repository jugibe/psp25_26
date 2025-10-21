#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

// Función para limpiar saltos de línea
void limpiarSalto(char *texto) {
    texto[strcspn(texto, "\n")] = '\0';
}

int main() {
    int pipePadreHijo[2];
    int pipeHijoPadre[2];
    int pipeHijoNieto[2];
    int pipeNietoHijo[2];

    pipe(pipePadreHijo);  // Padre → Hijo
    pipe(pipeHijoPadre);  // Hijo → Padre
    pipe(pipeHijoNieto);  // Hijo → Nieto
    pipe(pipeNietoHijo);  // Nieto → Hijo

    pid_t pidHijo = fork();

    if (pidHijo == 0) {
        // ======== PROCESO HIJO ========
        pid_t pidNieto = fork();

        if (pidNieto == 0) {
            // ======== PROCESO NIETO ========

            // Cerrar extremos no usados
            close(pipePadreHijo[0]);
            close(pipePadreHijo[1]);
            close(pipeHijoPadre[0]);
            close(pipeHijoPadre[1]);
            close(pipeHijoNieto[1]); // Solo lee del hijo
            close(pipeNietoHijo[0]); // Solo escribe al hijo

            char mensaje[100];
            read(pipeHijoNieto[0], mensaje, sizeof(mensaje));
            printf("[NIETO] Recibido del hijo: %s\n", mensaje);

            strcat(mensaje, " [procesado por el nieto]");
            write(pipeNietoHijo[1], mensaje, strlen(mensaje) + 1);

            close(pipeHijoNieto[0]);
            close(pipeNietoHijo[1]);
            return 0;
        } 
        else {
            // ======== PROCESO HIJO ========

            // Cerrar extremos no usados
            close(pipePadreHijo[1]); // Lee del padre
            close(pipeHijoPadre[0]); // Escribe al padre
            close(pipeHijoNieto[0]); // Escribe al nieto
            close(pipeNietoHijo[1]); // Lee del nieto

            char mensaje[100];
            read(pipePadreHijo[0], mensaje, sizeof(mensaje));
            printf("[HIJO] Recibido del padre: %s\n", mensaje);

            strcat(mensaje, " [reenviado por el hijo]");
            write(pipeHijoNieto[1], mensaje, strlen(mensaje) + 1);

            // Esperar respuesta del nieto
            char respuesta[200];
            read(pipeNietoHijo[0], respuesta, sizeof(respuesta));
            printf("[HIJO] Recibido del nieto: %s\n", respuesta);

            strcat(respuesta, " [devuelto por el hijo]");
            write(pipeHijoPadre[1], respuesta, strlen(respuesta) + 1);

            close(pipePadreHijo[0]);
            close(pipeHijoPadre[1]);
            close(pipeHijoNieto[1]);
            close(pipeNietoHijo[0]);

            wait(NULL); // Esperar al nieto
            return 0;
        }
    } 
    else {
        // ======== PROCESO PADRE ========

        // Cerrar extremos no usados
        close(pipePadreHijo[0]); // Escribe al hijo
        close(pipeHijoPadre[1]); // Lee del hijo
        close(pipeHijoNieto[0]);
        close(pipeHijoNieto[1]);
        close(pipeNietoHijo[0]);
        close(pipeNietoHijo[1]);

        char mensaje[100];
        printf("Introduce un mensaje para enviar al hijo: ");
        fgets(mensaje, sizeof(mensaje), stdin);
        limpiarSalto(mensaje);

        write(pipePadreHijo[1], mensaje, strlen(mensaje) + 1);

        char respuesta[200];
        read(pipeHijoPadre[0], respuesta, sizeof(respuesta));
        printf("[PADRE] Recibido del hijo: %s\n", respuesta);

        close(pipePadreHijo[1]);
        close(pipeHijoPadre[0]);

        wait(NULL); // Esperar al hijo (y al nieto indirectamente)
    }

    return 0;
}
