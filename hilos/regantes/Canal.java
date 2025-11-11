/* 
 * Clase Canal. Es el hilo productor del deposito
 */
public class Canal extends Thread{

    private String nombre; 
    private Deposito d;

    public Canal(String nombre, Deposito d) {
        this.nombre=nombre;
        this.d=d;
    }

    //metodo que ejecutaran los hilos consuidores

    @Override
    public void run(){
        while(true){
            d.llenar((int)(Math.random()+10 +1), nombre);
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }


}
