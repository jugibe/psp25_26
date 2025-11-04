/*
 * psp 2025
 * Crea tres hilos que simulen corredores en una carrera. Asigna diferentes prioridades (Thread.MIN_PRIORITY, Thread.NORM_PRIORITY, Thread.MAX_PRIORITY) y observa cuál termina primero. Cada hilo debe imprimir su progreso en intervalos.
Crea tres hilos que simulen corredores en una carrera. Asigna diferentes prioridades (Thread.MIN_PRIORITY, Thread.NORM_PRIORITY, Thread.MAX_PRIORITY) y observa cuál termina primero. Cada hilo debe imprimir su progreso en intervalos.

 */
public class Ejercicio1 {
    private static final int  PASOS=100000;
public static void main(String[] args) throws Exception {
       //Creo los tres hilos
       Thread h1=new Thread(()->correr());
       Thread h2=new Thread(()->correr());
       Thread h3=new Thread(()->correr());

       //Cambio las prioridades
       h1.setPriority(Thread.MIN_PRIORITY);
       h2.setPriority(Thread.NORM_PRIORITY);
       h3.setPriority(Thread.MAX_PRIORITY);

       //los pongo a correr
       h1.start();
       h2.start();
       h3.start();
       //Espero los hilos
       h1.join();
       h2.join();
       h3.join();
       
    }

private static void correr() {
    long inicio=System.currentTimeMillis();
    for (int i = 1; i <= PASOS; i++) {
        for(int c=1;c<=PASOS;c++){
            //perdemos el tiempo
        }        
    }
        System.out.println(Thread.currentThread().getName()+ " ha terminado la carrera! "+ (System.currentTimeMillis()-inicio));
    
}
}
