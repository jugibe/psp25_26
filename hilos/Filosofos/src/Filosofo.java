
/*
 * psp 2025
 * problema de los filososos que da lugar a bloqueos o DeadLock
 * clase filososofo
 */

public class Filosofo extends Thread {
    //Un filosofo tiene dos tenedores
    private Tenedor tenedorIzquierdo;
    private Tenedor tenedorDerecho;
    

    //Al constructor  se le pasasn dos tenedores
    public Filosofo(String nombre, Tenedor izquierdo, Tenedor derecho) {
        super(nombre);
        this.tenedorIzquierdo = izquierdo;
        this.tenedorDerecho = derecho;
    }

    
    //El hilo filosofo hace dos cosas, pensar y comer
    @Override
    public void run() {
        while (true) {
            pensar();
            comer();
        }
    }

    //cuando el filósofo piensa deja el hilo bloqueado con un sleep
    private void pensar() {
        System.out.println(getName() + " está pensando...");
        try {
            Thread.sleep((int)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Para comer toma el tenedor izquierdo, y luego el izquierdo
    private void comer() {
        synchronized (tenedorIzquierdo) {//Bloquea el tenedor izquierdo
            System.out.println(getName() + " tomó su tenedor izquierdo");
            try {
                Thread.sleep(100); // Simula tiempo antes de intentar tomar el otro
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (tenedorDerecho) {//Bloquea el tenedor derecho
                System.out.println(getName() + " tomó su tenedor derecho y está comiendo 🍝");
                try {
                    Thread.sleep((int)(Math.random() * 100));//come
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(getName() + " soltó su tenedor derecho");
        }

        System.out.println(getName() + " soltó su tenedor izquierdo");
    }
}