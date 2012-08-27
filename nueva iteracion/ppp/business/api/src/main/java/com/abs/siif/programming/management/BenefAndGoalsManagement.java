/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.BenefAndGoalsEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan Antonio Zavala Aguilar define los metodos de negocio para metas
 * y beneficiarios de la preficha
 */
public interface BenefAndGoalsManagement {

    Map<String, List<?>> getSupportBenGoalList();

    Long persist(BenefAndGoalsEntity myBenefAndGoal);

    BenefAndGoalsEntity getBenefGoalsByInvPreFile(Long invPreFileId);
}
