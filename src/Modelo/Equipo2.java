/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Cliente.ConectorCliente.C3E2;
import static Cliente.ConectorCliente.C4E2;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import static Cliente.Messenger.cliente;
import static Cliente.VCliente.jLabel2;
import static Cliente.VCliente.jLabel3;
import static Cliente.VCliente.jScrollPane1;
import static Cliente.VCliente.jTextArea1;
import Simulacion.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LyonDj
 */
public class Equipo2 extends Thread{
    
    String nombre;
    int limite;
    JLabel label;
    int pantalla;
    
    int velocidad;
    int probabilidadFalla;
    Equipo e;
    static int idJugador;
    static int idEquipo;
    static String nombreEquipo;
    
     public Equipo2(String nombre, int limite, JLabel label,int pantalla, int velocidad, int probabilidadFallas){
        
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
        Equipo2.idJugador = idJugador;
        Equipo2.idEquipo=idEquipo;
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
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Equipo2.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
        public void dormirPreZona(){
        
        try {
                Thread.sleep(velocidad+50);
            } catch (InterruptedException ex){
                Logger.getLogger(Equipo1.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    //@Override
    public void run(){
       // if(probabilidadFalla!=5){
        if (pantalla==1){
            //moverCcorredorOchoIzquierda();
            //movimientoDerecha();   
            cliente.enviarMSG("22");
             
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
                     
                     }   e.desconectar();  
       
             } catch (SQLException ex) {
                    Logger.getLogger(Equipo2.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        if (pantalla==3){
            
            if(nombre=="corredor1"){
                //Mover corredor1 equipo2
                System.out.println("corriendo corredor 1 equipo2");
                moverCorredor1Equipo2Derecha();
                cliente.enviarMSG("26");  
                label.setVisible(false);
                //C2E2.start();
                //stop(); 
            }
            
            if(nombre=="corredor2"){
                System.out.println("corriendo corredor 2 equipo3");
                //moverCorredor2Equipo2Derecha();
                //Enviar mensaje para pasar de la pantalla 3 a 2
                
            }
            
        }
        
        if (pantalla==4){
                
            for (int n=label.getX(); n>=10 ;n-=10){
                label.setLocation(n,label.getY());
                dormir();
            }
            label.setVisible(false);
            cliente.enviarMSG("28");  
        }
        
        if (pantalla==5){
           
                if (nombre=="corredor2"){
                    //entrada
                    System.out.println("paso el corredor 2");
                    moverCorredor2Equipo2Izquierda();
                    C3E2.start();
                    stop();
                    }
                
                if(nombre=="corredor3"){
                    //tranferencia
                    System.out.println("paso el corredor 3");
                    moverCorredor3Equipo2Izquierda();
                    //C4E2.start();
                    stop();
                    
                }
                
                if(nombre == "corredor4"){
                    System.out.println("paso el corredor 4");
                    moverCorredor4Equipo2Izquierda();
                    cliente.enviarMSG("22");                    
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
//        }
     
    }
  

    //Izquierda
    public void moverCorredor4Equipo2Izquierda(){
        label.setLocation(260,400);
        dormir(); label.setLocation(270,410);
        dormir(); label.setLocation(270,420);
        dormir(); label.setLocation(280,420);
        dormir(); label.setLocation(290,430);
        dormir(); label.setLocation(300,440);
        dormir(); label.setLocation(310,440);
        dormir(); label.setLocation(320,450);
        dormir(); label.setLocation(330,450);
        dormir(); label.setLocation(340,450);
        dormir(); label.setLocation(350,450);
        dormir(); label.setLocation(360,460);
        dormir(); label.setLocation(370,460);
        dormir(); label.setLocation(380,460);
        dormir(); label.setLocation(390,460);
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
     C4E2.start();
     dormir(); label.setLocation(250,410);
     dormir(); label.setLocation(270,420);
     dormir(); label.setLocation(280,430);
     dormir(); label.setLocation(290,430);
     dormir(); label.setLocation(310,440);
     dormir(); label.setLocation(320,450);
     dormir(); label.setLocation(330,450);
     dormir();
    }
    public void moverCorredor2Equipo2Izquierda(){
         label.setLocation(390,130);
     dormir(); label.setLocation(380,130);
     dormir(); label.setLocation(370,130);
     dormir(); 
     }
    
    //Derecha
    public void moverCorredor1Equipo2Derecha(){
        
        label.setLocation(40,440);
        dormir();
        label.setLocation(60,430);
        dormir();
        label.setLocation(80,420);
        dormir();
        label.setLocation(100,410);
        dormir();
        label.setLocation(110,400);
        dormir();
        label.setLocation(120,390);
        dormir();
        label.setLocation(130,370);
        dormir();
        label.setLocation(140,350);
        dormir();
        label.setLocation(150,330);
        dormir();
        label.setLocation(150,310);
        dormir();
        label.setLocation(160,390);
        dormir();
        label.setLocation(160,270);
        dormir();
        label.setLocation(150,250);
        dormir();
        label.setLocation(150,230);
        dormir();
        label.setLocation(140,210);
        dormir();
        label.setLocation(130,190);
        dormir();
        //------------------------------------------
        label.setLocation(110,170);
        dormir();
        label.setLocation(90,150);
        dormir();
        label.setLocation(70,140);
        dormir();
        
    }
    
    /**********************Movimientos de PANTALLA IZQUIERDA FIJOS CORREDOR3************************************/

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
     public void moverCorredor2Equipo4Izquierda(){
         label.setLocation(390,90);
     dormir(); label.setLocation(380,90);
     dormir(); label.setLocation(370, 90);
     dormir(); label.setLocation(360,90);
     dormir(); label.setLocation(350,100);
     dormir(); label.setLocation(340,100);
     dormir(); label.setLocation(330,100);
     dormir();label.setLocation(332,100);
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

          
    /**********************Movimientos de PANTALLA DERECHA SALIDA************************************/
    
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
