/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceDaoFilters
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public interface DependenceDaoFilters {
     /**
     * Obtiene las dependencias que son programables
     * en base el filtro, utilizado por el AOP
     * @param filter filtro utilizado para restringir para la consuilta
     * @return 
     */
    public List<DependenceEntity> getViewDepHasProgrammingFilter(String filter);
    /** Obtiene las Dependencias que estan marcadas con InstitutionalPlan
     *  se manda un filtrado segun determine la seguridad
     * @param filter
     * @return 
     */
    public List<DependenceEntity> getViewDepHasInstitutionalPlanFilter(String filter);
    
    /**
     * Se utiliza para obtener las dependencias tipo UR con filtro
     * @param filter
     * @return 
     */
    public List<DependenceEntity> getViewDepTypeURFilter(String filter);
    
    /**
     * Se utiliza para obtener su información filtrada;
     * de las dependencias con presupuesto
     */
    public Collection<DependenceEntity> getViewHasBudgetFilter(String filter);
    /**
     * Obtiene todas las Dependencias que esten marcadas como Ejecutoras
     * y ademas que requieran de ser filtradas
     * @param filter
     * @return 
     */
    public  List<DependenceEntity>  getViewDepIsExecutionUnitFilter(String filter);
    /**
     * Obtiene las dependencias marcadas como Branch y que deben de ser filtradas
     * @param filter
     * @return 
     */
    public List<DependenceEntity> getViewDepIsBranchFilter(String filter);
    /**
     * Obteine las dependencias que estan marcadas como Sector y que requieren 
     * de ser filtradas
     * @param filter
     * @return 
     */
    public List<DependenceEntity> getViewDepIsSectorFilter(String filter);
    
         /**
      * Obtiene las dependencias que estan marcadas como objeto para encuadre
      * y que requieren de ser filtradas
      * @return 
      */
     public List<DependenceEntity> getViewDepHasObjFrameFilter(String filter);
     /**
      * Obtiene todas las dependencias de son de tipo presupuestal 
      * y que requieren un filtro de seguridad
      * @param filter
      * @return 
      */
     public List<DependenceEntity> getViewDepIsBudgetUnitFilter(String filter);
     /**
      * Obtiene una lista de dependencias que sean de tipo presupuestable
      * y que deban de verificarse segun el nivel de seguridad
      * @param idDependency
      * @param filter
      * @return 
      */
     public List<DepencenceDto> getDependciesIsBudgetByDependIdRelatedFilter(
            Long idDependency,  String filter);
      /**
       * Obtiene las dependecias que se encuentran marcadas como responsables
       * y que estaran requeridas a traves de un criterio de seguridad
       * @param idDependency
       * @param filter
       * @return 
       */
      public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelatedFilter(

            Long idDependency, String filter);
       /**
     * Obtiene las dependencias que son de tipo ejecutara que se obtienen
     * a partir de un id de uan dependencia da y que se require sea filtrada
     * por seguridad
     * @param idDependency
     * @param filter
     * @return 
     */
      
      
    public List<DepencenceDto> getDependciesIsExecUnitByDependIdRelatedFilter(

            Long idDependency,  String filter);
    
    public List<DepencenceDto> getDependciesHasInstPlanByDependIdRelatedFilter(

            Long idDependency, String filter);
    
    public List<DepencenceDto> getDependciesIsBranchByDependIdRelatedFilter(
            Long idDependency,  String filter);
    
    public List<DepencenceDto> getDependciesIsSectorByDependIdRelatedFilter(
            Long idDependency,  String filter);
   
    public List<DepencenceDto> getDependciesHasBudgetByDependIdRelatedFilter(
            Long idDependency,  String filter);
    public List<DepencenceDto> getDependciesHasProgrammingByDependIdRelatedFilter(
            Long idDependency,  String filter);

    
    public List<DepencenceDto> getDependciesHasObjectiveFramingByDependIdRelatedFilter(

            Long idDependency,  String filter);

 public List<DepencenceDto> getDependciesIsFrammingSectorByDependIdRelatedFilter(
            Long idDependency, String filter);
    
}
