public class Main {
    /*psp 2025
     * programa principal. crea una cuenta bancaria y tres hilos que retiran y depositan una cantidad
     * NO GARANTIZA UNA COHERENCIA ENTRE HISTORIAL E INGRESOS.
     * SE PUEDE COLAR UN HISTORIAL A OTRO ANTERIOR
     * 
     */
    public static void main(String[] args) {
        
        
        CuentaBancaria cuenta = new CuentaBancaria();

        Thread t1 = new Thread(() -> {
            cuenta.depositar(300);
            cuenta.retirar(150);
        }, "Hilo-1");

        Thread t2 = new Thread(() -> {
            cuenta.retirar(200);
            cuenta.depositar(400);
        }, "Hilo-2");

        Thread t3 = new Thread(() -> {
            cuenta.depositar(100);
            cuenta.retirar(50);
        }, "Hilo-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cuenta.imprimirHistorial();
    }
}