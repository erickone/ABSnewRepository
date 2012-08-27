/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.SefinValidationControllerApi;
import com.abs.siif.programming.dto.DraftProjectStatusEnum;
import com.abs.siif.programming.dto.ValidationDto;
import com.abs.siif.programming.dto.ValidationQuestionDto;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import com.abs.siif.programming.entities.SefinValidationEntity;
import com.abs.siif.programming.management.ComponentManagement;
import com.abs.siif.programming.management.DeliveryManagement;
import com.abs.siif.programming.management.DraftProjectBinnacleManagement;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.programming.management.SefinValidationManagement;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores
 */
@Controller
@Scope("session")
public class SefinValidationController extends SIIFControllerBase
        implements Serializable, SefinValidationControllerApi
{

    private ValidationDto itsValidationDto = new ValidationDto();
    private List<ComponentEntity> theirComponentList;
    private DraftProjectEntity itsDraftProjectEntity;
    private HashMap<ComponentEntity, SefinValidationEntity[]> theirEvaluationMap;
    private String theirObservations;
    private ArrayList<ValidationQuestionDto> theirPreguntas;
    private boolean isBtnSaveDisabled;
    private boolean isBtnChangeStatusDisabled;
    private boolean isChkSelectAllDisabled;
    private UserEntity theirUser;
    //Preguntas
    private ArrayList<ValidationQuestionDto> theirPropositQuestions;
    private ArrayList<ValidationQuestionDto> theirComponentQuestions;
    private ArrayList<ValidationQuestionDto> theirIndicatorQuestions;
    private ArrayList<ValidationQuestionDto> theirGoalQuestions;
    private ArrayList<ValidationQuestionDto> theirGeneralQuestions;
    //Recursos
    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement itsDraftProjectManagement;
    @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi itsDraftProjectController;
    @Resource(name = "deliveryManagement")
    private transient DeliveryManagement itsDeliveryManagement;
    @Resource(name = "componentManagement")
    private transient ComponentManagement itsComponentManagement;
    @Resource(name = "SefinValidationManagement")
    private transient SefinValidationManagement itsSefinValidationManagement;
    @Resource(name = "draftProjectBinnacleManagement")
    private transient DraftProjectBinnacleManagement itsDraftProjectBinnacleManagement;

    /**
     * @return the theirComponentList
     */
    @Override
    public List<ComponentEntity> getTheirComponentList()
    {
        return theirComponentList;
    }

    /**
     * @param theirComponentList the theirComponentList to set
     */
    @Override
    public void setTheirComponentList(List<ComponentEntity> theirComponentList)
    {
        this.theirComponentList = theirComponentList;
    }

    @Override
    public void init()
    {
        this.setTheirUser(getUser());
        //Obtener le Draft Project del cual se obtendra el Delivery (Entregable)
        this.itsDraftProjectEntity = new DraftProjectEntity();
        this.itsDraftProjectEntity.setDraftProjectId(
                this.itsDraftProjectController.getTheirCurrentDraftProjectId());
        this.itsDraftProjectEntity = this.itsDraftProjectManagement.getDraftProjectById(
                this.itsDraftProjectEntity.getDraftProjectId());

        if (this.itsDraftProjectEntity.getDraftProjectId() > 0)
        {
            //Obtener Delivery
            DeliveryEntity myDelivery;
            myDelivery = this.itsDeliveryManagement.getDeliveries(itsDraftProjectEntity).get(0);
            // Asignar el delivery al objeto DTO
            this.itsValidationDto.setDelivery(myDelivery);
            //TODO: Validación para activar desactivar botón de cambio de estatus

            // Obtener los componentes para el delivery obtenido
            this.theirComponentList = this.itsComponentManagement.getComponentByDelivery(myDelivery);
            if (this.theirComponentList.isEmpty())
            {
                //Si no hay componentes, no debe habilitar botones de Guardar y Cambiar estatus
                this.setIsBtnChangeStatusDisabled(Boolean.TRUE);
                this.setIsBtnSaveDisabled(Boolean.TRUE);
                this.setIsChkSelectAllDisabled(Boolean.TRUE);
                //setear a null las preguntas, ya ue no hay componentes
                this.setTheirComponentQuestions(null);
                this.setTheirGeneralQuestions(null);
                this.setTheirGoalQuestions(null);
                this.setTheirIndicatorQuestions(null);
                this.setTheirPreguntas(null);
                this.setTheirPropositQuestions(null);
                this.setTheirObservations("");
                //Enviar mensaje
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.progr.ValSeplan.noComponentsRelated"),
                        this.getMessage("ppp.progr.ValSeplan.noComponentsRelated"));
            } else
            {
                //cargar las preguntas, ya sea en blanco o cargarlas del sistema
                prepareQuestionsProposit();
                prepareComponentQuestions();
                prepareIndicatorQuestion();
                prepareGoalQuestions();
                prepareGeneralQuestions();
                prepareQuestions();
                obtenerEvaluaciones();
                //habilitar botones de Guardar
                this.setIsBtnSaveDisabled(Boolean.FALSE);
                this.setIsChkSelectAllDisabled(Boolean.FALSE);
                //Obtner el estatus dle anteproyecto
                int myDraftProjectStatus = this.itsDraftProjectEntity.getDraftProjectStatus().getDraftProjectStatusConsecutiveStatus();
                //Si el estatus del anteproyecto ya es sefin, no lo debe dejar cambiar el Estatus
                if (!(myDraftProjectStatus == DraftProjectStatusEnum.VALIDADO_SEFIN.ordinal()))
                {
                    this.setIsBtnChangeStatusDisabled(Boolean.FALSE);
                }
            }
        }
    }

    // Obtener las preguntas de Proposito
    private void prepareQuestionsProposit()
    {
        this.setTheirPropositQuestions(this.itsValidationDto.listOfValSeplanQuestion(1));
    }

    private void prepareComponentQuestions()
    {
        this.setTheirComponentQuestions(this.itsValidationDto.listOfValSeplanQuestion(2));
    }

    private void prepareIndicatorQuestion()
    {
        this.setTheirIndicatorQuestions(this.itsValidationDto.listOfValSeplanQuestion(3));
    }

    private void prepareGoalQuestions()
    {
        this.setTheirGoalQuestions(this.itsValidationDto.listOfValSeplanQuestion(4));
    }

    private void prepareGeneralQuestions()
    {
        this.setTheirGeneralQuestions(this.itsValidationDto.listOfSefinGeneralQuestions());
    }

    private void prepareQuestions()
    {
        this.setTheirPreguntas(this.itsValidationDto.listOfValSeplanQuestion());
        for (ValidationQuestionDto myValDto : this.itsValidationDto.listOfSefinGeneralQuestions())
        {
            this.theirPreguntas.add(myValDto);
        }
    }

    private void obtenerEvaluaciones()
    {
        //Se obtiene por cda componente, su evaluación y cada evaluación se guarda en un mapa
        this.theirEvaluationMap = new HashMap<ComponentEntity, SefinValidationEntity[]>();
        SefinValidationEntity[] mySefinValidations = null;

        for (ComponentEntity myComponent : this.theirComponentList)
        {
            List<SefinValidationEntity> myListOfSefinValidation = this.itsSefinValidationManagement.getSefinValidationByComponent(myComponent);
            if (!myListOfSefinValidation.isEmpty())
            {
                //setear el campo de Observaciones
                this.setTheirObservations(myListOfSefinValidation.get(0).getValidationObservations());
                //Se convierte la lista encontrada en un array
                mySefinValidations = new SefinValidationEntity[myListOfSefinValidation.size()];
                mySefinValidations = myListOfSefinValidation.toArray(mySefinValidations);
                this.theirEvaluationMap.put(myComponent, mySefinValidations);
            } //Si no hay evaluaciones en la tabla... se prepara una estructura limpia para trabajarla
            else
            {
                ArrayList<SefinValidationEntity> myEmptyListOfValidation = new ArrayList<SefinValidationEntity>();
                for (int i = 0; i <= this.theirPreguntas.size() - 1; i++)
                {
                    SefinValidationEntity myEntity = new SefinValidationEntity();
                    myEntity.setValidationAnswer(true);
                    myEntity.setValidationObservations("");
                    myEmptyListOfValidation.add(myEntity);
                }
                mySefinValidations = new SefinValidationEntity[this.theirPreguntas.size()];
                mySefinValidations = myEmptyListOfValidation.toArray(mySefinValidations);
                theirEvaluationMap.put(myComponent, mySefinValidations);
            }
        }
    }

    public void saveSefinValidation()
    {
        //Con la lista de componentes, ejecutar el guardado

        List<SefinValidationEntity> myValidationToSave = new ArrayList<SefinValidationEntity>();
        List<Long> myResult = new ArrayList<Long>();
        for (ComponentEntity myComponent : this.theirComponentList)
        {
            myValidationToSave = prepareListOfSefinValidation(this.theirEvaluationMap.get(myComponent), myComponent);
            myResult = this.itsSefinValidationManagement.saveSefinValidation(myValidationToSave);
        }
        if (myResult.size() == this.theirPreguntas.size())
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("Se han guardado las validaciones SEFIN exitosamente"),
                    this.getMessage("Se han guardado las validaciones SEFIN exitosamente"));
        } else
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("No Se han guardado las validaciones SEFIN exitosamente"),
                    this.getMessage("No Se han guardado las validaciones SEFIN exitosamente"));
        }
        init();
    }

    private List<SefinValidationEntity> prepareListOfSefinValidation(SefinValidationEntity[] aValidationArray, ComponentEntity aComponentEntiy)
    {
        for (int i = 0; i <= aValidationArray.length - 1; i++)
        {
            aValidationArray[i].setValidationObservations(this.theirObservations);
            aValidationArray[i].setValidationQuestionDescription("");
            aValidationArray[i].setComponent(aComponentEntiy);
        }
        return Arrays.asList(aValidationArray);
    }

    public void actualizarEstatusEnBitacora()
    {
        Date myDate = new Date();
        long myDateInMilliseconds = myDate.getTime();
        Timestamp myTimestamp = new Timestamp(myDateInMilliseconds);

        //Comparar el estatus anterior obteniendo la entity de Status
        DraftProjectStatusEntity myLastStatus;
        myLastStatus = this.itsDraftProjectManagement.getStatusEntityByConsecutive(DraftProjectStatusEnum.INICIADO.ordinal());

        Date myLastTimeStamp = this.itsDraftProjectBinnacleManagement.getDateOfLastStatus(
                this.itsDraftProjectController.getTheirCurrentDraftProjectId(), myLastStatus.getDraftProjectStatusId());

        //Definir Usuario
        UserEntity myUser = new UserEntity();
        myUser.setUserId(theirUser.getUserId());

        //Definr nuevo Estatus del anteproyecto: Validado Seplan
        DraftProjectStatusEntity myStatus;
        myStatus = this.itsDraftProjectManagement.getStatusEntityByConsecutive(DraftProjectStatusEnum.VALIDADO_SEFIN.ordinal());

        this.itsDraftProjectEntity.setDraftProjectStatus(myStatus);
        DraftProjectBinnacleEntity myBinnacle = new DraftProjectBinnacleEntity();


        myBinnacle.setDraftProject(itsDraftProjectEntity);
        //TODO:Manejador de consecutivos en bitácora
        myBinnacle.setDraftProjectBinnacleConsecutive(2);
        myBinnacle.setDraftProjectBinnacleDate(myTimestamp);

        int myRealDays = this.getDaysDifference(myDate, myLastTimeStamp);
        myBinnacle.setDraftProjectBinnacleRealDay(myRealDays);
        myBinnacle.setDraftProjectStatus(myStatus);
        myBinnacle.setUserBinnacle(myUser);

        itsDraftProjectBinnacleManagement.saveDraftProjectBinnacle(myBinnacle);

        this.itsDraftProjectController.setDraftStatusId(myStatus.getDraftProjectStatusId());
        this.itsDraftProjectController.setDraftStatusDescription(myStatus.getDraftProjectStatusdescription().toString());
        this.itsDraftProjectController.setStatusConsecutive(DraftProjectStatusEnum.VALIDADO_SEFIN.ordinal());
        this.itsDraftProjectBinnacleManagement.updateDraftProjectStatus(myBinnacle);
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                this.getMessage("Se ha cambiado el estatus del anteproyecto a Validado SEFIN"),
                this.getMessage("Se ha cambiado el estatus del anteproyecto a Validado SEFIN"));
        this.isBtnChangeStatusDisabled = Boolean.TRUE;

    }

    private int getDaysDifference(Date anOlderDate, Date aNewestDate)
    {
        long myOlderDateInMilliseconds = anOlderDate.getTime();
        long myNewestDateInMilliseconds = aNewestDate.getTime();
        long myDifference = myOlderDateInMilliseconds - myNewestDateInMilliseconds;
        double myDraftDurationInDays = Math.floor(myDifference / ((1000 * 60 * 60 * 24)));
        return ((int) myDraftDurationInDays);
    }

    /**
     * @return the itsDraftProjectController
     */
    public DraftProjectHeaderControllerApi getItsDraftProjectController()
    {
        return itsDraftProjectController;
    }

    /**
     * @param itsDraftProjectController the itsDraftProjectController to set
     */
    public void setItsDraftProjectController(DraftProjectHeaderControllerApi itsDraftProjectController)
    {
        this.itsDraftProjectController = itsDraftProjectController;
    }

    /**
     * @return the itsDraftProjectEntity
     */
    public DraftProjectEntity getItsDraftProjectEntity()
    {
        return itsDraftProjectEntity;
    }

    /**
     * @param itsDraftProjectEntity the itsDraftProjectEntity to set
     */
    public void setItsDraftProjectEntity(DraftProjectEntity itsDraftProjectEntity)
    {
        this.itsDraftProjectEntity = itsDraftProjectEntity;
    }

    /**
     * @return the itsDraftProjectManagement
     */
    public DraftProjectManagement getItsDraftProjectManagement()
    {
        return itsDraftProjectManagement;
    }

    /**
     * @param itsDraftProjectManagement the itsDraftProjectManagement to set
     */
    public void setItsDraftProjectManagement(DraftProjectManagement itsDraftProjectManagement)
    {
        this.itsDraftProjectManagement = itsDraftProjectManagement;
    }

    /**
     * @return the theirPropositQuestions
     */
    public ArrayList<ValidationQuestionDto> getTheirPropositQuestions()
    {
        return theirPropositQuestions;
    }

    /**
     * @param theirPropositQuestions the theirPropositQuestions to set
     */
    public void setTheirPropositQuestions(ArrayList<ValidationQuestionDto> theirPropositQuestions)
    {
        this.theirPropositQuestions = theirPropositQuestions;
    }

    /**
     * @return the theirComponentQuestions
     */
    public ArrayList<ValidationQuestionDto> getTheirComponentQuestions()
    {
        return theirComponentQuestions;
    }

    /**
     * @param theirComponentQuestions the theirComponentQuestions to set
     */
    public void setTheirComponentQuestions(ArrayList<ValidationQuestionDto> theirComponentQuestions)
    {
        this.theirComponentQuestions = theirComponentQuestions;
    }

    /**
     * @return the theirIndicatorQuestions
     */
    public ArrayList<ValidationQuestionDto> getTheirIndicatorQuestions()
    {
        return theirIndicatorQuestions;
    }

    /**
     * @param theirIndicatorQuestions the theirIndicatorQuestions to set
     */
    public void setTheirIndicatorQuestions(ArrayList<ValidationQuestionDto> theirIndicatorQuestions)
    {
        this.theirIndicatorQuestions = theirIndicatorQuestions;
    }

    /**
     * @return the theirGoalQuestions
     */
    public ArrayList<ValidationQuestionDto> getTheirGoalQuestions()
    {
        return theirGoalQuestions;
    }

    /**
     * @param theirGoalQuestions the theirGoalQuestions to set
     */
    public void setTheirGoalQuestions(ArrayList<ValidationQuestionDto> theirGoalQuestions)
    {
        this.theirGoalQuestions = theirGoalQuestions;
    }

    /**
     * @return the theirGeneralQuestions
     */
    public ArrayList<ValidationQuestionDto> getTheirGeneralQuestions()
    {
        return theirGeneralQuestions;
    }

    /**
     * @param theirGeneralQuestions the theirGeneralQuestions to set
     */
    public void setTheirGeneralQuestions(ArrayList<ValidationQuestionDto> theirGeneralQuestions)
    {
        this.theirGeneralQuestions = theirGeneralQuestions;
    }

    /**
     * @return the theirEvaluationMap
     */
    public HashMap<ComponentEntity, SefinValidationEntity[]> getTheirEvaluationMap()
    {
        return theirEvaluationMap;
    }

    /**
     * @param theirEvaluationMap the theirEvaluationMap to set
     */
    public void setTheirEvaluationMap(HashMap<ComponentEntity, SefinValidationEntity[]> theirEvaluationMap)
    {
        this.theirEvaluationMap = theirEvaluationMap;
    }

    /**
     * @return the theirObservations
     */
    public String getTheirObservations()
    {
        return theirObservations;
    }

    /**
     * @param theirObservations the theirObservations to set
     */
    public void setTheirObservations(String theirObservations)
    {
        this.theirObservations = theirObservations;
    }

    /**
     * @return the theirPreguntas
     */
    public ArrayList<ValidationQuestionDto> getTheirPreguntas()
    {
        return theirPreguntas;
    }

    /**
     * @param theirPreguntas the theirPreguntas to set
     */
    public void setTheirPreguntas(ArrayList<ValidationQuestionDto> theirPreguntas)
    {
        this.theirPreguntas = theirPreguntas;
    }

    /**
     * @return the isBtnSaveDisabled
     */
    public boolean isIsBtnSaveDisabled()
    {
        return isBtnSaveDisabled;
    }

    /**
     * @param isBtnSaveDisabled the isBtnSaveDisabled to set
     */
    public void setIsBtnSaveDisabled(boolean isBtnSaveDisabled)
    {
        this.isBtnSaveDisabled = isBtnSaveDisabled;
    }

    /**
     * @return the isBtnChangeStatusDisabled
     */
    public boolean isIsBtnChangeStatusDisabled()
    {
        return isBtnChangeStatusDisabled;
    }

    /**
     * @param isBtnChangeStatusDisabled the isBtnChangeStatusDisabled to set
     */
    public void setIsBtnChangeStatusDisabled(boolean isBtnChangeStatusDisabled)
    {
        this.isBtnChangeStatusDisabled = isBtnChangeStatusDisabled;
    }

    /**
     * @return the isChkSelectAllDisabled
     */
    public boolean isIsChkSelectAllDisabled()
    {
        return isChkSelectAllDisabled;
    }

    /**
     * @param isChkSelectAllDisabled the isChkSelectAllDisabled to set
     */
    public void setIsChkSelectAllDisabled(boolean isChkSelectAllDisabled)
    {
        this.isChkSelectAllDisabled = isChkSelectAllDisabled;
    }

    /**
     * @return the theirUser
     */
    public UserEntity getTheirUser()
    {
        return theirUser;
    }

    /**
     * @param theirUser the theirUser to set
     */
    public void setTheirUser(UserEntity theirUser)
    {
        this.theirUser = theirUser;
    }
}