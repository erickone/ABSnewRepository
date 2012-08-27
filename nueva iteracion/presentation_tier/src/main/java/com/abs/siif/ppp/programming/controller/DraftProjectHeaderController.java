package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.common.dto.SampleEntityDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.planning.controller.OptionsController;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.InvPreFileControllerApi;
import com.abs.siif.ppp.programming.api.controller.UbicationPreFileControllerApi;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.DraftProjectHeaderUIHelper;
import com.abs.siif.programming.dto.DraftProjectStatusEnum;
import com.abs.siif.programming.entities.*;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.DateSelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

/**
 * Esta clase proporciona los datos relacionados al encabezado de la ficha de
 * anteproyecto
 *
 * @author jacob.flores
 */
@Controller("draftProjectHeaderController")
@Scope("session")
public class DraftProjectHeaderController extends SIIFControllerBase implements Serializable, DraftProjectHeaderControllerApi {

    @Resource(name = "optionsController")
    private transient OptionsController theirOptionsController;
    @Resource(name = "ubicationPreFileController")
    private transient UbicationPreFileControllerApi ubicationPreFileController;
    @Resource(name = "invPreFileController")
    private transient InvPreFileControllerApi theirInvPreFileController;
    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement theirDraftProjectManagement;
    private SampleEntityDto theirSelectedDraftType;
    private SampleEntityDto theirSelectedDraftProjectState;
    private SampleEntityDto theirSelectedDraftProjectScope;
    private String theirDraftShortName;
    private String theirDraftNumber = "";
    private String theirDraftName;
    private int theirDraftType;
    private Date theirDraftStartDate;
    private Date theirDraftEndDate;
    private int theirDraftDurationInMonths;
    private String theirDraftDescription;
    private String theirDraftLegalBase;
    private Long theirCurrentDraftProjectId;
    private Long theirDraftTypeId;
    private Long theirDraftStateId;
    private Long theirDraftStatusId;
    private int theirStatusConsecutive;
    private String theirDraftStatusDescription;
    private Long theirDraftScopeId;
    private Long idDependency;
    private transient DraftProjectHeaderUIHelper itsDraftProjectUIHelper = new DraftProjectHeaderUIHelper();
    private Long theirProgrammingId;
    private String theirDraftPurpose;
    private boolean itsDisabledTab = true;
    private boolean itsDisabledTabDelivery = true;
    private boolean itsDisabledTabClassif = false;
    private boolean itsDisabledValidacionSeplanTab = true;
    private boolean itsDisabledTabButton = true;
    private Long theirObjectiveId;
    private Map<String, List<?>> theirSupportList;
    private boolean isNewButtonDisabled = true;
    private DraftProjectEntity itsDraftProjectEntity;
    //inyeccion de los objetos seleccionados en la programacion
    private ObjectiveJoinLevelTreeviewDto itsProgramSelected;
    private ObjectiveJoinLevelTreeviewDto itsSubProgramSelected;
    private ObjectiveJoinLevelTreeviewDto itsAxisSelected;
    private List<DraftProjectTypeEntity> itsFileTypeList = new ArrayList<DraftProjectTypeEntity>();
    private List<DraftProjectTypeEntity> itsCmbTypeList = new ArrayList<DraftProjectTypeEntity>();
    private List<String> selectedVulGrops = new ArrayList<String>();
    private List<VulnerableGroupEntity> vulnerableGroup;
    private List<VulnerableGroupEntity> vulnerableGroupSelected;
    private Long itsSelectedUEG = 0L;
    private int itsTabActiveIndex = 0;
    private boolean isDisabledTxtShortName;
    private Boolean btnSrvSave = true;

    @Override
    public Long getTheirObjectiveId() {
        return theirObjectiveId;
    }

    @Override
    public void setTheirObjectiveId(Long theirObjectiveId) {
        this.theirObjectiveId = theirObjectiveId;
    }

