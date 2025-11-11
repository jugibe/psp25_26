import java.util.Random;

public class Cliente extends Thread{


    private  final String nombre;
    private  final Cajera[] cajeras;
    private final Random random = new Random();


    public Cliente(String nombre, Cajera [] cajeras){
        this.nombre=nombre;
        this.cajeras=cajeras;
    }
    @Override
    public void run(){
        //elige una cajera al azar
        int i=random.nextInt(cajeras.length);
        System.out.println(nombre + " llega y elige "+ cajeras[i].getNombre());
        cajeras[i].atenderCliente(nombre);
        System.out.println(nombre + " termino compra");
        


    }

}
