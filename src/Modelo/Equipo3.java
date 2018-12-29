/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Cliente.ConectorCliente;
import static Cliente.ConectorCliente.C2E3;
import static Cliente.ConectorCliente.C3E2;
import static Cliente.ConectorCliente.C3E3;
import static Cliente.ConectorCliente.C4E2;
import static Cliente.ConectorCliente.C4E3;
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
import Servidor.ServidorVista;
import Simulacion.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LyonDj
 */
public class Equipo3 extends Thread{
    
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
    
     public Equipo3(String nombre, int limite, JLabel label,int pantalla, int velocidad, int probabilidadFallas){
        
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
        Equipo3.idJugador = idJugador;
        Equipo3.idEquipo=idEquipo;
    }
    public void movimientoDerecha(){
        
        for (int n=0; n<limite;n++){
            label.setVisible(true);
            label.setLocation(n,label.getY());
            //System.out.println(nombre + " avanza "+ label.getX());
            
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Equipo2.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
        
    }
      public void dormir(){
        
        try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex) {
                Logger.getLogger(Equipo3.class.getName()).log(Level.SEVERE, null, ex);
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
        //if(probabilidadFalla!=5){
        if (pantalla==1){
            //moverCcorredorOchoIzquierda();
            //movimientoDerecha();   
            cliente.enviarMSG("32"); 
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
                System.out.println("corriendo corredor 1 equipo3");
                moverCorredor1Equipo3Derecha();
                //C2E3.start();
                stop(); 
            }
                
            if(nombre=="corredor2"){
                System.out.println("corriendo corredor 2 equipo3");
                moverCorredor2Equipo3Derecha();
                //Enviar mensaje para pasar de la pantalla 3 a 2
                cliente.enviarMSG("36"); 
                label.setVisible(false);
            }
            
            
        }
        
        if (pantalla==4){
                
            for (int n=label.getX(); n>=10 ;n-=10){
                label.setLocation(n,label.getY());
                dormir();
            }
            label.setVisible(false);
            cliente.enviarMSG("38");  
        }
        
        if (pantalla==5){
           
                if (nombre=="corredor2"){
                    //entrada
                    System.out.println("paso el corredor 2");
                    moverCorredor2Equipo3Izquierda();
                    C3E3.start();
                    stop();
                    }
                
                if(nombre=="corredor3"){
                    //tranferencia
                    System.out.println("paso el corredor 3");
                    moverCorredor3Equipo3Izquierda();
                    //C4E3.start();
                    stop();
                    
                }
                
                if(nombre == "corredor4"){
                    System.out.println("paso el corredor 4");
                    moverCorredor4Equipo3Izquierda();
                    cliente.enviarMSG("32");  
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

    public void moverCorredor4Equipo3Izquierda(){
        label.setLocation(250,420);
        dormir(); label.setLocation(260,430);
        dormir(); label.setLocation(270,440);
        dormir(); label.setLocation(280,450);
        dormir(); label.setLocation(290,450);
        dormir(); label.setLocation(300,460);
        dormir(); label.setLocation(310,460);
        dormir(); label.setLocation(320,460);
        dormir(); label.setLocation(330,470);
        dormir(); label.setLocation(340,470);
        dormir(); label.setLocation(350,470);
         dormir(); label.setLocation(360,470);
         dormir(); label.setLocation(370,480);
         dormir(); label.setLocation(380,480);
         dormir(); label.setLocation(390,480);
         dormir();
    }
    public void moverCorredor3Equipo3Izquierda(){
         
     dormir(); label.setLocation(330,120);
     dormir(); label.setLocation(320,130);
     dormir(); label.setLocation(300,130);
     dormir(); label.setLocation(290,130);
     dormir(); label.setLocation(280,140);
     dormir(); label.setLocation(270,150);
     dormir(); label.setLocation(260,150);
     dormir(); label.setLocation(250,160);
     dormir(); label.setLocation(240,170);
     dormir(); label.setLocation(230,180);
     dormir(); label.setLocation(230,190);
     dormir(); label.setLocation(220,200);
     dormir(); label.setLocation(220,210);
     dormir(); label.setLocation(210,220);
     dormir(); label.setLocation(210,230);
     dormir(); label.setLocation(210,240);
     dormir(); label.setLocation(200,250);
     dormir(); label.setLocation(200,260);
     dormir(); label.setLocation(200,270);
     dormir(); label.setLocation(200,280);
     dormir(); label.setLocation(200,290);
     dormir(); label.setLocation(200,300);
     dormir(); label.setLocation(200,310);
     dormir(); label.setLocation(200,320);
     dormir(); label.setLocation(200,340);
     dormir(); label.setLocation(210,350);
     dormir(); label.setLocation(210,360);
     dormir(); label.setLocation(210,370);
     dormir(); label.setLocation(220,380);
     dormir(); label.setLocation(220,390);
     dormir(); label.setLocation(230,400);
     dormir(); label.setLocation(240,410);
     
     /**************************************/
     C4E3.start();
     dormir(); label.setLocation(250,420);
     dormir(); label.setLocation(260,430);
     dormir(); label.setLocation(270,440);
     dormir(); label.setLocation(280,450);
     dormir(); label.setLocation(290,460);
     dormir(); label.setLocation(300,460);
     dormir(); 
    }
    public void moverCorredor2Equipo3Izquierda(){
         label.setLocation(250,100);
     dormir(); label.setLocation(390,110);
     dormir(); label.setLocation(380,110);
     dormir(); label.setLocation(370,110);
     dormir(); label.setLocation(360,110);
     dormir(); label.setLocation(350,110);
     dormir();
     }
    //Derecha
    
    
    /**********************Movimientos de PANTALLA IZQUIERDA FIJOS CORREDOR3************************************/
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
            //Despues de raya
            label.setLocation(290,y+550);
            dormir();label.setLocation(310,y+560);
            dormir();label.setLocation(320,y+560);
            dormir();label.setLocation(330,y+560);
            dormir();label.setLocation(340,y+560);
            dormir();label.setLocation(350,y+560);
            dormir();label.setLocation(360,y+560);
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
     label.setLocation(290,530);
     dormir();label.setLocation(300,530);
     dormir();label.setLocation(310,540);
     dormir();label.setLocation(320,540);
     dormir();label.setLocation(330,540);
     dormir();label.setLocation(340,540);
     dormir();
    }    
    public void moverCorredor3Equipo6Izquierda(){
         label.setLocation(250,100);
     dormir(); label.setLocation(240,100);
     dormir(); label.setLocation(230,110);
     dormir(); label.setLocation(220,120);
     dormir(); label.setLocation(210,130);
     dormir(); label.setLocation(200,140);
     dormir(); label.setLocation(190,160);
     dormir(); label.setLocation(180,170);
     dormir(); label.setLocation(180,180);
     dormir(); label.setLocation(170,190);
     dormir(); label.setLocation(170,200);
     dormir(); label.setLocation(160,210);
     dormir(); label.setLocation(160,220);
     dormir(); label.setLocation(150,230);
     dormir(); label.setLocation(150,240);
     dormir(); label.setLocation(150,250);
     dormir(); label.setLocation(140,260);
     dormir(); label.setLocation(140,280);
     dormir(); label.setLocation(140,300);
     dormir(); label.setLocation(140,320);
     dormir(); label.setLocation(150,350);
     dormir(); label.setLocation(150,360);
     dormir(); label.setLocation(150,370);
     dormir(); label.setLocation(160,390);
     dormir(); label.setLocation(170,400);
     dormir(); label.setLocation(170,410);
     dormir(); label.setLocation(180,420);
     dormir(); label.setLocation(180,430);
     dormir(); label.setLocation(190,440);
     dormir(); label.setLocation(200,450);
     dormir(); label.setLocation(210,460);
     dormir(); label.setLocation(220,470);
     dormir(); label.setLocation(230,480);
     dormir(); label.setLocation(240,490);
     dormir(); label.setLocation(250,490);
     dormir(); label.setLocation(260,500);
     /************************************/
     dormir(); label.setLocation(270,510);
     dormir(); label.setLocation(280,510);
     dormir(); label.setLocation(290,520);
     dormir(); label.setLocation(300,520);
     dormir(); label.setLocation(310,530);
     dormir(); label.setLocation(320,530);
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
    public void moverCorredor3Equipo4Izquierda(){
        label.setLocation(280,100);
     dormir(); label.setLocation(300,110);
     dormir(); label.setLocation(290,120);
     dormir(); label.setLocation(280,120);
     dormir(); label.setLocation(270,130);
     dormir(); label.setLocation(280,140);
     dormir(); label.setLocation(260,150);
     dormir(); label.setLocation(250,160);
     dormir(); label.setLocation(240,170);
     dormir(); label.setLocation(230,180);
     dormir(); label.setLocation(220,190);
     dormir(); label.setLocation(210,200);
     dormir(); label.setLocation(210,210);
     dormir(); label.setLocation(200,220);
     dormir(); label.setLocation(200,123);
     dormir(); label.setLocation(190,240);
     dormir(); label.setLocation(190,250);
     dormir(); label.setLocation(190,260);
     dormir(); label.setLocation(180,270);
     dormir(); label.setLocation(180,280);
     dormir(); label.setLocation(180,290);
     dormir(); label.setLocation(180,300);
     dormir(); label.setLocation(180,320);
     dormir(); label.setLocation(180,320);
     dormir(); label.setLocation(180,330);
     dormir(); label.setLocation(180,340);
     dormir(); label.setLocation(190,350);
     dormir(); label.setLocation(190,360);
     dormir(); label.setLocation(190,370);
     dormir(); label.setLocation(200,380);
     dormir(); label.setLocation(200,390);
     dormir(); label.setLocation(210,400);
     dormir(); label.setLocation(210,410);
     dormir(); label.setLocation(220,420);
     dormir(); label.setLocation(230,430);
     dormir(); label.setLocation(240,440);
     /***********************************/
     dormir(); label.setLocation(260,460);
     dormir(); label.setLocation(270,470);
     dormir(); label.setLocation(280,480);
     dormir(); label.setLocation(300,480);
     dormir(); label.setLocation(310,490);
     dormir(); label.setLocation(330,490);
     dormir(); 
    }    

    public void moverCorredor3Equipo2Izquierda(){
         label.setLocation(350,140);
     dormir(); label.setLocation(340,140);
     dormir(); label.setLocation(330,140);
     dormir(); label.setLocation(320,150);
     dormir(); label.setLocation(310,150);
     dormir(); label.setLocation(300,160);
     dormir(); label.setLocation(290,160);
     dormir(); label.setLocation(280,170);
     dormir(); label.setLocation(270,180);
     dormir(); label.setLocation(260,190);
     dormir(); label.setLocation(250,200);
     dormir(); label.setLocation(250,210);
     dormir(); label.setLocation(240,220);
     dormir(); label.setLocation(240,230);
     dormir(); label.setLocation(230,240);
     dormir(); label.setLocation(230,250);
     dormir(); label.setLocation(220,260);
     dormir(); label.setLocation(220,270);
     dormir(); label.setLocation(220,280);
     dormir(); label.setLocation(220,290);
     dormir(); label.setLocation(220,300);
     dormir(); label.setLocation(220,320);
     dormir(); label.setLocation(220,340);
     dormir(); label.setLocation(230,350);
     dormir(); label.setLocation(230,360);
     dormir(); label.setLocation(240,370);
     dormir(); label.setLocation(240,380);
     dormir(); label.setLocation(250,390);
     /************************************/
     dormir(); label.setLocation(250,410);
     dormir(); label.setLocation(270,420);
     dormir(); label.setLocation(280,430);
     dormir(); label.setLocation(290,430);
     dormir(); label.setLocation(310,440);
     dormir(); label.setLocation(320,450);
     dormir(); label.setLocation(330,450);
     dormir();
    }
    public void moverCorredor3Equipo1Izquierda(){
    
     label.setLocation(370,150);
     dormir();label.setLocation(360,150);
     dormir();label.setLocation(350,160);
     dormir();label.setLocation(340,160);
     dormir();label.setLocation(330,160);
     dormir();label.setLocation(320,170);
     dormir();label.setLocation(310,170);
     dormir();label.setLocation(300,180);
     dormir();label.setLocation(290,180);
     dormir();label.setLocation(280,190);
     dormir();label.setLocation(270,200);
     dormir();label.setLocation(270,210);
     dormir();label.setLocation(260,220);
     dormir();label.setLocation(250,230);
     dormir();label.setLocation(250,240);
     dormir();label.setLocation(250,250);
     dormir();label.setLocation(240,260);
     dormir();label.setLocation(240,270);
     dormir();label.setLocation(240,280);
     dormir();label.setLocation(240,290);
     dormir();label.setLocation(240,300);
     dormir();label.setLocation(240,310);
     dormir();label.setLocation(240,320);
     dormir();label.setLocation(240,320);
     dormir();label.setLocation(245,320);
     dormir();label.setLocation(246,320);
     dormir();label.setLocation(250,325);
     /***************************************/
     dormir();label.setLocation(260,380);
     dormir();label.setLocation(270,390);
     dormir();label.setLocation(280,400);
     dormir();label.setLocation(290,410);
     dormir();label.setLocation(300,420);
     dormir();label.setLocation(310,430);
     dormir();
    }

    /**********************Movimientos de PANTALLA IZQUIERDA ENTRADA CORREDOR2************************************/
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
     public void moverCorredor2Equipo6Izquierda(){
        
     label.setLocation(390,60);
     dormir();
     label.setLocation(380,60);
     dormir();
     label.setLocation(370,60);
     dormir();
     label.setLocation(360,60);
     dormir();
     label.setLocation(350,60);
     dormir();
      label.setLocation(340,60);
     dormir();
     label.setLocation(330,60);
     dormir();
     label.setLocation(320,60);
     dormir();
     label.setLocation(310,70);
     dormir();
     label.setLocation(300,70);
     dormir();
     label.setLocation(290,70);
     dormir();
      label.setLocation(280,80);
     dormir();
     label.setLocation(270,80);
     dormir();
     /**********************************/
     label.setLocation(250,90);
     dormir(); label.setLocation(240,100);
     dormir(); label.setLocation(230,110);
     dormir(); label.setLocation(220,120);
     dormir(); label.setLocation(210,130);
     dormir(); label.setLocation(200,140);
     dormir(); label.setLocation(190,160);
     dormir();
     
     }
     public void moverCorredor2Equipo5Izquierda(){
     label.setLocation(390,100);
     dormir(); label.setLocation(380,70);
     dormir(); label.setLocation(370,70);
     dormir(); label.setLocation(360,70);
     dormir(); label.setLocation(350,80);
     dormir(); label.setLocation(340,80);
     dormir(); label.setLocation(330,80);
     dormir();label.setLocation(320,80);
     dormir(); label.setLocation(310,90);
     dormir(); label.setLocation(300,90);
     dormir(); 
     }

     
     public void moverCorredor2Equipo2Izquierda(){
         label.setLocation(390,130);
     dormir(); label.setLocation(380,130);
     dormir(); label.setLocation(370,130);
     dormir(); 
     }
          
    /**********************Movimientos de PANTALLA DERECHA SALIDA************************************/
    public void moverCorredor1Equipo2Derecha(){
        
        label.setLocation(34,80+342);
        dormir();
        label.setLocation(44,80+337);
        dormir();
        label.setLocation(59,80+332);
        dormir();
        label.setLocation(75,80+323);
        dormir();
        label.setLocation(86,80+312);
        dormir();
        label.setLocation(100,80+295);
        dormir();
        label.setLocation(110,80+280);
        dormir();
        label.setLocation(118,80+268);
        dormir();
        label.setLocation(120,80+250);
        dormir();
        label.setLocation(121,80+240);
        dormir();
        label.setLocation(121,80+237);
        dormir();
        label.setLocation(121,80+222);
        dormir();
        label.setLocation(125,80+208);
        dormir();
        label.setLocation(124,80+200);
        dormir();
        label.setLocation(117,80+184);
        dormir();
        label.setLocation(108,80+174);
        dormir();
        label.setLocation(105,80+158);
        dormir();
        label.setLocation(92,80+142);
        dormir();
        label.setLocation(82,80+126);
        dormir();
        label.setLocation(66,80+117);
        dormir();
        label.setLocation(45,80+105);
        dormir();
        label.setLocation(27,80+99);
        dormir();
        label.setLocation(8,80+99);
        dormir();
        
    }
    public void moverCorredor1Equipo3Derecha(){
         label.setLocation(50,470);
           dormir(); label.setLocation(60,470);
           dormir(); label.setLocation(70,460);
           dormir(); label.setLocation(80,460);
           dormir(); label.setLocation(90,450);
           dormir(); label.setLocation(100,440);
           dormir(); label.setLocation(110,430);
           dormir(); label.setLocation(120,420);
           dormir(); label.setLocation(130,410);
           dormir(); label.setLocation(140,400);
           dormir(); label.setLocation(150,390);
           dormir(); label.setLocation(160,380);
           dormir(); label.setLocation(160,370);
           dormir(); label.setLocation(170,360);
           dormir(); label.setLocation(170,350);
           dormir(); label.setLocation(170,340);
           dormir(); label.setLocation(180,330);
           dormir(); label.setLocation(180,320);
           dormir(); label.setLocation(180,310);
           dormir(); label.setLocation(180,300);
           dormir(); label.setLocation(180,290);
           dormir(); label.setLocation(180,280);
           dormir(); label.setLocation(180,270);
           dormir(); label.setLocation(180,260);
           dormir(); label.setLocation(170,250);
           dormir(); label.setLocation(170,240);
           dormir(); label.setLocation(170,230);
           dormir(); label.setLocation(160,220);
           dormir(); label.setLocation(160,210);
           dormir(); label.setLocation(150,200);
           dormir(); label.setLocation(150,190);
           dormir(); label.setLocation(140,180);
           dormir(); label.setLocation(130,170);
           dormir();
           /***********************************/
           C2E3.start();
           label.setLocation(120,160);
           dormir(); label.setLocation(110,150);
           dormir(); label.setLocation(100,140);
           dormir(); label.setLocation(90,140);
           dormir(); label.setLocation(80,130);
           dormir(); label.setLocation(70,130);
           dormir(); label.setLocation(60,120);
           dormir();

    } 
    
    /**********************Movimientos de PANTALLA DERECHA CAMBIO************************************/
    public void moverCorredor2Equipo3Derecha(){
           label.setLocation(120,160);
           dormir(); label.setLocation(110,150);
           dormir(); label.setLocation(100,140);
           dormir(); label.setLocation(90,140);
           dormir(); label.setLocation(80,130);
           dormir(); label.setLocation(70,130);
           dormir(); label.setLocation(60,120);
           dormir(); label.setLocation(50,120);
           dormir(); label.setLocation(40,120);
           dormir(); label.setLocation(30,120);
           dormir(); label.setLocation(20,110);
           dormir(); label.setLocation(10,110);
           dormir(); label.setLocation(0,110);
           dormir();
       }


}
