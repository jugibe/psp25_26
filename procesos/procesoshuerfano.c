/*
Ejemplo de creación de un proceso huerfano. 
Ejecuta el código desde consola
gcc programa.c -o nombreejecutable para compilar
./mombreejecutable para ejecutarlo
con ps lf en otra terminal vemos los procesos.
Puedes matar al padre con kill -9 pid y el hijo quedará huerfano, tendra un nuevo pid de padre.
Si con los dos procesos corriendo matas al hijo, el hijo seguirá apareciendo en ps en estado Zombie Z+ en le estado
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
        printf("Hijo: mi PID = %d, valor de dato = %d\n", getpid(), dato);
            

    } else {
        // Código del padre
        dato --;
        printf("Padre: mi PID = %d, PID de hijo = %d, valor de dato = %d\n", getpid(), pid, dato);
                   
        
    }
    //ESTE CÓDIGO LO EJECUTARÁN TANTO EL PADRE COMO EL HIJO
    while(1){
        usleep(10);
        printf("Soy el proceso pid=%d el id de mi padre %d y el valod de dato=%d \n", getpid(),getppid(),dato);
    }


    return 0;


}