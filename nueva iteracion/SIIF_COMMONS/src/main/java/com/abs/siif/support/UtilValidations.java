/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.support;

/**
 *
 * @author Israel Ruiz
 */
public class UtilValidations {
    public static boolean notNullOrBlank(Long data){
        if(data == null || data <=0){
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean notNullOrBlank(String data){
        if(data == null || data.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean validExReg(String data, String expReg){
        if ( data != null) {
            return data.matches(expReg);
        }else{
            return false;
        }
    }
    
}
