import java.util.LinkedList;
import java.util.Queue;

public class Puente {

    // atributos del puente
    private int cochesIn = 0;
    private int pesoIn = 0;
    private final int COCHESMAX = 10;
    private final int PESOMAX = 2000;
    // Cola para mantener el orden de llegada, no es sincronizada,
    // gestiono la sincronización a mano
    private final Queue<Coche> cola = new LinkedList<>();

    // el coche llega al puente e intenta pasar
    public synchronized void intentarPasar(Coche coche) {
        // El coche se coloca a la cola
        cola.add(coche); // añade ell coche a la cola
        System.out.println(cola);
        // si no puede entrar, espera
        // coche.peek() toma el primer elemento de la cola sin quitarlo
        while (cola.peek() != coche || cochesIn >= COCHESMAX || pesoIn + coche.getPeso() > PESOMAX) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
            // es su turno y puede pasar
            cola.poll();
            cochesIn++;
            pesoIn += coche.getPeso();
            System.out.println("Coche " + coche.getNombre() + " entra (peso=" + coche.getPeso() +
                    ", total=" + pesoIn + " kg, coches=" + cochesIn + ")");

            //no hago un notifyAll por que debe notifiar al salir, no al entrar

    }

    public synchronized void salir(Coche coche) {
        cochesIn--;
        pesoIn -= coche.getPeso();
        System.out.println("Coche " + coche.getNombre() + " sale (peso=" + coche.getPeso() +
                           ", total=" + pesoIn + " kg, coches=" + cochesIn + ")");
        notifyAll(); // despierta a los que esperan
    }

}
