/*
 * Crea un programa que simule tres tareas independientes
 *  (por ejemplo, cálculos o pausas de 2 segundos cada una). Primero ejecútalas de forma secuencial, luego en paralelo usando hilos. 
 * Mide el tiempo total en ambos casos y compara los resultados. 
 */
public class Ejercicio6 {
    public static void main(String [] args){
        //Ejecuto las tareas secuencialmente
        long inicio=System.nanoTime();
        for(int i=0;i<5;i++){
            tarea();

        }
        long tiempoFinal=System.nanoTime();
        long tSecuencial=(tiempoFinal-inicio);
        System.out.println("La tare secuencial tomo " + tSecuencial+ " ns");



        //Ejecuto las tareas mediante hilos
        Thread[] hilos=new Thread [5];
        inicio=System.nanoTime();
        for(int i=0; i<5; i++){
            hilos[i] = new Thread(() -> tarea());
            hilos[i].start();
        }
        //Esperamos a que acaben todos los hilos
        for(Thread h:hilos){
            try {
            h.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}
        
        tiempoFinal=System.nanoTime();
        long tParalela=(tiempoFinal-inicio);
        System.out.println("La tare en hilos tomo " + tParalela + " ns");
        System.out.println("tSecuencial/Paralela= "+ (double)tSecuencial/tParalela);

    }

        //Tarea de los hilos, prueba otras tareaas mas cortas o con sleep
        //Si estas en maquina virtual prueba el efecto de cambiar numero de procesadores

    private static void tarea() {

        for(int i=0;i<10000;i++){
            for(int f=1;f<=10000;f++){
                //pierdo el tiempo
            }
        }
    }

}