    @Override
    public void handleDateSelectStartDate(DateSelectEvent event) {
        this.theirDraftStartDate = event.getDate();
        theirDraftDurationInMonths = itsDraftProjectUIHelper.getDiferenceBetweenTwoDates(theirDraftStartDate,
                theirDraftEndDate);
    }

    @Override
    public void handleDateSelectEndDate(DateSelectEvent event) {

        this.theirDraftEndDate = event.getDate();
        theirDraftDurationInMonths = itsDraftProjectUIHelper.getDiferenceBetweenTwoDates(theirDraftStartDate,
                theirDraftEndDate);
    }

    @Override
    public Long getTheirProgrammingId() {
        return theirProgrammingId;
    }

    @Override
    public void setTheirProgrammingId(Long theirProgrammingId) {
        this.theirProgrammingId = theirProgrammingId;
    }

    @Override
    public Long getTheirCurrentDraftProjectId() {
        return theirCurrentDraftProjectId;
    }

    @Override
    public void setTheirCurrentDraftProjectId(Long theirCurrentDraftProjectId) {
        this.theirCurrentDraftProjectId = theirCurrentDraftProjectId;
        prepareEditProject();
    }

    @Override
    public SampleEntityDto getTheirSelectedDraftProjectScope() {
        return theirSelectedDraftProjectScope;
    }

    @Override
    public void setTheirSelectedDraftProjectScope(SampleEntityDto theirSelectedDraftProjectScope) {
        this.theirSelectedDraftProjectScope = theirSelectedDraftProjectScope;
    }

    @Override
    public SampleEntityDto getTheirSelectedDraftProjectState() {
        return theirSelectedDraftProjectState;
    }

    @Override
    public void setTheirSelectedDraftProjectState(SampleEntityDto theirSelectedDraftProjectState) {
        this.theirSelectedDraftProjectState = theirSelectedDraftProjectState;
    }

    @Override
    public SampleEntityDto getTheirSelectedDraftType() {
        return theirSelectedDraftType;
    }

    @Override
    public void setTheirSelectedDraftType(SampleEntityDto theirSelectedDraftType) {
        this.theirSelectedDraftType = theirSelectedDraftType;
    }

    public DraftProjectHeaderController() {
    }

    @Override
    public void initSupporList() {
        theirSupportList = new HashMap<String, List<?>>();
        theirSupportList = theirDraftProjectManagement.getSupportLists();
        setItsFileTypeList(itsFileTypeList);
        if (theirSupportList.containsKey("VulnerableGroupEntity")) {
            setVulnerableGroup((List<VulnerableGroupEntity>) theirSupportList.get("VulnerableGroupEntity"));
        }

        if (!(itsDraftProjectEntity == null)) {
            if (getItsDraftProjectEntity().getDraftProjectStatus().getDraftProjectStatusConsecutiveStatus() == 1) {
                itsDisabledValidacionSeplanTab = false;
            } else {
                itsDisabledValidacionSeplanTab = true;
            }
        }
        tipoFicha();
    }

    private void populateSupportList() {
        theirSupportList = theirDraftProjectManagement.getSupportLists();
    }

    @Override
    public Map<String, List<?>> getMySupportList() {
        return theirSupportList;
    }

    @Override
    public void setMySupportList(Map<String, List<?>> aSupportList) {
        this.theirSupportList = aSupportList;
    }

    @Override
    public int getDraftType() {
        return theirDraftType;
    }

    @Override
    public void setDraftType(int aDraftScope) {
        this.theirDraftType = aDraftScope;
    }

    @Override
    public String getDraftProjectName() {
        return theirDraftName;
    }

    @Override
    public void setDraftProjectName(String aDraftName) {
        this.theirDraftName = aDraftName;
    }

    @Override
    public String getDraftNumber() {
        return theirDraftNumber;
    }

    @Override
    public void setDraftNumber(String aDraftNumber) {
        this.theirDraftNumber = aDraftNumber;
    }

    @Override
    public String getDraftShortName() {
        return theirDraftShortName.toUpperCase();
    }

