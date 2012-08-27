package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.data.DraftFileType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.api.controller.FinancialStructureControllerApi;
import com.abs.siif.ppp.programming.api.controller.InvPreFileControllerApi;
import com.abs.siif.ppp.programming.api.controller.ObservationControllerApi;
import com.abs.siif.ppp.programming.api.controller.UbicationPreFileControllerApi;
import com.abs.siif.ppp.programming.uihelpers.InvPreFileHeaderUIHelper;
import com.abs.siif.programming.dto.DependenciesInvPreFileDto;
import com.abs.siif.programming.dto.UrlConnectionReportDTO;
import com.abs.siif.programming.entities.*;
import com.abs.siif.programming.management.InvPreFileManagement;
import com.abs.siif.programming.management.PromoterManagement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("invPreFileController")
@Scope("session")
public class InvPreFileController extends SIIFControllerBase implements Serializable, InvPreFileControllerApi {

    //These Injections are related to sendInvPreFileId method.
    @Resource(name = "goalsBeneficiariesController")
    private transient GoalsBeneficiariesController goalsBeneficiariesController;

    @Resource(name = "ubicationPreFileController")
    private transient UbicationPreFileControllerApi ubicationPreFileController;

    @Resource(name = "financialStructureController")
    private transient FinancialStructureControllerApi financialStructureController;

    @Resource(name = "physicalFinancialProgController")
    private transient PhysicalFinancialProgController physicalFinancialProgController;

    @Resource(name = "sizingController")
    private transient SizingController sizingController;

    @Resource(name = "PreInvRequestController")
    private transient PreInvRequestController preInvRequestController;

    @Resource(name = "invPreFileManagement")
    private transient InvPreFileManagement theirInvPreFileManagement;

    @Resource(name = "promoterManagement")
    private transient PromoterManagement promoterManagement;
    
    @Resource(name = "observationController")
    private transient ObservationControllerApi observationController;

    private transient InvPreFileHeaderUIHelper itsInvPreFileHeaderUIHelper = new InvPreFileHeaderUIHelper();
    /*
     * theirInvPreFileType value is used to define the Action and Build values
     */

    private String theirInvPreFileType;
    /*
     * theirInvPreFileDesagType value is used to define EspecifProy, Program and
     * Fund values.
     */

    private String theirInvPreFileDesagLevel;

    private Long theirInvPreFileId;

    private boolean isInvPreFileAction;

    private boolean isInvPreFileBuild;

    private String theirInvPreFileNumber = "";

    private boolean isInvPreFileEspecifProy;

    private boolean isInvPreFileProgram;

    private boolean isInvPreFileFund;

    private String theirInvPreFilePriority = "";

    private String theirInvPreFileName = "";

    private String theirInvPreFileDescription = "";

    private String theirInvPreFileBenefitDesc = "";

    private String theirInvPreFileURE = "";

    private List<String> theirInvPreFileUEGSelected = new ArrayList<String>();

    private Long theirInvPreFileURN;

    private Long theirInvPreFilePromoter;

    private String theirInvPreFilePcPy = "";

    private Long theirInvPreFileComponent;

    private boolean isSaveButtonDisabled = false;

    private boolean isPriorityReadOnly = true;
    /*
     * These variables control enable and disabled the tabs.
     */

    private boolean isGoalsTabDisabled = false;

    private boolean isUbicationTabDisabled = true;

    private boolean isFinStructureTabDisabled = true;

    private boolean isPFProgTabDisabled = true;

    private boolean isSizingTabDisabled = true;

    private boolean isRequestTabDisabled = true;
    //The following is obtained from Dto

    private DependenceEntity theirInvPreFileUREEntity;

    private List<FederalURRegulatoryEntity> theirInvPreFileURNList;

    private List<DependenceEntity> theirInvPreFileUEGList = new ArrayList<DependenceEntity>();;

    private List<PromoterEntity> theirInvPreFilePromoterList;

    private List<ComponentEntity> theirInvPreFileComponentList;

    private DraftFileType theirInvPreFileDraftProyect;

    private Set<ObservationEntity> theirInvPreFileObservations;

