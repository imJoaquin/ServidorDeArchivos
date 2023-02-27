/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import Sockets.HiloServ;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase del servidor que controla el envio de los mensajes
 * Se encarga de manejar principalmente los mensajes y reenviarlos
 * a los demas usuarios conectados
 *
 */
public class Servidor {
    
    public static void main(String[] args){
        //se asignan los datos principales a usar y muestra en la consola del servidor el estado
        int PUERTO = 4444;
        byte[] buffer = new byte[1024];
        System.out.println("Iniciando servidor...");
        
        
        //se crea el socket udp nulo luego se le asigna un puerto para usarse
        DatagramSocket UDPSocket = null;
        try {
            UDPSocket = new DatagramSocket(PUERTO);
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        
        //while para que el servidor siga escuchando los mensajes entrantes
        while(true){
        try{

            //codigo principal utilizado que escucha cuando entran mensajes
            //ademas de mostrarlos en la consola del servidor
            DatagramPacket peticion = new DatagramPacket(buffer,buffer.length);
            UDPSocket.receive(peticion);
            //String que guarda el mensaje recibido
            String mensaje = new String(peticion.getData());
            //obtiene puerto y direccion del usuario que mando el mensaje
            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();
           
            System.out.println(mensaje);
            
            HiloServ hs = new HiloServ(UDPSocket,mensaje,  direccion, puertoCliente);
            
            executor.execute(hs);
            //imprime el mensaje recibido en pantalla
            
        }catch(Exception e){
            //si ocurre un error
            System.out.println("ha habido un error");
        }
        }
        
    }   
}    
    
    
    
    
    

