/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ServidorHilo extends Thread {
    
    public Socket socket;
    public DataOutputStream dos;
    public DataInputStream dis;
    public int idSession;
    
    public ServidorHilo(Socket socket, int id) {
        
        this.socket = socket;
        this.idSession = id;
        
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        
        String accion = "";
        
        while(true){
            
            try {
            accion = dis.readLine();
            System.out.println(accion);
            
            ServidorVista.jTextArea.append("\nEl cliente "+ idSession + " dijo: "+accion );
            
            if(accion.equals("Conectado")){
                System.out.println("El cliente con idSesion "+ this.idSession +" saluda");
                dos.writeBytes("adios a Cliente "+ idSession+ "\n");
            }

            if(accion.equals("12")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("13");
            }

            if(accion.equals("22")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("23");
            }

            if(accion.equals("32")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("33");
            }

            if(accion.equals("42")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("43");
            }
            
            if(accion.equals("52")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("53");
            }
            
            if(accion.equals("62")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("63");
            }

            if(accion.equals("72")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("73");
            }

            if(accion.equals("82")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("83");
            }

            //--------------------------------------------------------------------------
            
            if(accion.equals("16")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("17");
            }

            if(accion.equals("26")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("27");
            }
            
            if(accion.equals("36")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("37");
            }

            if(accion.equals("46")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("47");
            }

            if(accion.equals("56")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("57");
            }

            if(accion.equals("66")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("67");
            }   

            if(accion.equals("76")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("77");
            }

            if(accion.equals("86")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket2, 2)).enviarMSG("87");
            }                     

            //--------------------------------------------------------------------------
            
            if(accion.equals("18")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("19"); 
            }            
            
            if(accion.equals("28")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("29"); 
            }
            
            if(accion.equals("38")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("39"); 
            }

            if(accion.equals("48")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("49"); 
            }

            if(accion.equals("58")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("59"); 
            }

            if(accion.equals("68")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("69"); 
            }

            if(accion.equals("78")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("79"); 
            }

            if(accion.equals("88")){
                ((ServidorHilo) new ServidorHilo(Servidor.socket1, 1)).enviarMSG("89"); 
            }

            //--------------------------------------------------------------------------
            
            } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public void enviarMSG(String msg)
    {
        try{
            dos.writeBytes(msg+"\n");
            ServidorVista.jTextArea.append("\nEl servidor envio: "+msg);
        }catch (IOException e){};
    }
    
}
