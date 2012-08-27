/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.common;

import com.abs.siif.base.exception.BaseBusinessException;
import java.sql.SQLException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class MapperDaoException {

    private static final String MESSAGE_FK_OBJECTIVELEVEL_OBJECTIVE = "No es posible eliminar el registro seleccionado, ya que existe información relacionada al mismo";
    private static final String MESSAGE_UNIQUE_LEVEL = "La descripción ya ha sido asignada";
    private static final String MESSAGE_UNIQUE_LEVEL_KEY = "La clave de nivel ya ha sido asignada";
    private static final String MESSAGE_UNIQUE_CVE_PPTAL = "Se ha agregado una Clave presupuestal con la misma fuente de fianciamiento";
    private static final String MESSAGE_UNIQUE_CANDIDATE = "Ya existe un registro guardado con la misma información";

    public static BaseBusinessException convertConstraintExceptionInBusinessException(
            ConstraintViolationException anError) {
        String myConstraintName = anError.getConstraintName();

        String myErrorMessage = "";
        DaoExceptionTypes myExceptioType = DaoExceptionTypes.valueOf(myConstraintName.toUpperCase());

        switch (myExceptioType) {
            case UI_NIVOBJ_CLAVE: {
                myErrorMessage = MESSAGE_UNIQUE_LEVEL_KEY;
                break;
            }
            case UI_CLAVE:
            case UC_SIIFPPPCVEPRESUPUESTAL_CVE_FUE_CVEDEF: {
                myErrorMessage = MESSAGE_UNIQUE_CVE_PPTAL;
                break;
            }
            case UNIQUE_LEVEL: {
                myErrorMessage = MESSAGE_UNIQUE_LEVEL;
                break;
            }
            case PK_SIIFPPPNIVCLASIFFUNC:
            case PK_SIIFABSNIVCLASIFREGIONAL:
            case PK_SIIFPPPNIVELOBJETIVO:
            case PK_SIIFPPPOBJETIVO:
            case PK_SIIFABSNIVDEPENDENCIA:
            case PK_SIIFABSDEPENDENCIA: 
            case PK_SIIFPPPCLASIFFUNCIONAL: {
                myErrorMessage = MESSAGE_FK_OBJECTIVELEVEL_OBJECTIVE;
                break;
            }

        }

        return new BaseBusinessException(myErrorMessage, anError.getCause());
    }

    public static BaseBusinessException convertSQLExceptionInBusinessException(
            SQLException anError) {
        BaseBusinessException myBaseBussines = null;
        String myErrorMessage = "";
        switch (anError.getErrorCode()) {
            case -268: {
                myErrorMessage = MESSAGE_UNIQUE_CANDIDATE;
                break;
            }
        }
        
        myBaseBussines=new BaseBusinessException(myErrorMessage, anError);

        return myBaseBussines;

    }
}