    private DraftProjectEntity theirInvPreFileDraftEntity;
    //variabla para el nuevo promotor

    private String newPromoter = "";
    
    //variable para el bug 207. UEG de la ficha madre de solo lectura
    private DependenceEntity itsInvPreFileUEGFicha = new DependenceEntity();
 
    private String imprimirReporte;
    
    private boolean disableHRefReport;
    
    private boolean btnObservations;

    @Override
    public String getTheirInvPreFileType() {
        return theirInvPreFileType;
    }
    /*
     * if theirInvPreFileType value is "1" then Action is True and Build false.
     * if theirInvPreFileType value is "0" then Action is False and Build True.
     */

    @Override
    public void setTheirInvPreFileType(String anInvPreFileType) {
        this.theirInvPreFileType = anInvPreFileType;
        if (anInvPreFileType.equalsIgnoreCase("1")) {
            setTheirInvPreFileAction(true);
            setTheirInvPreFileBuild(false);
        } else if (anInvPreFileType.equalsIgnoreCase("0")) {
            setTheirInvPreFileAction(false);
            setTheirInvPreFileBuild(true);
        }
    }

    @Override
    public String getTheirInvPreFileDesagLevel() {
        return theirInvPreFileDesagLevel;
    }

    /*
     * if theirInvPreFileDesagLevel value is "0" then EspecifProy is True if
     * theirInvPreFileDesagLevel value is "1" then Program is True if
     * theirInvPreFileDesagLevel value is "2" then Fund is True
     */
    @Override
    public void setTheirInvPreFileDesagLevel(String anInvPreFileDesagLevel) {
        this.theirInvPreFileDesagLevel = anInvPreFileDesagLevel;
        if (anInvPreFileDesagLevel.equalsIgnoreCase("0")) {
            setTheirInvPreFileEspecifProy(true);
            setTheirInvPreFileProgram(false);
            setTheirInvPreFileFund(false);
        } else if (anInvPreFileDesagLevel.equalsIgnoreCase("1")) {
            setTheirInvPreFileEspecifProy(false);
            setTheirInvPreFileProgram(true);
            setTheirInvPreFileFund(false);
        } else if (anInvPreFileDesagLevel.equalsIgnoreCase("2")) {
            setTheirInvPreFileEspecifProy(false);
            setTheirInvPreFileProgram(false);
            setTheirInvPreFileFund(true);
        }
    }

    @Override
    public Long getTheirInvPreFileId() {
        return theirInvPreFileId;
    }

    @Override
    public void setTheirInvPreFileId(Long anInvPreFileId) {
        this.theirInvPreFileId = anInvPreFileId;
    }

    @Override
    public boolean isTheirInvPreFileAction() {
        return isInvPreFileAction;
    }

    @Override
    public void setTheirInvPreFileAction(boolean anInvPreFileAction) {
        this.isInvPreFileAction = anInvPreFileAction;
    }

    @Override
    public boolean isTheirInvPreFileBuild() {
        return isInvPreFileBuild;
    }

    @Override
    public void setTheirInvPreFileBuild(boolean anInvPreFileBuild) {
        this.isInvPreFileBuild = anInvPreFileBuild;
    }

    @Override
    public String getTheirInvPreFileNumber() {
        return theirInvPreFileNumber;
    }

    @Override
    public void setTheirInvPreFileNumber(String anInvPreFileNumber) {
        this.theirInvPreFileNumber = anInvPreFileNumber;
    }

    @Override
    public boolean isTheirInvPreFileEspecifProy() {
        return isInvPreFileEspecifProy;
    }

    @Override
    public void setTheirInvPreFileEspecifProy(boolean anInvPreFileEspecifProy) {
        this.isInvPreFileEspecifProy = anInvPreFileEspecifProy;
    }

    @Override
    public boolean isTheirInvPreFileProgram() {
        return isInvPreFileProgram;
    }

    @Override
    public void setTheirInvPreFileProgram(boolean anInvPreFileProgram) {
        this.isInvPreFileProgram = anInvPreFileProgram;
    }

    @Override
    public boolean isTheirInvPreFileFund() {
        return isInvPreFileFund;
    }

