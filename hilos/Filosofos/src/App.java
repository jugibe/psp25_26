/*
 * psp 2025
 * problema de los filosofos
 * metodo principal
 */
public class App {
    public static void main(String[] args) throws Exception {
        //nombro a los filosofos
        String [] nombres= {"Aristotoels","Platon","Pitagoras","Kant", "Diogenes"};

        //Creo los tenedores uno por filosofo
        Tenedor[] tenedores = new Tenedor[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            tenedores[i] = new Tenedor();
        }

        // Crear  filósofos, cada uno con su tenedor izquierdo y derecho
        Filosofo[] filosofos = new Filosofo[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            Tenedor izq = tenedores[i];
            Tenedor der = tenedores[(i + 1) % nombres.length]; // el último comparte con el primero
            filosofos[i] = new Filosofo(nombres[i], izq, der);//Creo el filosofo le paso el nombre y los tenedores que le tocan
            filosofos[i].start(); //lo pongo a correr
        }
        
    }
}