    @Override
    public void setDraftShortName(String aDraftShortName) {
        this.theirDraftShortName = aDraftShortName.toUpperCase();
    }

    @Override
    public Date getDraftEndDate() {
        return theirDraftEndDate;
    }

    @Override
    public void setDraftEndDate(Date aDraftEndDate) {
        this.theirDraftEndDate = aDraftEndDate;
    }

    @Override
    public Date getDraftStartDate() {
        return theirDraftStartDate;
    }

    @Override
    public void setDraftStartDate(Date aDraftStartDate) {
        this.theirDraftStartDate = aDraftStartDate;
    }

    @Override
    public int getDraftDurationInMonths() {
        return theirDraftDurationInMonths;
    }

    @Override
    public void setDraftDurationInMonths(int aDurationInMonths) {
        this.theirDraftDurationInMonths = aDurationInMonths;
    }

    @Override
    public String getDraftDescription() {
        return theirDraftDescription;
    }

    @Override
    public void setDraftDescription(String aDraftDescription) {
        this.theirDraftDescription = aDraftDescription;
    }

    @Override
    public String getDraftLegalBase() {
        return theirDraftLegalBase;
    }

    @Override
    public void setDraftLegalBase(String aDraftLegalBase) {
        this.theirDraftLegalBase = aDraftLegalBase;
    }

    @Override
    public SampleEntityDto getSelectedDraftType() {
        return theirSelectedDraftType;
    }

    @Override
    public void setSelectedDraftType(SampleEntityDto aSelectedDraftType) {
        this.theirSelectedDraftType = aSelectedDraftType;
    }

    @Override
    public Long getDraftTypeId() {
        return theirDraftTypeId;
    }

    @Override
    public void setDraftTypeId(Long aDraftTypeId) {
        this.theirDraftTypeId = aDraftTypeId;
    }

    @Override
    public Long getIdDependency() {
        return idDependency;
    }

    /**
     * @return the itsDisabledTabDelivery
     */
    @Override
    public boolean isItsDisabledTabDelivery() {
        return itsDisabledTabDelivery;
    }

    /**
     * @param itsDisabledTabDelivery the itsDisabledTabDelivery to set
     */
    @Override
    public void setItsDisabledTabDelivery(boolean itsDisabledTabDelivery) {
        this.itsDisabledTabDelivery = itsDisabledTabDelivery;
    }

    /**
     * @return the itsDisabledTabClassif
     */
    @Override
    public boolean isItsDisabledTabClassif() {
        return itsDisabledTabClassif;
    }

    /**
     * @return the theirDraftPurpose
     */
    @Override
    public String getDraftPurpose() {
        return theirDraftPurpose;
    }

    /**
     * @param theirDraftPurpose the theirDraftPurpose to set
     */
    @Override
    public void setDraftPurpose(String theirDraftPurpose) {
        this.theirDraftPurpose = theirDraftPurpose;
    }

    /**
     * Este método obtiene el ID del estatus indicado en la enumeración, en base
     * al consecutivo asignado a al estatus
     */
    //TODO: Definir si la entidad tendra un numerador para definir el orden
    @Override
    public void assignStatusFromConsecutive() {
        DraftProjectStatusEntity myDraftProjectStatusEntity = new DraftProjectStatusEntity();
        myDraftProjectStatusEntity = this.theirDraftProjectManagement.getStatusEntityByConsecutive(this.getStatusConsecutive());
        this.setDraftStatusId(myDraftProjectStatusEntity.getDraftProjectStatusId());
    }

    @Override
    public Long getDraftStateId() {
        return theirDraftStateId;
    }

    @Override
    public void setDraftStateId(Long aDraftStateId) {
        this.theirDraftStateId = aDraftStateId;
    }

    @Override
    public Long getDraftScopeId() {
        return theirDraftScopeId;
    }

    @Override
    public void setDraftScopeId(Long theirDraftScopeId) {
        this.theirDraftScopeId = theirDraftScopeId;
    }

