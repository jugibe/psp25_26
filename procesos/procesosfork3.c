#include<stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
int main(){
   //El proceso es el programa

    
    fork();
    write(1,"Hola\n",5);//EScribe en la stdout 1 5 bytes
    fork();
    write(1,"Holappp\n",8);//EScribe en la stdout 1 5 bytes
    /*
    
    ¿Cuántas veces escribiraá?
    
    */
    sleep(1);
    //printf esta bufferizado, se escribe en un buffer, los hijos heredan el buffer, por lo que a veces la ejecución
    //no es correcta
    return 0;
}