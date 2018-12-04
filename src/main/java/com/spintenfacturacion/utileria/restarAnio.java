/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.utileria;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author willian
 */
public class restarAnio {
    
    public static Date restarAnio(Date fecha,int anio){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.YEAR,anio);
        return calendar.getTime();
    }
    
}
