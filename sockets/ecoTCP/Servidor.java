package ecoTCP;

/*
 * psp 2025
 * programa servidor 
 * Crea un Serversocket TCP
 * Acepta conexiones de clients gestionadas por hilos
 * Escribe por consola el mensaje recibido y quien lo manda
 * y reenvia al cliente el mismo mensaje
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String [] args){

        //puerto en el que estará escuchando nuestro 
        //servidor para establecer las conexiones
        final int PUERTO=500;

        //Creamos el serverSocket
        //Al ponerlo dentro del try se cierra automáticamente
        try(ServerSocket server=new ServerSocket(PUERTO)){
            System.out.println("Servidor escuchando en el puerto "+ PUERTO);

            //Estamos constantemente aceptando conexiones
            while(true){
                /*
                 * El programa se queda parado hasta que recibe una conexión.
                 * Una vez establecida crea un nuevo soxket para la comunicación
                 * cliente-servidor
                 */
                Socket socket=server.accept();
                //imprimo la ip desde que se ha conectado
                System.out.println("Cliente conectado"+socket.getInetAddress());

                //manejo el cliente con un hilo alq ue le paso el Socket
                new Thread (()->handleClient(socket)).start();

            }

            }catch(IOException e){
                e.printStackTrace();
            }

            
        }





    /*
     * metodo que meneja la conexión cliente servidor, recibe el socket
     */

    private static void handleClient(Socket socket) {
        //Extraigo del socket los InputStream/OutputStream para leer/escribir
        //Los transformo en otra estructura mas comoda para leer y escribir
        //BufferedReader/Writer
        // try with_resources 
        System.err.println("Entrando al hilo");
        try (BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);) {
                String mensaje;

                //leo el mensaje del BufferedReader devuelve null cuando se cierra
                while((mensaje=in.readLine())!=null){
                    System.out.println(mensaje+ " desde " + socket.getInetAddress());
                    //reenvio el mensaje
                    out.println("desde servidor " + mensaje);

                }

    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Socket servidor cerrado");

        
    }
    
}
