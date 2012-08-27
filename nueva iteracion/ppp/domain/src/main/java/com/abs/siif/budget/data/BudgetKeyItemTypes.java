/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.data;

import java.util.Map;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public enum BudgetKeyItemTypes {

    UR {

        @Override
        String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString) {
            return replaceParameterForDependency(anIdentities, aQueryString);
        }
    },
    UP {

        @Override
        String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString) {
            return replaceParameterForDependency(anIdentities, aQueryString);
        }
    },
    UEG {

        @Override
        String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString) {
            return replaceParameterForDependency(anIdentities, aQueryString);
        }
    },
    PROG {

        @Override
        String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString) {
            return replaceParameterForProject(anIdentities, aQueryString);
        }
    },
    PROY {

        @Override
        String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString) {
            return replaceParameterForProject(anIdentities, aQueryString);
        }
    },
    OBJG {

        @Override
        String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString) {
            String myKeySearch = "idobjetogasto";
            String aConstantKey = "$idobjetogasto";
            return replaceParameter(myKeySearch, aConstantKey, anIdentities, aQueryString);
        }
    },
    DESTINOOBJG {

        @Override
        String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString) {
            String myKeySearch = "iddestino";
            String aConstantKey = "$iddestino";
            return replaceParameter(myKeySearch, aConstantKey, anIdentities, aQueryString);
        }
    };

    private static String replaceParameterForDependency(Map<String, Long> anIdentities, String aQueryString) {
        String myKeySearch = "iddependencia";
        String aConstantKey = "$iddependencia";
        return replaceParameter(myKeySearch, aConstantKey, anIdentities, aQueryString);
    }

    private static String replaceParameterForProject(Map<String, Long> anIdentities, String aQueryString) {
        String myKeySearch = "idproyecto";
        String aConstantKey = "$idproyecto";
        return replaceParameter(myKeySearch, aConstantKey, anIdentities, aQueryString);
    }

    abstract String replaceParameterInQueryString(Map<String, Long> anIdentities, String aQueryString);

    private static String replaceParameter(String aKeySearch, String aConstantKey,
            Map<String, Long> anIdentities, String aQueryString) {
        Long myIdentity = anIdentities.get(aKeySearch);
        String myQueryString = "";
        if (myIdentity != null) {
            myQueryString=aQueryString.replace(aConstantKey, myIdentity.toString());
            myQueryString = myQueryString.replace("$assert", "t");
        }
        return myQueryString;
    }

    public String constructQueryString(Map<String, Long> anIdentities, String aQueryString) {
        return replaceParameterInQueryString(anIdentities, aQueryString);
    }
}
