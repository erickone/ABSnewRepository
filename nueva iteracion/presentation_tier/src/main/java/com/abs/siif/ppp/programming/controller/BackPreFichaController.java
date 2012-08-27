/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.InvPrefileSearchControllerApi;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author absvalenzuela
 */
@Scope("session")
@Controller("backPreFichaController")
public class BackPreFichaController implements Serializable{
    
     @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi objControllerProject;
     
     @Resource(name = "invPrefileSearchController")
    private transient InvPrefileSearchControllerApi objControllerPreFicha;
     
    private boolean fromConsult;
     
    public DraftProjectHeaderControllerApi getObjControllerProject() {
        return objControllerProject;
    }

    public void setObjControllerProject(DraftProjectHeaderControllerApi objControllerProgramming) {
        this.objControllerProject = objControllerProgramming;
    }

    public InvPrefileSearchControllerApi getObjControllerPreFicha() {
        return objControllerPreFicha;
    }

    public void setObjControllerPreFicha(InvPrefileSearchControllerApi objControllerPreFicha) {
        this.objControllerPreFicha = objControllerPreFicha;
    }   
    
    /**
     * Este metodo es el encargado de ejecutarse una vez qeu se haya presionado el boton de Atras 
     * para cada una de las pantallas donde aparece ya que por medio de la varible fromConsult, que nos
     * identifica si viene de una consulta o del proyecto.
     * 
     */
    public void backFlangeProgramming (){
        if (!isFromConsult()) {
            this.setFromConsult(false);
            objControllerProject.setTheirCurrentDraftProjectId(objControllerProject.getTheirCurrentDraftProjectId());
        }else{
            this.setFromConsult(true);
            objControllerPreFicha.navigateToMainMenu();
        }
        
    }
    
    /**
     * Este metodo es el encargado de devolver el nombre de la pagina hacia donde se redireccionara,
     * una vez que se haya ejectuado el evento del boton de atras.
     * 
     * @return String - Pagina a donde redirecciona al presionar el boton atras
     */
    public String navigationPage (){
        String navigatePage = "";
        if (!isFromConsult()){
            this.setFromConsult(false);
            navigatePage = "newDraftProject";
        }else{
            this.setFromConsult(true);
            navigatePage = objControllerPreFicha.navigatePreviousPreFicha();
        }
        return navigatePage;
    }
        /**
     * @return the fromColsult
     */
    public boolean isFromConsult() {
        return fromConsult;
    }

    /**
     * @param fromConsult the fromColsult to set
     */
    public void setFromConsult(boolean fromConsult) {
        this.fromConsult = fromConsult;
    }
    
}
