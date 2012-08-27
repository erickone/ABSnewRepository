/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import java.util.List;

/**
 *Esta interfaz de encarga de listar los metodos a implementar
 * @author Erick Leija
 */
public interface InstPlanProgManagement
{
    List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(Long aDependenceID);
}