    @Override
    public void addDraftProject() {
        this.isDisabledTxtShortName = false;
        itsDisabledTab = true;
        itsDisabledTabClassif = false;
        itsDisabledTabDelivery = true;
        isNewButtonDisabled = true;
        theirCurrentDraftProjectId = null;
        theirProgrammingId = null;
        theirDraftShortName = "";
        theirDraftName = "";
        selectedVulGrops = new ArrayList<String>();
        //TODO
        //Implementautogenerated number
        theirDraftNumber = "";
        theirDraftDescription = "";
        theirDraftLegalBase = "";
        if (theirDraftStartDate != null && theirDraftEndDate != null) {
            theirDraftDurationInMonths = itsDraftProjectUIHelper.getDiferenceBetweenTwoDates(theirDraftStartDate, theirDraftEndDate);
        } else {
            theirDraftDurationInMonths = 0;
        }
        theirDraftTypeId = new Long(0);
        theirDraftStateId = new Long(0);
        theirDraftScopeId = new Long(0);
        theirDraftType = 2;
        theirDraftPurpose = "";
        this.setDraftStatusDescription("");
        //Se especifica que en esta pantalla, el estatus es INICIADO
        this.setStatusConsecutive(DraftProjectStatusEnum.INICIADO.ordinal());
        //Se obtiene el id del estatus INICIADO y se adigna a una entity
        assignStatusFromConsecutive();
    }

    private void prepareEditProject() {
        setItsDraftProjectEntity(theirDraftProjectManagement.getDraftProjectById(theirCurrentDraftProjectId));
        this.setItsDisabledTabDelivery(false);
        this.setItsDisabledTabClassif(false);
        this.setItsTabActiveIndex(0);
        theirDraftShortName = getItsDraftProjectEntity().getDraftProjectShortName();
        theirDraftName = getItsDraftProjectEntity().getDraftProjectName();
        theirDraftEndDate = getItsDraftProjectEntity().getDraftProjectEndDate();
        theirDraftStartDate = getItsDraftProjectEntity().getDraftProjectStartDate();
        theirDraftNumber = getItsDraftProjectEntity().getDraftProjectKey();
        theirDraftDescription = getItsDraftProjectEntity().getDraftProjectDescription();
        theirDraftLegalBase = getItsDraftProjectEntity().getDraftProjectBaseLegal();
        theirDraftType = getItsDraftProjectEntity().getDraftFileType().ordinal();
        theirDraftDurationInMonths = DraftProjectHeaderUIHelper.getDiferenceBetweenTwoDates(theirDraftStartDate, theirDraftEndDate);

        theirDraftTypeId = getItsDraftProjectEntity().getDraftProjectType().getDraftProjectTypeId();
        theirDraftStateId = getItsDraftProjectEntity().getDraftProjectState().getDraftProjectStateId();
        theirDraftStatusId = getItsDraftProjectEntity().getDraftProjectStatus().getDraftProjectStatusId();
        theirDraftStatusDescription = getItsDraftProjectEntity().getDraftProjectStatus().getDraftProjectStatusdescription().toString();
        vulnerableGroupSelected = getItsDraftProjectEntity().getDraftProjectVulnerableGroup();
        selectedVulGrops = new ArrayList<String>();
        for (VulnerableGroupEntity selected : vulnerableGroupSelected) {
            selectedVulGrops.add(selected.getVulnerableGroupId().toString());
        }

        theirDraftPurpose = getItsDraftProjectEntity().getDraftProjectPurpose();
        isNewButtonDisabled = false;
        theirStatusConsecutive = this.getItsDraftProjectEntity().getDraftProjectStatus().getDraftProjectStatusConsecutiveStatus();
        ubicationPreFileController.setItsIdDraftProject(getItsDraftProjectEntity().getDraftProjectId());
        ubicationPreFileController.setTheirInvPreFileId(null);
        this.setIsDisabledTxtShortName(Boolean.TRUE);
    }

    /**
     * @param idDependency the idDependency to set
     */
    @Override
    public void setIdDependency(Long idDependency) {
        this.idDependency = idDependency;
    }

