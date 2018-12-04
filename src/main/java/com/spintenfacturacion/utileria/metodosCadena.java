/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.utileria;

/**
 *
 * @author willian
 */
public class metodosCadena {
    
    public static int obtenerSoloCorrelativo(String serieautorizada){
        String serieini=serieautorizada;
        char z;
        int y=0;
        int x;
        int valorentero=0;
        for(int n=serieini.length()-1;n>=0;n--){
            try{
                z=serieini.charAt(n);
                if(!Character.isDigit(z)){
                    y=n+1;
                    n=0;
                }
            }
            catch(NumberFormatException e){
            }
        }
            int f=serieini.length();
            System.out.println("VALORES y,f "+y+" "+f);
            if(!"".equals(serieini.substring(y,f))){
            valorentero=Integer.parseInt(serieini.substring(y,f));
            return valorentero;
            }else{
             return 0;
            }        
            
    }   
}
