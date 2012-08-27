/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.BigDecimalConverter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DoubleConverter;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.IntegerConverter;

/**
 *
 * @author Erick Leija
 */
@FacesConverter("digitsOnly")
public class DigitsOnlyConverter extends BigDecimalConverter 
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value.equals(""))
        {
            return "";
        }
        else
        {   try
            {
            return BigDecimal.valueOf(Double.parseDouble(value));
            }catch(Exception ex)
            {
                throw new ConverterException();
            }
        }
    }

}