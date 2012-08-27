/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RemotigInterceptor
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.remoting.client;

import com.abs.siif.AdapterBaseSiif;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.common.RemotingRException;
import com.siif.remoting.RemotingKeysEnum;
import com.siif.remoting.SiifPPPRemoting;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author Israel Ruiz
 */
@Aspect
public class RemotigInterceptor {

    @Resource(name = "remoting")
    SiifPPPRemoting remoting;

    @Around("execution(* com.abs.siif.programming.management.*.*(..))")
    public Object invokeProgramming(ProceedingJoinPoint pjp) throws Throwable {

        return invokeProcess(pjp);
    }
    
    @Around("execution(* com.abs.siif.budget.management.*.*(..))")
    public Object invokeBudget(ProceedingJoinPoint pjp) throws Throwable {
        return invokeProcess(pjp);
    }

    @Around("execution(* com.abs.siif.planning.management.*.*(..))")
    public Object invokePlanning(ProceedingJoinPoint pjp) throws Throwable {
        return invokeProcess(pjp);
    }
    
    @Around("execution(* com.abs.siif.security.management.*.*(..))")
    public Object invokeSecurity(ProceedingJoinPoint pjp) throws Throwable {
        return invokeProcess(pjp);
    }
    
    private Object invokeProcess(ProceedingJoinPoint pjp)throws Throwable{
                AdapterBaseSiif refThis = null;
        Map<RemotingKeysEnum, Object> returnData;
        Object result;

        Map<RemotingKeysEnum, Object> params = new HashMap<RemotingKeysEnum, Object>();


        pjp.proceed();

        Object obj = pjp.getTarget();
        if (obj instanceof AdapterBaseSiif) {
            refThis = (AdapterBaseSiif) obj;
        }

        params.put(RemotingKeysEnum.SERVICE_BEAN, refThis.getBeanName());
        params.put(RemotingKeysEnum.SERVICE_NAME, pjp.getSignature().getName());
        params.put(RemotingKeysEnum.SERVICE_ARGS, pjp.getArgs());
        SessionKeyEnum[] keys = SessionKeyEnum.values();

        for (SessionKeyEnum key : keys) {
            params.put(RemotingKeysEnum.valueOf(key.name()),
                    SIIFContextBase.getParameterSession(key));
        }


        returnData = (Map<RemotingKeysEnum, Object>) remoting.executeService(params);
        Exception exc = (Exception) returnData.get(RemotingKeysEnum.EXCEPTION);
        if (exc != null) {
            throw new RemotingRException(exc);
        } else {
            result = returnData.get(RemotingKeysEnum.RESULT);
        }

        return result;

    }
    
}
