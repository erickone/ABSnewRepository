/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.InvestmentLineEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface InvestmentLineDao {
    
    Collection<InvestmentLineEntity> getInvestmentLines();
    
    InvestmentLineEntity getDefaultInvestmentLine();
}
