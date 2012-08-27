/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.io.Serializable;
import javax.faces.application.FacesMessage;

/**
 *
 * @author jacob.flores
 */
public class RegionalCatalogUIHelper  extends SIIFControllerBase implements Serializable
{
  public boolean validateRequiredFields(RegionalClassifierEntity aRegionalClassifier)
  {
    boolean myResult = false;
    if(aRegionalClassifier.getRegionalClassifierKey().equals(""))
    {
      myResult = Boolean.FALSE;
       addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("Debe introducir una Clave para la región"),
              this.getMessage("Debe introducir una Clave para la región"));
    }
    else if(aRegionalClassifier.getRegionalClassifierDescription().equals(""))
    {
      myResult = Boolean.FALSE;
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("Debe introducir una Descripción para la región"),
              this.getMessage("Debe introducir una Descripción para la región"));
    }
    else
    {
      myResult = Boolean.TRUE;
    }
    return myResult;
  }
          
}