/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  SiifPPPRemotingImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.siif.ppp.business.remoting;

import com.abs.siif.base.async.AsyncListener;
import com.abs.siif.base.async.AsynchCall;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.siif.remoting.RemotingKeysEnum;
import com.siif.remoting.SiifPPPRemoting;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Israel Ruiz
 */
public class SiifPPPRemotingImpl implements SiifPPPRemoting, AsyncListener {

    @Override
    public Object executeService(Map<RemotingKeysEnum, Object> params) {
        Map<RemotingKeysEnum, Object> returnData =
                new EnumMap<RemotingKeysEnum, Object>(RemotingKeysEnum.class);
        //SIIFContextBase.initTest();

        SessionKeyEnum[] keys = SessionKeyEnum.values();
        for (SessionKeyEnum key : keys) {
            SIIFContextBase.setParameterSession(key,
                    params.get(RemotingKeysEnum.valueOf(key.name())));
        }

        Object bean = SIIFContextBase.getAppContext().getBean((String) params.get(RemotingKeysEnum.SERVICE_BEAN));

        String methodName = (String) params.get(RemotingKeysEnum.SERVICE_NAME);
        Object[] arg = (Object[]) params.get(RemotingKeysEnum.SERVICE_ARGS);

        AsynchCall asyCall = new AsynchCall();
        try {

            asyCall.executCall(this, bean, methodName, arg);

        } catch (Exception ex) {
            Logger.getLogger(SiifPPPRemotingImpl.class.getName()).log(Level.SEVERE, null, ex);
            returnData.put(RemotingKeysEnum.EXCEPTION, ex);

        }

        while (!asyCall.isFinished()) {
            waitToWakeUp();
        }
        //Libera los recursos de la session a la base de datos
        returnData.put(RemotingKeysEnum.RESULT, asyCall.getResultCall());
        return returnData;

    }

    @Override
    public synchronized void waitToWakeUp() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(SiifPPPRemotingImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void notifyWakeUp() {
        this.notify();
    }
}
