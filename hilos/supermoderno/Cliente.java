
import java.util.concurrent.LinkedBlockingQueue;

public class Cliente extends Thread{

    private final String nombre;
    private final LinkedBlockingQueue<Cliente> cola;
    public Cliente(LinkedBlockingQueue<Cliente> cola, String nombre) {
        this.cola = cola;
        this.nombre=nombre;
    }

    @Override
    public void run(){
        System.out.println(nombre + " llega al supermercado y se pone en la fila.");
        try {
            //Coloca el cliente en la cola
            //En este ejemplo la cola no tiene tamaño limitado
            //Se pueden generar colas de un tamaño fijo
            //Si es asi y la cola esta llena se espera a que haya hueco
            cola.put(this); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    public String getNombre() {
        return nombre;
       
    }

}
