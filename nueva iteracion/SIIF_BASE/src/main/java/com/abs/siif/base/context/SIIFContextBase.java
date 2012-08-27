/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.context;

import com.abs.siif.base.context.utilTest.MapSession;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Esta clase mantien una referencia a contexto de Spring esto permitirá tener
 * control para acceder a procedimientos generales de Spring, por ejemplo a los
 * recurso de mensajes
 *
 * @author Israel Ruiz
 */
//@Component
public class SIIFContextBase implements ApplicationContextAware {

    private static ApplicationContext context;
    private static Map<String, Object> siffContext = new HashMap<String, Object>();
    /**
     * Declare a local Thread to handle one context by thread
     */
    private static ThreadLocal<Map<KeyContextEnum, Object>> localcontext =
            new ThreadLocal<Map<KeyContextEnum, Object>>();
    /**
     * Inicializa el contexto para el thread local
     */
    public static void initLocal() {
        if (localcontext.get() == null) {
            localcontext.set(new HashMap<KeyContextEnum, Object>());
        }
    }

    /**
     * Es utilizada excusivamente para pruebas
     */
    public static void initTest(){
        if (localcontext.get() == null) {
            localcontext.set(new HashMap<KeyContextEnum, Object>());
        }
        localcontext.get().put(KeyContextEnum.SESSION, new MapSession());
    }
    /**
     * @return the localcontext
     */
    public static ThreadLocal<Map<KeyContextEnum, Object>> getLocalcontext() {
        return localcontext;
    }

    /**
     * @param aLocalcontext the localcontext to set
     */
    public static void setLocalcontext(ThreadLocal<Map<KeyContextEnum, Object>> aLocalcontext) {
        localcontext = aLocalcontext;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.context.ApplicationContextAware#setApplicationContext
     * (org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context = ac;
    }

    /**
     * Get app context
     *
     * @return application Context
     */
    public static ApplicationContext getAppContext() {
        return context;
    }
    /*
     * Coloca los valores para el contexto local
     */
    public static void setParamContext(KeyContextEnum key, Object objContext) {
        siffContext.put(key.name(), objContext);
    }
    /**
     * Objtiene el valor asociado a la llave desde el contexto local
     * este contexto solo vive a nivel del thread actual (request)
     * @param key 
     * @return 
     */
    public static Object getParamContext(KeyContextEnum key) {
        return siffContext.get(key.name());
    }
    /**
     * Coloca los valores al contexto que serám colocados a nivel de session
     * del usuario
     * @param key
     * @param object 
     */
    public static void setParameterSession(SessionKeyEnum key, Object object) {
        HttpSession session = (HttpSession) localcontext.get().get(KeyContextEnum.SESSION);
        if (session != null) {
            session.setAttribute(key.name(), object);
        }
    }
    
    /**
     * Coloca un parametro a nivel del Local Thread
     */
    public static void setParamLocalThread(KeyContextEnum key, Object data){
        localcontext.get().put(key, data);
    }
    
     /**
     * Obtiene un parametro del nivel del Local Thread
     */
    public static Object getParamLocalThread(KeyContextEnum key){
        return localcontext.get().get(key);
    }

    /**
     * Obtiene un objeto del contexto de nivel de session del usuario
     * @param key
     * @return 
     */
    public static Object getParameterSession(SessionKeyEnum key) {
        HttpSession session = (HttpSession) localcontext.get().get(KeyContextEnum.SESSION);
        Object result = null;
        if (session != null) {
            result = session.getAttribute(key.name());
        }
        if(result == null){
            result = siffContext.get(key.name());
        }
        return result;
    }
    
    public static void invalidateContext()
    {
        HttpSession session = (HttpSession) localcontext.get().get(KeyContextEnum.SESSION);
        if (session != null) {
            session.invalidate();
        }
    }
}