    @Override
    public void setTheirInvPreFileFund(boolean anInvPreFileFund) {
        this.isInvPreFileFund = anInvPreFileFund;
    }

    @Override
    public String getTheirInvPreFilePriority() {
        return theirInvPreFilePriority;
    }

    @Override
    public void setTheirInvPreFilePriority(String anInvPreFilePriority) {
        this.theirInvPreFilePriority = anInvPreFilePriority;
    }

    @Override
    public String getTheirInvPreFileName() {
        return theirInvPreFileName;
    }

    @Override
    public void setTheirInvPreFileName(String anInvPreFileName) {
        this.theirInvPreFileName = anInvPreFileName;
    }

    @Override
    public String getTheirInvPreFileDescription() {
        return theirInvPreFileDescription;
    }

    @Override
    public void setTheirInvPreFileDescription(String anInvPreFileDescription) {
        this.theirInvPreFileDescription = anInvPreFileDescription;
    }

    @Override
    public String getTheirInvPreFileBenefitDesc() {
        return theirInvPreFileBenefitDesc;
    }

    @Override
    public void setTheirInvPreFileBenefitDesc(String anInvPreFileBenefitDesc) {
        this.theirInvPreFileBenefitDesc = anInvPreFileBenefitDesc;
    }

    @Override
    public Long getTheirInvPreFileComponent() {
        return theirInvPreFileComponent;
    }

    @Override
    public void setTheirInvPreFileComponent(Long anInvPreFileComponent) {
        this.theirInvPreFileComponent = anInvPreFileComponent;
    }

    @Override
    public String getTheirInvPreFilePcPy() {
        return theirInvPreFilePcPy;
    }

    @Override
    public void setTheirInvPreFilePcPy(String anInvPreFilePcPy) {
        this.theirInvPreFilePcPy = anInvPreFilePcPy;
    }

    @Override
    public Long getTheirInvPreFilePromoter() {
        return theirInvPreFilePromoter;
    }

    @Override
    public void setTheirInvPreFilePromoter(Long anInvPreFilePromoter) {
        this.theirInvPreFilePromoter = anInvPreFilePromoter;
    }

    @Override
    public List<String> getTheirInvPreFileUEGSelected() {
        return theirInvPreFileUEGSelected;
    }

    @Override
    public void setTheirInvPreFileUEGSelected(List<String> anInvPreFileUEG) {
        this.theirInvPreFileUEGSelected = anInvPreFileUEG;
    }

    @Override
    public String getTheirInvPreFileURE() {
        return theirInvPreFileURE;
    }

    @Override
    public void setTheirInvPreFileURE(String anInvPreFileURE) {
        this.theirInvPreFileURE = anInvPreFileURE;
    }

    @Override
    public Long getTheirInvPreFileURN() {
        return theirInvPreFileURN;
    }

    @Override
    public void setTheirInvPreFileURN(Long anInvPreFileURN) {
        this.theirInvPreFileURN = anInvPreFileURN;
    }

    @Override
    public boolean isIsSaveButtonDisabled() {
        return isSaveButtonDisabled;
    }

    @Override
    public void setIsSaveButtonDisabled(boolean isSaveButtonDisabled) {
        this.isSaveButtonDisabled = isSaveButtonDisabled;
    }

    @Override
    public List<ComponentEntity> getTheirInvPreFileComponentList() {
        return theirInvPreFileComponentList;
    }

    @Override
    public void setTheirInvPreFileComponentList(List<ComponentEntity> theirInvPreFileComponentList) {
        this.theirInvPreFileComponentList = theirInvPreFileComponentList;
    }

    @Override
    public List<PromoterEntity> getTheirInvPreFilePromoterList() {
        return theirInvPreFilePromoterList;
    }

    @Override
    public void setTheirInvPreFilePromoterList(List<PromoterEntity> theirInvPreFilePromoterList) {
        this.theirInvPreFilePromoterList = theirInvPreFilePromoterList;
    }

    @Override
    public List<DependenceEntity> getTheirInvPreFileUEGList() {
        return theirInvPreFileUEGList;
    }

