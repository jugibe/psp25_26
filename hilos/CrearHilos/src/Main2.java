
/*
 * PSP 2025
 * Crea varios objetos de la clase Thread a los que 
 * les pasa como tarea nuevos objetos de la clase Hilo2
 * que implementa Runnable
 *
 */


public class Main2 {
public static void main(String[] args) {

    Thread h1=new Thread(new Hilo2());
    Thread h2=new Thread(new Hilo2());
    h1.start();
    h2.start();
    //El hilo principal sigue corriendo a la vez

    while(true){
        System.out.println("hilo principal");
        try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    
}
}
