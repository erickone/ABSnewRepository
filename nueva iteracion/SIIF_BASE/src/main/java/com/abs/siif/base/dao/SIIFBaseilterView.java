/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  SIIFBaseilterView
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.base.dao;

/**
 *
 * @author Israel Ruiz
 */
public interface SIIFBaseilterView {
     /**
     * Query al que se la agregará la restrincción indicada por 
     * el contrains configurado
     * @param sqlData query al caul se evaluará para inyectar las 
     * restricciones
     * @return  query con condiciones inyectadas si aplica
     */
    public String addConstrains(String sqlData); 
}
