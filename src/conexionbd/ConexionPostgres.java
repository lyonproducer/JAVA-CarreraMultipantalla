/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author diana
 */

public class ConexionPostgres {

    //Variables de conexion
    protected String bd;
    protected String usuario;
    protected String contrasena;
    protected String ip;
    protected String puerto;
    protected String url;
    private final String driver = "org.postgresql.Driver";

    //Variables de manipulacion
    protected Connection conexion;       //ALmacena los datos de la conexion
    protected Statement sentencia;      //Realiza la conexion
    protected ResultSet resultado;        //Almacena los datos de la consulta
    protected PreparedStatement pSentencia;

    public ConexionPostgres(String bd, String usuario, String contrasena, String ip, String puerto) {
        this.bd = bd;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.ip = ip;
        this.puerto = puerto;
        this.url = "jdbc:postgresql://"+ip+":"+puerto+"/" + bd;
    }
    
     public Connection getConexion() {
        return conexion;
    }
    /**
     * Establecemos la conexión con la base de datos <b>customerdb</b>. Método
     * para establecer la conexión a la base de datos mediante el paso de
     * parámetros.
     *
     */
       
    public void conectar() {
       
        try {// Registramos el driver de PostgresSQL          
            try {
                Class.forName(driver).newInstance();//indica la conexion que se utilizara
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
                JOptionPane.showMessageDialog(null,  "Error con el drive:" + ex.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
            }

            conexion = DriverManager.getConnection(url, usuario, contrasena);// Conectamos con la base de datos
           // JOptionPane.showMessageDialog(null, "Exito", "Exito al ingresar datos", JOptionPane.INFORMATION_MESSAGE);
            boolean valid = conexion.isValid(50000);//valida si la conexion se realizo o no
            System.out.println(valid ? "Conexion Exitosa" : "Fallo De Conexion");

        } catch (InstantiationException | IllegalAccessException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
            //JOptionPane.showMessageDialog(null,  "Error de conexion:" + e.getMessage()+" los datos ingresados son incorrectos", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
       

    }

    /**
     * Método que realiza la desconexión con la BD.
     */
    public void desconectar() {

        try {
           
            if (sentencia != null) {
                sentencia.close();
                System.out.println("Desconexion de sentencia Exitosa");
            }
            if (resultado != null) {
                resultado.close();
                System.out.println("Desconexion de resultado Exitosa");
            }
            if (pSentencia != null) {
                pSentencia.close();
                System.out.println("Desconexion de pSentencia Exitosa");
            }

        } catch (SQLException e) {
            System.err.println("Error al intentar cerrar la conexión:\n" + e.getMessage());
        }
    }

    /**
     * Método que realiza una consulta a la BD
     *
     * @param query
     * @return Resulset
     */
    public ResultSet RealizarConsulta(String query) {
 
        try {
            sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(query);

        } catch (SQLException e) {
            System.err.println("Error Consulta: " + e.getMessage());
        }
        return resultado;
    }

    /**
     * Método que agrega registros a la base de datos indicando en la sentencia
     * que recibe como parámetro.
     *
     * @param consulta
     */
    public void IngresarDatos(String consulta) {
     conectar();
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(consulta);
            System.out.println("Exito al ingresar datos");
           // JOptionPane.showMessageDialog(null, "Exito", "Exito al ingresar datos", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Problemas de SQL:" + e, "Error al Ingresar datos", JOptionPane.ERROR_MESSAGE);
            System.err.println("Problemas de SQL:" + e + "Error al Ingresar datos");
        }
      
    }

  
    public int totalRegistros2(String consulta) {
        int registros = 0;

        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = conexion.prepareStatement(consulta);
            resultado = pstm.executeQuery();
            resultado.next();
            registros = resultado.getInt("total");

        } catch (SQLException e) {
            System.err.println("Error en total registros2"+e);
        }

        return registros;
    }
    
    public int totalRegistros(String tabla) {
        int registros = 0;
    
        //obtenemos la cantidad de registros existentes en la tabla

        try {
            PreparedStatement pstm = conexion.prepareStatement("SELECT count(1) as total FROM " + tabla);
            resultado = pstm.executeQuery();
            resultado.next();
            registros = resultado.getInt("total");
            resultado.close();

        } catch (SQLException e) {
             System.err.println("Error en total registros"+e);
        }
        return registros;
    }
}

