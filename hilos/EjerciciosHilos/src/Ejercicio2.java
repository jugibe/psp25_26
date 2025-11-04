/*
 * psp 2025
 * Crea dos hilos que realicen tareas simples (como contar hasta 5 con pausas). 
 * Desde el hilo principal, imprime el estado (getState()) 
 * de cada hilo cada segundo hasta que ambos terminen.
 */

public class Ejercicio2 {
  public static void main(String[] args) throws Exception {
    //Creo los hilos
    Thread h1=new Thread(()->contar());
    Thread h2=new Thread(()->contar());
    
    // Mostrar estado inicial
    System.out.println("Estado inicial de Hilo 1: " + h1.getState());
    System.out.println("Estado inicial de Hilo 2: " + h2.getState());

    //Los pongo a corre
    h1.start();
    h2.start();

    while(h1.isAlive()||h2.isAlive()){
       System.out.println("Estado de los hilos");
       System.out.println("h1:"+h1.getState());
       System.out.println("h2:"+h2.getState());
       try {
        Thread.sleep(1000); // Espera 1 segundo
        } catch (InterruptedException e) {
          e.printStackTrace();
        }


    }
    System.out.println("Hilos finalizados");
    System.out.println("Estado final de Hilo 1: " + h1.getState());
    System.out.println("Estado final de Hilo 2: " + h2.getState());

}


    //tarea de los hilos, contar y esperar
  private static void contar() {
    for (int i=1; i<=5;i++){
        System.out.println(Thread.currentThread().getName()+ " cuenta "+ i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    
  }
}

/*
| Estado               | Descripción                                                                                                   | Cómo se alcanza                                                   |
| :------------------- | :------------------------------------------------------------------------------------------------------------ | :---------------------------------------------------------------- |
| 🆕 **NEW**           | El hilo ha sido creado pero **aún no ha comenzado**.                                                          | Cuando haces `new Thread(...)`, pero antes de llamar a `start()`. |
| 🟢 **RUNNABLE**      | El hilo está **listo para ejecutarse** o **en ejecución** (depende del planificador del sistema).             | Después de `start()`, o cuando sale de una espera.                |
| 🟡 **BLOCKED**       | El hilo **quiere entrar** a un bloque o método `synchronized`, pero **otro hilo tiene el candado (monitor)**. | Cuando otro hilo ya posee el bloqueo sobre el mismo objeto.       |
| 🔵 **WAITING**       | El hilo **espera indefinidamente** a que otro hilo lo despierte (con `notify()` o `notifyAll()`).             | Cuando llama a `wait()` o `join()` sin tiempo.                    |
| 🟣 **TIMED_WAITING** | El hilo **espera un tiempo determinado**.                                                                     | Cuando llama a `sleep(ms)`, `wait(ms)` o `join(ms)`.              |
| 🔴 **TERMINATED**    | El hilo ha **terminado su ejecución** (el método `run()` finalizó).                                           | Automáticamente al salir de `run()`.                              |
 

 */
