/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.exception;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class BaseBusinessException extends RuntimeException{

    public BaseBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseBusinessException(String message) {
        super(message);
    }
    
    
}
