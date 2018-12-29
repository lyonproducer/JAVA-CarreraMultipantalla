/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Cliente.ConectorCliente;

import static Cliente.ConectorCliente.C1E8;
import static Cliente.ConectorCliente.C2E8;
import static Cliente.ConectorCliente.C3E8;
import static Cliente.ConectorCliente.C4E8;

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
public class Equipo8 extends Thread{
    
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
    
     public Equipo8(String nombre, int limite, JLabel label,int pantalla, int velocidad, int probabilidadFallas){
        
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
        Equipo8.idJugador = idJugador;
        Equipo8.idEquipo=idEquipo;
    }
   

    public void movimientoDerecha(){
        
        for (int n=0; n<limite;n++){
            label.setVisible(true);
            label.setLocation(n,label.getY());
            //System.out.println(nombre + " avanza "+ label.getX());
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Equipo8.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
    
    public void dormir(){
        
        try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex){
                Logger.getLogger(Equipo8.class.getName()).log(Level.SEVERE, null, ex);
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
            cliente.enviarMSG("82");
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
                System.out.println("corriendo corredor 1 equipo8");
                moverCorredor1Equipo8Derecha();
                C2E8.start();//en funcion
                stop(); 
            }
                
            if(nombre=="corredor2"){
                System.out.println("corriendo corredor 2 equipo8");
                moverCorredor2Equipo8Derecha();
                //Enviar mensaje para pasar de la pantalla 3 a 2
                cliente.enviarMSG("86");
                label.setVisible(false);
            }
            
            
        }
        
        if (pantalla==4){
                
            for (int n=label.getX(); n>=10 ;n-=10){
                label.setLocation(n,label.getY());
                dormir();
            }
            label.setVisible(false);
            cliente.enviarMSG("88");
        }
        
        if (pantalla==5){
           
                if (nombre=="corredor2"){
                    //entrada
                    System.out.println("paso el corredor 2");
                    moverCorredor2Equipo8Izquierda();
                    C3E8.start();//En funcion 
                    stop();
                     movimientoDerecha();
            System.out.println(nombre + " ha llegado a la meta");
            movimientoDerecha();
            //cliente.enviarMSG("4");
            //label.setVisible(false);
             
                    }
                
                if(nombre=="corredor3"){
                    //tranferencia
                    System.out.println("paso el corredor 3");
                    moverCorredor3Equipo8Izquierda();
                    C4E8.start();//En funcion
                    stop();
                    
                }
                
                if(nombre == "corredor4"){
                    System.out.println("paso el corredor 4");
                    moverCorredor4Equipo8Izquierda();
                    cliente.enviarMSG("82"); 
                    label.setVisible(false);
                }  
        }
        
        //System.out.println(nombre + " ha llegado a la meta");
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
//    } 
    }
    
