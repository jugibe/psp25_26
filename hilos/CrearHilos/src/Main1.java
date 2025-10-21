/*
 * PSP 2025
 * Clase principal Ejemplo 1
 * Creamos dos objetos de la clase Hilo1 que extiende Thread y
 * los ponemos a correr
 * Ejecuta el programa varias veces y observa el orden de los mensajes
 * 
 */
public class Main1 {
   
   public static void main(String[] args) {
      Hilo1 var1 = new Hilo1();
      Hilo1 var2 = new Hilo1();
      var1.start();
      var2.start();

      while(true) {
         System.out.println("hilo principal");

         try {
            Thread.currentThread();
            Thread.sleep(10);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}
