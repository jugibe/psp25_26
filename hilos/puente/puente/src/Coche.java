import java.util.Random;

public class Coche extends Thread {
    private final int peso;
    private final Puente puente;
    private final String nombre;

    public Coche(String nombre,int peso, Puente puente){
        super(nombre);
        this.nombre=nombre;
        this.puente=puente;
        this.peso=peso;
    }

    public int getPeso() {
        return peso;
    }

    public String getNombre() {
        return nombre;
    }

    //metodo run de los coches
    @Override
    public void run() {
        try {
            puente.intentarPasar(this);
            // Simula el tiempo en el puente
            Thread.sleep(1000 + new Random().nextInt(2000));
            puente.salir(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

    



