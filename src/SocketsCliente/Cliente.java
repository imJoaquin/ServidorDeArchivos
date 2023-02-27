/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketsCliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase que representa a los usuarios permite conectarse y enviar peticiones
 * 
 */
public class Cliente {
    public static void main(String[] args){
        //crea principales recursos a utilizar
        Scanner in = new Scanner(System.in);
        int PUERTOSERVER= 4444;
        byte[] buffer = new byte[1024];
        
        
        try {
            //guarda los datos del servidor a conectarse
            InetAddress direccionServer = InetAddress.getByName("localhost");
            DatagramSocket UDPSocket = new DatagramSocket();
            //crea hilo que ayudara a leer mensajes entrantes
            //con el fin de no interrumpir el flujo 
            ClientThread ct = new ClientThread(UDPSocket);
            ct.start();
            
            System.out.println("use --salir para salir ");
            
            while(true){
            //tomas los datos del usuario
            System.out.println("ingrese archivo a solicitar: ");
            System.out.println("lista de archivos: ");
            System.out.println("1. nombres.txt");
            System.out.println("2. platillos.txt");
            System.out.println("3. pagos.txt");
            System.out.println("ingrese numero del archivo a solicitar");
            
            String archivoPeticion= in.nextLine();
            //mensaje de peticion
            
            if(archivoPeticion.equalsIgnoreCase("--salir")){   
                break;
            }
            
            String mensaje= archivoPeticion;
            
            buffer = mensaje.getBytes();
            
            // mensaje de peticion del archivo a pedir
            DatagramPacket mandarMensaje = new DatagramPacket(buffer,buffer.length,
                    direccionServer,PUERTOSERVER);
            //manda el mensaje antes mensionada
            UDPSocket.send(mandarMensaje);
            
            //limpia el buffer para recibir nuevos mensajes
            buffer = new byte[1024];
            
                
            }
            
            //cierra el socket
            UDPSocket.close();
            //cierra el sistema
            System.exit(0);

        } catch (Exception ex) {
            //si ocurre un error en algun momento
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
