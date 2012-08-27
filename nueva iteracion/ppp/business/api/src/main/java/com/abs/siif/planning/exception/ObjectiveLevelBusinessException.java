package com.abs.siif.planning.exception;

/**
 *Utilizado para mapear excepciones de negocio de Niveles de plan estrategico
 *@author Juan Antonio Zavala Aguilar
 * 
 */
public class ObjectiveLevelBusinessException extends RuntimeException{

    public ObjectiveLevelBusinessException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }

    public ObjectiveLevelBusinessException(String aMessage) {
        super(aMessage);
    }
    
    
}
