import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ejercicio4Pool {
  public static void main(String[] args) throws Exception {
        // Crear un pool de 3 hilos
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //Creo las tareas que voy a mandar a los hilos
        //mas tareas que hilo
        //Se espera a acabar una tarea y el hilko coge la tarea.
        for (int i = 1; i <= 5; i++) {
            int tareaId = i;
            pool.execute(() -> {
                System.out.println("Tarea " + tareaId + " ejecutada por " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simula trabajo durante 2 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Tarea " + tareaId + " finalizada por " + Thread.currentThread().getName());
            });
        }

        //Cierro el pool ya no admite tareas
        pool.close();
        //Espero que acaben las tareas un minuto
        //si no acaban  devuelve false y sigue el main, las tareas siguen en segundo plano

        pool.awaitTermination(1, TimeUnit.MINUTES);

    }
}
