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
import static Cliente.VCliente.jLabelCorredor2Equipo2;
import static Cliente.VCliente.jLabelCorredor2Equipo3;
import static Cliente.VCliente.jLabelCorredor3Equipo2;
import static Cliente.VCliente.jLabelCorredor3Equipo3;
import static Cliente.VCliente.jLabelCorredor4Equipo2;
import static Cliente.VCliente.jLabelCorredor4Equipo3;
import static Cliente.VCliente.jLabelCorredor4Equipo4;
import static Cliente.VCliente.jLabelPista;
import Servidor.ServidorVista;
import conexionbd.ConexionPostgres;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import static Cliente.VCliente.jLabelCorredor2Equipo4;
import static Cliente.VCliente.jLabelCorredor3Equipo4;
import static Cliente.VCliente.jLabelCorredor1Equipo4;
import static Cliente.VCliente.jLabelCorredor1Equipo5;
import static Cliente.VCliente.jLabelCorredor1Equipo6;
import static Cliente.VCliente.jLabelCorredor1Equipo7;
import static Cliente.VCliente.jLabelCorredor1Equipo8;
import static Cliente.VCliente.jLabelCorredor2Equipo1;
import static Cliente.VCliente.jLabelCorredor2Equipo5;
import static Cliente.VCliente.jLabelCorredor2Equipo6;
import static Cliente.VCliente.jLabelCorredor2Equipo7;
import static Cliente.VCliente.jLabelCorredor2Equipo8;
import static Cliente.VCliente.jLabelCorredor3Equipo1;
import static Cliente.VCliente.jLabelCorredor3Equipo5;
import static Cliente.VCliente.jLabelCorredor3Equipo6;
import static Cliente.VCliente.jLabelCorredor3Equipo7;
import static Cliente.VCliente.jLabelCorredor3Equipo8;
import static Cliente.VCliente.jLabelCorredor4Equipo1;
import static Cliente.VCliente.jLabelCorredor4Equipo5;
import static Cliente.VCliente.jLabelCorredor4Equipo6;
import static Cliente.VCliente.jLabelCorredor4Equipo7;
import static Cliente.VCliente.jLabelCorredor4Equipo8;
import static Cliente.VCliente.jScrollPane1;
import static Cliente.VCliente.jTextArea1;


/**
 *
 * @author marcano
 */
public class ConfiguracionBd extends javax.swing.JFrame {
private int comenzar;

 ServidorVista sv; 
 VCliente cliente;
    /**
     * Creates new form ConfiguracionBd
     */
    public ConfiguracionBd() {
        initComponents();
        setTitle("Conexión");
        setLocationRelativeTo(null);
        jTextNombreBD.setText("sistemasdistribuidos");
        jTextPuertoBD.setText("5432");
        jTextPuertoServidor.setText("10578");
        jTextPuertoServidor.setEnabled(false);
        jTextUsuario.setText("postgres");
        jTextIP.setText("localhost");
        jTextIPServidor.setText("localhost");
        jPassword.setText("leo123");
        jButton3.setEnabled(false);
        setResizable(false);
        
    }

    public ServidorVista getSv() {
        return sv;
    }

    public void setSv(ServidorVista sv) {
        this.sv = sv;
    }

    public int getComenzar() {
        return comenzar;
    }

