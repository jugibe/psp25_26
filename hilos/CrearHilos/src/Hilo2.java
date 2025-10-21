/*
 * PSP 2025
 * Forma 2 de crear hilos
 * Clase que implement la interface runable. implementaremos elmetodo run
 * En la calse principal crearemos un objero de la clase Thread al que le pasaremos como 
 * parametro un objeto de una clase que implemente runable.
 */

public class Hilo2 implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("hilo "+ Thread.currentThread().getName()+ " corriendo.");
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