    @Override
    public boolean isItsDisabledTab() {
        return itsDisabledTab;
    }

    @Override
    public void setItsDisabledTab(boolean itsDisabledTab) {
        this.itsDisabledTab = itsDisabledTab;
    }

    @Override
    public String getMinDate() {
        String minDate = "1/1/" + theirOptionsController.getYear().substring(2, 4);
        return minDate;
    }

    @Override
    public String navigateInvPre() {
        ubicationPreFileController.cleanFromAnteProj();
        InvPreFileEntity newInvPrefile = new InvPreFileEntity();
        newInvPrefile.setInvPreFileDraftProject(itsDraftProjectEntity);
        theirInvPreFileController.setTheirInvPreFileDraftEntity(newInvPrefile);
        return "newInPreFile";
    }

    @Override
    public boolean isIsNewButtonDisabled() {
        return isNewButtonDisabled;
    }

    @Override
    public void setIsNewButtonDisabled(boolean isNewButtonDisabled) {
        this.isNewButtonDisabled = isNewButtonDisabled;
    }

    @Override
    public ObjectiveJoinLevelTreeviewDto getItsAxisSelected() {
        return itsAxisSelected;
    }

    @Override
    public void setItsAxisSelected(ObjectiveJoinLevelTreeviewDto itsAxisSelected) {
        this.itsAxisSelected = itsAxisSelected;
    }

