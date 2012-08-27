/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  LogbookExceptionImpl
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.common.api.LogbookException;
import com.abs.siif.security.entities.UserEntity;
import com.abs.siif.support.entities.LogbookEntity;
import com.abs.siif.support.managment.LogbookManagement;
import java.util.Date;
import javax.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author luis.carreon
 */
@Aspect
public class LogbookExceptionImpl implements LogbookException{
    
    @Resource(name = "logbookManagment")
    private transient LogbookManagement logbookManagement;
    
    @AfterThrowing(pointcut = "execution(* com.abs.siif.*.*Management*.*.*(..))",
    throwing = "anError")
    @Override
    public void registerLog(JoinPoint joinPoint, Throwable anError) {
                
        LogbookEntity myLogbook = new LogbookEntity();
        
        Long myDateLong = System.currentTimeMillis();
        Date myDate = new Date(myDateLong);
        UserEntity myUser = 
                (UserEntity)SIIFContextBase.getParamContext(KeyContextEnum.USER);
        
        myLogbook.setLogbookErrorCause(anError.getCause().toString());
        myLogbook.setLogbookSingClass(anError.getClass().toString());
        myLogbook.setLogbookDate(myDate);
        myLogbook.setLogbookUser(myUser.getUserDescription());
        myLogbook.setLogbookId(myDateLong);
        
        if(anError instanceof org.hibernate.exception.GenericJDBCException){
            logbookManagement.saveLogbook(myLogbook);
        }
    }
}