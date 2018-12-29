/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Cliente.ConectorCliente;

import static Cliente.ConectorCliente.C1E5;
import static Cliente.ConectorCliente.C2E5;
import static Cliente.ConectorCliente.C3E5;
import static Cliente.ConectorCliente.C4E5;

import Servidor.Servidor;
import Servidor.ServidorHilo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import static Cliente.Messenger.cliente;
import static Cliente.VCliente.jLabel2;
import static Cliente.VCliente.jLabel3;
import static Cliente.VCliente.jScrollPane1;
import static Cliente.VCliente.jTextArea1;
import static Modelo.Equipo1.idJugador;
import static Modelo.Equipo6.idJugador;
import static Modelo.Equipo8.idEquipo;
import Simulacion.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LyonDj
 */
public class Equipo5 extends Thread{
    
    String nombre;
    int limite;
    JLabel label;
    int pantalla;
    
   
    int velocidad;
    int probabilidadFalla;
    Equipo e;
    static int idJugador;
    static int idEquipo;
    String nombreEquipo;
    
     public Equipo5(String nombre, int limite, JLabel label,int pantalla, int velocidad, int probabilidadFallas){
        
        this.nombre=nombre;
        this.limite=limite;
        this.label=label;
        this.pantalla=pantalla;
         this.pantalla=pantalla;
        this.velocidad=velocidad;
        this.probabilidadFalla=probabilidadFallas;
        System.err.println(nombre+" probabilidadFallar "+probabilidadFalla);
    }
    
     public void setE(Equipo e) {
        this.e = e;
    }
    
    public static void setIdJugadorIdEquipo(int idJugador, int idEquipo) {
        Equipo5.idJugador = idJugador;
        Equipo5.idEquipo=idEquipo;
    }

