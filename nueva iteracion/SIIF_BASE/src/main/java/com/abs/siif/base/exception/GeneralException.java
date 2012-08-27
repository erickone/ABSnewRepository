/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.exception;

/**
 *
 * @author Israel Ruiz
 */
public class GeneralException extends RuntimeException {
    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralException(String message) {
        super(message);
    }
    
}
