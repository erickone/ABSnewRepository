/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *Esta es la implementación del Dependence Management que manejara los
 * servicios relacionados con las dependencias 
 * @author Erick Leija
 */
@Service("dependenceProgrammingManagement")
public class DependenceProgrammingManagementImpl implements DependenceProgrammingManagement, Serializable
{
    @Resource(name="DependenceDao")
    private DependenceDao theirDependenceDao;
    
   
    /** 
     * Este es el metodo que regresa la lista dummy de las dependencias
     * que son Unidades Ejecutoras del Gasto(UEG), cuando se haga la logica de negocio
     * se cambiaran las firmas que apuntaran a los Dao que se hayan definido para el manejo 
     * de estas operaciones
     * @return 
     */
    @Override
    public List<DependenceEntity> getDependencesByUEG()
    {
        Collection<DependenceEntity> myDependenceCollection = 
        theirDependenceDao.getViewDepIsExecutionUnit();
        
        List<DependenceEntity> itsListOfDependencesDummy = 
                new ArrayList<DependenceEntity>(myDependenceCollection);
        return itsListOfDependencesDummy;
    }
    
    
    @Override
    public List<DependenceEntity> getDependencesHasProgramming()
    {
        Collection<DependenceEntity> myDependenceCollection = 
        theirDependenceDao.getViewDepHasProgramming();
        
        List<DependenceEntity> itsListOfDependencesDummy = 
                new ArrayList<DependenceEntity>(myDependenceCollection);
        return itsListOfDependencesDummy;
    }
    
       
    @Override
    public Collection<DependenceEntity> getViewDepWithoutFather() {
        return this.theirDependenceDao.getViewDepWithoutFather();
    }

    @Override
    public Collection<DependenceEntity> getViewDepByFather(DependenceEntity
            aDependency) {
        return this.theirDependenceDao.getViewDepByFather(aDependency);
    }
    
       
}
