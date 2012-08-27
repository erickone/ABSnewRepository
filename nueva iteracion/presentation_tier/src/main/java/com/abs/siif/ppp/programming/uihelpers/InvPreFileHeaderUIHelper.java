/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.controller.InvPreFileController;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.FederalURRegulatoryEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.PromoterEntity;
import com.abs.siif.support.UtilValidations;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 *
 * @author FENIX-02
 */
public class InvPreFileHeaderUIHelper extends SIIFControllerBase  implements Serializable
{
    public  Boolean validateInvPreFile(InvPreFileController anInvPreFileController)
    {
        Boolean isValid = true;
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileType()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrType"),
                    this.getMessage("ppp.progr.InvPreFileErrType"));
            isValid = false;
        }
        
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileDesagLevel()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrDesLevel"),
                    this.getMessage("ppp.progr.InvPreFileErrDesLevel"));
            isValid = false;
        }
        
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileName()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrName"),
                    this.getMessage("ppp.progr.InvPreFileErrName"));
            isValid = false;
        }
        
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileDescription()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrDesc"),
                    this.getMessage("ppp.progr.InvPreFileErrDesc"));
            isValid = false;
        }
        
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileBenefitDesc()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrBenDesc"),
                    this.getMessage("ppp.progr.InvPreFileErrBenDesc"));
            isValid = false;
        }
        
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileURE()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrURE"),
                    this.getMessage("ppp.progr.InvPreFileErrURE"));
            isValid = false;
        }
        
        /*if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileURN()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrURN"),
                    this.getMessage("ppp.progr.InvPreFileErrURN"));
            isValid = false;
        }*/
        
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFilePromoter()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrPromoter"),
                    this.getMessage("ppp.progr.InvPreFileErrPromoter"));
            isValid = false;
        }
        
        /*if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileUEGList()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrUEG"),
                    this.getMessage("ppp.progr.InvPreFileErrUEG"));
            isValid = false;
        }*/
        
        if(!UtilValidations.notNullOrBlank(anInvPreFileController.getTheirInvPreFileComponent()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.InvPreFileErrComponent"),
                    this.getMessage("ppp.progr.InvPreFileErrComponent"));
            isValid = false;
        }
        return isValid;
    }
    
    public static InvPreFileEntity prepareInvPreFileEntity(InvPreFileController anInvPreFile)
    {
        InvPreFileEntity myInvPreFileEntity = new InvPreFileEntity();
        
        myInvPreFileEntity.setAction(anInvPreFile.isTheirInvPreFileAction());
        myInvPreFileEntity.setBuild(anInvPreFile.isTheirInvPreFileBuild());
        myInvPreFileEntity.setFolio(anInvPreFile.getTheirInvPreFileNumber());
        myInvPreFileEntity.setEspecificProyect(anInvPreFile.isTheirInvPreFileEspecifProy());
        myInvPreFileEntity.setProgramm(anInvPreFile.isTheirInvPreFileProgram());
        myInvPreFileEntity.setFund(anInvPreFile.isTheirInvPreFileFund());
        myInvPreFileEntity.setPriority(anInvPreFile.getTheirInvPreFilePriority());
        myInvPreFileEntity.setName(anInvPreFile.getTheirInvPreFileName());
        myInvPreFileEntity.setDescription(anInvPreFile.getTheirInvPreFileDescription());
        myInvPreFileEntity.setDescBenefits(anInvPreFile.getTheirInvPreFileBenefitDesc());
        myInvPreFileEntity.setInvPreFileDraftProject(anInvPreFile.getTheirInvPreFileDraftEntity());
        
        DependenceEntity myDependencyURE = anInvPreFile.getTheirInvPreFileUREEntity();
        myInvPreFileEntity.setInvPreFileUExecuting(myDependencyURE);
        
        FederalURRegulatoryEntity myFederalURN = new FederalURRegulatoryEntity();
        myFederalURN.setFederalUrRegulatoryId(anInvPreFile.getTheirInvPreFileURN());
        myInvPreFileEntity.setInvPreFileURRegulatory(myFederalURN);
        
        PromoterEntity myPromoter = new PromoterEntity();
        myPromoter.setIdPromoter(anInvPreFile.getTheirInvPreFilePromoter());
        myInvPreFileEntity.setPromoter(myPromoter);
        
        List<DependenceEntity> myUEGs = new ArrayList<DependenceEntity>();
        anInvPreFile.getTheirInvPreFileUEGSelected().add(anInvPreFile.getItsInvPreFileUEGFicha().getDependenceId().toString());
        //anInvPreFile.getTheirInvPreFileUEGList().add(anInvPreFile.getItsInvPreFileUEGFicha());
        for(String id : anInvPreFile.getTheirInvPreFileUEGSelected())
        {
            if (!id.equalsIgnoreCase("-1")){
                DependenceEntity dep = new DependenceEntity();
                dep.setDependenceId(Long.valueOf(id));
                myUEGs.add(dep);
            }
        }
        myInvPreFileEntity.setUnitExecSpending(myUEGs);
        
        ComponentEntity myComponent = new ComponentEntity();
        myComponent.setComponentId(anInvPreFile.getTheirInvPreFileComponent());
        myInvPreFileEntity.setInvPreFileComponent(myComponent);
        
        //Editando
        if (anInvPreFile.getTheirInvPreFileId()!=null)
            myInvPreFileEntity.setInvPreFileId(anInvPreFile.getTheirInvPreFileId());
        
       // myInvPreFileEntity.setObservations();
        
        return myInvPreFileEntity;
    }
}