    @Override
    public void setTheirInvPreFileUEGList(List<DependenceEntity> theirInvPreFileUEGList) {
        this.theirInvPreFileUEGList = theirInvPreFileUEGList;
    }

    @Override
    public List<FederalURRegulatoryEntity> getTheirInvPreFileURNList() {
        return theirInvPreFileURNList;
    }

    @Override
    public void setTheirInvPreFileURNList(List<FederalURRegulatoryEntity> theirInvPreFileURNList) {
        this.theirInvPreFileURNList = theirInvPreFileURNList;
    }

    @Override
    public DraftProjectEntity getTheirInvPreFileDraftEntity() {
        return theirInvPreFileDraftEntity;
    }

    @Override
    public void setTheirInvPreFileDraftEntity(InvPreFileEntity invPrefile) {
        if (invPrefile.getInvPreFileId() == null) {
            //Nuevo
            this.theirInvPreFileDraftEntity = invPrefile.getInvPreFileDraftProject();
            this.theirInvPreFileId = null;
            prepareNewInvPreFile();
            goalsBeneficiariesController.cleanView();
            goalsBeneficiariesController.setInvPreFileId(null);
            this.setDisableHRefReport(Boolean.TRUE);

        } else {
            //Editando
            theirInvPreFileUEGSelected.clear();
            this.theirInvPreFileDraftEntity = invPrefile.getInvPreFileDraftProject();
            goalsBeneficiariesController.setInvPreFileId(invPrefile.getInvPreFileId());
            prepareNewInvPreFile();
            setIsSaveButtonDisabled(false);

            if (invPrefile.isAction()) {
                setTheirInvPreFileType("1");
            } else {
                setTheirInvPreFileType("0");
            }

            if (invPrefile.isEspecificProyect()) {
                setTheirInvPreFileDesagLevel("0");
            } else if (invPrefile.isProgramm()) {
                setTheirInvPreFileDesagLevel("1");
            } else if (invPrefile.isFund()) {
                setTheirInvPreFileDesagLevel("2");
            }

            theirInvPreFileNumber = invPrefile.getFolio();
            int index = invPrefile.getPriority().indexOf("_");
            theirInvPreFilePriority = invPrefile.getPriority().substring(index + 1);
            theirInvPreFileName = invPrefile.getName();
            theirInvPreFileDescription = invPrefile.getDescription();
            theirInvPreFileBenefitDesc = invPrefile.getDescBenefits();
            theirInvPreFileURN = invPrefile.getInvPreFileURRegulatory().getFederalUrRegulatoryId();
            theirInvPreFilePromoter = invPrefile.getPromoter().getIdPromoter();
            theirInvPreFileComponent = invPrefile.getInvPreFileComponent().getComponentId();
            for (DependenceEntity dep : invPrefile.getUnitExecSpending()) {
                theirInvPreFileUEGSelected.add(dep.getDependenceId().toString());
            }
            this.theirInvPreFileId = invPrefile.getInvPreFileId();
            disableTabs(false);
            setIsPriorityReadOnly(true);
            sendInvPreFileId();
            sendUEGToFinancialStructure();
            observationController.setItsInvPreFileId(invPrefile.getInvPreFileId());
            
            UrlConnectionReportDTO objUrlServer = (UrlConnectionReportDTO) SIIFContextBase.getParamContext(KeyContextEnum.URLSERVERREPORT);
                objUrlServer.setIdObjecto(invPrefile.getInvPreFileId().toString());
                objUrlServer.setTipoFicha(this.getMessage("ppp.progr.InvPreFile.report.preficha"));
                setImprimirReporte(objUrlServer.getUrlDocumento());
            this.setDisableHRefReport(Boolean.FALSE);
        }


    }

    @Override
    public DraftFileType getTheirInvPreFileDraftProyect() {
        return theirInvPreFileDraftProyect;
    }

    @Override
    public void setTheirInvPreFileDraftProyect(DraftFileType theirInvPreFileDraftProyect) {
        this.theirInvPreFileDraftProyect = theirInvPreFileDraftProyect;
    }

    @Override
    public DependenceEntity getTheirInvPreFileUREEntity() {
        return theirInvPreFileUREEntity;
    }

