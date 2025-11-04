/*
 * psp 2025
 * Crea un hilo que simule una tarea larga (por ejemplo, 
 * contar hasta 100 con pausas). Desde el hilo principal, interrúmpelo después de 3 segundos usando interrupt()
 *  y maneja la interrupción correctamente. 
 */

public class Ejercicio5 {

    public static void main (String[] args){
        
        //defino el
        Thread hilo = new Thread(() -> {
            for (int i = 1; i <= 20000; i++) {
                // Comprobar interrupción manual en el for
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupción detectada en el for. Saliendo...");
                    //El hilo tiene un flag interrumpido en true
                    break;
                }

                System.out.println("Contando: " + i);

                // Simula trabajo bloqueante (sleep)
                try {
                    Thread.sleep(1); // sleep puede lanzar InterruptedException
                } catch (InterruptedException e) {
                    //Si la interrupción se produce en el sleep, va al catch
                    //El flag se regenera a false
                    System.out.println("Interrupción detectada durante sleep. Terminando hilo...");
                    break; // salir del bucle
                }
            }
            System.out.println("hilo finalizado");
            
        });

        hilo.start();
        Thread.currentThread();

        try {
            Thread.sleep(1000);
            hilo.interrupt(); // interrumpe el sleep o la ejecución
            hilo.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }

}
/*
 * Cuando llamas hilo.interrupt():

No detiene el hilo de forma inmediata.

No elimina el hilo del planificador.

Solo marca un flag de interrupción (interrupted = true).

Si el hilo está en:

sleep(), wait(), join(), etc. → se lanza InterruptedException.

Código normal → el hilo sigue ejecutando hasta que tu código revise isInterrupted() y decida salir.

En ambos casos, el hilo sigue existiendo y puede continuar ejecutando instrucciones.
 */
