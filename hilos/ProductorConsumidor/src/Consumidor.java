/*
 * psp 2025
 * Modelo productor consumidor
 * los hilos productores producen y lo colocan enun buffer
 * los hilos ocnsumidores consumen de un buffer
 */

public class Consumidor extends Thread {
    private final Buffer buffer;

    //El consumidor es un hilo que comparte un buffer con los productores
    
    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                buffer.consumir();
                Thread.sleep(100); // Simula tiempo de consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
