package com.abs.siif.common;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Erick Leija
 */
@Scope("session")
@Controller("closeSessionController")
public class CloseSessionController implements Serializable
{
    public void closeCurrentSession(List<String> aListOfControllers)
    {
        
        for(String myController: aListOfControllers)
        {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(myController);
        }

    }
    public void cleanAllCurrentSession()
    {    //No Invalidar la session
       //FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 
    }
}
