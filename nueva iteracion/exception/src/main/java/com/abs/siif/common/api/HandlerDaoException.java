/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.common.api;

import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
 
public interface HandlerDaoException {

    void logAfterExecuteException(JoinPoint joinPoint, Throwable error);
}
