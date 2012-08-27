/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dao.InstitutionalPlanDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * esta implementación es el que se encargara de proporcionar los datos necesarios por 
 * el modulo de Programación, relativos a la entidad de Plan Institucional
 * @author Erick Leija
 */
@Service("instPlanProgManagementImpl")
public class InstPlanProgManagementImpl implements InstPlanProgManagement, Serializable
{
    @Resource(name="InstitutionalPlanDao")
    private InstitutionalPlanDao theirInstitutionalPlanDao;
    
     @Resource(name="DependenceDao")
    private DependenceDao theirDependenceDao;
    
   

    /** Este metodo regresa la lista Dummy magicamente de los planes institucionales, para
     * la presentacion, pero en realidad deberia de conectarse con el dao de la institutional Plan Entity
     * este metodo regresa una lista de planes institucionales relacionados con el Id
     * de la dependencia proporcionado
     * @param aDependenceID
     * @return 
     */
    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(Long aDependenceId)
    {
        DependenceEntity myDependence = theirDependenceDao.getDependenceById(aDependenceId);
        //Obtener 
        Long myIdWithIP2 = myDependence.getFather().getDependenceId();
        Long myIdWithIP = myDependence.getDependenceId();
       // return theirInstitutionalPlanDao.getInstitutionalPlanByDependenceId(myIdWithIP);
        return theirInstitutionalPlanDao.getInstitutionalPlanByDependenceId(myIdWithIP2);
         //populateListOfInstitutionalPlan();
    }
    
   
}
