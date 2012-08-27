/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.programming.controller.InvPrefileSearchController;
import com.abs.siif.support.UtilValidations;
import java.io.Serializable;
import javax.faces.application.FacesMessage;

/**
 *
 * @author jacob.flores
 */
public class InvPreFileSearchUIHelper extends SIIFControllerBase implements Serializable
{

  public boolean validateRequiredFields(InvPrefileSearchController aInvPrefileSearchController)
  {
    boolean myResult = Boolean.TRUE;

    if (!UtilValidations.notNullOrBlank(aInvPrefileSearchController.getYear()))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("ppp.progr.InvPrefileSearch.MissingYear"),
              this.getMessage("ppp.progr.InvPrefileSearch.MissingYear"));
      myResult = false;
    }
    return myResult;
  }

  /**
   * Este método valida la regla de negocio que dice: dentro de un rango no mayor a cinco
   * años hacia atrás y hacia adelante, respecto al año de operación. Ejemplo: Si el año
   * de operación es 2012, el valor mínimo permitido es lo que resulta de restar: 2012-5 y
   * el valor máximo permitido es 2012 + 5.
   *
   * @return
   */
  public boolean validateYearOfSearch(InvPrefileSearchController aInvPrefileSearchController)
  {
    boolean myResult = Boolean.TRUE;
    int myProvidedYear = Integer.parseInt(aInvPrefileSearchController.getYear());
    int myCurrentYear = Integer.parseInt(aInvPrefileSearchController.getCurrentOperationYear());
    int myMaxPermittedYear = myCurrentYear + 5;
    int myMinPermittedYear = myCurrentYear - 5;

    if ((myProvidedYear < myMinPermittedYear))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("ppp.progr.InvPrefileSearch.MinYear", Integer.toString(myMinPermittedYear)),
              this.getMessage("ppp.progr.InvPrefileSearch.MinYear", Integer.toString(myMinPermittedYear)));
      myResult = false;
    }
    else
    {
      if ((myProvidedYear > myMaxPermittedYear))
      {
        addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                this.getMessage("ppp.progr.InvPrefileSearch.MaxYear", Integer.toString(myMaxPermittedYear)),
                this.getMessage("ppp.progr.InvPrefileSearch.MaxYear" , Integer.toString(myMaxPermittedYear)));
        myResult = false;
      }
    }
    return myResult;
  }
}
