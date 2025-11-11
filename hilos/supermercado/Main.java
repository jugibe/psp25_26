/*
 * En un supermercado hay 3 cajeras disponibles para atender a los clientes. Cada cajera solo puede
 atender a un cliente a la vez. Los clientes llegan de forma concurrente (simulados con hilos) y
 eligen una cajera al azar para hacer su compra. Si la cajera está ocupada, el cliente debe esperar su
 turno hasta que quede libre. 
 */
public class Main {
    public static void main(String [] args){
        final int NUMEROCAJERAS=3;
        //Creo un array de Cajeras
        Cajera [] cajeras=new Cajera [NUMEROCAJERAS];
        //inicializao las cajeras
        for (int i = 0; i < 3; i++) {
            cajeras[i] = new Cajera("Cajera " + (i + 1));
        }

        //Creo los hilos y le paso las cajeras y ospongo a correr
        for(int i=1;i<=10;i++){
            new Cliente("Hilo"+i,cajeras).start();
        }


    }
}
