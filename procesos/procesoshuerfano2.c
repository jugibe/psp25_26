/*
Ejemplo de creación de un proceso huerfano. 
Ejecuta el código desde consola
gcc programa.c -o nombreejecutable para compilar
./mombreejecutable para ejecutarlo
ps lf para ver los procesos en la terminal

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
    Para el padre pid es 0 
    para el hijo es >0, vale el pid del padre.
    
    */
    if (pid < 0) {
        perror("Error en fork");
        exit(1);
    } else if (pid == 0) {
        dato++;
        while(1){
            sleep(1);
            printf("Soy el proceso pid=%d el id de mi padre %d y el valod de dato=%d \n", getpid(),getppid(),dato);
        }      

    } else {
        // Código del padre
        //Solo se ejcuta una vez, el padre muere y deja al hijo huerfano.
        //su padre pasa a ser otro, prueba a matar al nuevo padre.
        dato --;
        printf("Padre: mi PID = %d, PID de hijo = %d, valor de dato = %d\n", getpid(), pid, dato);
                   
        
    }


    return 0;


}