    @Override
    public void saveDraftProject() {


        if (itsDraftProjectUIHelper.validateDraftProject((DraftProjectHeaderControllerApi) this)) {
            VulnerableGroupEntity refVulGroup;

            List<VulnerableGroupEntity> listVulGroups =
                    new ArrayList<VulnerableGroupEntity>();

            for (String idVulGroup : selectedVulGrops) {

                refVulGroup = new VulnerableGroupEntity();
                refVulGroup.setVulnerableGroupId(
                        Long.parseLong(idVulGroup));
                refVulGroup = SearchList.findObjectList(
                        vulnerableGroup, refVulGroup);

                listVulGroups.add(refVulGroup);
            }

            DraftProjectEntity myDraftEntity = new DraftProjectEntity();
            DependenceEntity entityDep = new DependenceEntity();
            entityDep.setDependenceId(idDependency);

            this.theirCurrentDraftProjectId = this.getTheirCurrentDraftProjectId();
            myDraftEntity = itsDraftProjectUIHelper.prepareDraftProjectEntity(this);

            Long mySaveDraftProject = 0L;
            myDraftEntity.setDraftProjectDependency(entityDep);
            myDraftEntity.setDraftProjectVulnerableGroup(listVulGroups);

            //En caso de que sea una edición debemos mantener la cardinalidad hacia
            //el registro de programacion correspondiente
            ProgrammingEntity myProgramming = new ProgrammingEntity();
            myProgramming.setProgrammingId(theirProgrammingId);
            myProgramming.setProgrammingDescription(theirDraftShortName);
            if (theirProgrammingId != null) {
                myDraftEntity.setDraftProjectProgramming(myProgramming);
            } else {
                String myNewKey = theirDraftProjectManagement.getTotalProjects(idDependency);
                myDraftEntity.setDraftProjectKey(myNewKey);
            }
            try {
                myDraftEntity = theirDraftProjectManagement.saveDraftProject(myDraftEntity, theirObjectiveId);
            } catch (DataIntegrityViolationException exc) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.ErrorShortNameExist", new String[]{myDraftEntity.getDraftProjectShortName()}),
                        this.getMessage("ppp.progr.ErrorShortNameExist", new String[]{myDraftEntity.getDraftProjectShortName()}));
                return;
            } catch (RuntimeException exc) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.ErrorDraftProjectGuardado"),
                        this.getMessage("ppp.progr.ErrorDraftProjectGuardado"));
                this.setTheirCurrentDraftProjectId(myDraftEntity.getDraftProjectId());
                setItsDraftProjectEntity(myDraftEntity);
                return;
            }


            theirDraftNumber = myDraftEntity.getDraftProjectKey();
            this.setTheirCurrentDraftProjectId(myDraftEntity.getDraftProjectId());

            ubicationPreFileController.setItsIdDraftProject(myDraftEntity.getDraftProjectId());
            ubicationPreFileController.setTheirInvPreFileId(null);

            isNewButtonDisabled = false;
            //Si el anteproyecto ya fue guardado en ese status, no lo registra en bitácora
            itsDisabledTab = false;
            setItsDisabledTabButton(false);
            btnSrvSave = false;
            itsDisabledTabDelivery = false;
            itsDisabledTabClassif = false;
            setItsDraftProjectEntity(theirDraftProjectManagement.getDraftProjectById(myDraftEntity.getDraftProjectId()));
            theirProgrammingId = getItsDraftProjectEntity().getDraftProjectProgramming().getProgrammingId();
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.progr.DraftProjectGuardado"),
                    this.getMessage("ppp.progr.DraftProjectGuardado"));
            this.setIsDisabledTxtShortName(Boolean.TRUE);

        }
    }

    @Override
    public Long getDraftStatusId() {
        return theirDraftStatusId;
    }

    /**
     * @param theirDraftStatusId the theirDraftStatusId to set
     */
    @Override
    public void setDraftStatusId(Long theirDraftStatusId) {
        this.theirDraftStatusId = theirDraftStatusId;
    }

    @Override
    public ObjectiveJoinLevelTreeviewDto getItsProgramSelected() {
        return itsProgramSelected;
    }

    @Override
    public void setItsProgramSelected(ObjectiveJoinLevelTreeviewDto itsProgramSelected) {
        this.itsProgramSelected = itsProgramSelected;
    }

    @Override
    public ObjectiveJoinLevelTreeviewDto getItsSubProgramSelected() {
        return itsSubProgramSelected;
    }

    @Override
    public void setItsSubProgramSelected(ObjectiveJoinLevelTreeviewDto itsSubProgramSelected) {
        this.itsSubProgramSelected = itsSubProgramSelected;
    }

    /**
     * @return the theirDraftStatusDescription
     */
    @Override
    public String getDraftStatusDescription() {
        return theirDraftStatusDescription;
    }

    /**
     * @param theirDraftStatusDescription the theirDraftStatusDescription to set
     */
    @Override
    public void setDraftStatusDescription(String theirDraftStatusDescription) {
        this.theirDraftStatusDescription = theirDraftStatusDescription;
    }

    /**
     * @return the theirStatusConsecutive
     */
    @Override
    public int getStatusConsecutive() {
        return theirStatusConsecutive;
    }

    /**
     * @param theirStatusConsecutive the theirStatusConsecutive to set
     */
    @Override
    public void setStatusConsecutive(int theirStatusConsecutive) {
        this.theirStatusConsecutive = theirStatusConsecutive;
    }

    @Override
    public List<DraftProjectTypeEntity> getItsFileTypeList() {
        return itsFileTypeList;
    }

    @Override
    public void setItsFileTypeList(List<DraftProjectTypeEntity> itsFileTypeList) {
        itsFileTypeList.clear();
        for (Map.Entry<String, List<?>> entry : theirSupportList.entrySet()) {
            String label = entry.getKey();
            if (label.equalsIgnoreCase("DraftProjectTypes")) {
                for (Object value : entry.getValue()) {
                    itsFileTypeList.add((DraftProjectTypeEntity) value);
                }
            }
        }
        this.itsFileTypeList = itsFileTypeList;
    }

    @Override
    public List<DraftProjectTypeEntity> getItsCmbTypeList() {
        return itsCmbTypeList;
    }

    @Override
    public void setItsCmbTypeList(List<DraftProjectTypeEntity> itsCmbTypeList) {
        this.itsCmbTypeList = itsCmbTypeList;
    }

    @Override
    public void tipoFicha() {
        this.itsCmbTypeList.clear();
        for (DraftProjectTypeEntity tipo : this.itsFileTypeList) {
            if (getDraftType() == 0 && tipo.isIsProceso()) {//Proceso
                this.itsCmbTypeList.add(tipo);
            }
            if (getDraftType() == 1 && tipo.isIsProyecto()) {//Proyecto
                this.itsCmbTypeList.add(tipo);
            }
        }
    }

    /**
     * @return the itsDraftProjectEntity
     */
    @Override
    public DraftProjectEntity getItsDraftProjectEntity() {
        return itsDraftProjectEntity;
    }

    /**
     * @param itsDraftProjectEntity the itsDraftProjectEntity to set
     */
    @Override
    public void setItsDraftProjectEntity(DraftProjectEntity itsDraftProjectEntity) {
        this.itsDraftProjectEntity = itsDraftProjectEntity;
    }

    @Override
    public boolean isItsDisabledValidacionSeplanTab() {
        return itsDisabledValidacionSeplanTab;
    }

    @Override
    public void setItsDisabledValidacionSeplanTab(boolean itsDisabledValidacionSeplanTab) {
        this.itsDisabledValidacionSeplanTab = itsDisabledValidacionSeplanTab;
    }

    /**
     * @return the selectedOptions
     */
    @Override
    public List<String> getSelectedVulGrops() {
        return selectedVulGrops;
    }

    /**
     * @param selectedOptions the selectedOptions to set
     */
    @Override
    public void setSelectedVulGrops(List<String> selectedOptions) {
        this.selectedVulGrops = selectedOptions;
    }

    /**
     * @return the vulnerableGroup
     */
    @Override
    public List<VulnerableGroupEntity> getVulnerableGroup() {
        return vulnerableGroup;
    }

    /**
     * @param vulnerableGroup the vulnerableGroup to set
     */
    @Override
    public void setVulnerableGroup(List<VulnerableGroupEntity> vulnerableGroup) {
        this.vulnerableGroup = vulnerableGroup;
    }

    @Override
    public Long getItsSelectedUEG() {
        return itsSelectedUEG;
    }

    @Override
    public int getItsTabActiveIndex() {
        return itsTabActiveIndex;
    }

    @Override
    public void setItsTabActiveIndex(int itsTabActiveIndex) {
        this.itsTabActiveIndex = itsTabActiveIndex;
    }

    @Override
    public void setItsSelectedUEG(Long itsSelectedUEG) {
        this.itsSelectedUEG = itsSelectedUEG;
    }

    /**
     * @param itsDisabledTabClassif the itsDisabledTabClassif to set
     */
    @Override
    public void setItsDisabledTabClassif(boolean itsDisabledTabClassif) {
        this.itsDisabledTabClassif = itsDisabledTabClassif;
    }

    /**
     * @return the itsDisabledTabButton
     */
    @Override
    public boolean isItsDisabledTabButton() {
        return itsDisabledTabButton;
    }

    /**
     * @param itsDisabledTabButton the itsDisabledTabButton to set
     */
    @Override
    public void setItsDisabledTabButton(boolean itsDisabledTabButton) {
        this.itsDisabledTabButton = itsDisabledTabButton;
    }

    /**
     * @return the isDisabledTxtShortName
     */
    @Override
    public boolean isIsDisabledTxtShortName() {
        return isDisabledTxtShortName;
    }

    /**
     * @param isDisabledTxtShortName the isDisabledTxtShortName to set
     */
    @Override
    public void setIsDisabledTxtShortName(boolean isDisabledTxtShortName) {
        this.isDisabledTxtShortName = isDisabledTxtShortName;
    }

    /**
     * @return the btnSrvSave
     */
    @Override
    public Boolean getBtnSrvSave() {
        return btnSrvSave;
    }

    /**
     * @param btnSrvSave the btnSrvSave to set
     */
    @Override
    public void setBtnSrvSave(Boolean btnSrvSave) {
        this.btnSrvSave = btnSrvSave;
    }
}
