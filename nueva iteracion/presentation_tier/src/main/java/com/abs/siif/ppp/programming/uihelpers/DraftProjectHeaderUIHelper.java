/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.data.DraftFileType;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectStateEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import com.abs.siif.programming.entities.DraftProjectTypeEntity;
import com.abs.siif.support.UtilValidations;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class DraftProjectHeaderUIHelper extends SIIFControllerBase  implements Serializable
{

    public static int getDiferenceBetweenTwoDates(Date aStartDate, Date aEndDate) {
        int myStartMonth = aStartDate.getMonth();
        int myEndMonth = aEndDate == null ? -10: aEndDate.getMonth();
        int myStartYear = aStartDate.getYear();
        int myEndYear = aEndDate == null ? -10: aEndDate.getYear();
        int myResult;
        if( myEndMonth == -10 ){
            return 0;
        }
        if(myStartYear == myEndYear)
        {
            myResult = myEndMonth - myStartMonth;
        }
        else
        {
            myResult = myEndMonth - myStartMonth + (12*(myEndYear - myStartYear));
        }
        return myResult+1;
    }
    /**
     * Evalua si una fecha dada es mayor a una fecha inicial
     * Con esto se evita que se introduzca una fecha incorrecta
     * @param aFechaInicial
     * @param aFechaFinal
     * @return
     */
    public static Boolean compareIfEndDateIsGreaterThanInitialDate(Date aFechaInicial, Date aFechaFinal)
    {
        Boolean isGreater = false;
        if(aFechaInicial.compareTo(aFechaFinal)>0)
        {
            isGreater = true;
        }
        else
        {
            isGreater = false;
        }
        return isGreater;
    }
    public  Boolean validateDraftProject(DraftProjectHeaderControllerApi aDraftProjectController)
    {
        Boolean isValid = true;

        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftShortName()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrShortName"),
                    this.getMessage("ppp.progr.DraftProjectErrShortName"));
            isValid = false;
        }

        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftProjectName()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrName"),
                    this.getMessage("ppp.progr.DraftProjectErrName"));
            isValid = false;
        }

        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftDescription()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrDescripcion"),
                    this.getMessage("ppp.progr.DraftProjectErrDescripcion"));
            isValid = false;
        }

        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftLegalBase()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrFundLegal"),
                    this.getMessage("ppp.progr.DraftProjectErrFundLegal"));
            isValid = false;
        }

        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftTypeId()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrTipo"),
                    this.getMessage("ppp.progr.DraftProjectErrTipo"));
            isValid = false;
        }

        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftStateId()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrEstado"),
                    this.getMessage("ppp.progr.DraftProjectErrEstado"));
            isValid = false;
        }
        
        if(aDraftProjectController.getSelectedVulGrops() == null ||
                aDraftProjectController.getSelectedVulGrops().isEmpty()){

            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrVulGroup"),
                    this.getMessage("ppp.progr.DraftProjectErrVulGroup"));
            isValid = false;
            
        }

//        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftScopeId()))
//        {
//            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
//                    this.getMessage("ppp.progr.DraftProjectErrAmbito"),
//                    this.getMessage("ppp.progr.DraftProjectErrAmbito"));
//            isValid = false;
//        }

        if(compareIfEndDateIsGreaterThanInitialDate(aDraftProjectController.getDraftStartDate(),
                                                    aDraftProjectController.getDraftEndDate()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrFecha"),
                    this.getMessage("ppp.progr.DraftProjectErrFecha"));
            isValid = false;
        }

        if(!UtilValidations.notNullOrBlank(aDraftProjectController.getDraftPurpose()))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftProjectErrPurpose"),
                    this.getMessage("ppp.progr.DraftProjectErrPurpose"));
            isValid = false;
        }
        return isValid;
    }

    public static DraftProjectEntity prepareDraftProjectEntity(DraftProjectHeaderControllerApi aDraftProject)
    {
        DraftProjectEntity myDraftProject = new DraftProjectEntity();

        DraftFileType myConvertedDraftProjectType = null;
        myConvertedDraftProjectType = convertDraftProjectTypeToEnumType(aDraftProject.getDraftType());

        DraftProjectTypeEntity myDraftType = new DraftProjectTypeEntity();
        myDraftType.setDraftProjectTypeId(aDraftProject.getDraftTypeId());

        DraftProjectStatusEntity myDraftStatus = new DraftProjectStatusEntity();
        myDraftStatus.setDraftProjectStatusId(aDraftProject.getDraftStatusId());

        DraftProjectStateEntity myDraftState = new DraftProjectStateEntity();
        myDraftState.setDraftProjectStateId(aDraftProject.getDraftStateId());
        

        myDraftProject.setDraftProjectId(aDraftProject.getTheirCurrentDraftProjectId());
        myDraftProject.setDraftProjectShortName(aDraftProject.getDraftShortName());
        myDraftProject.setDraftFileType(myConvertedDraftProjectType);
        myDraftProject.setDraftProjectName(aDraftProject.getDraftProjectName());
        myDraftProject.setDraftProjectStartDate(aDraftProject.getDraftStartDate());
        myDraftProject.setDraftProjectEndDate(aDraftProject.getDraftEndDate());
        myDraftProject.setDraftProjectType(myDraftType);
        myDraftProject.setDraftProjectStatus(myDraftStatus);
        myDraftProject.setDraftProjectState(myDraftState);
        //myDraftProject.setDraftProjectScope(myDraftScope);
        myDraftProject.setDraftProjectDescription(aDraftProject.getDraftDescription());
        myDraftProject.setDraftProjectBaseLegal(aDraftProject.getDraftLegalBase());
        myDraftProject.setDraftProjectPurpose(aDraftProject.getDraftPurpose());
        myDraftProject.setDraftProjectKey(aDraftProject.getDraftNumber());
        //columna que se encarga del eliminado logico
        myDraftProject.setIsDraftProjectActive(true);
        return myDraftProject;
    }
    private static DraftFileType convertDraftProjectTypeToEnumType(int aDraftProjectTypeId)
    {
        int myConvertedDraftProjectTypeId = aDraftProjectTypeId;
        DraftFileType myDraftTypeId = null;

        if(myConvertedDraftProjectTypeId == 0)
        {
            myDraftTypeId= DraftFileType.PROCESS;
        }

        if(myConvertedDraftProjectTypeId == 1)
        {
            myDraftTypeId = DraftFileType.PROJECT;
        }

        return myDraftTypeId;
    }
}
