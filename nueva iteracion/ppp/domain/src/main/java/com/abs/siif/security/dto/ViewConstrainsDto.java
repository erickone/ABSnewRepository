/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ViewConstrainsDto
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.dto;

import java.io.Serializable;

/**
 *
 * @author Israel Ruiz
 */
public class ViewConstrainsDto implements 
        Comparable<ViewConstrainsDto>, Serializable {
    //Las dos primeras salen de BudgetKeyItemEntity
   private String entityName;
   private String levelEntity;
   //Esta ultima sale de ViewItemEntity
   private String condition;

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * @return the levelEntity
     */
    public String getLevelEntity() {
        return levelEntity;
    }

    /**
     * @param levelEntity the levelEntity to set
     */
    public void setLevelEntity(String levelEntity) {
        this.levelEntity = levelEntity;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    /**
     * Compara dato a dato para verificar si son iguales
     * en caso de que sea iguales manda cero
     */
    public int compareTo(ViewConstrainsDto obj) {
        int result = -1;
         result =  condition.compareTo(obj.condition) ;
         return result;
    }
   
    
   
}
