/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Servidor extends Thread {
    
    ServerSocket ss;
    ServidorVista SV;
    int idSession = 1;
    
    public static Socket socket1;
    public static Socket socket2;
    public static Socket socket3;

    
    public Servidor() {
       
    }
    
    @Override
    public void run(){
        
        System.out.print("Inicializando servidor... ");
        ServidorVista.jTextArea.append("Inicializando servidor... ");
        
        try {
            ss = new ServerSocket(10578);
            System.out.println("\t[OK]");
            ServidorVista.jTextArea.append("\t[OK]");
            
            while (true) {
                
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: " +socket);
                
                ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
                
                if(idSession==1){
                    socket1 = socket;
                    System.out.println("Guardado socket 1: " + socket1);
                }
                
                if(idSession==2){
                    socket2 = socket;
                    System.out.println("Guardado socket 2: " + socket2);
                }
                
                if(idSession==3){
                    socket3 = socket;
                    System.out.println("Guardado socket 2: " + socket2);
                }
                
                idSession++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
           
        }

    }
    
}