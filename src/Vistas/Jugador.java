/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Simulacion.Equipo;
import Simulacion.Jugadores;
import conexionbd.ConexionPostgres;
import conexionbd.Tabla;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author diana
 */
public final class Jugador extends javax.swing.JFrame {
Jugadores M;
Equipo P;
    private static   String nombre;
    private static    String usuario ;
    private static    String clave ;
    private static    String IPbd;
    private static    String puertobd;

    /**
     * Creates new form Materiales
     * @param bd
     * @param usuario
     * @param contrasena
     * @param puerto
     * @param ip
     * @throws java.sql.SQLException
     */
    public Jugador(String bd, String usuario, String contrasena, String ip, String puerto) throws SQLException {
        initComponents();
        setTitle("Configuración de jugadores");
        setResizable(false);
        
        Jugador.nombre=bd;
        Jugador.usuario=usuario;
        Jugador.clave=contrasena;
        Jugador.IPbd=ip;
        Jugador.puertobd=puerto;
        jTextField1.setEnabled(false);
        jTextField3.setEnabled(false);
        Tabla T = new Tabla(nombre, usuario, clave, IPbd, puertobd);
        P =new Equipo(nombre, usuario, clave, IPbd, puertobd);
        M = new Jugadores(nombre, usuario, clave, IPbd, puertobd);
        
        for(int i=0; i<=100; i++){
            jComboBox1.addItem(i+"%");
            jComboBox2.addItem(i+"%");
        }
        int i =0;
        while( i<=50){
            jComboBox5.addItem(i+"-Km/h");
            jComboBox6.addItem(i+"-Km/h");
            i=i+10;
        }
        
        P.conectar();
        habilitar(false);
        
        String Es = M.consultarM();
        T.llenar(Es, false, jScrollPane1);
        
        ResultSet resul = P.RealizarConsulta(P.consultarIdEquipo());
        
        while (resul.next()) { //llenar los combo box mediante consulta sql
            jComboBox3.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox4.addItem(resul.getString(1)+"-"+resul.getString(2));
        }
        
         ResultSet resultSet = P.RealizarConsulta(M.consultarTUltimo());
            while (resultSet.next()) { //Creando las filas para el JTable
                jTextField3.setText(String.valueOf(resultSet.getInt(1)+1));
                
            }
        jComboBox3.setSelectedItem(null);
        jComboBox2.setSelectedItem(null);
        jComboBox5.setSelectedItem(null);
        jComboBox6.setSelectedItem(null);
        jComboBox4.setSelectedItem(null);
        jComboBox1.setSelectedItem(null);
       
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setjButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }
    
    public JButton getjButton2() {
        return jButton1;
    }

    public void setjButton2(JButton jButton1) {
        this.jButton1 = jButton1;
    }

  public void habilitar(boolean f) { 
        jTextField2.setEnabled(f);
        jComboBox2.setEnabled(f);
        jComboBox3.setEnabled(f);
        jComboBox5.setEnabled(f);
        jRadioButton4.setEnabled(f);
        jRadioButton5.setEnabled(f);
        jRadioButton6.setEnabled(f);
        btnaceptarmodificacion.setEnabled(f);
  
  }

    // Limpia el contenido de los jTextFields, jLabelFoto, JRadioButton y JDateChooser pasados por parametro.
    public void Limpiar1(JTextField nombre, JComboBox velocidad,JComboBox  probabilidad_fallas, JComboBox  jComboBox2, JRadioButton r1, JRadioButton r2, JRadioButton r3) {
        nombre.setText("");
        velocidad.setSelectedItem(null);
        probabilidad_fallas.setSelectedItem(null);
        jComboBox2.setSelectedItem(null);
        r1.setSelected(false);
        r2.setSelected(false);
        r3.setSelected(false);
    }

