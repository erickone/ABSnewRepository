/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AsynchCall
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.base.async;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

/**
 *
 * @author Israel Ruiz
 */
public class AsynchCall {

    private Logger log = Logger.getLogger(this.getClass());
    private Object resultCall;
    private boolean isFinished = false;
    private final Object session;
    private Thread t1;
    public AsynchCall() {
       session = SIIFContextBase.getLocalcontext().
                        get().get(KeyContextEnum.SESSION);
    }

    public void executCall(final AsyncListener caller, final Object service,
            final String methodName, final Object... args) throws Exception {

        Runnable toExec = new Runnable() {

            @Override
            public void run() {
                SIIFContextBase.initLocal();
                SIIFContextBase.getLocalcontext()
                        .get().put(KeyContextEnum.SESSION, session);
                try {
                    Method[] m = service.getClass().getMethods();
                    for (Method m1 : m) {
                        if (m1.getName().trim().equals(methodName)) {
                            Class[] argTypes = m1.getParameterTypes();
                            Method d = service.getClass().getDeclaredMethod(m1.getName(),
                                    argTypes);
                            if (args != null && !(args.length == 0)) {
                                resultCall = d.invoke(service, args);
                                break;
                            } else {
                                resultCall = d.invoke(service);
                                break;
                            }
                        }
                    }
                    isFinished = true;
                    caller.notifyWakeUp();

                } catch (Exception ex) {
                    log.error("The call can not be executed" + ex.getMessage());
                    isFinished = true;
                    caller.notifyWakeUp();
                    throw new RuntimeException("Error while execute AsynchCall", ex);
                }

            }
        };
        t1 = new Thread(toExec);
        t1.setName(methodName);
        t1.start();
        Thread.sleep(100);
    }
    
    public Thread getThread(){
        return t1;
    }

    public boolean isFinished() {
        return isFinished;
    }

    /**
     * @return the resultCall
     */
    public Object getResultCall() {
        return resultCall;
    }
    /**
     * @return the resultCall
     */
    public void setResultCall(Object obj) {
        resultCall = obj;
    }
}
