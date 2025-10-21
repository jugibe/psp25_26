
/*
 * psp 2025
 * Ejempo tipico en el que los hilos comparten un objeto
 * Este objeto tiene un metodo que incrementa en uno el valor de un contador.
 * podria simular una cuente corriente donde los hilos hacen ingresos
 * Al finalizar los hilos el valor del contador no es la suma de todos los incrementos
 * contador ++ no es una operacion unitaria y puede que los hilos interrumpan eu trabajo en medio de 
 * esa instrucción.
 * 
 */
import java.util.ArrayList;

public class ContadorCompartido {
public static void main(String[] args) {
    
    Contador c=new Contador(0);
    final int NUMEROHILOS=10;
    ArrayList <Thread> listaHilos=new ArrayList<>();
    //Genero los 10 hilos con su tarea y los pongo a correr

    for(int i=0;i<NUMEROHILOS;i++){
        Thread h=new Thread(()->tarea(c));//Creo el hilo y le paso el contador
        listaHilos.add(h);
        h.start();

    }
    //El hio principal espera a que finalicen los hilos
    for(Thread t:listaHilos){
        try {
            t.join();//Esperamos a que cada hilo acabe
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
   

    //Imprimo el valor de contador. deberia ser NUMEROHILOS * CANTIDAD
    System.out.println(c);

}


//La tarea es incrementar el contador 1000 veces
private static void tarea(Contador x) {
    final int CANTIDAD=1000;
    for (int i=0;i<CANTIDAD;i++){
        x.incrementar();
    }
    
}

}
