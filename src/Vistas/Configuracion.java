/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Cliente.Messenger;
import Cliente.VCliente;
import static Cliente.VCliente.jLabel2;
import static Cliente.VCliente.jLabel3;
import static Cliente.VCliente.jLabelCorredor1Equipo1;
import static Cliente.VCliente.jLabelCorredor1Equipo2;
import static Cliente.VCliente.jLabelCorredor1Equipo3;
import static Cliente.VCliente.jLabelCorredor1Equipo4;
import static Cliente.VCliente.jLabelCorredor1Equipo5;
import static Cliente.VCliente.jLabelCorredor1Equipo6;
import static Cliente.VCliente.jLabelCorredor1Equipo7;
import static Cliente.VCliente.jLabelCorredor1Equipo8;
import static Cliente.VCliente.jLabelCorredor2Equipo1;
import static Cliente.VCliente.jLabelCorredor2Equipo2;
import static Cliente.VCliente.jLabelCorredor2Equipo3;
import static Cliente.VCliente.jLabelCorredor2Equipo4;
import static Cliente.VCliente.jLabelCorredor2Equipo5;
import static Cliente.VCliente.jLabelCorredor2Equipo6;
import static Cliente.VCliente.jLabelCorredor2Equipo7;
import static Cliente.VCliente.jLabelCorredor2Equipo8;
import static Cliente.VCliente.jLabelPista;
import static Cliente.VCliente.jScrollPane1;
import static Cliente.VCliente.jTextArea1;
import Servidor.ServidorVista;
import Simulacion.Equipo;
import Simulacion.Jugada;
import Simulacion.Jugadores;
import conexionbd.Tabla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author marcano
 */
public class Configuracion extends javax.swing.JFrame {

   VCliente v;
   String ipCliente;
   Jugada Jugada;
   Tabla T;
   Equipo Equipo;
   Jugadores jugadores;
    ServidorVista sv ;
   
    private static   String nombre;
    private static    String usuario ;
    private static    String clave ;
    private static    String IPbd;
    private static    String puertobd;
    /**
     * Creates new form Principal
     * 
     * @param bd
     * @param usuario
     * @param contrasena
     * @param ip
     * @param puerto
     * @throws java.sql.SQLException
     */
    public Configuracion(String bd, String usuario, String contrasena, String ip, String puerto) throws SQLException {
        
        initComponents();
        setTitle("Configuracion de Equipos");
        setLocationRelativeTo(null);
        setResizable(false);
        Configuracion.nombre=bd;
        Configuracion.usuario=usuario;
        Configuracion.clave=contrasena;
        Configuracion.IPbd=ip;
        Configuracion.puertobd=puerto;
        
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        Equipo=new Equipo(nombre, usuario, clave, IPbd, puertobd);
        jugadores = new Jugadores(nombre, usuario, clave, IPbd, puertobd);
        Jugada = new Jugada(nombre, usuario, clave, IPbd, puertobd);
        T = new Tabla(nombre, usuario, clave, IPbd, puertobd);
        Equipo.conectar();
        jugadores.conectar();
        Jugada.conectar();
        habilitar(false);
        habilitar2(false);
        habilitar3(false);
        String e = Equipo.consultarEquipoTotal();
        T.llenar(e, false, jScrollPane2);
        
        jButton1.setEnabled(false);
        
        ResultSet resul = Equipo.RealizarConsulta(Equipo.consultarIdEquipo());
        while (resul.next()) { //llenar los combo box mediante consulta sql
            jComboBox1.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox2.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox3.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox4.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox5.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox6.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox7.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox8.addItem(resul.getString(1)+"-"+resul.getString(2));
        }
            jComboBox1.setSelectedItem(null);
            jComboBox2.setSelectedItem(null);
            jComboBox3.setSelectedItem(null);
            jComboBox4.setSelectedItem(null);
            jComboBox5.setSelectedItem(null);
            jComboBox6.setSelectedItem(null);
            jComboBox7.setSelectedItem(null);
            jComboBox8.setSelectedItem(null);
            jComboBox9.setSelectedItem(null);
            jComboBox10.setSelectedItem(null);
            jComboBox11.setSelectedItem(null);
            jComboBox12.setSelectedItem(null);
        
    }

