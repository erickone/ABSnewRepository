/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.servlet.webFilters;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Israel Ruiz
 */

public class UrlsBeanMap {
 
    private Map<String, String> urlBeansMap;

    /**
     * @return the urlBeansMap
     */
    public Map<String, String> getUrlBeansMap() {
        return urlBeansMap;
    }

    /**
     * @param urlBeansMap the urlBeansMap to set
     */
    public void setUrlBeansMap(Map<String,String> urlBeansMap) {
        this.urlBeansMap = urlBeansMap;
    }
    
    public String[] getControllersBeans(String keyUrl){
        String[] beans = null;
        if(urlBeansMap != null){
            beans = urlBeansMap.get(keyUrl).trim().split(",");
        }
        return beans;
    }
    
}
