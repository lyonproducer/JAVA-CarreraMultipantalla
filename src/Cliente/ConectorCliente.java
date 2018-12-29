
package Cliente;

import java.net.*;
import java.io.*;

import Modelo.Equipo1;
import Modelo.Equipo2;
import Modelo.Equipo3;
import Modelo.Equipo4;
import Modelo.Equipo5;
import Modelo.Equipo6;
import Modelo.Equipo7;
import Modelo.Equipo8;


import static Cliente.VCliente.jLabelCorredor4Equipo1;
import static Cliente.VCliente.jLabelCorredor2Equipo1;
import static Cliente.VCliente.jLabelCorredor3Equipo1;
import static Cliente.VCliente.jLabelCorredor1Equipo1;

import static Cliente.VCliente.jLabelCorredor4Equipo2;
import static Cliente.VCliente.jLabelCorredor3Equipo2;
import static Cliente.VCliente.jLabelCorredor2Equipo2;
import static Cliente.VCliente.jLabelCorredor1Equipo2;

import static Cliente.VCliente.jLabelCorredor1Equipo3;
import static Cliente.VCliente.jLabelCorredor2Equipo3;
import static Cliente.VCliente.jLabelCorredor3Equipo3;
import static Cliente.VCliente.jLabelCorredor4Equipo3;

import static Cliente.VCliente.jLabelCorredor4Equipo4;
import static Cliente.VCliente.jLabelCorredor2Equipo4;
import static Cliente.VCliente.jLabelCorredor3Equipo4;
import static Cliente.VCliente.jLabelCorredor1Equipo4;

import static Cliente.VCliente.jLabelCorredor4Equipo5;
import static Cliente.VCliente.jLabelCorredor2Equipo5;
import static Cliente.VCliente.jLabelCorredor3Equipo5;
import static Cliente.VCliente.jLabelCorredor1Equipo5;

import static Cliente.VCliente.jLabelCorredor4Equipo6;
import static Cliente.VCliente.jLabelCorredor2Equipo6;
import static Cliente.VCliente.jLabelCorredor3Equipo6;
import static Cliente.VCliente.jLabelCorredor1Equipo6;

import static Cliente.VCliente.jLabelCorredor1Equipo7;
import static Cliente.VCliente.jLabelCorredor2Equipo7;
import static Cliente.VCliente.jLabelCorredor3Equipo7;
import static Cliente.VCliente.jLabelCorredor4Equipo7;