    @Override
    public void setTheirInvPreFileUREEntity(DependenceEntity anInvPreFileUREEntity) {
        this.theirInvPreFileUREEntity = anInvPreFileUREEntity;
    }

    public InvPreFileController() {
    }

    @Override
    public void saveInvPreFile() {

        if (itsInvPreFileHeaderUIHelper.validateInvPreFile(this)) {

            Severity myMessageFaces = FacesMessage.SEVERITY_INFO;
            String myMessageUI = this.getMessage("ppp.progr.InvPreFileGuardado");

            try {
                InvPreFileEntity myInvPreFileEntity = new InvPreFileEntity();
                myInvPreFileEntity = InvPreFileHeaderUIHelper.prepareInvPreFileEntity(this);
                if (myInvPreFileEntity.getInvPreFileURRegulatory().getFederalUrRegulatoryId() == -1){
                    myInvPreFileEntity.setInvPreFileURRegulatory(theirInvPreFileManagement.getDefaultFederalURN());
                }
                myInvPreFileEntity = theirInvPreFileManagement.saveGeneralDataInvPreFile(myInvPreFileEntity);
                setTheirInvPreFileId(myInvPreFileEntity.getInvPreFileId());

                setTheirInvPreFileNumber(myInvPreFileEntity.getFolio());
                //setIsSaveButtonDisabled(true);
                disableTabs(false);
                sendInvPreFileId();
                sendUEGToFinancialStructure();

                UrlConnectionReportDTO objUrlServer = (UrlConnectionReportDTO) SIIFContextBase.getParamContext(KeyContextEnum.URLSERVERREPORT);
                objUrlServer.setIdObjecto(myInvPreFileEntity.getInvPreFileId().toString());
                objUrlServer.setTipoFicha(this.getMessage("ppp.progr.InvPreFile.report.preficha"));
                setImprimirReporte(objUrlServer.getUrlDocumento());
                this.setDisableHRefReport(Boolean.FALSE);

            } catch (Exception e) {
                myMessageUI = this.getMessage(e.getMessage());
                myMessageFaces = FacesMessage.SEVERITY_ERROR;
            } finally {

                addMessageCurrentInstance(myMessageFaces,
                        myMessageUI,
                        myMessageUI);
            }



        }
    }

    @Override
    public void prepareNewInvPreFile() {
        DependenciesInvPreFileDto myInvPreFileDto = theirInvPreFileManagement.getDepenInvPreFile(theirInvPreFileDraftEntity);
        theirInvPreFileComponentList = myInvPreFileDto.getComponents();
        theirInvPreFileDraftProyect = myInvPreFileDto.getDraftFileType();
//        theirInvPreFilePcPy = this.getMessage(theirInvPreFileDraftProyect.getName());
        theirInvPreFilePcPy = theirInvPreFileDraftEntity.getDraftProjectShortName();
        //theirInvPreFileObservations = myInvPreFileDto.
        theirInvPreFilePromoterList = myInvPreFileDto.getPromoters();
        theirInvPreFileUEGList.clear();
        theirInvPreFileUEGSelected.clear();
        itsInvPreFileUEGFicha.setDependenceId(myInvPreFileDto.getUrExecutingFicha().getDependenceId());
            for(DependenceEntity dep : myInvPreFileDto.getUnitExeExpend()){
                if (dep.getDependenceId().longValue() == itsInvPreFileUEGFicha.getDependenceId().longValue())
                    itsInvPreFileUEGFicha = dep;
                else
                    theirInvPreFileUEGList.add(dep);
            }
        theirInvPreFileUREEntity = myInvPreFileDto.getUrExecuting();
        theirInvPreFileURE = theirInvPreFileUREEntity.getComposedDependenceName();
        theirInvPreFileURNList = myInvPreFileDto.getUrNorm();
        theirInvPreFileURNList.remove(theirInvPreFileManagement.getDefaultFederalURN());
        
    }

