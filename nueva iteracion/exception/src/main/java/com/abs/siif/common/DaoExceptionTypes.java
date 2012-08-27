/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.common;

/**
 *
 * @author hunabKu
 */
public enum DaoExceptionTypes {

    PK_SIIFABSNIVDEPENDENCIA("PK_SIIFABSNIVDEPENDENCIA"),
    PK_SIIFPPPOBJETIVO("PK_PPPOBJETIVO"),    
    UNIQUE_LEVEL("unique_level"),
    PK_SIIFPPPNIVELOBJETIVO("PK_SIIFPPPNIVELOBJETIVO"),
    UI_NIVOBJ_CLAVE("UI_NIVOBJ_CLAVE"),
    UC_SIIFPPPCVEPRESUPUESTAL_CVE_FUE_CVEDEF("UC_SIIFPPPCVEPRESUPUESTAL_CVE_FUE_CVEDEF"),
    UI_CLAVE("UI_CLAVE"),
    PK_SIIFABSDEPENDENCIA("PK_SIIFABSDEPENDENCIA"),
    PK_SIIFPPPNIVCLASIFFUNC("PK_SIIFPPPNIVCLASIFFUNC"),//Pk entre nivel funcional y clasificador
    PK_SIIFABSNIVCLASIFREGIONAL("PK_SIIFABSNIVCLASIFREGIONAL"),//Pk de siifabsnivclasifregional
    PK_SIIFPPPCLASIFFUNCIONAL("PK_SIIFPPPCLASIFFUNCIONAL");//Pk de siifpppclasiffuncional
    
    
    private String theirValue;

    private DaoExceptionTypes(String aValue) {
        theirValue = aValue;
    }

    public void setTheirValue(String aValue) {
        theirValue = aValue;
    }
}
