/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DirectCaptureDraftProjectImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dao.ObjectiveDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.programming.dao.DraftProjectStateDao;
import com.abs.siif.programming.dao.DraftProjectTypeDao;
import com.abs.siif.programming.dao.FunctionalClassifierProgrammingDao;
import com.abs.siif.programming.dao.ObjectiveProgrammingDao;
import com.abs.siif.programming.dto.ClasificationDTO;
import com.abs.siif.programming.entities.DraftProjectStateEntity;
import com.abs.siif.programming.entities.DraftProjectTypeEntity;
import com.abs.siif.programming.entities.VulnerableGroupEntity;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Israel Ruiz
 */
@Service("directCaptureDraftProject")
public class DirectCaptureDraftProjectImpl implements DirectCaptureDraftProject {

    @Resource(name = "DependenceDao")
    private DependenceDao theirDependenceDao;
    @Resource(name = "ObjectiveProgrammingDao")
    private ObjectiveProgrammingDao theirObjectiveProgrammingDao;
    @Resource(name = "ObjectiveDao")
    private ObjectiveDao theirObjectiveDao;
    @Resource(name = "draftProjectTypeDaoImpl")
    private DraftProjectTypeDao theirDraftProjectTypeDao;
    @Resource(name = "draftProjectStateDaoImpl")
    private DraftProjectStateDao theirDraftProjectStateDao;
    @Resource(name = "vulnerableGroupManagement")
    VulnerableGroupManagement vulnerableGroupManagement;
    @Resource(name = "functionalClassifierProgrammingDao")
    private FunctionalClassifierProgrammingDao theirFunctionalClassifier;

    /**
     * Obtien los catalogos requeridos para la ventana de Clasificador
     * Administrativo utilizado duirante la captura directa de la Ficha
     * (AnteProyecto)
     *
     * @param resposableUnit
     * @return ClasificationDTO
     */
    @Override
    public ClasificationDTO getCatalogs(Long aDependenceId) {
        ClasificationDTO clasificationDTO = new ClasificationDTO();
        try {
            clasificationDTO.setBudgetUnitList(theirDependenceDao.getDependciesIsBudgetByDependIdRelated(aDependenceId));
            clasificationDTO.setExecuteUnitList(theirDependenceDao.getDependciesIsExecUnitByDependIdRelated(aDependenceId));
            clasificationDTO.setBranchList(theirDependenceDao.getDependciesIsBranchByDependIdRelated(aDependenceId));
            clasificationDTO.setSectorList(theirDependenceDao.getDependciesIsSectorByDependIdRelated(aDependenceId));
        } catch (Exception ex) {
            Logger.getLogger(DirectCaptureDraftProjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clasificationDTO;
    }

    /**
     * Extrae todo lo referente a Objetivos
     *
     * @param aDependenceId
     * @return lista de objetivos
     */
    public List<ObjectiveEntity> getAllObjectivesByDependenceId(Long urId) {
        //se obtiene la entidad segun su Id
        Long aDependenceId = theirDependenceDao.GetUEGbyUR(urId);
        DependenceEntity myDependence = theirDependenceDao.getDependenceById(aDependenceId);
        //Se obtiene el plan Institucional que esta ligado a la dependencia
        Long myIdWithIP = myDependence.getDependenceId();
        //Se obtienen los objetivos que tienen el encuadre
        List<ObjectiveEntity> myListofObjectives = theirObjectiveProgrammingDao.getObjectivesByDependencyId(myIdWithIP);
        //Se regresa una lista de objetivos con su ascendencia
        return (List<ObjectiveEntity>) theirObjectiveProgrammingDao.getHierarchicalObjectivesByEntities(myListofObjectives);
    }

    @Override
    public Map<String, List<?>> getDraftProjectSupportLists() {
        Collection<DraftProjectTypeEntity> myTypes = theirDraftProjectTypeDao.getAllDraftProjectTypes();
        Collection<DraftProjectStateEntity> myStatus = theirDraftProjectStateDao.getAllDraftProjectState();
        Collection<VulnerableGroupEntity> refVulnCatalog = vulnerableGroupManagement.getVulnerableGroups();
        Map<String, List<?>> mySupportList = new HashMap<String, List<?>>();

        mySupportList.put("DraftProjectTypes", new ArrayList<DraftProjectTypeEntity>(myTypes));
        mySupportList.put("DraftProjectState", new ArrayList<DraftProjectStateEntity>(myStatus));
        mySupportList.put("VulnerableGroupEntity", new ArrayList<VulnerableGroupEntity>(refVulnCatalog));
        return mySupportList;
    }

    /**
     * Este es el metodo que regresa la lista dummy de las dependencias que son
     * Unidades Ejecutoras del Gasto(UEG), cuando se haga la logica de negocio
     * se cambiaran las firmas que apuntaran a los Dao que se hayan definido
     * para el manejo de estas operaciones
     *
     * @return
     */
    @Override
    public List<DependenceEntity> getDraftProjectDependencesByUEG() {
        Collection<DependenceEntity> myDependenceCollection =
                theirDependenceDao.getDependencesByUr();

        List<DependenceEntity> itsListOfDependencesDummy =
                new ArrayList<DependenceEntity>(myDependenceCollection);
        return itsListOfDependencesDummy;
    }

    @Override
    public Collection<FunctionalClassifierEntity> getMyAncestry(Long aObjectiveId) {

        List<FunctionalClassifierEntity> myListOfFunctionalClassifierEntity = new ArrayList<FunctionalClassifierEntity>(theirFunctionalClassifier.getFunctionalClassifiersByObjectiveId(aObjectiveId));

        return theirFunctionalClassifier.getHierarchicalFunctionalClassifierByEntities(myListOfFunctionalClassifierEntity);

    }

    /**
     * Regresa UEG en base a la UR seleccionada
     *
     * @param resposableUnit
     * @return
     */
    @Override
    public Long GetUEGbyUR(DependenceEntity resposableUnit) {
        Long aDependenceId = theirDependenceDao.GetUEGbyUR(resposableUnit.getDependenceId());
        return aDependenceId;
    }
}
