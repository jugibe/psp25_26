#include<stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
int main(){
    fflush(stdout); 
    fork();
    fflush(stdout); 
    fork();
    printf("Mi pid=%d y el pid de mi padre%d \n",getpid(),getppid());
    /*
    
    ¿Cuántas veces escribiraá?
    
    */
    sleep(1);
    //printf esta bufferizado, se escribe en un buffer, los hijos heredan el buffer, por lo que a veces la ejecución
    //no es correcta
    return 0;
}