/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.ObjectiveDao;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *este Servicio, va a proveer una lista con los objetivos de ultimo nivel que se
 * van a obtener de la entidad de encuadre IntitutionalPlanFraming, despues de 
 * de haber obtenido esos datos el servcio además buscara en la entidad Objective entity
 * toda la herencia ascendente de los objetivos obtenidos en la consulta anterior
 * @author Erick Leija
 */
@Service("objectiveInstitutionalPlanFramingManagement")
public class ObjectiveInstitutionalPlanFramingManagementImpl implements ObjectiveInstitutionalPlanFramingManagement, Serializable
{
    @Resource(name = "ObjectiveDao")
    private ObjectiveDao objectiveDao;
    
    private List<ObjectiveEntity> itsMagicalListOfObjectives = new ArrayList<ObjectiveEntity>();
    //Este metodo recibe el Id de la dependencia y magicamente regresa la lista
    //que contiene a los objetivos de ultimo nivel relacionados con esa Dependencia
    //todo gracias al famoso encuadre, despues de muchas operaciones y llamadas recursivas
    //regresa la lista de los objetivos con sus padres, nosotros por facilidad regresaremos
    //una lista completa de todos los objetivos, la pueden quitar cuando empiezen a trabajar con
    //este management

    @Override
    public List<ObjectiveEntity> getAllObjByDependId(Long aDependenceId)
    {
        return objectiveDao.GetAllObjectives();
    }
    

}
