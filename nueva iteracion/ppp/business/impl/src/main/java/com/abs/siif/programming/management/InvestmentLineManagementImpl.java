/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.InvestmentLineDao;
import com.abs.siif.programming.entities.InvestmentLineEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("investmentLineManagement")
public class InvestmentLineManagementImpl implements InvestmentLineManagement {

    @Resource(name = "investmentLineDao")
    private InvestmentLineDao itsInvestmentLineDao;

    @Override
    public Collection<InvestmentLineEntity> getInvestmentLines() {
        return itsInvestmentLineDao.getInvestmentLines();
    }
    
    @Override
    public InvestmentLineEntity getDefaultInvestmentLine() {
        return itsInvestmentLineDao.getDefaultInvestmentLine();
    }
}
