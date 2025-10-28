/*
 * psp 2025
 * Modelo productor consumidor
 * los hilos productores producen y lo colocan enun buffer
 * los hilos ocnsumidores consumen de un buffer
 */


 //programa primcipal, crea un buffer un productor y un consumidor y les pasa el buffer
public class App {

    public static void main(String[] args)  {
        Buffer buffer=new Buffer();
        // 2 productores y 1 consumidor
        new Productor(buffer).start();
        new Productor(buffer).start();
        
        new Consumidor(buffer).start();
        
    }
}
