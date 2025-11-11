/*
 * psp 2025
 * Un sistema de riego consta de varios canales de alimentación que aportan agua y varios regantes (agricultores/aspersores) que consumen agua para irrigar parcelas. 
 * El agua se almacena en depósitos intermedios con capacidad limitada (cada depósito puede contener como máximo C unidades de agua).
Los canales de alimentación son productores: periódicamente vierten unidades de agua en los depósitos.
Los regantes son consumidores: sacan unidades de agua de los depósitos para regar.
Si el depósito está lleno, el canal que trate de añadir agua debe esperar (no puede desperdiciar agua).
Si el depósito está vacío, el regante que quiera consumir debe esperar hasta que haya agua.
Queremos simular esta interacción con hilos: M canales de alimentación (productores), N regantes (consumidores) y un depósito con capacidad C. Cada operación (producir o consumir) trata una unidad (o un lote, según la variante).
 */

public class Main {
    public static void main(String [] args){
        final int NUMEROREGANTES=4;
        final int NUMEROCANALES=5;
        final int CAPACIDADDEPOSITO=100;
        //Creo el objeto compartido
        Deposito deposito=new Deposito(CAPACIDADDEPOSITO);
        //creo los hilos consumidor  y los pongo a correr
        for(int i=1;i<=NUMEROREGANTES;i++){
            new Regante("regante"+i,deposito).start();
        }
        //creo los hilos productor  y los pongo a correr
        for(int i=1;i<=NUMEROCANALES;i++){
            new Canal("canal "+i,deposito).start();
        }



    }
    
}
