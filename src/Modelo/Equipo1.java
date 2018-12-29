/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Cliente.ConectorCliente;

import static Cliente.ConectorCliente.C1E1;
import static Cliente.ConectorCliente.C2E1;
import static Cliente.ConectorCliente.C3E1;
import static Cliente.ConectorCliente.C4E1;

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
public class Equipo1 extends Thread{
    
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
    
     public Equipo1(String nombre, int limite, JLabel label,int pantalla, int velocidad, int probabilidadFallas){
        
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
        Equipo1.idJugador = idJugador;
        Equipo1.idEquipo=idEquipo;
    }
    
    public void movimientoDerecha(){
        
        for (int n=0; n<limite;n++){
            label.setVisible(true);
            label.setLocation(n,label.getY());
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Equipo1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
     public void dormir(){
        
        try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex){
                Logger.getLogger(Equipo1.class.getName()).log(Level.SEVERE, null, ex);
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
        
     //   if(probabilidadFalla!=5){
        if (pantalla==1){
            //moverCcorredorOchoIzquierda();
            //movimientoDerecha();   
            cliente.enviarMSG("12");
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
                     
                     }    e.desconectar(); 
       
             } catch (SQLException ex) {
                    Logger.getLogger(Equipo2.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        if (pantalla==3){
            
            if(nombre=="corredor1"){
                //Mover corredor1 equipo2
                System.out.println("corriendo corredor 1 equipo6");
                moverCorredor1Equipo1Derecha();
                C2E1.start();//En funcion
                stop(); 
            }
                
            if(nombre=="corredor2"){
                System.out.println("corriendo corredor 2 equipo6");
                moverCorredor2Equipo1Derecha();
                //Enviar mensaje para pasar de la pantalla 3 a 2
                cliente.enviarMSG("16");
                label.setVisible(false);
            }
            
        }
        
        if (pantalla==4){
                
            for (int n=label.getX(); n>=10 ;n-=10){
                label.setLocation(n,label.getY());
                dormir();
            }
            label.setVisible(false);
            cliente.enviarMSG("18");
        }
        
        if (pantalla==5){
           
                if (nombre=="corredor2"){
                    //entrada
                    System.out.println("paso el corredor 2");
                    moverCorredor2Equipo1Izquierda();
                    //C3E1.start();//En funcion 
                    stop();
                    }
                
                if(nombre=="corredor3"){
                    //tranferencia
                    System.out.println("paso el corredor 3");
                    moverCorredor3Equipo1Izquierda();
                    //En funcion
                    stop();
                    
                }
                
                if(nombre == "corredor4"){
                    System.out.println("paso el corredor 4");
                    moverCorredor4Equipo1Izquierda();
                    cliente.enviarMSG("12"); 
                    label.setVisible(false);
                }  
        }
        
        
        yield();
//    }else{
//        try { 
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
//        
//    }
    }
    
    public void moverCorredor4Equipo1Izquierda(){
        label.setLocation(260,370);
        dormir();
        label.setLocation(270,380);
        dormir();
        label.setLocation(280,390);
        dormir();
        label.setLocation(290,400);
        dormir();
        label.setLocation(310,410);
        dormir();
        label.setLocation(330,420);
        dormir();
        label.setLocation(340,420);
        dormir();
        label.setLocation(360,430);
        dormir();
        label.setLocation(380,430);
        dormir();
        
    }
    
    public void moverCorredor3Equipo1Izquierda(){
        label.setLocation(370,140);
        dormir();
        label.setLocation(350,140);
        dormir();
        label.setLocation(330,150);
        dormir();
        label.setLocation(310,160);
        dormir();
        label.setLocation(290,170);
        dormir();
        label.setLocation(270,180);
        dormir();
        label.setLocation(260,200);
        dormir();
        label.setLocation(250,220);
        dormir();
        label.setLocation(240,240);
        dormir();
        label.setLocation(230,280);
        dormir();
        label.setLocation(240,320);
        dormir();
        label.setLocation(250,340);
        dormir();
        //------------------------
        label.setLocation(270,380);
        dormir();
        C4E1.start();
        
        
    }
    
    public void moverCorredor2Equipo1Izquierda(){
        C3E1.start();
        label.setLocation(380,140);
        dormir();
        label.setLocation(360,140);
        dormir();
        label.setLocation(350,140);
        dormir();
        label.setLocation(340,140);
        dormir();
        label.setLocation(330,150);
        dormir();
        
    }
    
    public void moverCorredor1Equipo1Derecha(){
        
        label.setLocation(20,425);
        dormir();
        label.setLocation(40,420);
        dormir();
        label.setLocation(60,410);
        dormir();
        label.setLocation(80,400);
        dormir();
        label.setLocation(100,380);
        dormir();
        label.setLocation(115,360);
        dormir();
        label.setLocation(130,330);
        dormir();
        label.setLocation(129,310);
        dormir();
        label.setLocation(135,290);
        dormir();
        label.setLocation(138,280);
        dormir();
        label.setLocation(135,270);
        dormir();
        label.setLocation(130,250);
        dormir();
        label.setLocation(130,240);
        dormir();
        label.setLocation(120,220);
        dormir();  
    }
    
    public void moverCorredor2Equipo1Derecha(){
        
        label.setLocation(90,180);
        dormir(); 
        label.setLocation(80,170);
        dormir(); 
        label.setLocation(60,160);
        dormir();
        label.setLocation(10,140);
        dormir(); 

    }
   
    
}