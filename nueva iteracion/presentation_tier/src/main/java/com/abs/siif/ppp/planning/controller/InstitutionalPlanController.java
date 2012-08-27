package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.common.dto.SampleEntityDto;
import com.abs.siif.planning.comparators.SampleEntityDtoComparator;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.planning.management.InstitutionalPlanManagement;
import com.abs.siif.ppp.planning.api.controller.InstitutionalPlanControllerApi;
import com.abs.siif.ppp.planning.uihelpers.InstitutionalPlanUIHelper;
import com.abs.siif.ppp.planning.uihelpers.ObjectiveInstitutionalPlanUIHelper;
import com.abs.siif.support.SearchList;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Erick Leija
 */
@Controller
@Scope("session")
public class InstitutionalPlanController extends SIIFControllerBase implements Serializable, InstitutionalPlanControllerApi {

    @Resource(name = "dependenceManagement")
    private transient DependenceManagement theirDependence;
    @Resource(name = "institutionalPlanManagement")
    private transient InstitutionalPlanManagement theirInstitutionalPlanManagement;
    @Resource(name= "institutionalPlanComponent")
    private transient InstitutionalPlanUIHelper thieirInstitutionalPlanUIHelper;
    @Resource(name = "optionsController")
    private transient OptionsController theirOptionsController;
    @Resource(name="objectiveInstitutionalPlanUIHelper")
    private transient ObjectiveInstitutionalPlanUIHelper objInstPlanUIHelper;
    private Long itsInstitutionalPlanId;
    private Long itsInstitutionalDependenceId;
    private Date itsInstitutionalOfficialPubOnDailyDate;
    private Date itsInstitutionalstartDateOfActivities;
    private Date itsInstitutionalDateOfLastModif;
    private Date itsInstitutionalInternalRegDate;
    private Date itsInstitutionalActualDate;
    private Date itsInstitutionalDateOfExtintion;
    private String itsInstitutionalNumPerOficial;
    private String itsInstitutionalFormOfExtintion;
    private String itsInstitutionalNumberOfLastModif;
    private String itsInstitutionalInternalReg;
    private String itsInstitutionalOrganizational;
    private String itsInstitutionalLegalBasis;
    private String itsInstitutionalMission;
    private String itsInstitutionalVision;
    private String itsInstitutionalDiag;
    private String itsInstitutionalGeneralObjective;
    private Long itsInstitutionalToDeleteId;    
    private boolean itsInstitutionalInvesEjec;
    private boolean itsInstitutionalInvesNorm;
    private boolean itsInstitutionaBlock;
    private boolean itsInstitutionalOrganism;

    @Override
    public Long SaveOrUpdateAnInstitutionalPlan() 
    {
         String myMessage;
        try
        {
        if (validations()) {
            InstitutionalPlanEntity myInstitutionalPlanEntity = new InstitutionalPlanEntity();
            myInstitutionalPlanEntity = thieirInstitutionalPlanUIHelper.setValuesOfEntity(
                    itsInstitutionalPlanId, itsInstitutionalNumPerOficial, itsInstitutionalOfficialPubOnDailyDate,
                    itsInstitutionalstartDateOfActivities, itsInstitutionalDateOfExtintion, itsInstitutionalFormOfExtintion,
                    itsInstitutionalNumberOfLastModif, itsInstitutionalDateOfLastModif, itsInstitutionalInternalReg,
                    itsInstitutionalInternalRegDate, itsInstitutionalActualDate, itsInstitutionalOrganizational,
                    itsInstitutionalLegalBasis, itsInstitutionalMission, itsInstitutionalVision, itsInstitutionalDiag,
                    itsInstitutionalGeneralObjective, itsInstitutionalInvesEjec, itsInstitutionalInvesNorm,
                    itsInstitutionaBlock, itsInstitutionalOrganism,theirDependence.getDependenceById(itsInstitutionalDependenceId));
            Long myIdentity = -1L;
            myIdentity = theirInstitutionalPlanManagement.updateInstitutionalPlan(myInstitutionalPlanEntity);
            itsInstitutionalPlanId = myIdentity;
           
            myMessage = this.getMessage("ppp.planning.succesSave");
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", myMessage);

            return myIdentity;
        }
        }catch(Exception exc)
        {
            myMessage = "Error";
             addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", myMessage);
             exc.printStackTrace(System.out);
        }
        return itsInstitutionalPlanId;
    }

