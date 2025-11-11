import java.util.concurrent.LinkedBlockingQueue;

public class Cajera extends Thread{
    private final LinkedBlockingQueue<Cliente> cola;
    private final String nombre;

    public Cajera(String nombre, LinkedBlockingQueue<Cliente> cola) {
        this.nombre=nombre;
        this.cola=cola;
    }

    @Override
    public void run(){
        
        
        while(true){
            Cliente c;
            try {
            //Toma el cliente de la cola. Si esta vacia espera
             c=cola.take();
            System.out.println(nombre + " atiende a "+ c.getNombre());
            //Esperamos
            Thread.sleep((int)(Math.random()*300+300));
            System.out.println(c.getNombre() + " saliendo.");

        } catch (Exception e) {
            // TODO: handle exception
        }
        }
       

    }

}
 