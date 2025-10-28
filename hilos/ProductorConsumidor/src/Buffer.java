/*
 * psp 2025
 * Modelo productor consumidor
 * los hilos productores producen y lo colocan enun buffer
 * los hilos ocnsumidores consumen de un buffer
 */

import java.util.LinkedList;

public class Buffer {

    private final LinkedList<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    //Metodo sincronizado pruducir. añade un numero a la lista si el buffer no esta lleno
    public synchronized void producir(int valor) throws InterruptedException {
        while (queue.size() == CAPACITY) {
            wait(); // Espera si el búfer está lleno
        }
        queue.add(valor);
        System.out.println("Productor"+ Thread.currentThread().getName()+" produjo: " + valor);
        System.out.println("lista "+ queue);
        notify(); // Notifica a los hilos que estan esperando
    }
    //Metodo consumir. quita el primer valor de la lista si el buffer no esta vacio

    public synchronized int consumir() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Espera si el búfer está vacío
        }
        int valor = queue.removeFirst();
        System.out.println("Consumidor" + Thread.currentThread().getName()+" consumió: " + valor);
        System.out.println("lista "+ queue);
        notify(); // Notifica a todos ls hilos. 
        //Diferencia entre notify () y notifyAll()
        return valor;
    }

}
