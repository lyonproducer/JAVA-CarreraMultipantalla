/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Cliente.ConectorCliente;

import static Cliente.ConectorCliente.C1E6;
import static Cliente.ConectorCliente.C2E6;
import static Cliente.ConectorCliente.C3E6;
import static Cliente.ConectorCliente.C4E6;

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
import Simulacion.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;

/**
 *
 * @author LyonDj
 */
public class Equipo6 extends Thread{
    
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
    
     public Equipo6(String nombre, int limite, JLabel label,int pantalla, int velocidad, int probabilidadFallas){
        
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
        Equipo6.idJugador = idJugador;
        Equipo6.idEquipo=idEquipo;
    }
    

    public void movimientoDerecha(){
        
        for (int n=0; n<limite;n++){
            label.setVisible(true);
            label.setLocation(n,label.getY());
            //System.out.println(nombre + " avanza "+ label.getX());
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Equipo6.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
     public void dormir(){
        
        try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex){
                Logger.getLogger(Equipo6.class.getName()).log(Level.SEVERE, null, ex);
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
            cliente.enviarMSG("62");
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
                System.out.println("corriendo corredor 1 equipo6");
                moverCorredor1Equipo6Derecha();
                C2E6.start();//En funcion
                stop(); 
            }
                
            if(nombre=="corredor2"){
                System.out.println("corriendo corredor 2 equipo6");
                moverCorredor2Equipo6Derecha();
                //Enviar mensaje para pasar de la pantalla 3 a 2
                cliente.enviarMSG("66");
                label.setVisible(false);
            }
            
            
        }
        
        if (pantalla==4){
                
            for (int n=label.getX(); n>=10 ;n-=10){
                label.setLocation(n,label.getY());
                dormir();
            }
            label.setVisible(false);
            cliente.enviarMSG("68");
        }
        
        if (pantalla==5){
           
                if (nombre=="corredor2"){
                    //entrada
                    System.out.println("paso el corredor 2");
                    moverCorredor2Equipo6Izquierda();
                    C3E6.start();//En funcion 
                    stop();
                    }
                
                if(nombre=="corredor3"){
                    //tranferencia
                    System.out.println("paso el corredor 3");
                    moverCorredor3Equipo6Izquierda();
                    C4E6.start();//En funcion
                    stop();
                    
                }
                
                if(nombre == "corredor4"){
                    System.out.println("paso el corredor 4");
                    moverCorredor4Equipo6Izquierda();
                    cliente.enviarMSG("62"); 
                    label.setVisible(false);
                }  
        }
        
        
        yield();
    }
//        else{
//    try { 
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
    
    public void moverCorredor2Equipo6Izquierda(){
        
        dormir(); label.setLocation(370,50);
        dormir(); label.setLocation(360,50);
        dormir(); label.setLocation(352,55);
        dormir(); label.setLocation(340,50);
        dormir(); label.setLocation(320,50);
        dormir(); label.setLocation(300,60);
        dormir(); label.setLocation(280,70);
        dormir(); label.setLocation(260,50);
        dormir(); label.setLocation(260,80);
        
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
    public void moverCorredor4Equipo6Izquierda(){
        label.setLocation(270,500);
        dormir(); label.setLocation(290,510);
        dormir(); label.setLocation(300,510);
        dormir(); label.setLocation(310,520);
        dormir(); label.setLocation(320,520);
        dormir(); label.setLocation(330,520);
        dormir(); label.setLocation(340,530);
        dormir(); label.setLocation(350,530);
        dormir(); label.setLocation(360,530);
        dormir(); label.setLocation(370,530);
        dormir(); label.setLocation(380,530);
         dormir(); label.setLocation(390,530);
        dormir(); 
    }
    public void moverCorredor2Equipo6Derecha(){
           label.setLocation(70,70);
           dormir(); label.setLocation(60,60);
           dormir(); label.setLocation(50,60);
           dormir(); label.setLocation(40,60);
           dormir(); label.setLocation(30,60);
           dormir(); label.setLocation(20,50);
           dormir(); label.setLocation(10,50);
           dormir(); label.setLocation(0,50);
           dormir(); 
       }
    public void moverCorredor1Equipo6Derecha(){
        
        dormir(); label.setLocation(140,470);
        dormir(); label.setLocation(160,450);
        dormir(); label.setLocation(170,440);
        dormir(); label.setLocation(180,430);
        dormir(); label.setLocation(180,440);
        dormir(); label.setLocation(190,420);
        dormir(); label.setLocation(200,400);
        dormir(); label.setLocation(220,360);
        dormir(); label.setLocation(220,340);
        dormir(); label.setLocation(230,320);
        dormir(); label.setLocation(230,300);
        dormir(); label.setLocation(230,280);
        dormir(); label.setLocation(230,260);
        dormir(); label.setLocation(230,240);
        dormir(); label.setLocation(220,220);
        dormir(); label.setLocation(213,200);
        dormir(); label.setLocation(210,190);
        dormir(); label.setLocation(203,170);
        dormir(); label.setLocation(190,150);
        dormir(); label.setLocation(180,140);
        dormir(); label.setLocation(170,140);
        dormir(); label.setLocation(170,120);
        dormir(); label.setLocation(150,100);
        dormir(); label.setLocation(137,90);
        dormir(); label.setLocation(120,80);
        dormir(); label.setLocation(100,70);
        dormir(); label.setLocation(80,60); 
    }
}