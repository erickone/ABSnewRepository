/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.programming.dao.ObjectiveProgrammingDao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Esta implemetacion regresa las listas de los objetivos especificos y los
 * problemas relacionados al Objetivo Seleccionado, 
 * @author Erick Leija
 */
@Service("objectiveProgrammingManagement")
public class ObjectiveProgrammingManagementImpl implements ObjectiveProgrammingManagement, Serializable
{ 
    @Resource(name="ObjectiveProgrammingDao")
    private ObjectiveProgrammingDao theirObjectiveProgrammingDao;
    @Resource(name="DependenceDao")
    private DependenceDao theirDependenceDao;
   
    /**
     * Este Metodo regresa una lista de Objetivos que tienen encuadre con la Dependencia
     * @param aDependenceId
     * @return 
     */
    @Override
    public List<ObjectiveEntity> getAllObjectivesByDependenceId(Long aDependenceId)
    {
        //se obtiene la entidad segun su Id
      // DependenceEntity myDependence = theirDependenceDao.getDependenceById(aDependenceId);
        //Se obtiene el plan Institucional que esta ligado a la dependencia
        //Long myIdWithIP = myDependence.getDependenceId();
        //Se obtienen los objetivos que tienen el encuadre
        List<ObjectiveEntity> myListofObjectives = theirObjectiveProgrammingDao.getObjectivesByDependencyId(aDependenceId);
        //Se regresa una lista de objetivos con su ascendencia
        return (List<ObjectiveEntity>)theirObjectiveProgrammingDao.getHierarchicalObjectivesByEntities(myListofObjectives);
    }
}