    @Override
    public void deleteAnInstitutionalPlan() {
        theirInstitutionalPlanManagement.deleteInstitutionalPlan(itsInstitutionalToDeleteId);
        String myMessage;
        myMessage = this.getMessage("ppp.planning.succesDelete");
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", myMessage);
    }

    @Override
    public boolean validations() {
        boolean myReturn = true;
        String myMessage;
        if ((itsInstitutionalstartDateOfActivities != null) && (itsInstitutionalOfficialPubOnDailyDate != null)) {
            if (itsInstitutionalOfficialPubOnDailyDate.compareTo(itsInstitutionalstartDateOfActivities) > 0) {
                myMessage = this.getMessage("ppp.planning.errorPublicationDate");
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", myMessage);
                myReturn = false;
            }
        }
        if ((itsInstitutionalDateOfExtintion != null) && (itsInstitutionalstartDateOfActivities != null)) {
            if (itsInstitutionalstartDateOfActivities.compareTo(itsInstitutionalDateOfExtintion) > 0) {
                myMessage = this.getMessage("ppp.planning.errorExtintionDate");
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", myMessage);
                myReturn = false;
            }
        }
       

        return myReturn;
    }

    @Override
    public boolean getItsInstitutionaBlock() {
        return itsInstitutionaBlock;
    }

