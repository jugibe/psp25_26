/*
 * Clase Regante. Es el hilo consunidor del deposito
 */
public class Regante extends Thread{

    private String nombre; 
    private Deposito d;

    public Regante(String nombre, Deposito d) {
        this.nombre=nombre;
        this.d=d;
    }

    //metodo que ejecutaran los hilos consuidores

    @Override
    public void run(){
        while(true){
            d.vaciar((int)(Math.random()+10 +1), nombre);
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }


}