    /**
     * Muestra el contenido del JTable en Cada JtextFields correspondiente a la
     * opcion modificar o eliminar
     */
    public void Ver() {
        Tabla T = new Tabla(nombre, usuario, clave, IPbd, puertobd);
//        Equipo P = new Equipo();
//        Jugadores M = new Jugadores();
        String Es = M.consultarM();
        T.llenar(Es, false, jScrollPane1);
        T.jTable.addMouseListener(new MouseAdapter() {
         
            @Override
            public void mouseClicked(MouseEvent e) {

                Limpiar1(jTextField2,jComboBox5, jComboBox2,  jComboBox3, jRadioButton4, jRadioButton5, jRadioButton6);
                habilitar(false);

                int row = T.jTable.rowAtPoint(e.getPoint());
                jTextField1.setText(T.jTable.getValueAt(row, 0).toString());
                jTextField2.setText(T.jTable.getValueAt(row, 1).toString());
                jComboBox5.setSelectedItem(T.jTable.getValueAt(row, 3).toString()+"-Km/h");
                jComboBox2.setSelectedItem(T.jTable.getValueAt(row, 5).toString());
                P.conectar();
        
                

                try { 
                    
                    
                     ResultSet resul = P.RealizarConsulta(P.consultarIdEquipo(jTextField1.getText()));
                     while (resul.next()) {
                        //llenar los combo box mediante consulta sql
                        jComboBox3.setSelectedItem(resul.getString(1)+"-"+T.jTable.getValueAt(row, 2).toString());

                     }
                     
                     ResultSet resultSet = P.RealizarConsulta(M.consultarTUltimo());
                     while (resultSet.next()) { //Creando las filas para el JTable
                     jTextField3.setText(String.valueOf(resultSet.getInt(1)+1));
                
                     }
               } catch (SQLException ex) {
                        Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                if ("Jugador de Curva".equals(T.jTable.getValueAt(row, 4).toString()) == true) {
                    jRadioButton4.setSelected(true);
                } else if ("Jugador de Fuerza".equals(T.jTable.getValueAt(row, 4).toString()) == true) {
                    jRadioButton5.setSelected(true);
                }else if ("Jugador de Rapidez".equals(T.jTable.getValueAt(row, 4).toString()) == true) {
                    jRadioButton6.setSelected(true);
                }
            }
        });
        P.desconectar();
         
        
    }

    public boolean advertencias(JTextField nombre, JComboBox velocidad, JComboBox probabilidad_fallas, JComboBox equipo, JRadioButton r1, JRadioButton r2, JRadioButton r3) {
        boolean e=false;
        // metodos de comparacion para que no falte un campo sin llenar
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Nombre");
            e=false;
        }else if (velocidad.getSelectedItem().toString()==null) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar la Velocidad");
            e=false;
        }else if (probabilidad_fallas.getSelectedItem().toString()==null) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar la Probabilidad de Fallas");
            e=false;
        }else if(equipo.getSelectedItem().toString()==null){
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta seleccionar un equipo");
            e=false;
        }else if(r1.isSelected()==false && r2.isSelected()==false && r3.isSelected()==false ){
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Debe seleccionar alguna caracteristica");
            e=false;
        }else{
            e=true;
        }
        
       return e;
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        tablaopciones1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonGuardar1 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField8 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabelError1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnaceptarmodificacion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnaceptareliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tablaopciones1.setBackground(new java.awt.Color(246, 244, 242));
        tablaopciones1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tablaopciones1.setFont(new java.awt.Font("Adobe Caslon Pro", 0, 14)); // NOI18N
        tablaopciones1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tablaopciones1StateChanged(evt);
            }
        });
        tablaopciones1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaopciones1MouseClicked(evt);
            }
        });

        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setPreferredSize(new java.awt.Dimension(77, 29));
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonGuardar1.setText("Guardar");
        jButtonGuardar1.setPreferredSize(new java.awt.Dimension(77, 29));
        jButtonGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardar1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Caracteristicas"));

        buttonGroup1.add(jRadioButton7);
        jRadioButton7.setText("Jugador de Curva");

        buttonGroup1.add(jRadioButton8);
        jRadioButton8.setText("Jugador de Fuerza");

        buttonGroup1.add(jRadioButton9);
        jRadioButton9.setText("Jugador de Rapidez");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton7)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton8)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton9)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel8.setText("Equipo");

        jLabel9.setText("Probabilidad de Fallas");

        jLabel10.setText("Velocidad");

        jLabel11.setText("Nombre");

        jLabel2.setText("Jugador Nro.");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jButtonGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(37, 37, 37))))
        );

        tablaopciones1.addTab("Nuevo", jPanel4);

        jLabelError1.setForeground(new java.awt.Color(189, 9, 9));
        jLabelError1.setText("   ");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnaceptarmodificacion.setFont(new java.awt.Font("Adobe Caslon Pro", 0, 14)); // NOI18N
        btnaceptarmodificacion.setText("Aceptar");
        btnaceptarmodificacion.setPreferredSize(new java.awt.Dimension(77, 29));
        btnaceptarmodificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarmodificacionActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Caracteristicas"));

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Jugador de Curva");

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("Jugador de Fuerza");

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("Jugador de Rapidez");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton4)
                .addGap(32, 32, 32)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton6)
                .addGap(22, 22, 22))
        );

        jLabel4.setText("Equipo");

        jLabel5.setText("Nombre");

        btnaceptareliminar.setFont(new java.awt.Font("Adobe Caslon Pro", 0, 14)); // NOI18N
        btnaceptareliminar.setText("Eliminar");
        btnaceptareliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptareliminarActionPerformed(evt);
            }
        });

        jLabel6.setText("Velocidad");

        jLabel7.setText("Probabilidad de Fallas");

        jLabel1.setText("Jugador Nro.");

        jButton2.setText("Volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelError1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnaceptareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnaceptarmodificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabelError1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnaceptarmodificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(btnaceptareliminar))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(49, 49, 49)))))))
                .addContainerGap())
        );

        tablaopciones1.addTab("Detallar", jPanel7);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablaopciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablaopciones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaopciones1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaopciones1MouseClicked
        Ver();
        habilitar(false);
    }//GEN-LAST:event_tablaopciones1MouseClicked

    private void tablaopciones1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tablaopciones1StateChanged

    }//GEN-LAST:event_tablaopciones1StateChanged

    private void btnaceptareliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptareliminarActionPerformed
        // TODO add your handling code here:
        M.eliminar(jTextField1.getText());
        Ver();
    }//GEN-LAST:event_btnaceptareliminarActionPerformed

    private void btnaceptarmodificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarmodificacionActionPerformed

        String []c = jComboBox3.getSelectedItem().toString().split("-");
        Integer  id_equipo= Integer.parseInt(c[0]);
        String []c1 = jComboBox5.getSelectedItem().toString().split("-"); 
        int vel= Integer.parseInt(c1[0]);
        M.setNombre(jTextField2.getText());
        M.setVelocidadPromedio(vel);
        M.setProbabilidad_fallas(jComboBox2.getSelectedItem().toString());
        M.setIdEquipo(id_equipo);
        M.setIdJugador(Integer.valueOf(jTextField1.getText()));
             
       if(jRadioButton4.isSelected()==true){
           String caracteristica = "Jugador de Curva";
           M.setCaracteristica(caracteristica);
       }if(jRadioButton5.isSelected()==true){
           String caracteristica = "Jugador de Fuerza";
           M.setCaracteristica(caracteristica);
       }if(jRadioButton6.isSelected()==true){
           String caracteristica = "Jugador de Rapidez";
           M.setCaracteristica(caracteristica);
       }
       if(advertencias(jTextField2, jComboBox5, jComboBox2, jComboBox3, jRadioButton4, jRadioButton5, jRadioButton6)==true){
            M.actualizarJugador();
            Tabla T = new Tabla(nombre, usuario, clave, IPbd, puertobd);
            habilitar(false);
            String Es = M.consultarM();
            T.llenar(Es, false, jScrollPane1);
            Ver();
        
       }
        
    }//GEN-LAST:event_btnaceptarmodificacionActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        habilitar(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jButtonGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardar1ActionPerformed
    try {
      
        String []c = jComboBox4.getSelectedItem().toString().split("-");
        System.err.println("c"+c);
        Integer  id_equipo= Integer.parseInt(c[0]);
        
        
        String []c1 = jComboBox6.getSelectedItem().toString().split("-"); 
        int vel= Integer.parseInt(c1[0]);
        
        
        M.setNombre(jTextField8.getText());
        M.setVelocidadPromedio(vel);
        M.setProbabilidad_fallas(jComboBox1.getSelectedItem().toString());
        M.setIdEquipo(id_equipo);
        
        if(jRadioButton7.isSelected()==true){
            String caracteristica = "Jugador de Curva";
            M.setCaracteristica(caracteristica);
        }if(jRadioButton8.isSelected()==true){
            String caracteristica = "Jugador de Fuerza";
            M.setCaracteristica(caracteristica);
        }if(jRadioButton9.isSelected()==true){
            String caracteristica = "Jugador de Rapidez";
            M.setCaracteristica(caracteristica);
        }
        
        if(advertencias(jTextField8, jComboBox6, jComboBox1, jComboBox4, jRadioButton7, jRadioButton8, jRadioButton9)==true){
            M.guardar();
            M.conectar();
            ResultSet resultSet = M.RealizarConsulta(M.consultarTUltimo());

            while (resultSet.next()) { //Creando las filas para el JTable
                jTextField3.setText(String.valueOf(resultSet.getInt(1)+1));     
            }


            Tabla T = new Tabla(nombre, usuario, clave, IPbd, puertobd);
            Limpiar1(jTextField8,jComboBox6,jComboBox1, jComboBox4, jRadioButton8, jRadioButton9, jRadioButton7);
            String Es = M.consultarM();
            T.llenar(Es, false, jScrollPane1);
            Ver();

            M.desconectar();
        }
       
    } catch (SQLException ex) {
        Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButtonGuardar1ActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
             
            Limpiar1(jTextField8,jComboBox6,jComboBox1,jComboBox4, jRadioButton8, jRadioButton9, jRadioButton7);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try {
        // TODO add your handling code here:
        Configuracion c= new Configuracion(nombre, usuario, clave, IPbd, puertobd);
        c.setVisible(true);
        this.setVisible(false);
    } catch (SQLException ex) {
        Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         try {
        // TODO add your handling code here:
        Configuracion c= new Configuracion(nombre, usuario, clave, IPbd, puertobd);
        c.setVisible(true);
        this.setVisible(false);
    } catch (SQLException ex) {
        Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Jugador(nombre, usuario, clave, IPbd, puertobd).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnaceptareliminar;
    private javax.swing.JButton btnaceptarmodificacion;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonGuardar1;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelError1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTabbedPane tablaopciones1;
    // End of variables declaration//GEN-END:variables
}
