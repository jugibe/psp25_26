/*
 * Forma 3 de crear hilos, utilizando funciones lambda
 * Le pasamos la trea directamente al argumento 
 * new Thread(()->Tarea)
 */

public class Main3 {
public static void main(String[] args) {
    //Crea el hilo y o lanza usando funciones lamda
    new Thread(()->tarea()).start();
    new Thread(()->tarea()).start();
    //hilo principal sigue corriendo
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

private static void tarea() {
     while(true){
        System.out.println(Thread.currentThread().getName());
        try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    
}
}
