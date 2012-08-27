/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ViewUserModuleDaoImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.dao;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.security.dto.ViewConstrainsDto;
import com.abs.siif.security.entities.UserEntity;
import com.abs.siif.security.entities.ViewUserModuleEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Israel Ruiz
 */
@Repository("viewUserModuleDao")
public class ViewUserModuleDaoImpl extends SIIFBaseDaoImpl<ViewUserModuleEntity,
        Long> implements
        ViewUserModuleDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViewConstrainsDto> getUserConstrains(String itemLevelAttribute) {
        Integer myAnnio = 0;
        
        Object objAnio = SIIFContextBase.getParamLocalThread(KeyContextEnum.YEAR_SEARCH);
        if (objAnio != null ){
            myAnnio = Integer.parseInt(objAnio.toString());
        }else {
        
            myAnnio = Integer.parseInt(
                    SIIFContextBase.getParameterSession(
                            SessionKeyEnum.YEAR).toString());
        }
        
        UserEntity myUser = (UserEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.USER);

        String myQueryString = "select "
                + " budgetItem.budgetKeyItemBaseEntity as entityName"
                + ",budgetItem.budgetKeyItemLevelAttribute as levelEntity,"
                + "items.viewItemCriteria as condition"
                + " from  ViewUserModuleEntity viewU"
                + "  join viewU.viewUserModuleView viewUM"
                + "   join viewUM.budgetKeyDefinitionBudgetKey budget"
                + "   join viewUM.viewItems items"
                + "   join items.viewItemBudgetKeyItem budgetItem"
                + " where (budget.presupuestalKeyDefinitionYear=%s)and"
                + "        (viewU.viewUserModuleUser.userId=%s)";
        
        if (itemLevelAttribute != null) {
            myQueryString += " and"
                    + " (budgetItem.budgetKeyItemLevelAttribute='%s') ";
            myQueryString = 
                    String.format(myQueryString, myAnnio, myUser.getUserId()
                    .toString(),
                    itemLevelAttribute);
        }else{
        
            myQueryString = 
                    String.format(myQueryString, myAnnio, myUser.getUserId()
                    .toString());
            
        }
        Query myQuery = theirSessionFactory.getCurrentSession().
                                            createQuery(myQueryString);

        List myResults = myQuery.setResultTransformer(
                Transformers.aliasToBean(ViewConstrainsDto.class)).list();

        return myResults;
    }
}
