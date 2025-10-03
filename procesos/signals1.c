#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>   // fork(), _exit(), sleep()
#include <sys/types.h> // pid_t


/*
signal una señal es una notificación que el kernel manda al proceso
para indicarle que ha ocurrido un evento

signal -l listado de las señales
Se pueden mandar desde linea de comandos
kill -n pid
no matan el proceso necesarioamente, pueden parar y reanudar o señales
personalizadas para hacer una determinada tarea.
*/


//función que se ejecutara al mandar una señal sigusr al proceso
void manejador1(int signal){
    printf("recibida señal SIGUSR1 %d\n",signal);
}
void manejador2(int signal){
    printf("recibida señal SIGUSR2 %d\n",signal);
}
//Se puede mandar un maejador y distinguir la señal por int signal
int main(){

    pid_t pid=fork();//creamos el hijo

    if (pid < 0) {
    perror("fork falló");
    _exit(-1);
} else if (pid == 0) {
    signal(SIGUSR1, manejador1); 
    signal(SIGUSR2, manejador2);
    //El hijo cuando reciba una señal del tipo sigusr1 ejecutara 
    //la funcion manejador
    while(1){
        pause();
    };//Siempre escuchando
} else {
    sleep(1);
    printf("programa padre\n");
    kill(pid,SIGUSR1);//mandamos uns señal SIGUSR1 al proceso con identificadr pid
    sleep(1);
    kill(pid,SIGUSR1);//mandamos uns señal SIGUSR1 al proceso con identificadr pid
    sleep(1);
    kill(pid,SIGUSR2);//pausar el proceso hijo
    sleep(1);
    kill(pid,SIGTERM);//acabado el hijo
    printf("Fin de mandar señales\n");


}
return(0);




}