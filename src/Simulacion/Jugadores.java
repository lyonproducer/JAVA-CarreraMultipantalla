/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacion;

import conexionbd.ConexionPostgres;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author diana
 */
public class Jugadores extends ConexionPostgres{
    
  private int idJugador ;
  private int idEquipo ;
  private String nombre ;
  private int velocidadPromedio;
  private String caracteristica ;
  private String probabilidad_fallas ;
  private int kmRecorridos;
  private int fallos;

    public Jugadores(String bd, String usuario, String contrasena, String ip, String puerto) {
        super(bd, usuario, contrasena, ip, puerto);
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    
    public String getProbabilidad_fallas() {
        return probabilidad_fallas;
    }

    public void setProbabilidad_fallas(String probabilidad_fallas) {
        this.probabilidad_fallas = probabilidad_fallas;
    }
    
    public int getIdJugador() {
        return idJugador;
    }

     public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidadPromedio() {
        return velocidadPromedio;
    }

    public void setVelocidadPromedio(int velocidadPromedio) {
        this.velocidadPromedio = velocidadPromedio;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public int getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(int kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }
    
    //*********************Consultas******************************************************
    public String consultar() {
            String consulta = "SELECT *FROM Jugador;";
            return consulta;
    }
    public String consultarM() {
        
            String consulta = "SELECT a.id_jugador, a.nombre, b.equipo, a.velocidad, a.caracteristicas, a.probabilidad_fallas "
                             + "FROM Jugador a, equipo b WHERE a.id_equipo=b.id_equipo;";
            return consulta;
    }
       public String consultarM(String equipo) {
        
            String consulta = "SELECT a.id_jugador, a.nombre, b.equipo, a.velocidad, a.total_fallos, a.caracteristicas, a.probabilidad_fallas "
                             + "FROM Jugador a, equipo b WHERE a.id_equipo="+equipo+" and a.id_equipo=b.id_equipo;";
            return consulta;
    }
       public String consultarJugadores(String equipo) {
        
            String consulta = "SELECT id_jugador, nombre, caracteristicas FROM Jugador WHERE id_equipo="+equipo+";";
            return consulta;
    }
    public String consultarM2() {
            String consulta = "SELECT id_jugador, nombre FROM Jugadores;";
            return consulta;
    }
    
    public String consultarTUltimo() {
            String consulta = "SELECT max(id_jugador) FROM jugador;";
            return consulta;
    }
    
   //****************************guardar en bdd***************************************************************
    public void guardar() {
        
            String instruccion1 = "INSERT INTO jugador(nombre, velocidad, caracteristicas, id_equipo, probabilidad_fallas) VALUES ( '" + nombre + "',"+velocidadPromedio+",'"
                   + caracteristica +  "'," + idEquipo+  ",'"+   probabilidad_fallas+"');";
            IngresarDatos(instruccion1);            
    }  
    
    //********************************actualizar en bdd**********************************************************
    public void actualizarJugador() {
        
        try {
            conectar();
            String instruccion = "update jugador set "
                    + " nombre = ?,"
                    + " velocidad = ?,"
                    + " caracteristicas = ?,"
                    + " id_equipo = ?"
                    + " where id_jugador= ? ;";
            pSentencia = conexion.prepareStatement(instruccion);            
            pSentencia.setString(1, nombre);
            pSentencia.setInt(2, velocidadPromedio);
            pSentencia.setString(3, caracteristica);
            pSentencia.setInt(4, idEquipo);
            pSentencia.setInt(5, idJugador);
            pSentencia.execute();
            desconectar();
            
            System.out.println("Datos modificados");
            JOptionPane.showMessageDialog(null, "El registro ha sido actualizado correctamente.","Actulizando información",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog( null, "Problemas de SQL:"+ ex, "Error al Ingresar datos",JOptionPane.ERROR_MESSAGE);       
        }
    }
    
     
     
    //**************************************eliminar en bdd****************************************************************************************
    public void eliminar(String idJugador){
       
        IngresarDatos("DELETE FROM jugador WHERE id_jugador = " + idJugador + ";");

    }
    
  
    
    
    
}




