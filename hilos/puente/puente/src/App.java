/*
 * psp 2025
 * Simulación de un puente.
 * no pueden pasar mas de 10 coches a la vez
 * El peso total no debe exceder 2500 kg
 * Los coches deben ordenarse en una cola, el primero que entra debe ser el que 
 * primero que pasa el puente FIFO
 */


 /*
  * Metodo principal
  */

import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
       //Creo el objeto puente 
       //Creo los coches y los pongo a correr
       final int [] PESOS={100,200,300};//pesos de los coches
       final int NUMEROCOCHES=100;//numero de coches para probar. Podría ser un while(true)
       Puente puente=new Puente();
       Random rand=new Random();//aleatorio para elegir el tipo de coche
       for(int i=0;i< NUMEROCOCHES;i++){
        int peso=PESOS[rand.nextInt(PESOS.length)];
        new Coche("coche "+ i,peso,puente).start();
        Thread.sleep(150);

       }


    }
}
