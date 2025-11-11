/*
 * psp 2025
 * clase deposito, objeto compartido 
 * metodos para llenar y vaciar el deposito
 */
public class Deposito {
    private int nivel=0;//se inicia con niveo cero
    private final int NIVELMAX; //máxia capacidad del deposito
    public Deposito(int NIVELMAX){
        this.NIVELMAX=NIVELMAX;
    }//Consturctor del deposito

    //metodo que utilizaran los productores para llenar el canal
    public synchronized void llenar(int n, String canal){

        while((nivel +n) >NIVELMAX ){
            try {
                //Esperamos mientras el deposito esté lleno
                System.out.println(canal + " esperando");
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        nivel=nivel+n;
        //llenamos y notificamos que hemos llenado canal
        System.out.println(canal + " ha finalizado. nivel "+ nivel);
        notifyAll(); 

    }

    //metodo para vaciar el deposito que uaran los consumidores
    public synchronized void vaciar (int n, String regante){
        while(nivel<n){
            System.out.println(regante + "intentando extraer");
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        //Bajamos el nivel y lo comunicamos a los otros hilos
        nivel-=n;
        System.out.println(regante + " finalizado. Nivel " +nivel);
        notifyAll();


    }


}
