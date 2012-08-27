/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  IntOnlyConverter
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.uihelpers;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.IntegerConverter;

/**
 *
 * @author Erick Leija
 */
@FacesConverter("intOnly")
public class IntOnlyConverter extends IntegerConverter
{
   @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException 
   {
        if (!value.matches("\\d*")) {
            throw new ConverterException();
        }

        return super.getAsObject(context, component, value);
    }
}

