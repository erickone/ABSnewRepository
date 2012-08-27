/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ViewUserDependecy
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao.composite;

import com.abs.siif.base.dao.SIIFBaseilterView;
import com.abs.siif.security.dto.ViewConstrainsDto;

/**
 *
 * @author Israel Ruiz
 */
public interface ViewUserConstrains extends SIIFBaseilterView{
    
    /**
     * Se inyecta un filtro en caso de que existe mas de una condición 
     * para la llamana en la que participa la vista
     * @param argFilter 
     */
    public void setFilter(ViewUserConstrains argFilter);
    /**
     * Coloca las restriciones que estan configuradas en base al usuario y
     * el año
     * @param constrains 
     */
    public void setConstrains(ViewConstrainsDto constrains);
}