    public VCliente getV() {
        return v;
    }

    public void setV(VCliente v) {
        this.v = v;
    }

    public ServidorVista getSv() {
        return sv;
    }

    public void setSv(ServidorVista sv) {
        this.sv = sv;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }
    
    
    
    public final void habilitar(boolean f){
            jComboBox1.setEnabled(f);
            jComboBox2.setEnabled(f);
            jComboBox3.setEnabled(f);
            jComboBox4.setEnabled(f);
            jComboBox5.setEnabled(f);
            jComboBox6.setEnabled(f);
            jComboBox7.setEnabled(f);
            jComboBox8.setEnabled(f);
    }
    public void limpiar(){
            jComboBox1.setSelectedItem(null);
            jComboBox2.setSelectedItem(null);
            jComboBox3.setSelectedItem(null);
            jComboBox4.setSelectedItem(null);
            jComboBox5.setSelectedItem(null);
            jComboBox6.setSelectedItem(null);
            jComboBox7.setSelectedItem(null);
            jComboBox8.setSelectedItem(null);
            
    }
   public final void habilitar2(boolean f){
            jComboBox9.setEnabled(f);
            jComboBox10.setEnabled(f);
            jComboBox11.setEnabled(f);
            jComboBox12.setEnabled(f);
           
            
          
    }
   public final void habilitar3(boolean f){
            jRadioButton5.setEnabled(f);
            jRadioButton6.setEnabled(f);
            jRadioButton7.setEnabled(f);
            jRadioButton8.setEnabled(f);
            jRadioButton9.setEnabled(f);
            jRadioButton10.setEnabled(f);
            jRadioButton11.setEnabled(f);
            jRadioButton12.setEnabled(f);
   }
    public void limpiar3(){
            jComboBox9.setSelectedItem(null);
            jComboBox10.setSelectedItem(null);
            jComboBox11.setSelectedItem(null);
            jComboBox12.setSelectedItem(null);
            jRadioButton5.setSelected(false);
            jRadioButton6.setSelected(false);
            jRadioButton7.setSelected(false);
            jRadioButton8.setSelected(false);
            jRadioButton9.setSelected(false);
            jRadioButton10.setSelected(false);
            jRadioButton11.setSelected(false);
            jRadioButton12.setSelected(false);
           
    }
    public void limpiar2(){
        jComboBox9.removeAllItems();
        jComboBox10.removeAllItems();
        jComboBox11.removeAllItems();
        jComboBox12.removeAllItems();
        
            
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Modo de Seleccion del Equipo"));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Manual");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Automatico");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addGap(35, 35, 35)
                .addComponent(jRadioButton2)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton1)
                .addComponent(jRadioButton2))
        );

        jLabel9.setText("Equipo Nro. 5");

        jLabel10.setText("Equipo Nro. 6");

        jLabel11.setText("Equipo Nro. 7");

        jLabel12.setText("Equipo Nro. 8");

        jLabel5.setText("Equipo Nro. 1");

        jLabel6.setText("Equipo Nro. 2");

        jLabel7.setText("Equipo Nro. 3");

        jLabel8.setText("Equipo Nro. 4");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("Detalles de los Equipos");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Seleccion de Equipos");

        jButton5.setText("Guardar Equipo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setText("Empezar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("Detalles de los jugadores de cada equipo");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Seleccion de Jugadores");

        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipos"));

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("Equipo Nro. 1");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("Equipo Nro. 2");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setText("Equipo Nro. 3");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton8);
        jRadioButton8.setText("Equipo Nro. 4");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton9);
        jRadioButton9.setText("Equipo Nro. 5");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton10);
        jRadioButton10.setText("Equipo Nro. 6");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton11);
        jRadioButton11.setText("Equipo Nro. 7");
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton12);
        jRadioButton12.setText("Equipo Nro. 8");
        jRadioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton11))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jRadioButton9))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton10)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton12)))
        );

        jLabel3.setText("Jugador inicial        (Jugador de Curva)");

        jLabel4.setText("1er Relevo                (Jugador de fuerza)");

        jLabel15.setText("2do Relevo               (Jugador de curva)");

        jLabel16.setText("3er Relevo                (Jugador de rapidez)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox9, 0, 219, Short.MAX_VALUE)
                    .addComponent(jComboBox10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox11, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jButton1.setText("Agregar Jugador");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(369, 369, 369))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configuracion");

        jMenuItem3.setText("Jugadores");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public boolean advertencia3(){
    boolean e = false;
     if( jRadioButton5.isEnabled()==true){
         JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 1");
         e=false;
     }else if( jRadioButton6.isEnabled()==true){
         JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 2");
          e=false;
        }if(jRadioButton7.isEnabled()==true){
       JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 3");
        e=false;
        }else if(jRadioButton8.isEnabled()==true){
       JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 4");
        e=false;
       }
        else if(jRadioButton9.isEnabled()==true){
       JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 5");
        e=false;
       }
       else  if(jRadioButton10.isEnabled()==true){
       JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 6");
        e=false;
       }
        else if(jRadioButton11.isEnabled()==true){
       JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 7");
        e=false;
       }
        else if(jRadioButton12.isEnabled()==true){
        JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar los jugadores del equipo 8");
         e=false;
        }
        else{
            e=true;
        }
 return e;
}

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(advertencia3()!=false){
            v = new VCliente();
            sv.setVisible(true);
           
  this.dispose();
                    v.show();
                    
                    jLabelPista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/izquierda.png")));
                    jLabelCorredor2Equipo1.setVisible(false);
                    jLabelCorredor1Equipo1.setVisible(false);
                    
                    jLabelCorredor2Equipo2.setVisible(false);
                    jLabelCorredor1Equipo2.setVisible(false);//solo corre 3 y 4
                    
                    jLabelCorredor2Equipo3.setVisible(false);
                    jLabelCorredor1Equipo3.setVisible(false);//solo corre 3 y 4
                    
                    jLabelCorredor2Equipo4.setVisible(false);
                    jLabelCorredor1Equipo4.setVisible(false);//solo corre 3 y 4
                    
                    jLabelCorredor2Equipo5.setVisible(false);
                    jLabelCorredor1Equipo5.setVisible(false);
                    
                    jLabelCorredor2Equipo6.setVisible(false);
                    jLabelCorredor1Equipo6.setVisible(false);
                    
                    jLabelCorredor2Equipo7.setVisible(false);
                    jLabelCorredor1Equipo7.setVisible(false);//solo corre 3 y 4
                    
                    jLabelCorredor2Equipo8.setVisible(false);
                    jLabelCorredor1Equipo8.setVisible(false);
                    
                    jScrollPane1.setVisible(false);
                    jTextArea1.setVisible(false);
                    jLabel2.setVisible(false);
                    jLabel3.setVisible(false);
                    
                    Messenger.initCliente(ipCliente,nombre, usuario, clave, IPbd, puertobd);
                    Equipos e = new Equipos();
                    e.setVisible(true);
                   Equipos.jTextArea2.setText("Equipo \t\t\tPosicion\n\n"+jComboBox1.getSelectedItem().toString()+"\t\t\t1 \n\n"
                                                +               	jComboBox2.getSelectedItem().toString()+"\t\t\t2 \n\n"
                    				+                       jComboBox3.getSelectedItem().toString()+"\t\t\t3 \n\n"
                    				+                       jComboBox4.getSelectedItem().toString()+"\t\t\t4 \n\n"
                    				+                       jComboBox5.getSelectedItem().toString()+"\t\t\t5 \n\n"
                    				+                       jComboBox6.getSelectedItem().toString()+"\t\t\t6 \n\n"
                    				+                       jComboBox7.getSelectedItem().toString()+"\t\t\t7 \n\n"
                    				+                       jComboBox8.getSelectedItem().toString()+"\t\t\t8");
                    
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        limpiar();
        habilitar(true);
        
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed

  
  private int Random(int Max){
        return (int) (Math.random()*Max+1);
    }
   
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        habilitar(false);
        
        Equipo.conectar();
        int numeroEquipos = Equipo.contarE();
           ArrayList numerosAleatorios = new ArrayList();
        int numero;

        while (numerosAleatorios.size() <8) {   
            numero = Random(numeroEquipos-1);
            System.err.println(numeroEquipos);
            if (numerosAleatorios.isEmpty()) {                
                //Si la lista esta vacía, se añade
                numerosAleatorios.add(numero);
            } else {                                          
                //Si no, se comprueba que no esté ya en la lista
                if (!numerosAleatorios.contains(numero)) {
                    numerosAleatorios.add(numero);
                }
            }
        }
            jComboBox1.setSelectedIndex((int) numerosAleatorios.get(0));
            jComboBox2.setSelectedIndex((int) numerosAleatorios.get(1));
            jComboBox3.setSelectedIndex((int) numerosAleatorios.get(2));
            jComboBox4.setSelectedIndex((int) numerosAleatorios.get(3));
            jComboBox5.setSelectedIndex((int) numerosAleatorios.get(4));
            jComboBox6.setSelectedIndex((int) numerosAleatorios.get(5));
            jComboBox7.setSelectedIndex((int) numerosAleatorios.get(6));
            jComboBox8.setSelectedIndex((int) numerosAleatorios.get(7));
            
        
    }//GEN-LAST:event_jRadioButton2ActionPerformed
         
public void cargarJugador(JComboBox jComboBox ){
    try {
        // TODO add your handling code here:
        jugadores.conectar();
        String []c = jComboBox.getSelectedItem().toString().split("-");
        String  id= c[0];
        String Es = jugadores.consultarM(id);
        T.llenar(Es, false, jScrollPane1);
        ResultSet resul = Equipo.RealizarConsulta(jugadores.consultarJugadores(id));
//        ResultSet resul2 = P.RealizarConsulta(M.consultarJugadores(id));
//         ResultSet resul4 = P.RealizarConsulta(M.consultarJugadores(id));
        System.err.println(id);
   
        while (resul.next()) { //llenar los combo box mediante consulta sql
            if("Jugador de Curva".equals(resul.getString(3))){
                 jComboBox9.addItem(resul.getString(1)+"-"+resul.getString(2));
            jComboBox11.addItem(resul.getString(1)+"-"+resul.getString(2));
            }else if("Jugador de Fuerza".equals(resul.getString(3))){
                jComboBox10.addItem(resul.getString(1)+"-"+resul.getString(2));
            }else if("Jugador de Rapidez".equals(resul.getString(3))){
                   jComboBox12.addItem(resul.getString(1)+"-"+resul.getString(2));
            }
           
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        limpiar2();
        cargarJugador(jComboBox1);
        habilitar2(true);
        
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
      limpiar2();
        habilitar2(true);
        cargarJugador(jComboBox2);
    
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
        limpiar2();
        habilitar2(true);
        cargarJugador(jComboBox3);
       
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
    limpiar2();
        habilitar2(true);
        cargarJugador(jComboBox4);
       
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
        limpiar2();
        habilitar2(true);
        cargarJugador(jComboBox5);
   
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
    limpiar2();
        habilitar2(true);
        cargarJugador(jComboBox6);
   
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
       limpiar2();
        habilitar2(true);
        cargarJugador(jComboBox7);
       
    }//GEN-LAST:event_jRadioButton11ActionPerformed

    private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton12ActionPerformed
        // TODO add your handling code here:
     limpiar2();
        habilitar2(true);
        cargarJugador(jComboBox8);
 
    }//GEN-LAST:event_jRadioButton12ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    try {
        // TODO add your handling code here:
        Jugador J = new Jugador(nombre, usuario, clave, IPbd, puertobd);
        J.setVisible(true);
        this.setVisible(false);
    } catch (SQLException ex) {
        Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jMenuItem3ActionPerformed
public String cortar(JComboBox jComboBox){
    String []c = jComboBox.getSelectedItem().toString().split("-");
    
        String  id= c[0];
    return id;    
}
public boolean advertencia(){
    boolean  e = false;
        if (jComboBox9.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el jugador inicial");
            e=false;
        }else if (jComboBox10.getSelectedItem().equals("")) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el 1er relevo");
            e=false;
        }else if(jComboBox11.getSelectedItem().equals("")) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el 2do. relevo");
            e=false;
        }else if(jComboBox10.getSelectedItem().equals("")) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el 3er relevo");
            e=false;
        }else{
            e=true;
        }
    return e;
}
public void guardar(){
  
    try {
        if(advertencia()==true){
        ResultSet resul = Jugada.RealizarConsulta(Jugada.consultarTUltimo());
         while (resul.next()) { 
            Jugada.guardarJugadaJugador(resul.getString(1), cortar(jComboBox9),1);
            Jugada.guardarJugadaJugador(resul.getString(1), cortar(jComboBox10),2);
            Jugada.guardarJugadaJugador(resul.getString(1), cortar(jComboBox11),3);
            Jugada.guardarJugadaJugador(resul.getString(1), cortar(jComboBox12),4);
         }
        }
    } catch (SQLException ex) {
        Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
    }
  
}

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    // TODO add your handling code here:
   if (jRadioButton5.isSelected()==true  && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString())) {
        guardar();
        jRadioButton5.setEnabled(false);
    }if(jRadioButton6.isSelected()==true  && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString())){
        guardar();
        jRadioButton6.setEnabled(false);
     }if(jRadioButton7.isSelected()==true && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString())){
        guardar();
        jRadioButton7.setEnabled(false);
     }if(jRadioButton8.isSelected()==true && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString())){
        guardar();
        jRadioButton8.setEnabled(false);
     }if(jRadioButton9.isSelected()==true && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString())){
       guardar();
        jRadioButton9.setEnabled(false);
     }if(jRadioButton10.isSelected()==true && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString())){
       guardar();
        jRadioButton10.setEnabled(false);
     }if(jRadioButton11.isSelected()==true && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString()) ){
        guardar();
        jRadioButton11.setEnabled(false);
     }if(jRadioButton12.isSelected()==true && !jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString()) ){
        guardar();
        jRadioButton12.setEnabled(false);
    }if(jComboBox9.getSelectedItem().toString().equals(jComboBox11.getSelectedItem().toString())){
         JOptionPane.showMessageDialog(this, "Los dos jugadores de curva son los mismos, por favor cambie alguno de los jugadores");
    }
    }//GEN-LAST:event_jButton4ActionPerformed
