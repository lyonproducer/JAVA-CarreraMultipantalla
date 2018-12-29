/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacion;

import java.util.Random;

/**
 *
 * @author marcano
 */
public class Randam {
    
int numero =5;
int numeroMaximo= 100;
   Random r = new Random();

   public int getRandA( String proba) {
      String []c = proba.split("%");
       int num= Integer.parseInt(c[0]);
       float probabilidad=num/100;
      Random r = new Random(); 
      float x;
      int rand = r.nextInt(numeroMaximo) + 1;
      x = numeroMaximo * probabilidad;
      if (rand <= x) {
         return numero;
      } else {
         do {
            rand = r.nextInt(numeroMaximo) + 1;
         } while (rand == numero);
         return rand;
      }
   }
   public int cortar2(String text){
        String []c = text.split("-");
        String  id= c[1];
        return Integer.parseInt(id);
   }
   public int cortar(String text){
        String []c = text.split("-");
        String  id= c[0];
        return Integer.parseInt(id);
   }
   
   public int conversionVelocidad(int Velocidad){
       int velocidad = 150;
       
       if(Velocidad==10){
       velocidad = 150; 
       }   if(Velocidad==20){
       velocidad = 110; 
       }   if(Velocidad==30){
       velocidad = 90; 
       }   if(Velocidad==40){
       velocidad = 70; 
       }   if(Velocidad==50){
       velocidad = 60; 
       }   
       return velocidad;
   }

}
 

