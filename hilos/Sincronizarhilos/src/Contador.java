
/*
 * psp 2025
 * Clase contador. Tiene un metodo que incrementa en uno el valor del contador
 * los hilos van a comaprtir un objero contador
 * 
 */
public class Contador {

    private Integer contador; //variable que va a contener la cantidad
    
    //Constructor, generamos un contador con una cantidad incial
    public Contador(int c){
        this.contador=c;
    }


    //metodo incrementarContador que suma uno a la cantidad almacenada
    public void incrementar(){
        contador++;
    }
    public String toString(){
        return "El valor del contador es" + contador;
    }


}