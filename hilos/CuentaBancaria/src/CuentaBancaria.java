/*
 * psp 2025 Ejemplo de sincronización utilizando dos objetos de bloqueo
 *
 * 
 */
public class CuentaBancaria {
    
    private double saldo = 0;//Saldo incial
    private final Object bloqueoSaldo = new Object();
    private final Object bloqueoHistorial = new Object();
    private final StringBuilder historial = new StringBuilder();

    public void depositar(double cantidad) {
        synchronized (bloqueoSaldo) {
            System.out.println(Thread.currentThread().getName() + " depositando " + cantidad);
            saldo += cantidad;
            System.out.println(Thread.currentThread().getName() + " nuevo saldo: " + saldo);
        }
        registrarOperacion("Depósito de " + cantidad);
    }

    public void retirar(double cantidad) {
        synchronized (bloqueoSaldo) {
            if (saldo >= cantidad) {
                System.out.println(Thread.currentThread().getName() + " retirando " + cantidad);
                saldo -= cantidad;
                System.out.println(Thread.currentThread().getName() + " nuevo saldo: " + saldo);
            } else {
                System.out.println(Thread.currentThread().getName() + " saldo insuficiente");
            }
        }
        registrarOperacion("Retiro de " + cantidad);
    }

    private void registrarOperacion(String operacion) {
        synchronized (bloqueoHistorial) {
            historial.append(Thread.currentThread().getName())
                     .append(": ")
                     .append(operacion)
                     .append("\n");
        }
    }

    public void imprimirHistorial() {
        synchronized (bloqueoHistorial) {
            System.out.println("\n=== HISTORIAL DE OPERACIONES ===");
            System.out.println(historial.toString());
        }
    }
}



