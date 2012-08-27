/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.ProgrammingEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface ProgrammingManagement
{
    Collection<ProgrammingEntity> getProgrammingByObjectiveId(Long anIdentity);
    
    Collection<ProgrammingEntity> getProgramminByDependenceAndObjective(Long aDependencyId,Long aObjectiveId);
    
    
    
}