import static Cliente.VCliente.jLabelCorredor1Equipo8;
import static Cliente.VCliente.jLabelCorredor2Equipo8;
import static Cliente.VCliente.jLabelCorredor3Equipo8;
import static Cliente.VCliente.jLabelCorredor4Equipo8;
import Simulacion.Equipo;
import Simulacion.Jugada;
import Simulacion.Jugadores;
import Simulacion.Randam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectorCliente extends Thread{
  
    private Socket socket;
    private ServerSocket serverSocket;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 10578;
    

    public static Equipo1 C1E1,C2E1,C3E1, C4E1;
    public static Equipo2 C1E2,C2E2,C3E2, C4E2;
    public static Equipo3 C1E3,C2E3,C3E3, C4E3;
    public static Equipo4 C1E4,C2E4,C3E4, C4E4;
    public static Equipo5 C1E5,C2E5,C3E5, C4E5; 
    public static Equipo6 C1E6,C2E6,C3E6, C4E6;
    public static Equipo7 C1E7,C2E7,C3E7, C4E7;
    public static Equipo8 C1E8,C2E8,C3E8, C4E8;


    public static Jugada Jugada;
    public static Jugadores Jugadores;
    public static Equipo Equipo;
    public static Randam r;
    
    
    public static int idEquipo1;
    public static int idEquipo2;
    public static int idEquipo3;
    public static int idEquipo4;
    public static int idEquipo5;
    public static int idEquipo6;
    public static int idEquipo7;
    public static int idEquipo8;
    public static int idJugada;

    private static String nombre;
    private static String usuario;
    private static String clave;
    private static String IPbd;
    private static String puertobd;
    //public static Equipo4 C1E4,C2E4,C3E4, C4E4;

    /**
     *
     * @param ip
     * @param bd
     * @param usuario
     * @param contrasena
     * @param ipBD
     * @param puerto
     */
    public ConectorCliente(String ip, String bd, String usuario, String contrasena, String ipBD, String puerto) {
        try {
            ConectorCliente.nombre = bd;
            ConectorCliente.usuario = usuario;
            ConectorCliente.clave = contrasena;
            ConectorCliente.IPbd = ipBD;
            ConectorCliente.puertobd = puerto;

            Equipo = new Equipo(nombre, usuario, clave, IPbd, puertobd);
            Jugadores = new Jugadores(nombre, usuario, clave, IPbd, puertobd);
            Jugada = new Jugada(nombre, usuario, clave, IPbd, puertobd);
            r = new Randam();
            
            Jugadores.conectar();

            int equipo[] = new int[8];
            int i = 0;
            Jugada.conectar();
            //obtener id de ultima jugada
            ResultSet resultSet = Jugada.RealizarConsulta(Jugada.consultarTUltimo());
            while (resultSet.next()) {
                ConectorCliente.idJugada = resultSet.getInt(1);
                System.err.println(ConectorCliente.idJugada);

            }
            Equipo.conectar();
            //obtener id 
            ResultSet result = Equipo.RealizarConsulta(Jugada.consultarEquipo(idJugada));
            while (result.next()) {
                equipo[i] = result.getInt(1);
                System.err.println(equipo[i]);
                i++;
            }

            ConectorCliente.idEquipo1 = equipo[0];
            ConectorCliente.idEquipo2 = equipo[1];
            ConectorCliente.idEquipo3 = equipo[2];
            ConectorCliente.idEquipo4 = equipo[3];
            ConectorCliente.idEquipo5 = equipo[4];
            ConectorCliente.idEquipo6 = equipo[5];
            ConectorCliente.idEquipo7 = equipo[6];
            ConectorCliente.idEquipo8 = equipo[7];

            this.socket = new Socket(ip, this.puerto);
            this.entradaSocket = new InputStreamReader(socket.getInputStream());
            this.entrada = new BufferedReader(entradaSocket);

            this.salida = new DataOutputStream(socket.getOutputStream());
            //this.salida.writeUTF("Conectado\n");
            this.salida.writeBytes("Conectado\n");
            //VCliente.jTextArea1.append("Conectado al servidor");

        } catch (IOException e) {
            System.err.println("Error al conectar cliente");
        } catch (SQLException ex) {
            Logger.getLogger(ConectorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
  public void mensajesEquipo1(String texto) throws SQLException{
        if(texto.equals("11")){

         int velocidad, idJugador;
            String probabilidad;
            Equipo.conectar();
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 1, idJugada, idEquipo1));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				 System.out.println("\nse Recibio 11");
           		 C3E1 = new Equipo1("corredor3",220,jLabelCorredor3Equipo1,1, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	 C3E1.start();
            	 Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);
            }
        }
        
        if(texto.equals("13")){
            
			Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 1, idJugada, idEquipo1));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				//Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo1.setVisible(true);
	            jLabelCorredor4Equipo1.setLocation(10, 425);
	            C4E1 = new Equipo1("corredor4", 350,jLabelCorredor4Equipo1,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E1.start();
	            C4E1.setE(Equipo);
                Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);

            }
        }
        
        if(texto.equals("15")){
            
            //Corre Corredor 1 Equipo 2 de la pantalla 3 a 2
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 1, idJugada, idEquipo1));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
		C1E1 = new Equipo1("corredor1",220,jLabelCorredor1Equipo1,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);
            }
             Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 1, idJugada, idEquipo1));
            while (resultSet2.next()) {
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
		C2E1 = new Equipo1("corredor2",220,jLabelCorredor2Equipo1,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C1E1.start();
                Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);
        }
        
        }if(texto.equals("17")){

            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 1, idJugada, idEquipo1));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				System.out.println("se recibio 17");
	            //Corre hacia la izquierda de 3 a 2
	            jLabelCorredor2Equipo1.setVisible(true);
	            jLabelCorredor2Equipo1.setLocation(350, 135);
	            C2E1 = new Equipo1("corredor2",300,jLabelCorredor2Equipo1,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C2E1.start();
                
                Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);
            }
        }
        
        if(texto.equals("19")){
            System.out.println("se Recibio 19");
            //corre hacia la izquierda a la derecha curva pantalla 1             
            jLabelCorredor2Equipo1.setVisible(true);
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 1, idJugada, idEquipo1));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                C2E1 = new Equipo1("corredor2",350,jLabelCorredor2Equipo1,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);
            }
            Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 1, idJugada, idEquipo1));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                 C3E1 = new Equipo1("corredor3",350,jLabelCorredor3Equipo1,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
           		 Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);
            }
            Equipo.conectar();
            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 1, idJugada, idEquipo1));
            while (resultSet3.next()) { //Creando las filas para el JTable
                idJugador = resultSet3.getInt(1);
                velocidad = resultSet3.getInt(2);
                probabilidad = resultSet3.getString(3);
                C4E1 = new Equipo1("corredor4",220,jLabelCorredor4Equipo1,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
           		 C2E1.start();
               
                Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo1);
            }
        }
    } 

    