    public void setComenzar(int comenzar) {
        this.comenzar = comenzar;
    }
    
    
    public boolean advertencias(JTextField nombre, JTextField usuario, JTextField ip, JTextField puerto ) {
        boolean e=false;
        // metodos de comparacion para que no falte un campo sin llenar
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el Nombre");
            e=false;
        } else if (usuario.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar usuario de la base de datos");
            e=false;
        } else if (ip.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar la ip del equipo donde se ubica la base de datos");
            e=false;
        } else if (puerto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el puero donde se ubica la base de datos");
            e=false;
        }else{
            e= true;
        }
     return e;
    }
     public boolean advertencias2(JTextField ip, JTextField puerto ) {
        boolean e=false;
        // metodos de comparacion para que no falte un campo sin llenar
       if (ip.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar la ip del equipo donde se ubica el servidor");
            e=false;
        } else if (puerto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "**ADVERTENCIA**\n Falta ingresar el puero donde se ubica el servidor");
            e=false;
        }else{
            e= true;
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

        jPanel1 = new javax.swing.JPanel();
        jTextPuertoServidor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextIPServidor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonConectar = new javax.swing.JButton();
        jTextPuertoBD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextIP = new javax.swing.JTextField();
        jPassword = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextUsuario = new javax.swing.JTextField();
        jTextNombreBD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Puerto que utiliza el Servidor:");

        jLabel5.setText("IP donde se encuentra el Servidor:");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Conexion con el Servidor del Juegos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextIPServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jTextPuertoServidor))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextPuertoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(194, 194, 194))
        );

        jButtonConectar.setText("Conectar");
        jButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarActionPerformed(evt);
            }
        });

        jTextPuertoBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPuertoBDActionPerformed(evt);
            }
        });

        jLabel7.setText("Puerto que utiliza la base de Datos:");

        jLabel8.setText("IP donde se encuentra la Dase de Datos:");

        jLabel9.setText("Clave");

        jLabel10.setText("Usuario:");

        jTextNombreBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreBDActionPerformed(evt);
            }
        });

        jLabel11.setText("Nombre de la Base de Datos:");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Conexion con la Base de Datos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel12)
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonConectar, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jTextNombreBD, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextIP, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextPuertoBD, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextNombreBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextPuertoBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonConectar)
                .addGap(27, 27, 27))
        );

        jButton3.setText("Comenzar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarActionPerformed
        // TODO add your handling code here:
        String nombre = jTextNombreBD.getText();
        String usuario = jTextUsuario.getText();
        String clave= jPassword.getText();
        String IP = jTextIP.getText();
        String puerto = jTextPuertoBD.getText();
        
        if(advertencias(jTextNombreBD, jTextUsuario, jTextIP, jTextPuertoBD)==true){
            ConexionPostgres javaPostgreSQLBasic = new ConexionPostgres(nombre, usuario, clave, IP, puerto);
            javaPostgreSQLBasic.conectar(); 
            jButton3.setEnabled(true);
        }  
    }//GEN-LAST:event_jButtonConectarActionPerformed

    private void jTextNombreBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreBDActionPerformed

    private void jTextPuertoBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPuertoBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPuertoBDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String nombre = jTextNombreBD.getText();
        String usuario = jTextUsuario.getText();
        String clave= jPassword.getText();
        String IPbd = jTextIP.getText();
        String puertobd = jTextPuertoBD.getText();
        String IP = jTextIPServidor.getText();
        String puerto = jTextPuertoServidor.getText();
        
        if(advertencias2(jTextIPServidor, jTextPuertoServidor)==true){
         
            switch (comenzar) {
                case 0: //pantalla 1 con servidor
                    
                    
//                    cliente = new VCliente();
//                    sv.setVisible(true);
//                    Messenger.initCliente(jTextField10.getText(),nombre, usuario, clave, IPbd, puertobd);
//                        this.dispose();
//                    cliente.show();
//                    jLabelPista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/izquierda.png")));
//                    
//                    jLabelCorredor2Equipo1.setVisible(false);
//                    jLabelCorredor1Equipo1.setVisible(false);
//                    
//                    jLabelCorredor2Equipo2.setVisible(false);
//                    jLabelCorredor1Equipo2.setVisible(false);//solo corre 3 y 4
//                    
//                    jLabelCorredor2Equipo3.setVisible(false);
//                    jLabelCorredor1Equipo3.setVisible(false);//solo corre 3 y 4
//                    
//                    jLabelCorredor2Equipo4.setVisible(false);
//                    jLabelCorredor1Equipo4.setVisible(false);//solo corre 3 y 4
//                    
//                    jLabelCorredor2Equipo5.setVisible(false);
//                    jLabelCorredor1Equipo5.setVisible(false);
//                    
//                    jLabelCorredor2Equipo6.setVisible(false);
//                    jLabelCorredor1Equipo6.setVisible(false);
//                    
//                    jLabelCorredor2Equipo7.setVisible(false);
//                    jLabelCorredor1Equipo7.setVisible(false);//solo corre 3 y 4
//                    
//                    jLabelCorredor2Equipo8.setVisible(false);
//                    jLabelCorredor1Equipo8.setVisible(false);
                    
                    this.setVisible(false);
                    try {
                        Configuracion c = new Configuracion(nombre, usuario, clave, IPbd, puertobd);
                        c.setVisible(true);
                        c.setIpCliente(jTextIPServidor.getText());
                        c.setSv(sv);
                        this.dispose();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ConfiguracionBd.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    
                    
                    
                    break;
                
                case 1: //pantalla 2 solo cliente
                    {
                        cliente = new VCliente();
                        cliente.show();
                        jLabelPista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/central.png")));
                        this.dispose();
                        
                        jLabelCorredor1Equipo1.setVisible(false);
                        jLabelCorredor2Equipo1.setVisible(false);
                        jLabelCorredor3Equipo1.setVisible(false);
                        jLabelCorredor4Equipo1.setVisible(false);
                        
                        jLabelCorredor1Equipo2.setVisible(false);
                        jLabelCorredor2Equipo2.setVisible(false);
                        jLabelCorredor3Equipo2.setVisible(false);
                        jLabelCorredor4Equipo2.setVisible(false);
                        
                        jLabelCorredor2Equipo3.setVisible(false);
                        jLabelCorredor1Equipo3.setVisible(false);
                        jLabelCorredor3Equipo3.setVisible(false);
                        jLabelCorredor4Equipo3.setVisible(false);
                        
                        jLabelCorredor1Equipo4.setVisible(false);
                        jLabelCorredor3Equipo4.setVisible(false);
                        jLabelCorredor2Equipo4.setVisible(false);
                        jLabelCorredor4Equipo4.setVisible(false);
                        
                        jLabelCorredor1Equipo5.setVisible(false);
                        jLabelCorredor3Equipo5.setVisible(false);
                        jLabelCorredor2Equipo5.setVisible(false);
                        jLabelCorredor4Equipo5.setVisible(false);
                        
                        jLabelCorredor1Equipo6.setVisible(false);
                        jLabelCorredor2Equipo6.setVisible(false);
                        jLabelCorredor3Equipo6.setVisible(false);
                        jLabelCorredor4Equipo6.setVisible(false);
                        
                        jLabelCorredor1Equipo7.setVisible(false);
                        jLabelCorredor3Equipo7.setVisible(false);
                        jLabelCorredor2Equipo7.setVisible(false);
                        jLabelCorredor4Equipo7.setVisible(false);
                        
                        jLabelCorredor1Equipo8.setVisible(false);
                        jLabelCorredor3Equipo8.setVisible(false);
                        jLabelCorredor2Equipo8.setVisible(false);
                        jLabelCorredor4Equipo8.setVisible(false);
                        
                         jScrollPane1.setVisible(false);
                        jTextArea1.setVisible(false);
                        jLabel2.setVisible(false);
                        jLabel3.setVisible(false);
                                                
                        Messenger.initCliente(jTextIPServidor.getText(),nombre, usuario, clave, IPbd, puertobd);
                        this.dispose();
                        break;
                    }
                case 2:  //pntalla 3
                    {
                        cliente = new VCliente();
                        cliente.show();
                        jLabelPista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/derecha.png")));
                        this.dispose();
                        
                        jLabelCorredor4Equipo1.setVisible(false);
                        jLabelCorredor3Equipo1.setVisible(false);
                        
                        jLabelCorredor4Equipo2.setVisible(false);
                        jLabelCorredor3Equipo2.setVisible(false);
                        
                        jLabelCorredor4Equipo3.setVisible(false);
                        jLabelCorredor3Equipo3.setVisible(false);
                        
                        jLabelCorredor4Equipo4.setVisible(false);
                        jLabelCorredor3Equipo4.setVisible(false);
                        
                        jLabelCorredor4Equipo5.setVisible(false);
                        jLabelCorredor3Equipo5.setVisible(false);
                        
                        jLabelCorredor4Equipo6.setVisible(false);
                        jLabelCorredor3Equipo6.setVisible(false);
                        
                        jLabelCorredor4Equipo7.setVisible(false);
                        jLabelCorredor3Equipo7.setVisible(false);
                        
                        jLabelCorredor4Equipo8.setVisible(false);
                        jLabelCorredor3Equipo8.setVisible(false);
                        
                        jScrollPane1.setVisible(false);
                        jTextArea1.setVisible(false);
                        jLabel2.setVisible(false);
                        jLabel3.setVisible(false);
                       Messenger.initCliente(jTextIPServidor.getText(),nombre, usuario, clave, IPbd, puertobd);
                        this.dispose();
                        break;
                    }
                default:
                    break;
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ConfiguracionBd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionBd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionBd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionBd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguracionBd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonConectar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jTextIP;
    private javax.swing.JTextField jTextIPServidor;
    private javax.swing.JTextField jTextNombreBD;
    private javax.swing.JTextField jTextPuertoBD;
    private javax.swing.JTextField jTextPuertoServidor;
    private javax.swing.JTextField jTextUsuario;
    // End of variables declaration//GEN-END:variables
}
