/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  MapSession
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.base.context.utilTest;

import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 *
 * @author Israel Ruiz
 */
public class MapSession implements Map, HttpSession {

    private final static Map refMap;
    
    static {
        refMap = new HashMap();
    }
    
    public MapSession() {
        
    }

    @Override
    public int size() {
        return refMap.size();
    }

    @Override
    public boolean isEmpty() {
        return refMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return refMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return refMap.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return refMap.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return refMap.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return refMap.remove(key);
    }

    @Override
    public void putAll(Map m) {
        refMap.putAll(m);
    }

    @Override
    public void clear() {
        refMap.clear();
    }

    @Override
    public Set keySet() {
        return refMap.keySet();
    }

    @Override
    public Collection values() {
        return refMap.values();
    }

    @Override
    public Set entrySet() {
        return refMap.entrySet();
    }

    @Override
    public long getCreationTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long getLastAccessedTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ServletContext getServletContext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getMaxInactiveInterval() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HttpSessionContext getSessionContext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getAttribute(String name) {
        return refMap.get(name);
    }

    @Override
    public Object getValue(String name) {
        return refMap.get(name);
    }

    @Override
    public Enumeration getAttributeNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getValueNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAttribute(String name, Object value) {
        refMap.put(name, value);
    }

    @Override
    public void putValue(String name, Object value) {
        refMap.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        refMap.remove(name);
    }

    @Override
    public void removeValue(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void invalidate() {
        refMap.clear();
    }

    @Override
    public boolean isNew() {
        if (refMap.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
