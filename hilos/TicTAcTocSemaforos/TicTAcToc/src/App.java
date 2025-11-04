/*
 * psp 2025
 * uso de semaforos
 * Crear tres hilos, uno escribe tic, otro tac y otro toc de manera que lo escriban sincronizados
 * tic tac toc tic tac toc .....
 * usando semaforos
 * Un semáforo es un mecanismo de sincronización que controla cuántos hilos
 *  (threads) pueden acceder a un recurso compartido al mismo tiempo.
 * 
 */

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {
        final int REPETICIONES=5;
        // Creamos los semáforos
        Semaphore semTic = new Semaphore(1); // empieza con un permiso
        Semaphore semTac = new Semaphore(0); // bloqueado
        Semaphore semToc = new Semaphore(0); // bloqueado

        //Creamos los hilos
        Thread tic=new Thread(()->{
            for(int i=1;i<=REPETICIONES;i++){
                try {
                    semTic.acquire();//Espera su turno, quita uno al valor de semTic y se es 0 espera hasta que tenga 1
                    System.out.print("tic ");
                    semTac.release();//libera al siguiente, aumeta en uno semTAc
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }               
            }
        }
        );

        Thread tac=new Thread(()->{
            for(int i=1;i<=REPETICIONES;i++){
                try {
                    semTac.acquire();//Espera su turno, quita uno al valor de semTic y se es 0 espera hasta que tenga 1
                    System.out.print("tac ");
                    semToc.release();//libera al siguiente, aumeta en uno semTAc
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }               
            }
        }
        );

        Thread toc=new Thread(()->{
            for(int i=1;i<=REPETICIONES;i++){
                try {
                    semToc.acquire();//Espera su turno, quita uno al valor de semTic y se es 0 espera hasta que tenga 1
                    System.out.print("toc ");
                    semTic.release();//libera al siguiente, aumeta en uno semTAc
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }               
            }
        }
        );

        //iniciar hilos
        tic.start();tac.start();toc.start();
        //Espero su finalización
        tic.join();tac.join();toc.join();
        System.out.println("fin de programa");


    }
}
