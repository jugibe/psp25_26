/*
 * PSP 2025
 * Clase que extiende Thred.
 * Cada vez que creemos un objeto de esta clase se creaá un hilo
 * cuando lo pongamos a correr ejecutara el metodo run
 */

public class Hilo1 extends Thread{

    @Override
    public void run(){
        while(true){
            System.out.println("hilo "+ getName()+ " corriendo.");
            try {
                sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

}
