/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacion;

import conexionbd.ConexionPostgres;

/**
 *
 * @author marcano
 */
public class Jugada extends ConexionPostgres {
    
    //Atributos
    private String hora;
    private String fecha;
    private int id;
    
    public Jugada(String bd, String usuario, String contrasena, String ip, String puerto) {
        super(bd, usuario, contrasena, ip, puerto);
        
    }
    
    //Constructor sin parametros
    public Jugada(String hora, String fecha, String bd, String usuario, String contrasena, String ip, String puerto) {
        super(bd, usuario, contrasena, ip, puerto);
        this.hora = hora;
        this.fecha = fecha;
    }

    //Otros metodos
    public String consultarJugadas() {
        String consulta = "SELECT *FROM jugada";
        return consulta;
    }
    
     public String consultarPosEquipo(int posicion, int id_jugada) {
        String consulta = "SELECT a.id_equipo from equipo a, jugada_equipo b where b.posicion="+posicion+" and b.id_jugada="+id_jugada+";";
        return consulta;
    }
                 public String consultarVelJugador(int posicionJugador,int posicionEquipo, int id_jugada, int id_equipo) {
        String consulta = "SELECT d.id_jugador, d.velocidad, d.probabilidad_fallas from jugada_jugador a, jugada_equipo b,"
                + " jugador d where d.id_jugador=a.id_jugador and a.id_jugada="+id_jugada+" and a.posicion="+posicionJugador+" and b.posicion="+posicionEquipo+" and d.id_equipo="+id_equipo+" and  b.id_jugada=a.id_jugada;";
        return consulta;
    }
 
     public String consultarTUltimo() {
            String consulta = "SELECT max(id_jugada) FROM jugada;";
            return consulta;
    }
      public String consultarEquipo(int jugada) {
            String consulta = "SELECT id_equipo FROM jugada_Equipo where id_jugada="+jugada+";";
            return consulta;
    } 
     public String consultarJugador(int id_jugada) {
            String consulta = "SELECT a.id_jugador, a.velocidad, a.caracteristicas, b.equipo FROM jugador a, "
                    + "equipo b, jugada_jugador c where a.id_jugador = c.id_jugador and a.id_equipo=c.id_equipo and c.id_jugada="+id_jugada+";";
            return consulta;
    } 
     
     
 //****************************contar registro***************************************************************
   public int contarE() {
        String in = "SELECT count(1) as total FROM jugada";
        int t = totalRegistros2(in);
        return t;

    } 
   public int contar(String id_jugada) {
        String in = "SELECT count(1) as total FROM jugada_equipo WHERE id_jugada = "+id_jugada+";";
        int t = totalRegistros2(in);
        return t;

    }
   
    //****************************guardar en bdd***************************************************************
    public void guardarJugada() {
        
            String instruccion1 = "INSERT INTO jugada(fecha, hora) VALUES ( '"+ fecha +"','"+ hora+ "');";
            IngresarDatos(instruccion1);            
    }  
   
    public void guardarJugadaEquipo(String id_jugada, String id_equipo,int posicion) {
        
            String instruccion1 = "INSERT INTO jugada_equipo(id_jugada, id_equipo, posicion) VALUES ( "+id_jugada+","+ id_equipo+","+posicion+");";
            IngresarDatos(instruccion1);            
    }  
    public void guardarJugadaJugador(String id_jugada, String id_jugador, int posicion) {
        
            String instruccion1 = "INSERT INTO jugada_jugador(id_jugada, id_jugador, posicion) VALUES ( "+id_jugada+","+ id_jugador+","+posicion+");";
            IngresarDatos(instruccion1);            
    } 
    
   
   
}
