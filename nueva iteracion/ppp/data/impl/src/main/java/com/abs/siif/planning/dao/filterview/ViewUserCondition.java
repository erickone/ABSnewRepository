/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CompositeDependency
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao.filterview;

import com.abs.siif.planning.dao.composite.ViewUserConstrains;
import com.abs.siif.security.dto.ViewConstrainsDto;

/**
 *
 * @author Israel Ruiz
 */
public abstract class ViewUserCondition implements ViewUserConstrains {

   
    protected ViewUserConstrains filter;
    protected ViewConstrainsDto constrains;

    @Override
    public abstract String addConstrains(String sqlData);

 

    @Override
    public void setFilter(ViewUserConstrains argFilter) {
        filter = argFilter;
    }

    /**
     * @return the constrains
     */
    public ViewConstrainsDto getConstrains() {
        return constrains;
    }

    /**
     * @param constrains the constrains to set
     */
    public void setConstrains(ViewConstrainsDto constrains) {
        this.constrains = constrains;
    }
}
