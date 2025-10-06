/*
Ejemplo de uso de señales
Señal alarm(x)
manda una señal dentro de x seg, cuando puede, no vale como reloj
si hay manejador lo ejecuta, si no termina el proceso el proceso.
Ejercicio prueba 1. Probar a mandar la señal desde la linea de comandos
 y que el proceso cuente las veces que se le envia.
 Ejerccicio 2.- Al pulsar ctrl+c el proceso se detiene, capturar esa señal
  y en vez de parar poner el contador a cero.

*/

#include <stdio.h>
#include <signal.h>
#include <unistd.h>

volatile int segundos = 0;

// Manejador de la señal SIGALRM (cada segundo)
void manejar_alarma(int sig) {
    segundos++;
    printf("\rTiempo: %d segundos", segundos);
    fflush(stdout);
    alarm(1); // reprograma la alarma para dentro de 1 segundo
}



int main() {
    // Asociar señales con sus manejadores
    signal(SIGALRM, manejar_alarma);
    

    printf("Cronometro iniciado.");

    alarm(1); // lanzar la primera alarma en 1 segundo

    // El programa se mantiene en espera
    while (1) {
        pause(); // espera hasta recibir una señal
    }

    return 0;
}