    public void moverCorredor3Equipo8Izquierda(){
     int x = 0, y=0;
            
             label.setLocation(180,y+100);
            dormir();
            label.setLocation(170,y+120);
            dormir();
            label.setLocation(150,y+140);
            dormir();
            label.setLocation(140,y+160);
            dormir();
            label.setLocation(130,y+180);
            dormir();
            label.setLocation(120,y+200);
            dormir();
            label.setLocation(110,y+230);
            dormir();
            label.setLocation(110,y+250);
            dormir();
            label.setLocation(110,y+260);
            dormir();
            label.setLocation(110,y+280);
            dormir();
            label.setLocation(110,y+290);
            dormir();
            label.setLocation(110,y+310);
            dormir();
            label.setLocation(110,y+320);
            dormir();
            label.setLocation(110,y+340);
            dormir();
            label.setLocation(110,y+360);
            dormir();
            label.setLocation(130,y+400);
            dormir();
            label.setLocation(140,y+420);
            dormir();
            label.setLocation(150,y+440);
            dormir();
            label.setLocation(160,y+450);
            dormir();
            label.setLocation(170,y+470);
            dormir();
            label.setLocation(180,y+480);
            dormir();
            label.setLocation(190,y+490);
            dormir();
            label.setLocation(200,y+500);
            dormir();
            label.setLocation(220,y+510);
            dormir();
            label.setLocation(230,y+520);
            dormir();
            label.setLocation(240,y+530);
            dormir();
            label.setLocation(260,y+540);
            dormir();
            label.setLocation(280,y+550);
            dormir();
            /***************************************/
            label.setLocation(290,y+550);
            dormir();label.setLocation(310,y+560);
            dormir();label.setLocation(320,y+560);
            dormir();label.setLocation(330,y+560);
            dormir();label.setLocation(340,y+560);
            dormir();label.setLocation(350,y+560);
            dormir();label.setLocation(360,y+560);
            dormir();
    }
    public void moverCorredor2Equipo8Izquierda(){
         int y=0;
         
            label.setLocation(380,y+20);
            dormir();
            label.setLocation(360,y+20);
            dormir();
            label.setLocation(340,y+20);
            dormir();
            label.setLocation(320,y+20);
            dormir();
            label.setLocation(300,y+30);
            dormir();
            label.setLocation(280,y+30);
            dormir();
            label.setLocation(270,y+40);
            dormir();
            label.setLocation(250,y+50);
            dormir();
            label.setLocation(230,y+60);
            dormir();
            label.setLocation(220,y+70);
            dormir();
            label.setLocation(200,y+80);
            dormir();
            label.setLocation(190,y+90);
            dormir();
            /***********************************/
             label.setLocation(180,y+100);
            dormir();
            label.setLocation(170,y+120);
            dormir();
            label.setLocation(150,y+140);
            dormir();
            label.setLocation(140,y+160);
            dormir();
            label.setLocation(130,y+180);
            dormir();
            label.setLocation(120,y+200);
            dormir();
     }
    public void moverCorredor4Equipo8Izquierda(){
        label.setLocation(310,560);
        dormir(); label.setLocation(320,560);
        dormir(); label.setLocation(330,570);
        dormir(); label.setLocation(340,570);
        dormir(); label.setLocation(350,570);
        dormir(); label.setLocation(360,570);
        dormir(); label.setLocation(370,570);
        dormir(); label.setLocation(380,570);
        dormir(); label.setLocation(390,570);
        dormir(); 
    }
    public void moverCorredor2Equipo8Derecha(){
           label.setLocation(10,20);
           dormir(); label.setLocation(0,20);
           dormir(); 
       } 
    public void moverCorredor1Equipo8Derecha(){
         label.setLocation(200,470);
           dormir(); label.setLocation(210,470);
           dormir(); label.setLocation(220,460);
           dormir(); label.setLocation(230,450);
           dormir(); label.setLocation(240,440);
           dormir(); label.setLocation(240,430);
           dormir(); label.setLocation(240,420);
           dormir(); label.setLocation(250,410);
           dormir(); label.setLocation(250,400);
           dormir(); label.setLocation(260,390);
           dormir(); label.setLocation(260,380);
           dormir(); label.setLocation(270,370);
           dormir(); label.setLocation(270,360);
           dormir(); label.setLocation(270,350);
           dormir(); label.setLocation(270,340);
           dormir(); label.setLocation(270,330);
           dormir(); label.setLocation(270,320);
           dormir(); label.setLocation(270,310);
           dormir(); label.setLocation(270,300);
           dormir(); label.setLocation(270,290);
           dormir(); label.setLocation(270,280);
           dormir(); label.setLocation(270,270);
           dormir(); label.setLocation(270,260);
           dormir(); label.setLocation(270,250);
           dormir(); label.setLocation(270,240);
           dormir(); label.setLocation(270,230);
           dormir(); label.setLocation(270,220);
           dormir(); label.setLocation(260,210);
           dormir(); label.setLocation(260,190);
           dormir(); label.setLocation(260,180);
           dormir(); label.setLocation(260,170);
           dormir(); label.setLocation(250,160);
           dormir(); label.setLocation(250,150);
           dormir(); label.setLocation(240,140);
           dormir(); label.setLocation(240,130);
           dormir(); label.setLocation(230,120);
           dormir(); label.setLocation(220,110);
           dormir(); label.setLocation(210,100);
           dormir(); label.setLocation(190,90);
           dormir(); label.setLocation(180,80);
           dormir(); label.setLocation(170,70);
           dormir(); label.setLocation(160,70);
           dormir(); label.setLocation(150,60);
           dormir(); label.setLocation(140,50);
           dormir(); label.setLocation(130,40);
           dormir(); label.setLocation(110,40);
           dormir(); label.setLocation(90,30);
           dormir(); label.setLocation(70,20);
           dormir(); label.setLocation(50,20);
           dormir(); label.setLocation(30,20);
           dormir();
           /***********************************/
           label.setLocation(10,20);
           dormir(); label.setLocation(0,20);
           dormir(); 
    }
}