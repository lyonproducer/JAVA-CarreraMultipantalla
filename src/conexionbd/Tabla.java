/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diana
 */
public class Tabla extends JFrame {
       public JTable jTable;
        public JTable jTable1;
    private   String nombre;
    private String usuario ;
    private  String clave ;
    private  String IPbd;
    private String puertobd;

    public Tabla(String nombre, String usuario, String clave, String IPbd, String puertobd) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.IPbd = IPbd;
        this.puertobd = puertobd;
    }

    
    


       @SuppressWarnings("ConvertToTryWithResources")
       
       
    public void llenar(String consulta, boolean editar, JScrollPane jScrollPane2) {
        try {
            ConexionPostgres E = new ConexionPostgres(nombre, usuario, clave, IPbd, puertobd);
            DefaultTableModel modelo;
            modelo = new DefaultTableModel(){//establece el modelo 
                @Override
                public boolean isCellEditable(int row, int column) {//coloca la tabla en modo no editable
                    
                    return editar;
                }
            };
            jTable = new javax.swing.JTable();
            E.conectar();
            ResultSet resultSet = E.RealizarConsulta(consulta); // los datos de la consulta lo almacenamos en un ResultSet
            ResultSetMetaData rsMetaData = resultSet.getMetaData();//Se Obtiene la informacion de las columnas que estan siendo consultadas
            int cantidadColumnas = rsMetaData.getColumnCount(); //Se Obtiene la cantidad de columnas que tiene la consulta
            
            for (int i = 1; i <= cantidadColumnas; i++) { //Establecer como cabezeras el nombre de las columnas
                modelo.addColumn(rsMetaData.getColumnLabel(i));
            }
            while (resultSet.next()) { //Creando las filas para el JTable
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = resultSet.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            
            jTable.setModel(modelo);// añade el modelo al jTable
            jTable.getTableHeader().setResizingAllowed(false); //evita cambiar el tamaño de las tablas
            jTable.getTableHeader().setReorderingAllowed(false) ;//evita mover las tablas de posicion
            jScrollPane2.setViewportView(jTable);// añade la tabla al portView del scrollPane.
            
//            resultSet.close(); // cierra la conexion 
//            E.sentencia.close();
            E.desconectar();
           } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage().substring(26,32));
        }
    }
    
     
        
        
}    