    public void movimientoDerecha(){
        
        for (int n=0; n<limite;n++){
            label.setVisible(true);
            label.setLocation(n,label.getY());
            //System.out.println(nombre + " avanza "+ label.getX());
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Equipo5.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
    public void dormir(){
        
        try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex){
                Logger.getLogger(Equipo5.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
      public void dormirPreZona(){
        
        try {
                Thread.sleep(velocidad+50);
            } catch (InterruptedException ex){
                Logger.getLogger(Equipo1.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @Override
    public void run(){
//        if(probabilidadFalla!=5){
        if (pantalla==1){
            //moverCcorredorOchoIzquierda();
            //movimientoDerecha();   
            cliente.enviarMSG("52");
            //label.setVisible(false); 
        }
        
        if (pantalla==2){
             movimientoDerecha();
            System.out.println(nombre + " ha llegado a la meta");
           
            //cliente.enviarMSG("4");
            //label.setVisible(false);
          try {
                String resultados=jTextArea1.getText();
                int pganadas = 0;
                int pperdidas = 0;
                     e.conectar();
                     ResultSet result = e.RealizarConsulta(e.consultarNombreEquipo(idJugador));
                     while (result.next()) { //Creando las filas para el JTable
                         String equipo=String.valueOf(result.getString(1));
                          pganadas=result.getInt(2);
                          pperdidas=result.getInt(3);
                          System.err.println(equipo+pganadas+pperdidas);
                          if(resultados.isEmpty()){
                                e.actualizarEquipo(pganadas+1,pperdidas, idEquipo);
                                 jScrollPane1.setVisible(true);
                                 jTextArea1.setVisible(true);
                                 jLabel2.setVisible(true);
                                 jLabel3.setVisible(true);
                                 jTextArea1.setText("Equipo\t\tVictorias\tFallos\n\n"+equipo+"\t\t"+ (pganadas+1)+"\t"+pperdidas+"\n");
                            }else{
                                e.actualizarEquipo(pganadas,pperdidas+1, idEquipo);
                                String resultr=  jTextArea1.getText()+"\n" + equipo+"\t\t"+pganadas+"\t"+(pperdidas+1)+"\n";
                                jTextArea1.setText(resultr);
                               
                            
                            }
                     
                     }     
       
             } catch (SQLException ex) {
                    Logger.getLogger(Equipo2.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        if (pantalla==3){
            
            if(nombre=="corredor1"){
                //Mover corredor1 equipo2
                System.out.println("corriendo corredor 1 equipo5");
                moverCorredor1Equipo5Derecha();
                C2E5.start();//En funcion
                stop(); 
            }
                
            if(nombre=="corredor2"){
                System.out.println("corriendo corredor 2 equipo5");
                moverCorredor2Equipo5Derecha();
                //Enviar mensaje para pasar de la pantalla 3 a 2
                cliente.enviarMSG("56");
                label.setVisible(false);
            }
            
            
        }
        
        if (pantalla==4){
                
            for (int n=label.getX(); n>=10 ;n-=10){
                label.setLocation(n,label.getY());
                dormir();
            }
            label.setVisible(false);
            cliente.enviarMSG("58");
        }
        
        if (pantalla==5){
           
                if (nombre=="corredor2"){
                    //entrada
                    System.out.println("paso el corredor 2");
                    moverCorredor2Equipo5Izquierda();
                    //C3E5.start();//En funcion 
                    stop();
                    }
                
                if(nombre=="corredor3"){
                    //tranferencia
                    System.out.println("paso el corredor 3");
                    moverCorredor3Equipo5Izquierda();
                    C4E5.start();//En funcion
                    stop();
                    
                }
                
                if(nombre == "corredor4"){
                    System.out.println("paso el corredor 4");
                    moverCorredor4Equipo5Izquierda();
                    cliente.enviarMSG("52"); 
                    label.setVisible(false);
                }  
        }
        
        
        yield();
//    }else{
//         try { 
//            e.conectar();
//            ResultSet result = e.RealizarConsulta(e.consultarfallos(idJugador));
//        
//            while (result.next()) { //Creando las filas para el JTable
//                int fallos=result.getInt(1);
//                e.actualizarFallos(fallos+1, idJugador);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Equipo1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        }
    }
    
    public void moverCorredor2Equipo5Izquierda(){
        
            dormir(); label.setLocation(380,65);
            dormir(); label.setLocation(360,68);
            dormir(); label.setLocation(340,70);
            dormir(); label.setLocation(320,70);
            dormir(); label.setLocation(300,80);
            //-------------------------------------
            C3E5.start();
            dormir(); label.setLocation(260,100);
            dormir(); label.setLocation(240,110);
        
    }
    
    public void moverCorredor1Equipo5Derecha(){
            dormir(); label.setLocation(120,460);
            dormir(); label.setLocation(140,450);
            dormir(); label.setLocation(160,430);
            dormir(); label.setLocation(170,410);
            dormir(); label.setLocation(180,400);
            dormir(); label.setLocation(190,380);
            dormir(); label.setLocation(200,360);
            dormir(); label.setLocation(210,330);
            dormir(); label.setLocation(210,300);
            dormir(); label.setLocation(210,280);
            dormir(); label.setLocation(210,260);
            dormir(); label.setLocation(210,240);
            dormir(); label.setLocation(200,220);
            dormir(); label.setLocation(190,190);
            dormir(); label.setLocation(180,170);
            dormir(); label.setLocation(170,150);
            dormir(); label.setLocation(150,130);
            dormir(); label.setLocation(130,110);
            dormir(); label.setLocation(110,100);
            
    
    }

    public void moverCorredor2Equipo5Derecha(){
         label.setLocation(90,90);
           dormir(); label.setLocation(80,90);
           dormir(); label.setLocation(70,90);
           dormir(); label.setLocation(60,80);
           dormir(); label.setLocation(50,80);
           dormir(); label.setLocation(40,80);
           dormir(); label.setLocation(30,80);
           dormir(); label.setLocation(20,70);
           dormir(); label.setLocation(10,70);
           dormir(); label.setLocation(0,70);
           dormir();
       }
    public void moverCorredor4Equipo5Izquierda(){
        label.setLocation(260,480);
        dormir(); label.setLocation(270,480);
        dormir(); label.setLocation(280,490);
        dormir(); label.setLocation(290,490);
        dormir(); label.setLocation(300,500);
        dormir(); label.setLocation(310,500);
        dormir(); label.setLocation(320,500);
        dormir(); label.setLocation(330,510);
        dormir(); label.setLocation(340,510);
        dormir(); label.setLocation(350,510);
        dormir(); label.setLocation(360,510);
         dormir(); label.setLocation(370,510);
        dormir(); label.setLocation(380,510);
        dormir(); label.setLocation(390,510);
        dormir(); 
        dormir(); 
    }
    public void moverCorredor3Equipo5Izquierda(){
     label.setLocation(280,100);
     dormir(); label.setLocation(270,100);
     dormir(); label.setLocation(260,110);
     dormir(); label.setLocation(250,110);
     dormir(); label.setLocation(240,120);
     dormir(); label.setLocation(230,130);
     dormir(); label.setLocation(220,140);
     dormir(); label.setLocation(210,150);
     dormir(); label.setLocation(200,160);
     dormir(); label.setLocation(200,170);
     dormir(); label.setLocation(190,180);
     dormir(); label.setLocation(190,190);
     dormir(); label.setLocation(180,200);
     dormir(); label.setLocation(180,210);
     dormir(); label.setLocation(170,220);
     dormir(); label.setLocation(170,230);
     dormir(); label.setLocation(170,240);
     dormir(); label.setLocation(160,250);
     dormir(); label.setLocation(160,260);
     dormir(); label.setLocation(160,270);
     dormir(); label.setLocation(160,280);
     dormir(); label.setLocation(160,300);
     dormir(); label.setLocation(160,320);
     dormir(); label.setLocation(170,350);
     dormir(); label.setLocation(170,360);
     dormir(); label.setLocation(170,370);
     dormir(); label.setLocation(180,380);
     dormir(); label.setLocation(180,390);
     dormir(); label.setLocation(190,400);
     dormir(); label.setLocation(190,410);
     dormir(); label.setLocation(200,420);
     dormir(); label.setLocation(200,430);
     dormir(); label.setLocation(210,440);
     dormir(); label.setLocation(220,450);
     dormir(); label.setLocation(230,460);
     dormir(); label.setLocation(240,470);
     dormir(); label.setLocation(250,480);
     /********************************/
     dormir(); label.setLocation(260,480);
     dormir(); label.setLocation(270,490);
     dormir(); label.setLocation(280,490);
     dormir(); label.setLocation(290,500);
     dormir(); label.setLocation(300,500);
     dormir(); label.setLocation(310,510);
     dormir(); label.setLocation(320,510);
     dormir(); 
    }
}