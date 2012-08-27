/*
 *  Copyright (C) 2012 Advance Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  FormatNumber
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advance Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advance
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.support;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @versión   1.0  19-jun-2012
 * @author abs71
 */
public class FormatNumber {
/**
     * Formatea numero ###,###
     * @param num numero sin formato 1000
     * @return numero formateado 1,000
     */
    public static String formatNumber(String num){
        if(num != null && !num.equals("0") && !num.isEmpty())
        {
            num = num.replace(",", "");
            DecimalFormat df= new DecimalFormat("###,###");
            try{
                num=df.format(Double.parseDouble(num));            
            } catch (Exception ex) {
                Logger.getLogger(FormatNumber.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return num;
    }
    
/**
     * Formatea numero ###,###
     * @param num numero sin formato 1000
     * @return numero formateado 1,000
     */
    public static String formatNumberDecimal(String num){
        if(num != null && !num.equals("0") && !num.isEmpty())
        {
            num = num.replace(",", "");
            DecimalFormat df= new DecimalFormat("###,###.##");
            try{
                num=df.format(Double.parseDouble(num));            
            } catch (Exception ex) {
                Logger.getLogger(FormatNumber.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return num;
    }

    /**
     * Quita el formato al numero ###,###
     * @param num numero formateado 1,000
     * @return  numero sin formato 1000
     */
    public static String removeFormat(String num){
        String ret = num;
        if(num!=null && !num.isEmpty())
            ret= num.replace(",", "");
        return ret;
    }
}