    @Override
    public void addInvPreFile() {
        isInvPreFileAction = false;
        isInvPreFileBuild = false;
        theirInvPreFileNumber = "";
        isInvPreFileEspecifProy = false;
        isInvPreFileProgram = false;
        isInvPreFileFund = false;
        theirInvPreFilePriority = "";
        theirInvPreFileName = "";
        theirInvPreFileDescription = "";
        theirInvPreFileBenefitDesc = "";
        theirInvPreFileURE = "";
        // theirInvPreFileUEG = "";
        //theirInvPreFileUEGSelected = null;
        theirInvPreFileURN = null;
        theirInvPreFilePromoter = null;
        theirInvPreFilePcPy = "";
        theirInvPreFileComponent = null;
        theirInvPreFileType = "";
        theirInvPreFileDesagLevel = "";
        newPromoter = "";
        setIsSaveButtonDisabled(false);
        disableTabs(true);
    }

    /*
     * This method enables or disables the tabs in the InvPreFile view after the
     * PreFile is saved.
     */
    @Override
    public void disableTabs(boolean aFlag) {
        setIsGoalsTabDisabled(aFlag);//false hardcoded debido al bug reportado por cliente.
        setIsUbicationTabDisabled(aFlag);
        setIsFinStructureTabDisabled(aFlag);
        setIsPFProgTabDisabled(aFlag);
        setIsSizingTabDisabled(aFlag);
        setIsRequestTabDisabled(aFlag);
    }

    /*
     * This method cleans the view.
     */
    @Override
    public void cleanView() {
        setIsSaveButtonDisabled(false);
        isInvPreFileAction = false;
        isInvPreFileBuild = false;
        theirInvPreFileType = "";
        theirInvPreFileNumber = "";
        isInvPreFileEspecifProy = false;
        isInvPreFileProgram = false;
        isInvPreFileFund = false;
        theirInvPreFileDesagLevel = "";
        theirInvPreFilePriority = "";
        theirInvPreFileName = "";
        theirInvPreFileDescription = "";
        theirInvPreFileBenefitDesc = "";
        theirInvPreFileUEGSelected = null;
        theirInvPreFileURN = null;
        theirInvPreFilePromoter = null;
        theirInvPreFileComponent = null;
        itsInvPreFileUEGFicha = null;
        theirInvPreFileUEGList.clear();
        theirInvPreFileUEGSelected.clear();
        disableTabs(true);
    }

    /*
     * This method sends the InvPreFile Id to each tab controller.
     */
    @Override
    public void sendInvPreFileId() {
        goalsBeneficiariesController.setInvPreFileId(getTheirInvPreFileId());
        ubicationPreFileController.setTheirInvPreFileId(getTheirInvPreFileId());
        ubicationPreFileController.cleanFromPreFicha();
        financialStructureController.setTheirInvPreFileId(getTheirInvPreFileId());
        physicalFinancialProgController.setInvPreFileId(getTheirInvPreFileId());
        sizingController.setItsPrefileId(getTheirInvPreFileId());
        preInvRequestController.setPreFileId(getTheirInvPreFileId());
        observationController.setItsInvPreFileId(getTheirInvPreFileId());
    }

    @Override
    public void savePromoter() {
        PromoterEntity myProm = new PromoterEntity();
        myProm.setDescription(newPromoter);
        boolean exist = false;
        Severity myMessageFaces = FacesMessage.SEVERITY_INFO;
        String myMessageUI;
        for (PromoterEntity p : theirInvPreFilePromoterList) {
            if (p.getDescription().equalsIgnoreCase(newPromoter)) {
                exist = true;
            }
        }

        if (!exist) {
            this.setTheirInvPreFilePromoter(promoterManagement.savePromoter(myProm));
            myProm.setIdPromoter(theirInvPreFilePromoter);
            this.theirInvPreFilePromoterList.add(myProm);
            myMessageUI = this.getMessage("ppp.progr.NewPromSaved");
            myMessageFaces = FacesMessage.SEVERITY_INFO;
            addMessageCurrentInstance(myMessageFaces,
                    myMessageUI,
                    myMessageUI);
        } else {
            myMessageUI = this.getMessage("ppp.progr.NewPromDuplicated");
            myMessageFaces = FacesMessage.SEVERITY_ERROR;
            addMessageCurrentInstance(myMessageFaces,
                    myMessageUI,
                    myMessageUI);
        }
    }

