/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.common;


import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Locale;
import java.util.ArrayList;

/**
 * Handles messages and parameter
 * @author alvaro.ruiz
 *
 */
public class ResourceBundleMassage extends SIIFContextBase{        

        private static List<ResourceBundle> resourceBundleFiles;
        
	/**
	 * Constructor
	 */
	private ResourceBundleMassage(){            
	}
        
        /**
	 * se obtienen los paths de los properties y el locale para la creación
         * de ResourceBundles
	 */
        static{            
            String [] resources =(String [])SIIFContextBase.getParamContext(KeyContextEnum.RESOURCE);
            String localeString =(String)SIIFContextBase.getParamContext(KeyContextEnum.LOCALE);
            StringTokenizer st = new StringTokenizer(localeString,"_");
            Locale locale = new Locale(st.nextToken(),st.nextToken());
            createResourceBundleFiles(resources,locale);
        }        
        
        /**
	 * metodo encargado de crear los ResourceBundles
	 */
        private static void createResourceBundleFiles(String [] files, Locale locale){
            resourceBundleFiles = new ArrayList<ResourceBundle>();           
            for(String resourcePath: files){
                System.out.println(resourcePath);
                ResourceBundle resourceBundle = ResourceBundle.getBundle(resourcePath,locale);
                resourceBundleFiles.add(resourceBundle);
            }                      
        }
        
       

	/** 
	 * Get the value associate with the key 
	 * 
	 * @param key
	 * @return Valor
	 */
	public static String getString(String key) {
            String result = "";
            boolean keyExists = Boolean.FALSE;
            for(ResourceBundle resourceBundle: resourceBundleFiles){
                if(resourceBundle.containsKey(key)){
                    result = resourceBundle.getString(key);
                    keyExists = Boolean.TRUE;
                    break;
                }
            }
            if(!keyExists){
                result =  '!' + key + '!';
            }
            return result;              
	}

	/**
	 * Get the value associate with the key
	 * and replace the values according to the position
	 * 
	 * @param key
	 *            
	 * @param replace values
	 *           
	 * @return message with values replaced
	 */
	public static String getString(String key, String... values) {          
		String result = null;
                boolean keyExists = Boolean.FALSE;
                for(ResourceBundle resourceBundle: resourceBundleFiles){
                    if(resourceBundle.containsKey(key)){
                        String msg = resourceBundle.getString(key);
                        if (values != null) {
                            for (int i = 0; i < values.length; i++) {
                                msg =  msg.replaceAll("\\{" + i + "\\}", values[i]);
                            }
                        }
                        result = msg;
                        keyExists = Boolean.TRUE;
                        break;
                    }
                }
                if(!keyExists){
                    result =  '!' + key + '!';
                }
                return result;	
	}

	public static Integer getIntValue(String key){
            Integer result = null;
            boolean keyExists = Boolean.FALSE;
            for(ResourceBundle resourceBundle: resourceBundleFiles){
                if(resourceBundle.containsKey(key)){
                    String data = resourceBundle.getString(key);
                    try{
                        result = Integer.parseInt(data.trim());
                    }catch (Exception e) {
                        result = null;
                    }                      
                    keyExists = Boolean.TRUE;
                    break;
                }
            }
            if(!keyExists){                  
                result = null;
            }
            return result; 
        }
}
