/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketsCliente;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * hilo que ayudara a escuchar los mensajes recibidos del lado del cliente
 * 
 */
public class ClientThread extends Thread{
    //sockets y buffer a usar para interpretar el mensaje
    private DatagramSocket ds;
    private byte[] buffer = new byte[1024];
    /**
     * construcor que inicializa el socket al socket de la clase cliente
     * @param ds 
     */
    public ClientThread(DatagramSocket ds) {
        this.ds = ds;
    }
      
    /**
     * clase que activa el hilo
     */
    @Override
    public void run(){
        while(true){
            try {
                /**
                 * recibe el mensaje y lo muestra en consola
                 */
                
                FileOutputStream f=new FileOutputStream("respuestas.txt");
                        while(true){
                            DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
                            ds.receive(dp);
                            String mensaje = new String(dp.getData(),0,dp.getLength());
                            System.out.println(mensaje);           
                            f.write(dp.getData());
                            
                        }
                        
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
