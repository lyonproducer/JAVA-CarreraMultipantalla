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


public class Equipo extends ConexionPostgres {
    
    //Atributos
    private String nombre;
    private String pais;
    private int id;
    private int p_perdidas;
    private int p_ganadas;


    public Equipo(String bd, String usuario, String contrasena, String ip, String puerto) {
        super(bd, usuario, contrasena, ip, puerto);
    }

    public String getPais() {
        return pais;
    }

    //set y get
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
   
    }

    public int getId_equipo() {
        return id;
    }

    public void setId_equipo(int id_equipo) {
        this.id = id_equipo;
    }

    public int getP_perdidas() {
        return p_perdidas;
    }

    public void setP_perdidas(int p_perdidas) {
        this.p_perdidas = p_perdidas;
    }

    public int getP_ganadas() {
        return p_ganadas;
    }

    public void setP_ganadas(int p_ganadas) {
        this.p_ganadas = p_ganadas;
    }

  
 public String consultarIdEquipo() {
        String consulta = "SELECT a.id_equipo, a.equipo FROM equipo a;";
        return consulta;
    }
 
public String consultarIdEquipo(String idJugada) {
        String consulta = "SELECT a.id_equipo FROM jugador a Where a.id_jugada="+idJugada+"order by posicion;";
        return consulta;
    }   
public String consultarNombreEquipo(int idJugador) {
        String consulta = "SELECT a.equipo, a.partidas_ganadas, a.partidas_perdidas FROM equipo a, jugador b Where a.id_equipo=b.id_equipo and b.id_jugador="+idJugador+";";
        return consulta;
    } 
  public String consultarEquipoTotal() {
        
            String consulta = "SELECT a.id_equipo, a.equipo, a.partidas_perdidas,  a.partidas_ganadas FROM equipo a;";
            return consulta;
    }
     
  public String consultarfallos(int id_jugador) {
        
            String consulta = "SELECT total_fallos FROM Jugador WHERE id_jugador="+id_jugador+";";
            return consulta;
    }
   public int contarE() {
        String in = "SELECT count(1) as total FROM equipo";
        int t = totalRegistros2(in);
        return t;

    } 
   
   public int contar(String id_jugada) {
        String in = "SELECT count(1) as total FROM jugada_equipo WHERE id_jugada = "+id_jugada+";";
        int t = totalRegistros2(in);
        return t;

    }
   //****************************guardar en bdd***************************************************************
    public void guardar() {
        
            String instruccion1 = "INSERT INTO equipo(id_equipo, equipo) VALUES ( "+id+",'"+ nombre + "');";
            IngresarDatos(instruccion1);            
    }  
    
    
    
    //********************************actualizar en bdd**********************************************************
    public void actualizarEquipo(int p_ganadas, int p_perdidas, int id_equipo) {
        
        try {
            conectar();
            String instruccion = "update equipo set "
                    + " partidas_ganadas = ?,"
                    + " partidas_perdidas = ?"
                    + " where id_equipo= ? ;";
            pSentencia = conexion.prepareStatement(instruccion);            
            pSentencia.setInt(1, p_ganadas);
            pSentencia.setInt(2, p_perdidas);
            pSentencia.setInt(3, id_equipo);
            pSentencia.execute();
            desconectar();
            
            System.out.println("Datos modificados");
           // JOptionPane.showMessageDialog(null, "El registro ha sido actualizado correctamente.","Actulizando información",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog( null, "Problemas de SQL:"+ ex, "Error al Ingresar datos",JOptionPane.ERROR_MESSAGE);       
        }
    }
    public void actualizarFallos(int fallos, int idJugador) {
         try {
            conectar();
            String instruccion = "update jugador set "
                    + " fallos = ?"
                    + " where id_jugador= ? ;";
            pSentencia = conexion.prepareStatement(instruccion);            
            pSentencia.setInt(1, fallos);
            pSentencia.setInt(2, idJugador);
            pSentencia.execute();
            desconectar();
            System.out.println("Datos modificados");
            JOptionPane.showMessageDialog(null, "El registro ha sido actualizado correctamente.","Actulizando información",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog( null, "Problemas de SQL:"+ ex, "Error al Ingresar datos",JOptionPane.ERROR_MESSAGE);       
        }
       

    }
   
   
}

