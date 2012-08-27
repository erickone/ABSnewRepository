/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.SIIFBase;
import java.io.Serializable;

/**
 *
 * @author jacob.flores
 */
public class PreInvRequestUIHelper extends SIIFBase implements Serializable
{
  /**
   * Este método se encarga de convertir el Boolean que se recibe del PreInvRequest
   * a un valor de tipo entero, para poder ser usado por el control SelectOneRadioButton
   * @param aBooleanValue
   * @return 
   */
  public int converterBooleanExpresionToInt(boolean aBooleanValue)
  {
    int myIntValue;
    if(aBooleanValue)
    {
      myIntValue = 1;
    }
    else
    {
      myIntValue = 0;
    }
    return myIntValue;
  }
  
  /**
   * Este método convierte a boolean un valor entero obtenido del control select oneRadioButton
   * @param anIntValue
   * @return 
   */
  public boolean converterIntExpresionToBoolean(int anIntValue)
  {
    boolean myBooleanValue;
    if(anIntValue == 1)
    {
      myBooleanValue = Boolean.TRUE;
    }
    else
    {
      myBooleanValue = Boolean.FALSE;
    }
    return myBooleanValue;
  }
}
