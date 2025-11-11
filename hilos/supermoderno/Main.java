/*
 * psp 2025
 * programa que simula un supermercado con clientes y 3 cajeras.
 * usaremos la clase bloxkingqueue. que automatiza el uso de wait y notify
 * hilos producrores clientes y consumidores cajeras
 */

import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String [] args){
        //Creo la cloa en la que se colocaran los clientes
        LinkedBlockingQueue <Cliente> cola=new LinkedBlockingQueue<>();

        //Creo las cajeras y las pongo a correr
        for (int i=1; i<=3;i++){
            new Cajera("cajera "+ i, cola).start();
        }

        //Creo los clientes y los pongo a corre
        for(int i=1;i<=10;i++){
            new Cliente(cola, "cliente "+i).start();
        }

        

    }
    
}
