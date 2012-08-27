/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que proporciona Utilerias para formato de fechas
 *
 * @author Israel Ruiz
 */
public class UtilsDate {
    /**
     * Establece el formado de fecha MM/dd/yy
     */
    public static final String MMDDYY = "MM/dd/yy";
    /**
     * Establece el formado de fecha dd/MM/yyyy
     */
    public static final String DDMMYYYY = "dd/MM/yyyy";
    /**
     * Establece el formado de fecha dd/MM/yyyy
     */
    public static final String DD_MM_YYYY = "dd-MM-yyyy";

    public static String formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date convertToDate(String stringDate, String format) {
        DateFormat formatter;
        Date date = null;
        formatter = new SimpleDateFormat(format);
        try {
            date = (Date)formatter.parse(stringDate);
        } catch (ParseException ex) {
            Logger.getLogger(UtilsDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
//    public static void main(String... arg){
//        Date d = UtilsDate.convertToDate("10-02-2012", UtilsDate.DD_MM_YYYY);
//        System.out.println("Date :" +d);
//    }
}
