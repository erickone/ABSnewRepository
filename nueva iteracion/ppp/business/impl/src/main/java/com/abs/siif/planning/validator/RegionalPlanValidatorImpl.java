/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.validator;

import com.abs.siif.support.UtilValidations;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Component("regionPlanValidator")
public class RegionalPlanValidatorImpl implements RegionalPlanValidator {

    @Override
    public boolean validateObjectiveNotEmpty(String objectiveDescription) {
        return UtilValidations.notNullOrBlank(objectiveDescription);
    }
}