    @Override
    public void processFileUpload(FileUploadEvent event) throws IOException {

        try {
            String fileToReplace = "";
            
            if (!(this.getItsInstitutionalOrganizational() == null || 
                    this.getItsInstitutionalOrganizational().isEmpty())) 
            {
                fileToReplace = this.getItsInstitutionalOrganizational();
            }

            UploadedFile arq = event.getFile();

            InputStream in = new BufferedInputStream(arq.getInputstream());

            File file = new File(SIIFContextBase.getParamContext(KeyContextEnum.PATH) + arq.getFileName());

            this.setItsInstitutionalOrganizational(file.getAbsolutePath().toString());

            FileOutputStream fout = new FileOutputStream(file);

            while (in.available() != 0) {

                fout.write(in.read());

            }

            fout.close();
            if (!(fileToReplace == null || fileToReplace.isEmpty())) 
            {

                File fichero = new File(fileToReplace);
                if (!fichero.delete()) 
                {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_FATAL,
                        this.getMessage("ppp.planning.fileDeleteError"),
                        this.getMessage("ppp.planning.fileDeleteError"));
                }
            }

            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                            getMessage("ppp.planning.successAdd", file.getName()),
                            getMessage("ppp.planning.successAdd", file.getName()));

        } catch (Exception exc) 
        {
              addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
    }

    @Override
    public SampleEntityDto getSelectedDeleteSpecificObjective() {
        return objInstPlanUIHelper.getSelectedDeleteSpecificObjective();
    }

    @Override
    public void setSelectedDeleteSpecificObjective(SampleEntityDto selectedDeleteSpecificObjective) {
        objInstPlanUIHelper.setSelectedDeleteSpecificObjective(selectedDeleteSpecificObjective);
        deleteObjective();
    }

    @Override
    public SampleEntityDto getSelectedSpecificObjective() {
        return objInstPlanUIHelper.getSelectedSpecificObjective();
    }

    @Override
    public void setSelectedSpecificObjective(SampleEntityDto selectedSpecificObjective) {
        objInstPlanUIHelper.setSelectedSpecificObjective(selectedSpecificObjective);
        updateObjective();
    }

    @Override
    public void setItsInstitutionaBlock(boolean itsInstitutionaBlock) {
        this.itsInstitutionaBlock = itsInstitutionaBlock;
    }

    @Override
    public ObjectiveInstitutionalPlanUIHelper getObjInstPlanUIHelper() {
        return objInstPlanUIHelper;
    }

    @Override
    public void setObjInstPlanUIHelper(ObjectiveInstitutionalPlanUIHelper objInstPlanUIHelper) {
        this.objInstPlanUIHelper = objInstPlanUIHelper;
    }

    @Override
    public Date getItsInstitutionalActualDate() {
        return itsInstitutionalActualDate;
    }

    @Override
    public void setItsInstitutionalActualDate(Date itsInstitutionalActualDate) {
        this.itsInstitutionalActualDate = itsInstitutionalActualDate;
    }

    @Override
    public Date getItsInstitutionalDateOfExtintion() {
        return itsInstitutionalDateOfExtintion;
    }

    @Override
    public void setItsInstitutionalDateOfExtintion(Date itsInstitutionalDateOfExtintion) {
        this.itsInstitutionalDateOfExtintion = itsInstitutionalDateOfExtintion;
    }

    @Override
    public Date getItsInstitutionalDateOfLastModif() {
        return itsInstitutionalDateOfLastModif;
    }

    @Override
    public void setItsInstitutionalDateOfLastModif(Date itsInstitutionalDateOfLastModif) {
        this.itsInstitutionalDateOfLastModif = itsInstitutionalDateOfLastModif;
    }

    @Override
    public Long getItsInstitutionalDependence() {
        return itsInstitutionalDependenceId;
    }

    @Override
    public void setItsInstitutionalDependence(Long itsInstitutionalDependence) {
        this.itsInstitutionalDependenceId = itsInstitutionalDependence;
    }

    @Override
    public String getItsInstitutionalDiag() {
        return itsInstitutionalDiag;
    }

    @Override
    public void setItsInstitutionalDiag(String itsInstitutionalDiag) {
        this.itsInstitutionalDiag = itsInstitutionalDiag;
    }

    @Override
    public String getItsInstitutionalFormOfExtintion() {
        return itsInstitutionalFormOfExtintion;
    }

    @Override
    public void setItsInstitutionalFormOfExtintion(String itsInstitutionalFormOfExtintion) {
        this.itsInstitutionalFormOfExtintion = itsInstitutionalFormOfExtintion;
    }

    @Override
    public String getItsInstitutionalGeneralObjective() {
        return itsInstitutionalGeneralObjective;
    }

    @Override
    public void setItsInstitutionalGeneralObjective(String itsInstitutionalGeneralObjective) {
        this.itsInstitutionalGeneralObjective = itsInstitutionalGeneralObjective;
    }

    @Override
    public String getItsInstitutionalInternalReg() {
        return itsInstitutionalInternalReg;
    }

    @Override
    public void setItsInstitutionalInternalReg(String itsInstitutionalInternalReg) {
        this.itsInstitutionalInternalReg = itsInstitutionalInternalReg;
    }

    @Override
    public Date getItsInstitutionalInternalRegDate() {
        return itsInstitutionalInternalRegDate;
    }

    @Override
    public void setItsInstitutionalInternalRegDate(Date itsInstitutionalInternalRegDate) {
        this.itsInstitutionalInternalRegDate = itsInstitutionalInternalRegDate;
    }

    @Override
    public boolean getItsInstitutionalInvesEjec() {
        return itsInstitutionalInvesEjec;
    }

    @Override
    public void setItsInstitutionalInvesEjec(boolean itsInstitutionalInvesEjec) {
        this.itsInstitutionalInvesEjec = itsInstitutionalInvesEjec;
    }

    @Override
    public boolean getItsInstitutionalInvesNorm() {
        return itsInstitutionalInvesNorm;
    }

    @Override
    public void setItsInstitutionalInvesNorm(boolean itsInstitutionalInvesNorm) {
        this.itsInstitutionalInvesNorm = itsInstitutionalInvesNorm;
    }

    @Override
    public String getItsInstitutionalLegalBasis() {
        return itsInstitutionalLegalBasis;
    }

    @Override
    public void setItsInstitutionalLegalBasis(String itsInstitutionalLegalBasis) {
        this.itsInstitutionalLegalBasis = itsInstitutionalLegalBasis;
    }

    @Override
    public String getItsInstitutionalMission() {
        return itsInstitutionalMission;
    }

    @Override
    public void setItsInstitutionalMission(String itsInstitutionalMission) {
        this.itsInstitutionalMission = itsInstitutionalMission;
    }

    @Override
    public String getItsInstitutionalNumPerOficial() {
        return itsInstitutionalNumPerOficial;
    }

    @Override
    public void setItsInstitutionalNumPerOficial(String itsInstitutionalNumPerOficial) {
        this.itsInstitutionalNumPerOficial = itsInstitutionalNumPerOficial;
    }

    @Override
    public String getItsInstitutionalNumberOfLastModif() {
        return itsInstitutionalNumberOfLastModif;
    }

    @Override
    public void setItsInstitutionalNumberOfLastModif(String itsInstitutionalNumberOfLastModif) {
        this.itsInstitutionalNumberOfLastModif = itsInstitutionalNumberOfLastModif;
    }

    @Override
    public Date getItsInstitutionalOfficialPubOnDailyDate() {
        return itsInstitutionalOfficialPubOnDailyDate;
    }

    @Override
    public void setItsInstitutionalOfficialPubOnDailyDate(Date itsInstitutionalOfficialPubOnDailyDate) {
        this.itsInstitutionalOfficialPubOnDailyDate = itsInstitutionalOfficialPubOnDailyDate;
    }

    @Override
    public boolean getItsInstitutionalOrganism() {
        return itsInstitutionalOrganism;
    }

    @Override
    public void setItsInstitutionalOrganism(boolean itsInstitutionalOrganism) {
        this.itsInstitutionalOrganism = itsInstitutionalOrganism;
    }

    @Override
    public String getItsInstitutionalOrganizational() {
        return itsInstitutionalOrganizational;
    }

    @Override
    public void setItsInstitutionalOrganizational(String itsInstitutionalOrganizational) {
        this.itsInstitutionalOrganizational = itsInstitutionalOrganizational;
    }

    @Override
    public Long getItsInstitutionalPlanId() {
        return itsInstitutionalPlanId;
    }

    @Override
    public void setItsInstitutionalPlanId(Long itsInstitutionalPlanId) {
        this.itsInstitutionalPlanId = itsInstitutionalPlanId;
    }

    @Override
    public String getItsInstitutionalVision() {
        return itsInstitutionalVision;
    }

    @Override
    public void setItsInstitutionalVision(String itsInstitutionalVision) {
        this.itsInstitutionalVision = itsInstitutionalVision;
    }

    @Override
    public Date getItsInstitutionalstartDateOfActivities() {
        return itsInstitutionalstartDateOfActivities;
    }

    @Override
    public void setItsInstitutionalstartDateOfActivities(Date itsInstitutionalstartDateOfActivities) {
        this.itsInstitutionalstartDateOfActivities = itsInstitutionalstartDateOfActivities;
    }

    @Override
    public Long getItsInstitutionalToDeleteId() {
        return itsInstitutionalToDeleteId;
    }

    @Override
    public void setItsInstitutionalToDeleteId(Long itsInstitutionalToDeleteId) {
        this.itsInstitutionalToDeleteId = itsInstitutionalToDeleteId;
    }
    
    /**
     * Método para la acción del botón guardar de la pestaña de objetivos
     */
    @Override
    public void addObjetive(){
        SampleEntityDto current = new SampleEntityDto("");
        //current.setInstitucionalObjectiveId("");
        List<SampleEntityDto> specificObjectivesList = 
                                getObjInstPlanUIHelper()
                                    .getSpecificObjectives();
        List<SampleEntityDto> specificObjectRemoveList = 
                                getObjInstPlanUIHelper()
                                    .getSpecificObjectivesToDelete();
        if(getObjInstPlanUIHelper().getSpecificObjectiveDescription() != null 
                && !getObjInstPlanUIHelper()
                    .getSpecificObjectiveDescription().trim().equals("")){
            if(getObjInstPlanUIHelper().getSelectedSpecificObjective() != null){
                if(specificObjectivesList.size()>0){
                    SampleEntityDtoComparator comp = new SampleEntityDtoComparator();
                    Object founded = SearchList.findObjectListCom
                                                 (specificObjectivesList, getObjInstPlanUIHelper()
                                                     .getSelectedSpecificObjective(),comp);
                    current = (SampleEntityDto) founded;
                    current.setItsDescription
                            (getObjInstPlanUIHelper().getSpecificObjectiveDescription());
                    getObjInstPlanUIHelper().setSelectedSpecificObjective(null);
                    getObjInstPlanUIHelper().setSpecificObjectiveDescription("");
                    getObjInstPlanUIHelper().setBtnAgregarVisibility(true);
                    getObjInstPlanUIHelper().setBtnCancelarVisibility(true);
                    getObjInstPlanUIHelper().setGridEditObjective(true);
                    getObjInstPlanUIHelper().setBtnNuevoVisibility(false);
                }
            }else{
                current.setItsDescription
                        (getObjInstPlanUIHelper().getSpecificObjectiveDescription());
                specificObjectivesList.add(current);
                specificObjectRemoveList.add(current);
                getObjInstPlanUIHelper().setSpecificObjectives(specificObjectivesList);
                getObjInstPlanUIHelper().setSelectedSpecificObjective(null);
                getObjInstPlanUIHelper().setSpecificObjectiveDescription("");
                getObjInstPlanUIHelper().setBtnAgregarVisibility(true);
                getObjInstPlanUIHelper().setBtnCancelarVisibility(true);
                getObjInstPlanUIHelper().setGridEditObjective(true);
                getObjInstPlanUIHelper().setBtnNuevoVisibility(false);
            }
        }else{
            //TODO: mensaje que no puede ser desc en blanco
        }        
    }
    
    /**
     * Método para la acción del botón cancelar de la pestaña de objetivos
     */
    @Override
    public void cancelObjetive(){
        getObjInstPlanUIHelper().setBtnAgregarVisibility(true);
        getObjInstPlanUIHelper().setBtnCancelarVisibility(true);
        getObjInstPlanUIHelper().setGridEditObjective(true);
        getObjInstPlanUIHelper().setBtnNuevoVisibility(false);
        getObjInstPlanUIHelper().setSelectedSpecificObjective(null);
        getObjInstPlanUIHelper().setSpecificObjectiveDescription("");
    }
    
    /**
     * Método para la acción del botón nuevo de la pestaña de objetivos
     */
    @Override
    public void newObjetive(){
        getObjInstPlanUIHelper().setBtnAgregarVisibility(false);
        getObjInstPlanUIHelper().setBtnCancelarVisibility(false);
        getObjInstPlanUIHelper().setGridEditObjective(false);
        getObjInstPlanUIHelper().setBtnNuevoVisibility(true);
    }
    
    @Override
    public void updateObjective(){
        getObjInstPlanUIHelper()
                .setSpecificObjectiveDescription
                    (getObjInstPlanUIHelper()
                        .getSelectedSpecificObjective().getItsDescription());
        getObjInstPlanUIHelper().setBtnAgregarVisibility(false);
        getObjInstPlanUIHelper().setBtnCancelarVisibility(false);
        getObjInstPlanUIHelper().setGridEditObjective(false);
        getObjInstPlanUIHelper().setBtnNuevoVisibility(true);
    }
    
    @Override
    public void initObjectiveTagElements(){
        getObjInstPlanUIHelper().setSpecificObjectives(new ArrayList<SampleEntityDto>());
        getObjInstPlanUIHelper().setSpecificObjectivesToDelete(new ArrayList<SampleEntityDto>());
        InstitutionalPlanEntity dummy = new InstitutionalPlanEntity();
        dummy.setInstitutionalPlanId(getItsInstitutionalPlanId());
        List<InstitutionalPlanObjectiveEntity> result = 
                theirInstitutionalPlanManagement.searchInstPlanObjectives(dummy);
        for(InstitutionalPlanObjectiveEntity current : result){
            SampleEntityDto toBeAdd = new SampleEntityDto("");
            toBeAdd.setItsId(current.getInstitutionalPlanEntity().getInstitutionalPlanId());
            toBeAdd.setItsDescription(current.getObjectiveDescription());
            toBeAdd.setInstitucionalObjectiveId
                    (current.getInstitutionalPlanObjectiveId());
            getObjInstPlanUIHelper().getSpecificObjectives().add(toBeAdd);
            getObjInstPlanUIHelper().getSpecificObjectivesToDelete().add(toBeAdd);
        }
        getObjInstPlanUIHelper().setBtnAgregarVisibility(true);
        getObjInstPlanUIHelper().setBtnCancelarVisibility(true);
        getObjInstPlanUIHelper().setGridEditObjective(true);
        getObjInstPlanUIHelper().setBtnNuevoVisibility(false);
        getObjInstPlanUIHelper().setSpecificObjectiveDescription("");
    }
    
    @Override
    public void deleteObjective(){
        List<SampleEntityDto> specificObjectivesList = 
                                getObjInstPlanUIHelper()
                                    .getSpecificObjectives();
        SampleEntityDto toDelete = getObjInstPlanUIHelper()
                                        .getSelectedDeleteSpecificObjective();
        specificObjectivesList.remove(toDelete);
    }
    
    @Override
    public void saveObjectives(){
        List<InstitutionalPlanObjectiveEntity> listToSave =
                new ArrayList<InstitutionalPlanObjectiveEntity>();
        List<InstitutionalPlanObjectiveEntity> listToDelete =
                new ArrayList<InstitutionalPlanObjectiveEntity>();
        InstitutionalPlanEntity father = new InstitutionalPlanEntity();
        InstitutionalPlanObjectiveEntity objectives;
        father.setInstitutionalPlanId(itsInstitutionalPlanId);
        List<SampleEntityDto> specificObjectivesList = 
                                getObjInstPlanUIHelper()
                                    .getSpecificObjectives();
        List<SampleEntityDto> specificObjectDeleteList = 
                                getObjInstPlanUIHelper()
                                    .getSpecificObjectivesToDelete();
        for(SampleEntityDto current : specificObjectivesList){
            objectives = new InstitutionalPlanObjectiveEntity();
            objectives.setInstitutionalPlanObjectiveId
                                (current.getInstitucionalObjectiveId());
            objectives.setObjectiveDescription(current.getItsDescription());
            objectives.setInstitutionalPlanEntity(father);
            listToSave.add(objectives);
            specificObjectDeleteList.remove(current);
        }
        for(SampleEntityDto current : specificObjectDeleteList){
            objectives = new InstitutionalPlanObjectiveEntity();
            objectives.setInstitutionalPlanObjectiveId
                                (current.getInstitucionalObjectiveId());
            objectives.setObjectiveDescription(current.getItsDescription());
            objectives.setInstitutionalPlanEntity(father);
            listToDelete.add(objectives);
        }
        theirInstitutionalPlanManagement.saveObjectives(listToSave);
        theirInstitutionalPlanManagement.deleteObjectives(listToDelete);
    }
     //Este es el metodo para descargar el archivo desde el servidor
    @Override
     public StreamedContent getFileStreamedContent () throws Exception 
     {
         
          StreamedContent file = null; 
        
        try 
        {
            InputStream is = new BufferedInputStream(
                    new FileInputStream(this.getItsInstitutionalOrganizational()));
            String type = getType("file:" + this.getItsInstitutionalOrganizational());
            file = new DefaultStreamedContent(is, type,this.getItsInstitutionalOrganizational());
            
        } 
        catch (Exception exc) 
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));

        }
        
        return file;
     }
     public static String getType(String fileUrl) throws Exception {
        URL u = new URL(fileUrl);
        URLConnection uc = u.openConnection();
        String type = uc.getContentType();
        
        return type;
    }
    
}
