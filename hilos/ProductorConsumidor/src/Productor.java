/*
 * psp 2025
 * Modelo productor consumidor
 * los hilos productores producen y lo colocan enun buffer
 * los hilos ocnsumidores consumen de un buffer
 */

public class Productor extends Thread{
    private final Buffer buffer;


    //El productor es un hilo, comparte un buffer con los consumidores

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        int valor = 0;
        try {
            while (true) {
                buffer.producir(valor++);
                Thread.sleep(100); // Simula tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