public boolean advertencia1(){
    boolean  e = false;
    if (jComboBox1.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 1");
            e=false;
        }else if (jComboBox2.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 2");
            e=false;
        }else if(jComboBox3.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 3");
            e=false;
        }else if(jComboBox4.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 4");
            e=false;
        }else if(jComboBox5.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 5");
            e=false;
        }else if(jComboBox6.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 6");
            e=false;
        }else if(jComboBox7.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 7");
            e=false;
        }else if(jComboBox8.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Equipo nro. 8");
            e=false;
        }else{
            e=true;
        }
    return e;
}
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Una vez guarde los equipos no podra modificarlo ni a sus jugadores, esta seguro de continuar??","**Advertencia**", JOptionPane.YES_NO_OPTION)==0){
          try {
            // TODO add your handling code here:
            Date date = new Date();
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            String hora = hourFormat.format(date);

            //obtener la fecha y salida por pantalla con formato:
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = dateFormat.format(date);
            Jugada jugada = new Jugada(fecha, hora, nombre, usuario, clave, IPbd, puertobd);
             jugada.conectar();
        
         if(advertencia1()==true){
            
                jugada.guardarJugada();

                ResultSet resul = jugada.RealizarConsulta(jugada.consultarTUltimo());
                int i=0;
                String idJugada="";
               
                while (resul.next()){
                       idJugada= resul.getString(1);
                       jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox1),1 );
                jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox2),2 );
                jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox3),3 );
                jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox4),4 );
                jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox5),5 );
                jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox6),6 );
                jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox7),7 );
                jugada.guardarJugadaEquipo(idJugada,cortar(jComboBox8),8 );
                }

                
                habilitar(false);
                jButton5.setEnabled(false);
                jRadioButton1.setEnabled(false);
                jRadioButton2.setEnabled(false);
                jMenu2.setEnabled(false);
                jButton3.setEnabled(true);
                jButton4.setEnabled(true);       
                habilitar3(true);
                jButton1.setEnabled(true);
                
           }
        } catch (SQLException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
       
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
           // TODO add your handling code here:
           Jugador J = new Jugador(nombre, usuario, clave, IPbd, puertobd);
           J.setVisible(true);
           J.getjButton1().setVisible(false);
           J.getjButton2().setVisible(false);
       } catch (SQLException ex) {
           Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    new Configuracion(nombre, usuario, clave, IPbd, puertobd).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables


}
