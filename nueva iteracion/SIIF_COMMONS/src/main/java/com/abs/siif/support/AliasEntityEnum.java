/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AliasEntityEnum
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.support;

/**
 * Enumerador que contiene las entidades que participan en las vistas
 * y las cuales pueden ser configurables por usuario y año
 * cada entrada en Enum deberá de proporcionar el alias de la
 * entidad así como los datos relacionados a la llave con que va 
 * hacer los filtrados contra el View, el link al Nivel
 * y la entidad relacionada para dar los niveles, o en caso contario
 * los datos de propiedad de relacion al nivel y la entidad de niles
 * podran ser nulos o blancos
 * @author abs70
 */
public enum AliasEntityEnum {
    DEPENDENCY("DependenceEntity",
             "dependenceHidden","dependenceLevel",
             "com.abs.siif.planning.entities.DependenceLevelEntity");
     
     private String entityName;
     private String entityClave;
     private String linkToLevel;
     private String levelEntity;
     
     private AliasEntityEnum(String entity, String keyEntity, 
             String linkToLevel, String levelEntity){
         entityName = entity;
         entityClave = keyEntity;
         this.linkToLevel = linkToLevel;
         this.levelEntity = levelEntity;
     }
     
     public static AliasEntityEnum getEnumData(String entityName){
         AliasEntityEnum result = null;
         for(AliasEntityEnum  refEnum : AliasEntityEnum.values()){
             if(refEnum.getEntityName().equals(entityName)){
                 result = refEnum;
             }
         }
         return result;
     }
    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @return the entityClave
     */
    public String getEntityClave() {
        return entityClave;
    }

    
    /**
     * @return the levelEntity
     */
    public String getLevelEntity() {
        return levelEntity;
    }
    
    public boolean hasLevelEntity(){
        boolean result = false;
        if(levelEntity != null && levelEntity.trim().length()>0){
            result = true;
        }
        return result;
    }

    /**
     * @return the linkToLevel
     */
    public String getLinkToLevel() {
        return linkToLevel;
    }
}
