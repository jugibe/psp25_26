/*
 * Crea cinco hilos que impriman su nombre y un mensaje. Asigna nombres personalizados usando setName() y verifica que se impriman correctamente con getName(). 
 * Investiga otra forma de dar nombre a los hilos
 */

public class Ejercicio3 {
    public static void main(String[] args) throws Exception {
        //Creo los hilos, le pongo nombre y los pongo a correr
        for (int i=0;i<5;i++){
            new Thread(()->tarea(),"Hilo N"+i).start();
        }
    }

    private static void tarea() {
        System.out.println(Thread.currentThread().getName()+ " escribiendo");
        
    }
    //otras formas
    // en el constructor del hilo usando super
    // con el método setName()

}
