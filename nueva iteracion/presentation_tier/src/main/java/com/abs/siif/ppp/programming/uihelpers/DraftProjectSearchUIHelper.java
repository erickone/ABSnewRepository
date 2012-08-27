/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.programming.controller.DraftProjectSearchController;
import com.abs.siif.support.UtilValidations;
import java.io.Serializable;
import javax.faces.application.FacesMessage;

/**
 *
 * @author jacob.flores Esta clase implementa métodos de apoyo a la clase controladora.
 */
public class DraftProjectSearchUIHelper extends SIIFControllerBase implements Serializable
{

  public boolean validateRequiredFields(DraftProjectSearchController aDraftProjectSearchController)
  {
    boolean myResult = Boolean.TRUE;

    if (!UtilValidations.notNullOrBlank(aDraftProjectSearchController.getYear()))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("ppp.progr.yearNotAsigned"),
              this.getMessage("ppp.progr.yearNotAsigned"));
      myResult = false;
    }
  if (!UtilValidations.notNullOrBlank(aDraftProjectSearchController.getDependenceId()))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("ppp.progr.DepNotSelected"),
              this.getMessage("ppp.progr.DepNotSelected"));
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
  public boolean validateYearOfSearch(DraftProjectSearchController aDraftProjectSearchController)
  {
    boolean myResult = Boolean.TRUE;
    int myProvidedYear = Integer.parseInt(aDraftProjectSearchController.getYear());
    int myCurrentYear = Integer.parseInt(aDraftProjectSearchController.getCurrentOperationYear());
    int myMaxPermittedYear = myCurrentYear + 5;
    int myMinPermittedYear = myCurrentYear - 5;

    if ((myProvidedYear < myMinPermittedYear))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("ppp.progr.yearErrorLess" + myMinPermittedYear),
              this.getMessage("ppp.progr.yearErrorLess" + myMinPermittedYear));
      myResult = false;
    }
    else
    {
      if ((myProvidedYear > myMaxPermittedYear))
      {
        addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                this.getMessage("ppp.progr.yearErrorMore" + myMaxPermittedYear),
                this.getMessage("ppp.progr.yearErrorMore" + myMaxPermittedYear));
        myResult = false;
      }
    }
    return myResult;
  }
}