public void mensajesEquipo2(String texto) throws SQLException{
        if(texto.equals("21")){

            int velocidad, idJugador;
            String probabilidad;
            Equipo.conectar();
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 2, idJugada, idEquipo2));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				System.out.println("\nse Recibio 21");
				C3E2 = new Equipo2("corredor3",220,jLabelCorredor3Equipo2,1, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C3E2.start();

               
                Equipo1.setIdJugadorIdEquipo(idJugador, idEquipo2);
            }
        }
        
        if(texto.equals("23")){
          Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 2, idJugada, idEquipo2));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
 				   //Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo2.setVisible(true);
	            jLabelCorredor4Equipo2.setLocation(10, 448);
	            C4E2 = new Equipo2("corredor4", 350,jLabelCorredor4Equipo2,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E2.start();
                
                C4E2.setE(Equipo);
                Equipo2.setIdJugadorIdEquipo(idJugador, idEquipo2);
            }
        }
        
        if(texto.equals("25")){
            
            
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 2, idJugada, idEquipo2));
            while (resultSet.next()) { 
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                C1E2 = new Equipo2("corredor1",220,jLabelCorredor1Equipo2,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C1E2.start();
                
                Equipo2.setIdJugadorIdEquipo(idJugador, idEquipo2);
            }
             
        }
        
        if(texto.equals("27")){
            System.out.println("se recibio 27");
            //Corre hacia la izquierda de 3 a 2
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 2, idJugada, idEquipo2));
            while (resultSet.next()) { 
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				jLabelCorredor2Equipo2.setVisible(true);
	            jLabelCorredor2Equipo2.setLocation(350, 118);
	            C2E2 = new Equipo2("corredor2",300,jLabelCorredor2Equipo2,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C2E2.start();
                
                Equipo2.setIdJugadorIdEquipo(idJugador, idEquipo2);
            }

        }
        
        if(texto.equals("29")){
            System.out.println("se Recibio 29");
            //corre hacia la izquierda a la derecha curva pantalla 1             
            jLabelCorredor2Equipo2.setVisible(true);
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 2, idJugada, idEquipo2));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                C2E2 = new Equipo2("corredor2",350,jLabelCorredor2Equipo2,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

                Equipo2.setIdJugadorIdEquipo(idJugador, idEquipo2);
            }
            Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 2, idJugada, idEquipo2));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                C3E2 = new Equipo2("corredor3",350,jLabelCorredor3Equipo2,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

                Equipo2.setIdJugadorIdEquipo(idJugador, idEquipo2);
            }
            Equipo.conectar();
            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 2, idJugada, idEquipo2));
            while (resultSet3.next()) { //Creando las filas para el JTable
                idJugador = resultSet3.getInt(1);
                velocidad = resultSet3.getInt(2);
                probabilidad = resultSet3.getString(3);
               C4E2 = new Equipo2("corredor4",220,jLabelCorredor4Equipo2,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            C2E2.start();
               
                Equipo2.setIdJugadorIdEquipo(idJugador, idEquipo2);
            }
        }  
    } 
    
    public void mensajesEquipo3(String texto) throws SQLException{
        if(texto.equals("33")){
       		Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 3, idJugada, idEquipo3));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
 				 //Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo3.setLocation(10, 460);
	            C4E3 = new Equipo3("corredor4", 350,jLabelCorredor4Equipo3,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E3.start();
                C4E3.setE(Equipo);
                Equipo3.setIdJugadorIdEquipo(idJugador, idEquipo3);
            }
        }

        if(texto.equals("35")){
            //Corre Corredor 1 Equipo 3 de la pantalla 3 a 2
          
           
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 3, idJugada, idEquipo3));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
 				 C1E3 = new Equipo3("corredor1",220,jLabelCorredor1Equipo3,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo3.setIdJugadorIdEquipo(idJugador, idEquipo3);
            }
             Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 3, idJugada, idEquipo3));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
 				C2E3 = new Equipo3("corredor2",220,jLabelCorredor2Equipo3,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C1E3.start();
                Equipo3.setIdJugadorIdEquipo(idJugador, idEquipo3);
            }
        }

        if(texto.equals("37")){
			System.out.println("se recibio 37");
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 3, idJugada, idEquipo3));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				//Corre hacia la izquierda de 3 a 2
            	jLabelCorredor2Equipo3.setVisible(true);
            	jLabelCorredor2Equipo3.setLocation(350, 100);
            	C2E3 = new Equipo3("corredor2",300,jLabelCorredor2Equipo3,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C2E3.start();
                Equipo3.setIdJugadorIdEquipo(idJugador, idEquipo3);
            }
        }

        if(texto.equals("39")){
            System.out.println("\nse Recibio 39");
            //corre hacia la izquierda a la derecha curva pantalla 1             
            jLabelCorredor2Equipo3.setVisible(true);
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 3, idJugada, idEquipo3));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                 C2E3 = new Equipo3("corredor2",350,jLabelCorredor2Equipo3,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo3.setIdJugadorIdEquipo(idJugador, idEquipo3);
            }
            Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 3, idJugada, idEquipo3));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                 C3E3 = new Equipo3("corredor3",350,jLabelCorredor3Equipo3,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo3.setIdJugadorIdEquipo(idJugador, idEquipo3);
            }
            Equipo.conectar();
            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 3, idJugada, idEquipo3));
            while (resultSet3.next()) { //Creando las filas para el JTable
                idJugador = resultSet3.getInt(1);
                velocidad = resultSet3.getInt(2);
                probabilidad = resultSet3.getString(3);
                C4E3 = new Equipo3("corredor4",220,jLabelCorredor4Equipo3,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C2E3.start();               
                Equipo3.setIdJugadorIdEquipo(idJugador, idEquipo3);
            }
        }   
    }
    
    public void mensajesEquipo4(String texto) throws SQLException{
        
        if(texto.equals("43")){
            	 Equipo.conectar();
	            int velocidad, idJugador;
	            String probabilidad;
	            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 4, idJugada, idEquipo4));
	            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);

                //Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo4.setVisible(true);
	            jLabelCorredor4Equipo4.setLocation(10, 480);
	            C4E4 = new Equipo4("corredor4", 350,jLabelCorredor4Equipo4,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E4.start();
                C4E4.setE(Equipo);
                Equipo4.setIdJugadorIdEquipo(idJugador, idEquipo4);

            }
        }

        if(texto.equals("45")){
            //Corre Corredor 1 Equipo 2 de la pantalla 3 a 2
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 4, idJugada, idEquipo4));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				C1E4 = new Equipo4("corredor1",220,jLabelCorredor1Equipo4,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                
                Equipo4.setIdJugadorIdEquipo(idJugador, idEquipo1);
            }
             Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 4, idJugada, idEquipo4));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);

                 C2E4 = new Equipo4("corredor2",220,jLabelCorredor2Equipo4,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C1E4.start();
                Equipo4.setIdJugadorIdEquipo(idJugador, idEquipo4);
            }
        }
  
        if(texto.equals("47")){
            System.out.println("se recibio 47");
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 4, idJugada, idEquipo4));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                //Corre hacia la izquierda de 3 a 2
	            jLabelCorredor2Equipo4.setVisible(true);
	            jLabelCorredor2Equipo4.setLocation(350, 80);
	            C2E4 = new Equipo4("corredor2",300,jLabelCorredor2Equipo4,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C2E4.start();
                Equipo4.setIdJugadorIdEquipo(idJugador, idEquipo4);
            }
        }

        if(texto.equals("49")){
            System.out.println("\nse Recibio 49");
            //corre hacia la izquierda a la derecha curva pantalla 1             
            jLabelCorredor2Equipo4.setVisible(true);
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 4, idJugada, idEquipo4));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                C2E4 = new Equipo4("corredor2",350,jLabelCorredor2Equipo4,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo4.setIdJugadorIdEquipo(idJugador, idEquipo4);
            }
            Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 4, idJugada, idEquipo4));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                C3E4 = new Equipo4("corredor3",350,jLabelCorredor3Equipo4,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo4.setIdJugadorIdEquipo(idJugador, idEquipo4);
            }
            Equipo.conectar();
            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 4, idJugada, idEquipo4));
            while (resultSet3.next()) { //Creando las filas para el JTable
                idJugador = resultSet3.getInt(1);
                velocidad = resultSet3.getInt(2);
                probabilidad = resultSet3.getString(3);
               C4E4 = new Equipo4("corredor4",220,jLabelCorredor4Equipo4,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            C2E4.start();
                Equipo4.setIdJugadorIdEquipo(idJugador, idEquipo4);
            }
        }    
    }

    public void mensajesEquipo5(String texto) throws SQLException{
        
        if(texto.equals("53")){
         Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 5, idJugada, idEquipo5));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				//Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo5.setVisible(true);
	            jLabelCorredor4Equipo5.setLocation(10, 500);
	            C4E5 = new Equipo5("corredor4", 350,jLabelCorredor4Equipo5,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E5.start();
                
                C4E5.setE(Equipo);
                Equipo5.setIdJugadorIdEquipo(idJugador, idEquipo5);

            }
        }

        if(texto.equals("55")){
            //Corre Corredor 1 Equipo 4 de la pantalla 3 a 2

            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 5, idJugada, idEquipo5));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);

                C1E5 = new Equipo5("corredor1",220,jLabelCorredor1Equipo5,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
           
                Equipo5.setIdJugadorIdEquipo(idJugador, idEquipo5);
            }
             Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 5, idJugada, idEquipo5));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
				 C2E5 = new Equipo5("corredor2",220,jLabelCorredor2Equipo5,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C1E5.start();
                
                Equipo5.setIdJugadorIdEquipo(idJugador, idEquipo5);
            }
        }
  
        if(texto.equals("57")){ 
            System.out.println("se recibio 17");
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 5, idJugada, idEquipo5));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				//Corre hacia la izquierda de 3 a 2
	            jLabelCorredor2Equipo5.setVisible(true);
	            jLabelCorredor2Equipo5.setLocation(350, 60);
	            C2E5 = new Equipo5("corredor2",300,jLabelCorredor2Equipo5,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C2E5.start();                
                Equipo5.setIdJugadorIdEquipo(idJugador, idEquipo5);
            }
        }

        if(texto.equals("59")){
            System.out.println("\nse Recibio 59");
            //corre hacia la izquierda a la derecha curva pantalla 1             
            jLabelCorredor2Equipo5.setVisible(true);
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 5, idJugada, idEquipo5));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                 C2E5 = new Equipo5("corredor2",350,jLabelCorredor2Equipo5,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

                Equipo5.setIdJugadorIdEquipo(idJugador, idEquipo5);
            }
            Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 5, idJugada, idEquipo5));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                 C3E5 = new Equipo5("corredor3",350,jLabelCorredor3Equipo5,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

                Equipo5.setIdJugadorIdEquipo(idJugador, idEquipo5);
            }
            Equipo.conectar();
            
            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 5, idJugada, idEquipo5));
            while (resultSet3.next()) { //Creando las filas para el JTable
                idJugador = resultSet3.getInt(1);
                velocidad = resultSet3.getInt(2);
                probabilidad = resultSet3.getString(3);
                 C4E5 = new Equipo5("corredor4",220,jLabelCorredor4Equipo5,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
           		 C2E5.start();
               
                Equipo5.setIdJugadorIdEquipo(idJugador, idEquipo5);
            }
        }    
    }
    
    public void mensajesEquipo6(String texto) throws SQLException{
        
        if(texto.equals("63")){
           Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 6, idJugada, idEquipo6));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);

                //Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo6.setVisible(true);
	            jLabelCorredor4Equipo6.setLocation(10, 521);
	            C4E6 = new Equipo6("corredor4", 350,jLabelCorredor4Equipo6,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E6.start();
                C4E6.setE(Equipo);
                Equipo6.setIdJugadorIdEquipo(idJugador, idEquipo6);

            }
        }

        if(texto.equals("65")){
            //Corre Corredor 1 Equipo 4 de la pantalla 3 a 2
           
            
            //Corre Corredor 1 Equipo 2 de la pantalla 3 a 2
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 6, idJugada, idEquipo6));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                 C1E6 = new Equipo6("corredor1",220,jLabelCorredor1Equipo6,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo6.setIdJugadorIdEquipo(idJugador, idEquipo6);
            }
             Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 6, idJugada, idEquipo6));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                C2E6 = new Equipo6("corredor2",220,jLabelCorredor2Equipo6,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C1E6.start();
                Equipo6.setIdJugadorIdEquipo(idJugador, idEquipo6);
            }
        }
  
        if(texto.equals("67")){
            System.out.println("se recibio 67");
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 6, idJugada, idEquipo6));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				//Corre hacia la izquierda de 3 a 2
	            jLabelCorredor2Equipo6.setVisible(true);
	            jLabelCorredor2Equipo6.setLocation(350, 80);
	            C2E6 = new Equipo6("corredor2",300,jLabelCorredor2Equipo6,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C2E6.start();
                Equipo6.setIdJugadorIdEquipo(idJugador, idEquipo6);
            }
        }

        if(texto.equals("69")){
            System.out.println("\nse Recibio 59");
            //corre hacia la izquierda a la derecha curva pantalla 1             
            jLabelCorredor2Equipo6.setVisible(true);
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 6, idJugada, idEquipo6));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                 C2E6 = new Equipo6("corredor2",350,jLabelCorredor2Equipo6,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
                Equipo6.setIdJugadorIdEquipo(idJugador, idEquipo6);
            }
            Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 6, idJugada, idEquipo6));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                C3E6 = new Equipo6("corredor3",350,jLabelCorredor3Equipo6,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

                Equipo6.setIdJugadorIdEquipo(idJugador, idEquipo6);
            }
            Equipo.conectar();
            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 6, idJugada, idEquipo6));
            while (resultSet3.next()) { //Creando las filas para el JTable
                idJugador = resultSet3.getInt(1);
                velocidad = resultSet3.getInt(2);
                probabilidad = resultSet3.getString(3);
               
               C4E6 = new Equipo6("corredor4",220,jLabelCorredor4Equipo6,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            C2E6.start();
                Equipo6.setIdJugadorIdEquipo(idJugador, idEquipo6);
            }
        }    
    }

    public void mensajesEquipo7(String texto) throws SQLException{
        
        if(texto.equals("73")){
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 7, idJugada, idEquipo7));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);

	            //Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo7.setVisible(true);
	            jLabelCorredor4Equipo7.setLocation(10, 537);
	            C4E7 = new Equipo7("corredor4", 350,jLabelCorredor4Equipo7,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E7.start();
                C4E7.setE(Equipo);
                Equipo7.setIdJugadorIdEquipo(idJugador, idEquipo7);

            }
        }

        if(texto.equals("75")){
            //Corre Corredor 1 Equipo 4 de la pantalla 3 a 2
            

            //Corre Corredor 1 Equipo 2 de la pantalla 3 a 2
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 7, idJugada, idEquipo7));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);

                C1E7 = new Equipo7("corredor1",220,jLabelCorredor1Equipo7,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
           
                Equipo7.setIdJugadorIdEquipo(idJugador, idEquipo7);
            }
             Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 7, idJugada, idEquipo7));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
				 C2E7 = new Equipo7("corredor2",220,jLabelCorredor2Equipo7,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            	C1E7.start();
                
                Equipo7.setIdJugadorIdEquipo(idJugador, idEquipo7);
            }
        }
  
        if(texto.equals("77")){
            

            System.out.println("se recibio 17");
            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 7, idJugada, idEquipo7));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				//Corre hacia la izquierda de 3 a 2
	            jLabelCorredor2Equipo7.setVisible(true);
	            jLabelCorredor2Equipo7.setLocation(350, 30);
	            C2E7 = new Equipo7("corredor2",300,jLabelCorredor2Equipo7,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C2E7.start();
                
                Equipo7.setIdJugadorIdEquipo(idJugador, idEquipo7);
            }
         }

        if(texto.equals("79")){
            System.out.println("\nse Recibio 79");
            //corre hacia la izquierda a la derecha curva pantalla 1             
            jLabelCorredor2Equipo7.setVisible(true);
            
            

            Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 7, idJugada, idEquipo7));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
                C2E7 = new Equipo7("corredor2",350,jLabelCorredor2Equipo7,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

                Equipo7.setIdJugadorIdEquipo(idJugador, idEquipo7);
            }
            Equipo.conectar();
            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 7, idJugada, idEquipo7));
            while (resultSet2.next()) { //Creando las filas para el JTable
                idJugador = resultSet2.getInt(1);
                velocidad = resultSet2.getInt(2);
                probabilidad = resultSet2.getString(3);
                C3E7 = new Equipo7("corredor3",350,jLabelCorredor3Equipo7,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

                Equipo7.setIdJugadorIdEquipo(idJugador, idEquipo7);
            }
            Equipo.conectar();
            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 7, idJugada, idEquipo7));
            while (resultSet3.next()) { //Creando las filas para el JTable
                idJugador = resultSet3.getInt(1);
                velocidad = resultSet3.getInt(2);
                probabilidad = resultSet3.getString(3);
                 C4E7 = new Equipo7("corredor4",220,jLabelCorredor4Equipo7,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
            C2E7.start();
               
                Equipo7.setIdJugadorIdEquipo(idJugador, idEquipo7);
            }
        }
    }

     public void mensajesEquipo8(String texto) throws SQLException{     
         if(texto.equals("83")){
         	Equipo.conectar();
            int velocidad, idJugador;
            String probabilidad;
            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 8, idJugada, idEquipo8));
            while (resultSet.next()) { //Creando las filas para el JTable
                idJugador = resultSet.getInt(1);
                velocidad = resultSet.getInt(2);
                probabilidad = resultSet.getString(3);
				//Corre hacia a la derecha de pantalla 1 a 2
	            jLabelCorredor4Equipo8.setVisible(true);
	            jLabelCorredor4Equipo8.setLocation(10, 555);
	            C4E8 = new Equipo8("corredor4", 350,jLabelCorredor4Equipo8,2, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C4E8.start();
               
                C4E8.setE(Equipo);
                Equipo8.setIdJugadorIdEquipo(idJugador, idEquipo8);
	        }
         }

	        if(texto.equals("85")){
	            //Corre Corredor 1 Equipo 4 de la pantalla 3 a 2
	          
	             Equipo.conectar();
	            int velocidad, idJugador;
	            String probabilidad;
	            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 8, idJugada, idEquipo8));
	            while (resultSet.next()) { //Creando las filas para el JTable
	                idJugador = resultSet.getInt(1);
	                velocidad = resultSet.getInt(2);
	                probabilidad = resultSet.getString(3);
					 System.out.println("Entro en 85");
	            C1E8 = new Equipo8("corredor1",220,jLabelCorredor1Equipo8,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            
	              
	                Equipo8.setIdJugadorIdEquipo(idJugador, idEquipo8);
	            }
	             Equipo.conectar();
	            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(1, 8, idJugada, idEquipo8));
	            while (resultSet2.next()) { //Creando las filas para el JTable
	                idJugador = resultSet2.getInt(1);
	                velocidad = resultSet2.getInt(2);
	                probabilidad = resultSet2.getString(3);
					 C2E8 = new Equipo8("corredor2",220,jLabelCorredor2Equipo8,3, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            C1E8.start();

	                Equipo8.setIdJugadorIdEquipo(idJugador, idEquipo8);
                    }
	        }
	  
	        if(texto.equals("87")){
	            //Corre hacia la izquierda de 3 a 2
				 System.out.println("se recibio 87");
	            Equipo.conectar();
	            int velocidad, idJugador;
	            String probabilidad;
	            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 8, idJugada, idEquipo8));
	            while (resultSet.next()) { //Creando las filas para el JTable
	                idJugador = resultSet.getInt(1);
	                velocidad = resultSet.getInt(2);
	                probabilidad = resultSet.getString(3);

		            jLabelCorredor2Equipo8.setVisible(true);
		            jLabelCorredor2Equipo8.setLocation(350, 8);
		            C2E8 = new Equipo8("corredor2",300,jLabelCorredor2Equipo8,4, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
		            C2E8.start();
	                Equipo8.setIdJugadorIdEquipo(idJugador, idEquipo8);
	            }
	         }

	        if(texto.equals("89")){
	            System.out.println("\nse Recibio 89");
	            //corre hacia la izquierda a la derecha curva pantalla 1             
	            jLabelCorredor2Equipo8.setVisible(true);
	            
	            Equipo.conectar();
	            int velocidad, idJugador;
	            String probabilidad;
	            ResultSet resultSet = Equipo.RealizarConsulta(Jugada.consultarVelJugador(2, 8, idJugada, idEquipo8));
	            while (resultSet.next()) { //Creando las filas para el JTable
	                idJugador = resultSet.getInt(1);
	                velocidad = resultSet.getInt(2);
	                probabilidad = resultSet.getString(3);
	                C2E8 = new Equipo8("corredor2",350,jLabelCorredor2Equipo8,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

	                Equipo8.setIdJugadorIdEquipo(idJugador, idEquipo8);
	            }
	            Equipo.conectar();
	            ResultSet resultSet2 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(3, 8, idJugada, idEquipo8));
	            while (resultSet2.next()) { //Creando las filas para el JTable
	                idJugador = resultSet2.getInt(1);
	                velocidad = resultSet2.getInt(2);
	                probabilidad = resultSet2.getString(3);
	                C3E8 = new Equipo8("corredor3",350,jLabelCorredor3Equipo8,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));

	                Equipo8.setIdJugadorIdEquipo(idJugador, idEquipo8);
	            }
	            Equipo.conectar();
	            ResultSet resultSet3 = Equipo.RealizarConsulta(Jugada.consultarVelJugador(4, 8, idJugada, idEquipo8));
	            while (resultSet3.next()) { //Creando las filas para el JTable
	                idJugador = resultSet3.getInt(1);
	                velocidad = resultSet3.getInt(2);
	                probabilidad = resultSet3.getString(3);
	                C4E8 = new Equipo8("corredor4",220,jLabelCorredor4Equipo8,5, r.conversionVelocidad(velocidad), r.getRandA(probabilidad));
	            	C2E8.start();
	               
	                Equipo8.setIdJugadorIdEquipo(idJugador, idEquipo8);
	            }
	        }
    }
    
  


    @Override
    public void run()
    {
        String texto;
        while(true)
        {try{
            texto = entrada.readLine();
            
            mensajesEquipo1(texto);
            mensajesEquipo2(texto);
            mensajesEquipo3(texto);
            mensajesEquipo4(texto);
            mensajesEquipo5(texto);
            mensajesEquipo6(texto);
            mensajesEquipo7(texto);
            mensajesEquipo8(texto);
            
        }catch(IOException e){} 
        catch (SQLException ex) {
                Logger.getLogger(ConectorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    public void enviarMSG(String msg)
    {
        
        try{
            this.salida = new DataOutputStream(socket.getOutputStream());
            this.salida.writeBytes(msg+"\n");
            System.out.println("enviado " +msg);
        }catch (IOException e){
            System.out.println("Problema al enviar");
        }
    }
    
    public String leerMSG()
    {
        try{
            return entrada.readLine();
        }catch(IOException e){}
        return null;
    }
    
}
