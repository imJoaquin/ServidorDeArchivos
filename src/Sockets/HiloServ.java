/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Instant;

/**
 *
 *
 */
public class HiloServ implements Runnable {
    private DatagramSocket ds;
    private byte[] buffer = new byte[1024];
    private String mensaje;
    private InetAddress direccion;
    private int puertoCliente;

    public HiloServ(DatagramSocket ds, String mensaje, InetAddress direccion, int puertoCliente) {
        this.ds = ds;
        this.mensaje = mensaje;
        this.direccion = direccion;
        this.puertoCliente = puertoCliente;
    }
    
    
    @Override
    public void run() {
        try {
            
            System.out.println( "Reporting at " + Instant.now());
                /**
                 * recibe el mensaje y lo muestra en consola
                 */
                
            FileInputStream f = null;
            
            if(mensaje.contains("1")){
                 f=new FileInputStream("1. nombres.txt");
                        
                 int i=0;
                 while(f.available()!=0){
                        buffer[i]=(byte)f.read();
                        i++;
                 }                    
            }else if (mensaje.contains("2")){
                 f=new FileInputStream("2. platillos.txt");
                        
                 int i=0;
                 while(f.available()!=0){
                        buffer[i]=(byte)f.read();
                        i++;
                 } 
            }else if(mensaje.contains("3")){
                 f=new FileInputStream("3. pagos.txt");
                        
                 int i=0;
                 while(f.available()!=0){
                        buffer[i]=(byte)f.read();
                        i++;
                 } 
            }
            
            ds.send(new DatagramPacket(buffer,buffer.length
                    ,direccion,puertoCliente));

            //limpia el buffer para prepararse para leer un nuevo mensaje
            buffer = new byte[1024];
                        
            } catch (IOException ex) {
                System.out.println("Error");
            }
    
    }
    
}
