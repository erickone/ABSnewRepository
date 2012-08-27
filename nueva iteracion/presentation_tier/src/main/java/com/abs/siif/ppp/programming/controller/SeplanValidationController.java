package com.abs.siif.ppp.programming.controller;

import com.abs.siif.ppp.programming.api.controller.SeplanValidationControllerApi;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.programming.dto.DraftProjectStatusEnum;
import com.abs.siif.programming.dto.ValidationDto;
import com.abs.siif.programming.dto.ValidationQuestionDto;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.ValidationEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.management.DraftProjectBinnacleManagement;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.programming.management.ValidationManagement;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Date;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores
 */
@Controller//("SeplanValidationController")
@Scope("session")
public class SeplanValidationController extends SIIFControllerBase implements
        Serializable, SeplanValidationControllerApi {

    private static final int CONSECUTIVO_INICIAL_BITACORA = 2;
    private static final int DIAS_REALES_EN_ESTADO_INICIAL_BITACORA = 0;
    private static final String ESTATUS_VALIDADO = "132881ab-3750e88f-0137-50e8e998-0000";
    public static final String ESTATUS_VALIDADO_SEPLAN = "132881ab-3750e88f-0137-50e8e998-0002";
    private ArrayList<ValidationQuestionDto> theirPreguntas;
    private ArrayList<String> theirTitulos;
    private ValidationDto itsValidationDto = new ValidationDto();
    private List<ComponentEntity> theirComponentList;
    private List<ValidationEntity> theirValidationList;
    private HashMap<ComponentEntity, ValidationEntity[]> theirEvaluationMap;
    private boolean btnSrvChangeStatus;
    private String theirObservation;
    private UserEntity itsUser;
    private boolean btnSrvSave;
    private ArrayList<ValidationQuestionDto> theirPreguntasProposito;
    private ArrayList<ValidationQuestionDto> theirPreguntasComponente;
    private ArrayList<ValidationQuestionDto> theirPreguntasIndicadores;
    private ArrayList<ValidationQuestionDto> theirPreguntasMetas;
    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement theirDraftProjectManagement;
    @Resource(name = "validationManagement")
    private transient ValidationManagement itsValidationManagement;
    @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi itsDraftProjectHeaderController;
    @Resource(name = "draftProjectBinnacleManagement")
    private transient DraftProjectBinnacleManagement itsDraftProjectBinnacleManagement;
    private DraftProjectEntity itsDraftProjectEntity;

    @Override
    public void init() {
        btnSrvSave = false;
        this.itsUser = getUser();
        //Asignar el delivery del cual se obtendran los componentes
        itsDraftProjectEntity = new DraftProjectEntity();
        itsDraftProjectEntity.setDraftProjectId(itsDraftProjectHeaderController.getTheirCurrentDraftProjectId());
        if (itsDraftProjectEntity.getDraftProjectId() > 0) {
            itsValidationDto.setDelivery(itsValidationManagement.getDeliveryByDraftProject(itsDraftProjectEntity));
            //Si el consecutivo es INICIADO y no tiene compoenentes, deshabilita el botón de Validado SEPLAN
            if (!(itsDraftProjectHeaderController.getStatusConsecutive() == DraftProjectStatusEnum.VALIDADO_SEPLAN.ordinal())
                    && !(itsValidationManagement.getComponentsByDelivery(itsValidationDto.getDelivery()).isEmpty())) {
                this.btnSrvChangeStatus = Boolean.FALSE;
            } else {
                this.btnSrvChangeStatus = Boolean.TRUE;
            }
            if (itsValidationManagement.getComponentsByDelivery(itsValidationDto.getDelivery()).isEmpty()) {
                this.btnSrvSave = Boolean.TRUE;
                this.setTheirPreguntas(null);
                this.setTheirPreguntasProposito(null);
                this.setTheirPreguntasComponente(null);
                this.setTheirPreguntasIndicadores(null);
                this.setTheirPreguntasMetas(null);
                this.setObservation(null);
                ComponentEntity myComponent = null;
                ValidationEntity[] myVal = null;
                if (myComponent == null) {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.progr.ValSeplan.noComponentsRelated"),
                            this.getMessage("ppp.progr.ValSeplan.noComponentsRelated"));
                    return;
                }
                this.theirEvaluationMap.put(myComponent, myVal);
                this.theirComponentList = null;
            } else 
            {
                this.btnSrvSave = Boolean.FALSE;
                this.setObservation(null);
                prepareQuestionsProposito();
                prepareQuestionsComponente();
                prepareQuestionsIndicador();
                prepareQuestionsMetas();
                prepareQuestions();
                obtenerEvaluaciones();
            }

        }
    }

    public SeplanValidationController() {
    }

    private void prepareQuestions() {
        this.setTheirPreguntas(this.itsValidationDto.listOfValSeplanQuestion());
    }

    private void prepareQuestionsProposito() {
        this.setTheirPreguntasProposito(this.itsValidationDto.listOfValSeplanQuestion(1));
    }

    private void prepareQuestionsComponente() {
        this.setTheirPreguntasComponente(this.itsValidationDto.listOfValSeplanQuestion(2));
    }

    private void prepareQuestionsIndicador() {
        this.setTheirPreguntasIndicadores(this.itsValidationDto.listOfValSeplanQuestion(3));
    }

    private void prepareQuestionsMetas() {
        this.setTheirPreguntasMetas(this.itsValidationDto.listOfValSeplanQuestion(4));
    }

    private void prepareTitles() {
        this.setTheirTitulos(this.itsValidationDto.generatedTitlesbyVathegory());
    }

    @Override
    public ArrayList<String> getTheirTitulos() {
        return theirTitulos;
    }

    @Override
    public void setTheirTitulos(ArrayList<String> theirTitulos) {
        this.theirTitulos = theirTitulos;
    }

    @Override
    public ArrayList<ValidationQuestionDto> getTheirPreguntasComponente() {
        return theirPreguntasComponente;
    }

    @Override
    public void setTheirPreguntasComponente(ArrayList<ValidationQuestionDto> theirPreguntasComponente) {
        this.theirPreguntasComponente = theirPreguntasComponente;
    }

    @Override
    public ArrayList<ValidationQuestionDto> getTheirPreguntasIndicadores() {
        return theirPreguntasIndicadores;
    }

    @Override
    public void setTheirPreguntasIndicadores(ArrayList<ValidationQuestionDto> theirPreguntasIndicadores) {
        this.theirPreguntasIndicadores = theirPreguntasIndicadores;
    }

    @Override
    public ArrayList<ValidationQuestionDto> getTheirPreguntasProposito() {
        return theirPreguntasProposito;
    }

    @Override
    public void setTheirPreguntasProposito(ArrayList<ValidationQuestionDto> theirPreguntasProposito) {
        this.theirPreguntasProposito = theirPreguntasProposito;
    }

    @Override
    public ArrayList<ValidationQuestionDto> getTheirPreguntasMetas() {
        return theirPreguntasMetas;
    }

    @Override
    public void setTheirPreguntasMetas(ArrayList<ValidationQuestionDto> theirPreguntasMetas) {
        this.theirPreguntasMetas = theirPreguntasMetas;
    }

    /**
     * @return the theirPreguntas
     */
    @Override
    public ArrayList<ValidationQuestionDto> getTheirPreguntas() {
        return theirPreguntas;
    }

    /**
     * @param theirPreguntas the theirPreguntas to set
     */
    @Override
    public void setTheirPreguntas(ArrayList<ValidationQuestionDto> theirPreguntas) {
        this.theirPreguntas = theirPreguntas;
    }

    private void obtenerEvaluaciones() {
        //Número de Componentes


        Collection<ComponentEntity> componets = itsValidationManagement.getComponentsByDelivery(itsValidationDto.getDelivery());
        //Por cada componente obtener su evaluación
        //y guardarlo en un mapa
        theirEvaluationMap = new HashMap<ComponentEntity, ValidationEntity[]>();
        ValidationEntity[] arrayValidationEntity = null;

        for (ComponentEntity myComponent : componets) {

            ArrayList<ValidationEntity> myConverted =
                    new ArrayList<ValidationEntity>(
                    this.itsValidationManagement.getValidationByComponentId(myComponent));

            if (!myConverted.isEmpty()) {

                arrayValidationEntity = new ValidationEntity[myConverted.size()];
                arrayValidationEntity = myConverted.toArray(arrayValidationEntity);
                this.setObservation(myConverted.get(0).getValidationObservations());
                theirEvaluationMap.put(myComponent, arrayValidationEntity);
            } else {
                //TODO:Preparar estructura para guardar
                ArrayList<ValidationEntity> myEmptyListOfValidation = new ArrayList<ValidationEntity>();

                for (int i = 0; i <= this.theirPreguntas.size() - 1; i++) {
                    ValidationEntity myEntity = new ValidationEntity();
                    myEntity.setValidationAnswer(true);
                    myEntity.setValidationObservations("");
                    myEmptyListOfValidation.add(myEntity);
                }
                arrayValidationEntity = new ValidationEntity[this.theirPreguntas.size()];
                arrayValidationEntity = myEmptyListOfValidation.toArray(arrayValidationEntity);
                theirEvaluationMap.put(myComponent, arrayValidationEntity);

            }

        }
        this.theirComponentList = new ArrayList<ComponentEntity>(componets);
    }

    @Override
    public void actualizarEstatusEnBitacora() {
        Date myDate = new Date();
        long myDateInMilliseconds = myDate.getTime();
        Timestamp myTimestamp = new Timestamp(myDateInMilliseconds);

        //Comparar el estatus anterior obteniendo la entity de Status
        DraftProjectStatusEntity myLastStatus = new DraftProjectStatusEntity();
        myLastStatus = this.theirDraftProjectManagement.getStatusEntityByConsecutive(DraftProjectStatusEnum.INICIADO.ordinal());

        Date myLastTimeStamp = this.itsDraftProjectBinnacleManagement.getDateOfLastStatus(this.itsDraftProjectHeaderController.getTheirCurrentDraftProjectId(), myLastStatus.getDraftProjectStatusId());

        //Definir Usuario
        UserEntity myUser = new UserEntity();
        myUser.setUserId(itsUser.getUserId());

        //Definr nuevo Estatus del anteproyecto: Validado Seplan
        DraftProjectStatusEntity myStatus = new DraftProjectStatusEntity();
        myStatus = this.theirDraftProjectManagement.getStatusEntityByConsecutive(DraftProjectStatusEnum.VALIDADO_SEPLAN.ordinal());

        this.itsDraftProjectEntity.setDraftProjectStatus(myStatus);
        DraftProjectBinnacleEntity myBinnacle = new DraftProjectBinnacleEntity();


        myBinnacle.setDraftProject(itsDraftProjectEntity);
        //TODO:Manejador de consecutivos en bitácora
        myBinnacle.setDraftProjectBinnacleConsecutive(CONSECUTIVO_INICIAL_BITACORA);
        myBinnacle.setDraftProjectBinnacleDate(myTimestamp);

        int myRealDays = this.getDaysDifference(myDate, myLastTimeStamp);
        myBinnacle.setDraftProjectBinnacleRealDay(myRealDays);
        myBinnacle.setDraftProjectStatus(myStatus);
        myBinnacle.setUserBinnacle(myUser);

        itsDraftProjectBinnacleManagement.saveDraftProjectBinnacle(myBinnacle);

        this.itsDraftProjectHeaderController.setDraftStatusId(myStatus.getDraftProjectStatusId());
        this.itsDraftProjectHeaderController.setDraftStatusDescription(myStatus.getDraftProjectStatusdescription().toString());
        this.itsDraftProjectHeaderController.setStatusConsecutive(DraftProjectStatusEnum.VALIDADO_SEPLAN.ordinal());
        this.itsDraftProjectBinnacleManagement.updateDraftProjectStatus(myBinnacle);
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                this.getMessage("Se ha cambiado el estatus del anteproyecto a Validado SEPLAN"),
                this.getMessage("Se ha cambiado el estatus del anteproyecto a Validado SEPLAN"));
        this.btnSrvChangeStatus = Boolean.TRUE;

    }

    private int getDaysDifference(Date anOlderDate, Date aNewestDate) {
        long myOlderDateInMilliseconds = anOlderDate.getTime();
        long myNewestDateInMilliseconds = aNewestDate.getTime();
        long myDifference = myOlderDateInMilliseconds - myNewestDateInMilliseconds;
        double myDraftDurationInDays = Math.floor(myDifference / ((1000 * 60 * 60 * 24)));
        return ((int) myDraftDurationInDays);
    }

    @Override
    public void saveSeplanValidation() {
        Collection<ComponentEntity> componets = itsValidationManagement.getComponentsByDelivery(itsValidationDto.getDelivery());

        List<ValidationEntity> myValidationEntityToSave = new ArrayList<ValidationEntity>();
        for (ComponentEntity myComponent : componets) {
            myValidationEntityToSave = prepareListOfValidationsToSave(this.theirEvaluationMap.get(myComponent), myComponent);// Arrays.asList(this.theirEvaluationMap.get(myComponent));
            itsValidationManagement.saveSeplanValidation(myValidationEntityToSave);
        }
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                this.getMessage("Se han guardado las validaciones SEPLAN exitosamente"),
                this.getMessage("Se han guardado las validaciones SEPLAN exitosamente"));
    }

    //TODO: Verificar porque no se esta guardando el componente
    private List<ValidationEntity> prepareListOfValidationsToSave(ValidationEntity[] aValidationArray, ComponentEntity aComponentEntiy) {
        for (int i = 0; i <= aValidationArray.length - 1; i++) {
            aValidationArray[i].setValidationObservations(theirObservation);
            aValidationArray[i].setValidationQuestionDescription(" ");
            aValidationArray[i].setComponent(aComponentEntiy);
        }
        return Arrays.asList(aValidationArray);
    }

    /**
     * @return the theirComponentList
     */
    @Override
    public List<ComponentEntity> getComponentList() {
        return theirComponentList;
    }

    /**
     * @param theirComponentList the theirComponentList to set
     */
    @Override
    public void setComponentList(List<ComponentEntity> theirComponentList) {
        this.theirComponentList = theirComponentList;
    }

    /**
     * @return the theirValidationEntity
     */
    @Override
    public List<ValidationEntity> getValidationList() {
        return theirValidationList;
    }

    /**
     * @param theirValidationEntity the theirValidationEntity to set
     */
    @Override
    public void setValidationList(List<ValidationEntity> aValidationList) {
        this.theirValidationList = aValidationList;
    }

    /**
     * @return the theirEvaluationMap
     */
    @Override
    public HashMap<ComponentEntity, ValidationEntity[]> getEvaluationMap() {
        return theirEvaluationMap;
    }

    /**
     * @param theirEvaluationMap the theirEvaluationMap to set
     */
    @Override
    public void setEvaluationMap(HashMap<ComponentEntity, ValidationEntity[]> theirEvaluationMap) {
        this.theirEvaluationMap = theirEvaluationMap;
    }

    /**
     * @return the theirObservation
     */
    @Override
    public String getObservation() {
        return theirObservation;
    }

    /**
     * @param theirObservation the theirObservation to set
     */
    @Override
    public void setObservation(String theirObservation) {
        this.theirObservation = theirObservation;
    }

    /**
     * @return the theirSepladeActivo
     */
    @Override
    public boolean getBtnSrvChangeStatus() {
        return btnSrvChangeStatus;
    }

    /**
     * @param theirSepladeActivo the theirSepladeActivo to set
     */
    @Override
    public void setBtnSrvChangeStatus(boolean aSepladeActivo) {
        this.btnSrvChangeStatus = aSepladeActivo;
    }

    /**
     * @return the itsUser
     */
    @Override
    public UserEntity getItsUser() {
        return itsUser;
    }

    /**
     * @param itsUser the itsUser to set
     */
    @Override
    public void setItsUser(UserEntity itsUser) {
        this.itsUser = itsUser;
    }

    /**
     * @return the theirDraftProjectManagement
     */
    @Override
    public DraftProjectManagement getTheirDraftProjectManagement() {
        return theirDraftProjectManagement;
    }

    /**
     * @param theirDraftProjectManagement the theirDraftProjectManagement to set
     */
    @Override
    public void setTheirDraftProjectManagement(DraftProjectManagement theirDraftProjectManagement) {
        this.theirDraftProjectManagement = theirDraftProjectManagement;
    }

    /**
     * @return the btnSrvSave
     */
    @Override
    public boolean getBtnSrvSave() {
        return btnSrvSave;
    }

    /**
     * @param btnSrvSave the btnSrvSave to set
     */
    @Override
    public void setBtnSrvSave(boolean itsBtnSrvSave) {
        this.btnSrvSave = itsBtnSrvSave;
    }
}
