package ecoUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * psp 2025
 * cliente UDP
 * 
 */

public class ClienteUDP {
    public static void main(String[] args) {
        // Establecer ip de destino y puerto del datagrama
        final String HOST = "localhost";
        final int PUERTO = 500;

        // leo una frase por teclado construyo un datagrama y se lo envio al servidor
        try (DatagramSocket socket = new DatagramSocket();
                Scanner scanner = new Scanner(System.in)) {
            InetAddress address = InetAddress.getByName(HOST); // objeto InetAddress para la dirección del host
            byte[] buffer;
            System.out.println("Escribe un mensaje para enviar al servidor:");

            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                buffer = message.getBytes();// hay que transformarlo en byte[] para enviarlo en le datagrama

                // Enviar datagrama al servidor
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PUERTO);
                socket.send(packet);

                // Recibir respuesta del servidor
                DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
                socket.receive(response);

                String echo = new String(response.getData(), 0, response.getLength());
                System.out.println("Eco del servidor: " + echo);
                System.out.println("Durección respuesta" + response.getAddress());
                 System.out.println("Puerto" + response.getPort());


            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
