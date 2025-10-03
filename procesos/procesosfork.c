/*Ejecuta el programa y comprueba la salida, como se altena la ejecución de padre e hijo 
 incluso
 se puede alternar a mitad de escribir una línea
 Busca utilizando ps o desde el monitor del sistema los proceosos y comprueba sus pid
 finaliza alguno de ellos y analiza que ocurre.
 cambia el parámetro de usleep y analiza que ocurre.

 */

#include<stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
int main(){

    pid_t pid, hijo_pid;//vamos a almacenar los pid de los procesos
    int dato=100; //Damos un valor para comprobar que al duplicarse tienen el mismo valor y luego son independientes
    /*
    Duplicamos los procesos. padre e hijo. Tienen espacios de memoria diferentes. 
    Comparten los valores hasta que se ejecuta el fork.
    La función fork() devuelve un valor distinto en el proceso padre y en el hijo
    */
    pid=fork();//Ya hay dos procesos corriendo
    
    /*
    Para el hijo pid es 0 
    para el padre es >0, vale el pid del hijo.
    
    */
    if (pid < 0) {
        perror("Error en fork");
        exit(1);
    } else if (pid == 0) {
        // Código del hijo
        while (1) {
            printf("Hijo: mi PID = %d, valor de dato = %d\n", getpid(), dato);
            dato++; // demostramos independencia de memoria
            usleep(1);
        }
    } else {
        // Código del padre
        while (1) {
            printf("Padre: mi PID = %d, PID de hijo = %d, valor de dato = %d\n", getpid(), pid, dato);
            dato--; // demostramos independencia de memoria
            usleep(1);
        }
    }
    return 0;


}