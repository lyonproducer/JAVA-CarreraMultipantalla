/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Cliente.ConectorCliente;

import static Cliente.ConectorCliente.C1E7;
import static Cliente.ConectorCliente.C2E7;
import static Cliente.ConectorCliente.C3E7;
import static Cliente.ConectorCliente.C4E7;

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
import static Modelo.Equipo6.idJugador;
import Simulacion.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LyonDj
 */
public class Equipo7 extends Thread{
    
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
    
     public Equipo7(String nombre, int limite, JLabel label,int pantalla, int velocidad, int probabilidadFallas){
        
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
        Equipo7.idJugador = idJugador;
        Equipo7.idEquipo=idEquipo;
    }
    public void movimientoDerecha(){
        
        for (int n=0; n<limite;n++){
            label.setVisible(true);
            label.setLocation(n,label.getY());
            //System.out.println(nombre + " avanza "+ label.getX());
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Equipo7.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
    public void dormir(){
        
        try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex){
                Logger.getLogger(Equipo4.class.getName()).log(Level.SEVERE, null, ex);
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
       // if(probabilidadFalla!=5){
        if (pantalla==1){
            //moverCcorredorOchoIzquierda();
            //movimientoDerecha();   
            cliente.enviarMSG("72");
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
                System.out.println("corriendo corredor 1 equipo7");
                moverCorredor1Equipo7Derecha();
                //C2E7.start();En Funcion
                stop(); 
            }
                
            if(nombre=="corredor2"){
                System.out.println("corriendo corredor 2 equipo7");
                moverCorredor2Equipo7Derecha();
                //Enviar mensaje para pasar de la pantalla 3 a 2
                cliente.enviarMSG("76");
                label.setVisible(false);
            }
            
            
        }
        
        if (pantalla==4){
                
            for (int n=label.getX(); n>=10 ;n-=10){
                label.setLocation(n,label.getY());
                dormir();
            }
            label.setVisible(false);
            cliente.enviarMSG("78");
        }
        
        if (pantalla==5){
           
                if (nombre=="corredor2"){
                    //entrada
                    System.out.println("paso el corredor 2");
                    moverCorredor2Equipo7Izquierda();
                    //C3E7.start();En funcion 
                    stop();
                    }
                
                if(nombre=="corredor3"){
                    //tranferencia
                    System.out.println("paso el corredor 3");
                    moverCorredor3Equipo7Izquierda();
                    //C4E7.start();listo funcion
                    stop();
                    
                }
                
                if(nombre == "corredor4"){
                    System.out.println("paso el corredor 4");
                    moverCorredor4Equipo7Izquierda();
                    cliente.enviarMSG("72"); 
                    label.setVisible(false);
                }  
        }
        
        //System.out.println(nombre + " ha llegado a la meta");
        yield();
//    }else{
//     try { 
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
//    }
    }
    
    public void moverCorredor1Equipo7Derecha(){
        label.setLocation(170,490);
        dormir(); label.setLocation(180,480);
        dormir(); label.setLocation(190,470);
        dormir(); label.setLocation(200,460);
        dormir(); label.setLocation(210,450);
        dormir(); label.setLocation(220,440);
        dormir(); label.setLocation(220,430);
        dormir(); label.setLocation(230,420);
        dormir(); label.setLocation(230,410);
        dormir(); label.setLocation(240,400);
        dormir(); label.setLocation(240,390);
        dormir(); label.setLocation(240,380);
        dormir(); label.setLocation(240,370);
        dormir(); label.setLocation(250,360);
        dormir(); label.setLocation(250,350);
        dormir(); label.setLocation(250,340);
        dormir(); label.setLocation(250,330);
        dormir(); label.setLocation(250,320);
        dormir(); label.setLocation(250,310);
        dormir(); label.setLocation(250,300);
        dormir(); label.setLocation(250,290);
        dormir(); label.setLocation(250,280);
        dormir(); label.setLocation(250,270);
        dormir(); label.setLocation(250,260);
        dormir(); label.setLocation(250,250);
        dormir(); label.setLocation(250,240);
        dormir(); label.setLocation(250,230);
        dormir(); label.setLocation(240,220);
        dormir(); label.setLocation(240,210);
        dormir(); label.setLocation(240,200);
        dormir(); label.setLocation(230,190);
        dormir(); label.setLocation(230,180);
        dormir(); label.setLocation(220,170);
        dormir(); label.setLocation(220,160);
        dormir(); label.setLocation(220,150);
        dormir(); label.setLocation(210,140);
        dormir(); label.setLocation(190,130);
        dormir(); label.setLocation(180,120);
        dormir(); label.setLocation(170,110);
        dormir(); label.setLocation(160,100);
        dormir(); label.setLocation(150,90);
        dormir(); label.setLocation(140,90);
        dormir(); label.setLocation(130,80);
        dormir(); label.setLocation(120,80);
        dormir(); label.setLocation(110,70);
        dormir(); label.setLocation(100,70);
        dormir(); label.setLocation(900,60);
        dormir(); label.setLocation(80,60);
        dormir(); label.setLocation(70,60);
        C2E7.start();
        /***********************************/
         label.setLocation(40,40);
        dormir(); label.setLocation(30,40);
        dormir(); label.setLocation(20,40);
        dormir(); label.setLocation(10,40);
        dormir(); label.setLocation(0,40);
        dormir(); 
    }
    public void moverCorredor2Equipo7Derecha(){
       label.setLocation(40,40);
       dormir(); label.setLocation(30,40);
       dormir(); label.setLocation(20,40);
       dormir(); label.setLocation(10,40);
       dormir(); label.setLocation(0,40);
       dormir(); 
       } 
    public void moverCorredor4Equipo7Izquierda(){
        label.setLocation(290,530);
        dormir(); label.setLocation(300,540);
        dormir(); label.setLocation(310,540);
        dormir(); label.setLocation(320,540);
        dormir(); label.setLocation(330,550);
        dormir(); label.setLocation(340,550);
        dormir(); label.setLocation(350,550);
        dormir(); label.setLocation(360,550);
        dormir(); label.setLocation(370,550);
        dormir(); label.setLocation(380,550);
        dormir(); label.setLocation(390,550);
        dormir(); 
    }
    
    public void moverCorredor2Equipo7Izquierda(){
        label.setLocation(390,40);
        dormir();label.setLocation(380,40);
        dormir();label.setLocation(370,40);
        dormir();label.setLocation(360,40);
        dormir();label.setLocation(350,40);
        dormir();label.setLocation(340,40);
        dormir();label.setLocation(330,40);
        dormir();label.setLocation(320,40);
        dormir();label.setLocation(310,50);
        dormir();label.setLocation(300,50);
        dormir();label.setLocation(290,50);
        dormir();label.setLocation(280,60);
        dormir();label.setLocation(270,60);
        dormir();label.setLocation(260,70);
        dormir();label.setLocation(250,70);
        dormir();label.setLocation(240,80);
        dormir();label.setLocation(230,90);
        dormir();
        C3E7.start();
        /**************************************/
         label.setLocation(220,90);
        dormir();
        label.setLocation(210,100);
        dormir();
        label.setLocation(200,110);
        dormir();
        label.setLocation(190,120);
        dormir();
        label.setLocation(180,130);
        dormir();
        label.setLocation(170,140);
        dormir();
    }
    public void moverCorredor3Equipo7Izquierda(){
        label.setLocation(220,90);
        dormir();
        label.setLocation(210,100);
        dormir();
        label.setLocation(200,110);
        dormir();
        label.setLocation(190,120);
        dormir();
        label.setLocation(180,130);
        dormir();
        label.setLocation(170,140);
        dormir();
        label.setLocation(160,150);
        dormir();
        label.setLocation(160,160);
        dormir();
        label.setLocation(150,170);
        dormir();
        label.setLocation(150,180);
        dormir();
        label.setLocation(140,190);
        dormir();
        label.setLocation(140,200);
        dormir();
        label.setLocation(130,210);
        dormir();
        label.setLocation(130,220);
        dormir();
        label.setLocation(130,240);
        dormir();
        label.setLocation(130,260);
        dormir();
        label.setLocation(130,280);
        dormir();
        label.setLocation(130,300);
        dormir();
        label.setLocation(130,320);
        dormir();
        label.setLocation(130,330);
        dormir();
        label.setLocation(130,340);
        dormir();
        label.setLocation(130,360);
        dormir();
        label.setLocation(140,370);
        dormir();
        label.setLocation(140,380);
        dormir();
        label.setLocation(140,390);
        dormir();
        label.setLocation(150,400);
        dormir();
        label.setLocation(160,410);
        dormir();
        label.setLocation(160,420);
        dormir();
        label.setLocation(170,430);
        dormir();
        label.setLocation(170,440);
        dormir();
        label.setLocation(180,450);
        dormir();
        label.setLocation(190,460);
        dormir();
        label.setLocation(200,470);
        dormir();
        label.setLocation(210,480);
        dormir();
        label.setLocation(220,490);
        dormir();
        label.setLocation(230,500);
        dormir();
        label.setLocation(240,500);
        dormir();
        label.setLocation(2500,510);
        dormir();
        label.setLocation(260,520);
        dormir();
        label.setLocation(270,530);
        dormir();
        label.setLocation(280,530);
        dormir();
        /*********************************************/
        C4E7.start();
        label.setLocation(290,530);
        dormir();label.setLocation(300,530);
        dormir();label.setLocation(310,540);
        dormir();label.setLocation(320,540);
        dormir();label.setLocation(330,540);
        dormir();label.setLocation(340,540);
        dormir();
    }
}