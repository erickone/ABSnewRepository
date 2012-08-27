/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.ProgrammingDao;
import com.abs.siif.programming.entities.ProgrammingEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Esta es la implementacion del arbol de la programación que regresa la lista 
 * jerarquizada de la tabla de Programas para contruir el arbol
 * @author Erick Leija
 */
@Service("programmingManagement")
public class ProgrammingManagementImpl implements ProgrammingManagement, Serializable
{
    @Resource(name="programmingDaoImpl")
    private ProgrammingDao theirProgrammingDao;

    /**
     * Este Metodo sirve para ir a la base de datos y se obtiene el registro 
     * que tiene ese Id
     * @param anIdentity
     * @return 
     */
    @Override
    public Collection<ProgrammingEntity> getProgrammingByObjectiveId(Long anIdentity)
    {
        return theirProgrammingDao.getProgrammingByObjectiveId(anIdentity);
    }

    /**
     * Obtiene una Lista de Programas tomando como referencia los parametros enviados 
     * que son: el Id de la Dependencia y el Id del Objetivo, los dos de ultimo nivel
     * 
     * @param aDependencyId es el Id de la dependencia de ultimo nivel
     * @param aObjectiveId es el Id del Objetivo de Ultimo nivel Seleccionado
     * @return una lista jerarquizada para oncstruir un arbol con el encuadre de
     * dependencia con el objetivo
     */
    @Override
    public Collection<ProgrammingEntity> getProgramminByDependenceAndObjective(Long aDependencyId, Long aObjectiveId)
    {
        //Aqui se obtiene la lista de programas de ultimo nivel que tienen encuadre
        List<ProgrammingEntity> myListOfprograms = 
                new ArrayList<ProgrammingEntity>
                (theirProgrammingDao.getProgramminByDependenceAndObjective(aDependencyId, aObjectiveId));
        //aqui se obtiene la lista jerarquica que se obtiene con base a los programas obtenidos
        Collection<ProgrammingEntity> myCollection;
        if (myListOfprograms.isEmpty())
        {
            myCollection = new ArrayList<ProgrammingEntity>();
        }
        else
        {
            myCollection = theirProgrammingDao.getHierarchicalProgramsByEntities(myListOfprograms);    
        }
        return myCollection;
    }

    
    
}
