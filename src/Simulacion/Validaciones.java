/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacion;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class Validaciones {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     *
     * @param evt
     */
    public void sololetras(java.awt.event.KeyEvent evt) {

        char tecla;
        tecla = evt.getKeyChar();
        if (!Character.isLetter(tecla) && tecla != KeyEvent.VK_SPACE && tecla != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }

    public void solonumeros(java.awt.event.KeyEvent evt) {

        char tecla;
        tecla = evt.getKeyChar();
        if (!Character.isDigit(tecla) && tecla != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }
    
    public void solonumerosdouble(java.awt.event.KeyEvent evt, int bandera){
        char tecla;
        tecla = evt.getKeyChar();
        if(tecla=='.' && bandera==1){
            evt.consume();
        }
        if (((tecla < '0') || (tecla > '9')) && (tecla != KeyEvent.VK_BACK_SPACE) && tecla!='.') {
            evt.consume();
        }
    }
    
     public void mayusculas(JTextField text){
        String c = (text.getText()).toUpperCase();
        text.setText(c);  
        text.repaint();
    }
  

    /**
     * Transforma Fecha pasada por parametro de tipo String a tipo Date con
     * formato dd/MM/yy
     *
     * @param Fecha
     * @param format
     * @return
     */
    public static Date parseFecha(String Fecha, String format) {
        SimpleDateFormat formato = new SimpleDateFormat(format);        
        Date fecha = null;
        try {
            fecha = formato.parse(Fecha);
        } catch (ParseException ex) {
            System.out.println("error al transformar fecha" + ex);
        }
        return fecha;
    }

    /**
     * Funcion que Transforma Fecha pasada por parametro de tipo Date a tipo
     * String
     *
     * @param date
     * @param formato //dd/MM/yy
     * @return
     */
    public String fecha(Date date, String formato) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
        String string = String.valueOf(formatoFecha.format(date));
        return string;
    }
    
    

}