    @Override
    public boolean isIsFinStructureTabDisabled() {
        return isFinStructureTabDisabled;
    }

    @Override
    public void setIsFinStructureTabDisabled(boolean isFinStructureTabDisabled) {
        this.isFinStructureTabDisabled = isFinStructureTabDisabled;
    }

    @Override
    public boolean isIsGoalsTabDisabled() {
        return isGoalsTabDisabled;
    }

    @Override
    public void setIsGoalsTabDisabled(boolean isGoalsTabDisabled) {
        this.isGoalsTabDisabled = isGoalsTabDisabled;
    }

    @Override
    public boolean isIsPFProgTabDisabled() {
        return isPFProgTabDisabled;
    }

    @Override
    public void setIsPFProgTabDisabled(boolean isPFProgTabDisabled) {
        this.isPFProgTabDisabled = isPFProgTabDisabled;
    }

    @Override
    public boolean isIsRequestTabDisabled() {
        return isRequestTabDisabled;
    }

    @Override
    public void setIsRequestTabDisabled(boolean isRequestTabDisabled) {
        this.isRequestTabDisabled = isRequestTabDisabled;
    }

    @Override
    public boolean isIsSizingTabDisabled() {
        return isSizingTabDisabled;
    }

    @Override
    public void setIsSizingTabDisabled(boolean isSizingTabDisabled) {
        this.isSizingTabDisabled = isSizingTabDisabled;
    }

    @Override
    public boolean isIsUbicationTabDisabled() {
        return isUbicationTabDisabled;
    }

    @Override
    public void setIsUbicationTabDisabled(boolean isUbicationTabDisabled) {
        this.isUbicationTabDisabled = isUbicationTabDisabled;
    }

    @Override
    public boolean isIsPriorityReadOnly() {
        return isPriorityReadOnly;
    }

    @Override
    public void setIsPriorityReadOnly(boolean isPriorityReadOnly) {
        this.isPriorityReadOnly = isPriorityReadOnly;
    }

    @Override
    public String getNewPromoter() {
        return newPromoter;
    }

    @Override
    public void setNewPromoter(String newPromoter) {
        this.newPromoter = newPromoter;
    }

    @Override
    public DependenceEntity getItsInvPreFileUEGFicha()
    {
        return itsInvPreFileUEGFicha;
    }

    @Override
    public void setItsInvPreFileUEGFicha(DependenceEntity itsInvPreFileUEGSelected)
    {
        this.itsInvPreFileUEGFicha = itsInvPreFileUEGSelected;
    }
    
    /**
     * Setea las UEG Seleccionadas
     */
    private void sendUEGToFinancialStructure() {
        List<DependenceEntity> list = new ArrayList<DependenceEntity>();
        for (DependenceEntity dependenceEntity : getTheirInvPreFileUEGList()) {
            for (String key : getTheirInvPreFileUEGSelected()) {
                if (dependenceEntity.getDependenceId() == Long.parseLong(key)) {
                    list.add(dependenceEntity);
                }
            }
        }
        list.add(itsInvPreFileUEGFicha);
        financialStructureController.setItsItemsUEGCbo(list);
        financialStructureController.setTheirDraftProjectEntity(getTheirInvPreFileDraftEntity());
        
    }

    /**
     * @return the imprimirReporte
     */
    @Override
    public String getImprimirReporte() {
        return imprimirReporte;
    }

    /**
     * @param imprimirReporte the imprimirReporte to set
     */
    @Override
    public void setImprimirReporte(String imprimirReporte) {
        this.imprimirReporte = imprimirReporte;
    }

    @Override
    public boolean getDisableHRefReport() {
        return this.disableHRefReport;
    }

    @Override
    public void setDisableHRefReport(boolean enable) {
        this.disableHRefReport = enable;
    }

    @Override
    public boolean getBtnObservations() {
        return btnObservations;
    }

    @Override
    public void setBtnObservations(boolean btnObservations) {
        this.btnObservations = btnObservations;
    }
        
}
