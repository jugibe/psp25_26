/*
 * psp 2025
 * Crea tres hilos que simulen temporizadores independientes. 
 * Cada uno debe contar segundos desde 1 hasta 10, con pausas de 1 segundo entre cada número. 
 * El hilo principal debe esperar a que todos terminen usando join(). 
 */
public class Ejercicio4 {
    public static void main(String[] args) throws Exception {
        //Creo los hilos y los poongo a correr

        Thread h1=new Thread(()->tarea(),"Temporizador1");
        Thread h2=new Thread(()->tarea(),"Temporizador2");
        Thread h3=new Thread(()->tarea(),"Temporizador3");
        h1.start();h2.start();h3.start();

        //Espero los hilos
        h1.join(); h2.join(); h3.join();

        System.out.println("hilos finalizados");
        


    }

    private static void tarea() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(1000); // Pausa de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
