/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.data.ExecutionPeriodType;
import com.abs.siif.budget.entities.*;
import com.abs.siif.planning.data.PhysicalAndFinancialProgramPeriod;
import com.abs.siif.planning.data.PhysicalAndFinancialProgramType;
import com.abs.siif.ppp.planning.controller.OptionsController;
import com.abs.siif.ppp.programming.api.controller.PhysicalFinancialProgControllerApi;
import com.abs.siif.ppp.programming.uihelpers.DraftProjectHeaderUIHelper;
import com.abs.siif.programming.dto.FinancialAdvancedBudgetKeyDto;
import com.abs.siif.programming.dto.PhysicalAndFinancialProgramDto;
import com.abs.siif.programming.entities.*;
import com.abs.siif.programming.management.PhysicalAndFinancialProgramManagement;
import com.abs.siif.support.FormatNumber;
import com.abs.siif.support.MonthsEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.DateSelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Jorge Urrea
 */
@Controller("physicalFinancialProgController")
@Scope("session")
public class PhysicalFinancialProgController extends SIIFControllerBase implements Serializable, PhysicalFinancialProgControllerApi {

    private Date theirPhysicalFinancialStartDate;
    private Date theirPhysicalFinancialEndDate;
    
    private int processType;
    private int duracion;
    private int totalInitalAsigFinancialStruc;
    
    private String duracionTime;  
    private String physicalCurrent;
    private String physicalToExecute;
    private String physicalToExcuteNextYears;
    private String financialCurrent;
    private String financialToExecute;
    private String financialToExcuteNextYears;
    private String physicalTotal;
    private String financialTotal;  
    private Long theirPreFileId;
    private Long proPhysicFinancialId;  
    private Long inputPhysicalAndFinEntityId;
    private String visibilityMsj;
   
    private boolean enabledToExecuteNextYears;
    private boolean enabledCurrent;
    private boolean enabledCalendars;
    private boolean disableEne;
    private boolean disableFeb;
    private boolean disableMar;
    private boolean disableAbr;
    private boolean disableMay;
    private boolean disableJun;
    private boolean disableJul;
    private boolean disableAgo;
    private boolean disableSep;
    private boolean disableOct;
    private boolean disableNov;
    private boolean disableDic;   
 
    private List<FinancialAdvancedBudgetKeyDto> financialAdvProg;
    private Map<String, String> physicalAdvan;
    private Map<String, Long> physicalAdvanIds;
    private Map<String, BigDecimal> totalMonths;
    private InvPreFileEntity invPreFileEntity;
    private ExecutionPeriodType executionPeriod;
    
    private DraftProjectHeaderUIHelper itsDraftProjectUIHelper = new DraftProjectHeaderUIHelper();
       
    @Resource(name="physicalAndFinancialProgramManagement")
    private transient PhysicalAndFinancialProgramManagement physicalAndFinancialProgramManagement;
        
    @Resource(name = "optionsController")    
    private transient OptionsController theirOptionsController;
    
     /**
     * metodo encargado de inicializar la pantalla de programa fisico financiero
     */
    @Override
    public void init() {      
        this.physicalAdvan = new HashMap<String, String>(); 
        this.physicalAdvanIds = new HashMap<String, Long>();
        this.duracion = 0;
        this.totalInitalAsigFinancialStruc = 0;
        this.duracionTime= "";
        BenefAndGoalsEntity benefAndGoals = physicalAndFinancialProgramManagement.getBenefAndGoalsByInvPreFile(this.theirPreFileId);
        if(benefAndGoals != null){
            this.executionPeriod = benefAndGoals.getExecutionPeriod();
        }
        if(this.executionPeriod == null){
            setEnabledCalendars(Boolean.TRUE);
            setVisibilityMsj("visible");
        }else{
            setEnabledCalendars(Boolean.FALSE);
            setVisibilityMsj("hidden");
        }
        List<InputEntity> allInputInitialAsig = physicalAndFinancialProgramManagement.getAllInputInitialAsig(this.theirPreFileId);        
        for(InputEntity inputInitialAsig: allInputInitialAsig){
            this.totalInitalAsigFinancialStruc = (int)(inputInitialAsig.getInputFederal() + inputInitialAsig.getInputState() + 
                    inputInitialAsig.getInputMunicipality() + inputInitialAsig.getInputParticular());
        }
        disabledMonths();       
        if(this.theirPreFileId != null && this.theirPreFileId>0){            
            searchData();
            calcTotalPhysicalAdvan();
            if(this.proPhysicFinancialId != null){
                this.saveFinancialAdv(Boolean.FALSE);
            }
            calcTotalPhysicAdvanced();
            calcTotalFinancialAdvanced();   
        }
    }

    /**
     * 
     * @return totalMonths
     */
    public Map<String, BigDecimal> getTotalMonths() {
        return totalMonths;
    }

    /**
     * 
     * @param totalMonths 
     */
    public void setTotalMonths(Map<String, BigDecimal> totalMonths) {
        this.totalMonths = totalMonths;
    }

    
    /**
     * 
     * @return financialAdvProg
     */
    public List<FinancialAdvancedBudgetKeyDto> getFinancialAdvProg() {
        return financialAdvProg;
    }

    /**
     * 
     */
    public void setFinancialAdvProg(List<FinancialAdvancedBudgetKeyDto> financialAdvProg) {
        this.financialAdvProg = financialAdvProg;
    }
    
    /**
     * 
     * @return financialToExcuteNextYears
     */
    @Override
    public String getFinancialToExcuteNextYears() {           
        return financialToExcuteNextYears;
    }

    /**
     * 
     * @return physicalToExcuteNextYears
     */
    @Override
    public String getPhysicalToExcuteNextYears() {
        return physicalToExcuteNextYears;
    }
    
    /**
     * 
     * @param financialToExcuteNextYears
     */
    @Override
    public void setFinancialToExcuteNextYears(String financialToExcuteNextYears) {  
        this.financialToExcuteNextYears = financialToExcuteNextYears;
    }

    /**
     * 
     * @param physicalToExcuteNextYears
     */
    @Override
    public void setPhysicalToExcuteNextYears(String physicalToExcuteNextYears) {
        this.physicalToExcuteNextYears = physicalToExcuteNextYears;
    }
   
    /**
     * 
     * @return InvPreFileEntity
     */
    @Override
    public InvPreFileEntity getInvPreFileEntity() {
        return invPreFileEntity;
    }
    
    /**
     * 
     * @param invPreFileEntity
     */
    @Override
    public void setInvPreFileEntity(InvPreFileEntity invPreFileEntity) {
        this.invPreFileEntity = invPreFileEntity;
    }
    
    /**
     * 
     * @return theirPreFileId
     */
    @Override
    public Long getInvPreFileId() {
        return theirPreFileId;
    }

    /**
     * 
     * @param theirPreFileId
     */
    @Override
    public void setInvPreFileId(Long theirPreFileId) {
        this.theirPreFileId = theirPreFileId;
    }   
    
    /**
     * 
     * @return financialTotal
     */
    @Override
    public String getFinancialTotal() {
        return financialTotal;
    }
    
    /**
     * 
     * @return enabledCalendars
     */
    @Override
    public boolean isEnabledCalendars() {
        return enabledCalendars;
    }
    
    /**
     * 
     * @param enabledCalendars 
     */
    @Override
    public void setEnabledCalendars(boolean enabledCalendars) {
        this.enabledCalendars = enabledCalendars;
    }
    
    /**
     * 
     * @return visibilityMsj
     */
    @Override
    public String getVisibilityMsj() {
        return visibilityMsj;
    }

    /**
     * 
     * @param visibilityMsj 
     */
    @Override
    public void setVisibilityMsj(String visibilityMsj) {
        this.visibilityMsj = visibilityMsj;
    }
    
    
    
    /**
     * 
     * @return physicalTotal
     */
    @Override
    public String getPhysicalTotal() {
        return physicalTotal;
    }

    /**
     * 
     * @param financialTotal
     */
    @Override
    public void setFinancialTotal(String financialTotal) {
        this.financialTotal = financialTotal;
    }

    /**
     * 
     * @param physicalTotal
     */
    @Override
    public void setPhysicalTotal(String physicalTotal) {
        this.physicalTotal = physicalTotal;
    }   

    /**
     * 
     * @param financialCurrent
     */
    @Override
    public void setFinancialCurrent(String financialCurrent) {
        this.financialCurrent = financialCurrent;
    }  

    /**
     * 
     * @param financialToExecute
     */
    @Override
    public void setFinancialToExecute(String financialToExecute) {
        this.financialToExecute = financialToExecute;
    }

    /**
     * 
     * @param physicalCurrent
     */
    @Override
    public void setPhysicalCurrent(String physicalCurrent) {
        this.physicalCurrent = physicalCurrent;
    }

    /**
     * 
     * @param physicalToExecute
     */
    @Override
    public void setPhysicalToExecute(String physicalToExecute) {
        this.physicalToExecute = physicalToExecute;
    }

    /**
     * 
     * @return financialCurrent
     */
    @Override
    public String getFinancialCurrent() {
        return financialCurrent;
    }

    /**
     * 
     * @return financialToExecute
     */
    @Override
    public String getFinancialToExecute() {
        return financialToExecute;
    }

    /**
     * 
     * @return physicalCurrent
     */
    @Override
    public String getPhysicalCurrent() {
        return physicalCurrent;
    } 

    /**
     * 
     * @return physicalToExecute
     */
    @Override
    public String getPhysicalToExecute() {
        return physicalToExecute;
    }

    /**
     * 
     * @return physicalAdvan
     */
    @Override
    public Map<String, String> getPhysicalAdvan() {
        return physicalAdvan;
    }

    /**
     * 
     * @param physicalAdvan
     */
    @Override
    public void setPhysicalAdvan(Map<String, String> physicalAdvan) {
        this.physicalAdvan = physicalAdvan;
    }

    /**
     * 
     * @return duracionTime
     */
    @Override
    public String getDuracionTime() {
        return duracionTime;
    }

    /**
     * 
     * @param duracionTime
     */
    @Override
    public void setDuracionTime(String duracionTime) {
        this.duracionTime = duracionTime;
    }

    /**
     * 
     * @return duracion
     */
    @Override
    public int getDuracion() {
        return duracion;
    }

    /**
     * 
     * @param duracion
     */
    @Override
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * 
     * @return processType
     */
    @Override
    public int getProcessType() {
        return processType;
    }

    /**
     * 
     * @param processType
     */
    @Override
    public void setProcessType(int processType) {
        this.processType = processType;
    }

    /**
     * 
     * @return theirPhysicalFinancialStartDate
     */
    @Override
    public Date getPhysicalFinancialStartDate() {
        return theirPhysicalFinancialStartDate;
    }

    /**
     * 
     * @param aDraftStartDate
     */
    @Override
    public void setPhysicalFinancialStartDate(Date aDraftStartDate) {
        this.theirPhysicalFinancialStartDate = aDraftStartDate;
    }

    /**
     * Regresa la fecha minina dependiendo del año seleccionado 
     * del combo del optionsController
     * @return minDate
     */
    @Override
    public String getMinDateStartDate() {     
        String minDate;
        String year = theirOptionsController.getYear();
        String years = String.valueOf(Integer.parseInt(year) - 10);        
        if(this.processType == 1){
             minDate="1/1/"+theirOptionsController.getYear().substring(2,4); 
        }else{
             minDate = "1/1/" + years; 
        }
        return minDate;
    }
    
    /**
     *  Regresa la fecha maxima
     * @return maxDate
     */
    @Override
    public String getMaxDateStartDate(){
        String maxDate = "";
        String year = theirOptionsController.getYear();
        String years = String.valueOf(Integer.parseInt(year) + 10);
        if(this.processType == 1 && this.executionPeriod == ExecutionPeriodType.ANNUAL){
                maxDate = "31/12/" + year;
        }else{
            if(this.processType == 2){
                maxDate = "31/12/" + year;
            }else{
                 maxDate = "31/12/" + years;
            }
           
        } 
        return maxDate;
    }

    /**
     * 
     * @return theirPhysicalFinancialEndDate
     */
    @Override
    public Date getPhysicalFinancialEndDate() {
        return theirPhysicalFinancialEndDate;
    }

    /**
     * 
     * @param aDraftEndDate
     */
    @Override
    public void setPhysicalFinancialEndDate(Date aDraftEndDate) {
        this.theirPhysicalFinancialEndDate = aDraftEndDate;
    }

    /**
     * 
     * @return enabledCurrent
     */
    @Override
    public boolean isEnabledCurrent() {
        return enabledCurrent;
    }

    /**
     * 
     * @return enabledToExecuteNextYears
     */
    @Override
    public boolean isEnabledToExecuteNextYears() {
        return enabledToExecuteNextYears;
    }

    /**
     * 
     * @param disableAbr
     */
    @Override
    public void setDisableAbr(boolean disableAbr) {
        this.disableAbr = disableAbr;
    }

    /**
     * 
     * @param disableAgo
     */
    @Override
    public void setDisableAgo(boolean disableAgo) {
        this.disableAgo = disableAgo;
    }

    /**
     * 
     * @param disableDic
     */
    @Override
    public void setDisableDic(boolean disableDic) {
        this.disableDic = disableDic;
    }

    /**
     * 
     * @param disableEne
     */
    @Override
    public void setDisableEne(boolean disableEne) {
        this.disableEne = disableEne;
    }

    /**
     * 
     * @param disableFeb
     */
    @Override
    public void setDisableFeb(boolean disableFeb) {
        this.disableFeb = disableFeb;
    }

    /**
     * 
     * @param disableJul
     */
    @Override
    public void setDisableJul(boolean disableJul) {
        this.disableJul = disableJul;
    }

    /**
     * 
     * @param disableJun
     */
    @Override
    public void setDisableJun(boolean disableJun) {
        this.disableJun = disableJun;
    }

    /**
     * 
     * @param disableMar
     */
    @Override
    public void setDisableMar(boolean disableMar) {
        this.disableMar = disableMar;
    }

    /**
     * 
     * @param disableMay
     */
    @Override
    public void setDisableMay(boolean disableMay) {
        this.disableMay = disableMay;
    }

    /**
     * 
     * @param disableOct
     */
    @Override
    public void setDisableOct(boolean disableOct) {
        this.disableOct = disableOct;
    }

    /**
     * 
     * @param disableSep
     */
    @Override
    public void setDisableSep(boolean disableSep) {
        this.disableSep = disableSep;
    }

    /**
     * 
     * @param disableNov
     */
    @Override
    public void setDisableNov(boolean disableNov) {
        this.disableNov = disableNov;
    }

    /**
     * 
     * @return disableAbr
     */
    @Override
    public boolean isDisableAbr() {
        return disableAbr;
    }

    /**
     * 
     * @return disableAgo
     */
    @Override
    public boolean isDisableAgo() {
        return disableAgo;
    }

    /**
     * 
     * @return disableDic
     */
    @Override
    public boolean isDisableDic() {
        return disableDic;
    }

    /**
     * 
     * @return disableEne
     */
    @Override
    public boolean isDisableEne() {
        return disableEne;
    }

    /**
     * 
     * @return disableFeb
     */
    @Override
    public boolean isDisableFeb() {
        return disableFeb;
    }

    /**
     * 
     * @return disableJul
     */
    @Override
    public boolean isDisableJul() {
        return disableJul;
    }

    /**
     * 
     * @return disableJun
     */
    @Override
    public boolean isDisableJun() {
        return disableJun;
    }

    /**
     * 
     * @return disableMar
     */
    @Override
    public boolean isDisableMar() {
        return disableMar;
    }

    /**
     * 
     * @return disableMay
     */
    @Override
    public boolean isDisableMay() {
        return disableMay;
    }

    /**
     * 
     * @return disableNov
     */
    @Override
    public boolean isDisableNov() {
        return disableNov;
    }

    /**
     * 
     * @return disableOct
     */
    @Override
    public boolean isDisableOct() {
        return disableOct;
    }

    /**
     * 
     * @return disableSep
     */
    @Override
    public boolean isDisableSep() {
        return disableSep;
    }    
    
    
    /**
     * 
     * @return el mensaje para el header de avance ficico programado para
     */
    @Override
    public String getPhysicalAdvancedHeader(){
        return this.getMessage("ppp.progr.physicalFinancialProg.pnlAvanceFisicoHeader", new String[]{
                        theirOptionsController.getYear()
                    }); 
    }
    
    /**
     * 
     * @return el mensaje para el header de avance financiero programado para
     */
    @Override
    public String getFinancialAdvancedHeader(){
        return this.getMessage("ppp.progr.physicalFinancialProg.pnlAvanceFinancieroHeader", new String[]{
                        theirOptionsController.getYear()
                    }); 
    }
    
    /**
     * 
     * @return el mensaje para el header por ejecutar en la tabla de la seccion
     * de aportaciones
     */
    @Override
    public String getToExecHeader(){       
         return this.getMessage("ppp.progr.physicalFinancialProg.porEjecutarHeader", new String[]{
                        theirOptionsController.getYear()
                    }); 
    }

    /**
     * Metodo para validar que los valores que se intoduzcan en el avance fisico
     * programado sean validos (numericos)
     */
    private boolean reviewNumber(String label, Map<String, String> maps) {
        String value;
        boolean result;
        value = maps.get(label);
        if (value == null || value.trim().length() == 0) {
            maps.put(label, "0");
            value = maps.get(label);
        }
        if (value.matches("^[0-9]{1,5}(\\.[0-9]{0,2})?$")) {
            result = true;
        } else {
            result = false;
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.noNumero", new String[]{
                        label
                    }),
                    this.getMessage("ppp.progr.noNumero", new String[]{
                        label
                    }));
        }
        return result;
    } 

    /**
     * metodo que calcula la diferencia en meses entre la fecha de inicio y la
     * fecha final de la obra
     */
    @Override
    public void getCalcDuracionTime() {               
            Date endDate = getPhysicalFinancialEndDate();
            Date startDate = getPhysicalFinancialStartDate();
             if (startDate != null && endDate != null) {
                Calendar calend = Calendar.getInstance();
                calend.setTime(endDate);
                calend.add(Calendar.DAY_OF_MONTH, 1);
                calend.getTime();
                if(calend.getTime().after(startDate)){
                    int monthDiff = itsDraftProjectUIHelper.getDiferenceBetweenTwoDates(startDate,endDate);                    
                    this.duracionTime = String.valueOf(monthDiff);
                    if (monthDiff > 12) {
                        this.duracion = 2;
                    } else {
                        this.duracion = 1;
                    }
                    enabledMonths();
                } else {
                   addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.DraftDateError"),
                    this.getMessage("ppp.progr.DraftDateError"));
                }
                this.selectOption();
                calcTotalPhysicAdvanced();
                calcTotalFinancialAdvanced();  
            }else{
                  this.duracionTime = "0";
                  this.duracion = 0;                
            } 
    }
    
    /**
    * Metodo encargado validar cuales meses del avance fisico programado estaran
    * habilitados
    */
    @Override
    public void enabledMonths(){             
        Calendar calendStartDate = Calendar.getInstance();
        calendStartDate.setTime(this.theirPhysicalFinancialStartDate);        
        int startMonth = calendStartDate.get(Calendar.MONTH);
        int startYear = calendStartDate.get(Calendar.YEAR);
        
        Calendar calendEndDate = Calendar.getInstance();
        calendEndDate.setTime(this.theirPhysicalFinancialEndDate);
        int endYear = calendEndDate.get(Calendar.YEAR);
        int endMonth = calendEndDate.get(Calendar.MONTH); 
        
        int yearSelected = Integer.parseInt(theirOptionsController.getYear());
        disabledMonths();
        if((startYear <= yearSelected) && (endYear >= yearSelected)){
             if(endYear > yearSelected){
                endMonth = 11;
             }
             if(startYear < yearSelected){
                 startMonth = 0;
             }
             for(int i = startMonth ; i <= endMonth ; i++){
                 switch (i){
                     case 0:
                         setDisableEne(Boolean.FALSE);
                         break;
                     case 1:
                         setDisableFeb(Boolean.FALSE);
                         break;
                     case 2:
                         setDisableMar(Boolean.FALSE);
                         break;
                     case 3:
                         setDisableAbr(Boolean.FALSE);
                         break;
                     case 4:
                         setDisableMay(Boolean.FALSE);
                         break;
                     case 5:
                         setDisableJun(Boolean.FALSE);
                         break;
                     case 6:
                         setDisableJul(Boolean.FALSE);
                         break;
                     case 7:
                         setDisableAgo(Boolean.FALSE);
                         break;
                     case 8:
                         setDisableSep(Boolean.FALSE);
                         break;
                     case 9:
                         setDisableOct(Boolean.FALSE);
                         break;
                     case 10:
                         setDisableNov(Boolean.FALSE);
                         break;
                     case 11:
                         setDisableDic(Boolean.FALSE);
                         break;
                 } 
             }            
        }       
    }
    
    @Override
    public void disabledMonths(){
        setDisableNov(Boolean.TRUE);
        setDisableEne(Boolean.TRUE);
        setDisableFeb(Boolean.TRUE);
        setDisableMar(Boolean.TRUE);
        setDisableAbr(Boolean.TRUE);
        setDisableMay(Boolean.TRUE);
        setDisableJun(Boolean.TRUE);
        setDisableJul(Boolean.TRUE);
        setDisableAgo(Boolean.TRUE);
        setDisableSep(Boolean.TRUE);
        setDisableOct(Boolean.TRUE);
        setDisableNov(Boolean.TRUE);
        setDisableDic(Boolean.TRUE);
    }

    /**
     * Metodo encargado de hacer el salvado de la información
     */
    @Override
    public void saveFinancialAdv(boolean btn) {
        if(validateInputs()){           
            MonthsEnum[] months = MonthsEnum.values();    
            PhysicalAndFinancialProgramEntity header = new PhysicalAndFinancialProgramEntity();
            header.setProgPhysicFinancialEndDate(this.theirPhysicalFinancialEndDate);
            header.setProgPhysicFinancialStartDate(this.theirPhysicalFinancialStartDate);
            if(this.processType == 1){
                header.setProgPhysicFinancialType(PhysicalAndFinancialProgramType.NEW);       
            }else{
                header.setProgPhysicFinancialType(PhysicalAndFinancialProgramType.INPROCESS);       
            }
            if(this.duracion == 1){
                header.setProgPhysicFinancialPeriod(PhysicalAndFinancialProgramPeriod.YEARLY);
            }else{
                header.setProgPhysicFinancialPeriod(PhysicalAndFinancialProgramPeriod.MULTIYEAR);
            }
            if(this.proPhysicFinancialId != null){
                 header.setProgPhysicFinancialId(this.proPhysicFinancialId);
            }else{
                 header.setProgPhysicFinancialId(new Long(0));
            }
            InvPreFileEntity invPreFile = new InvPreFileEntity();
            invPreFile.setInvPreFileId(this.theirPreFileId);
            header.setPhysicalAndFinancialProgramInvPreFile(invPreFile);           
            List<PhysicalProgrammedAdvanceEntity> physicalAdvProgram = new ArrayList<PhysicalProgrammedAdvanceEntity>();
            Map<String, String> physicalAdva = this.physicalAdvan;
            Map<String, Long> physicalAdvaIds = this.physicalAdvanIds;
                  
            int mes = 1;
            for (MonthsEnum month : months) {
                PhysicalProgrammedAdvanceEntity physicalAvance = new PhysicalProgrammedAdvanceEntity(); 
                if(physicalAdvaIds.get(month.name())  != null)
                    physicalAvance.setPhysicalProgrammedAdvanceId(physicalAdvaIds.get(month.name()));
                else
                     physicalAvance.setPhysicalProgrammedAdvanceId(new Long(0));
                physicalAvance.setPhysicalProgrammedAdvanceMonth(mes);
                physicalAvance.setPhysicalProgrammedAdvancePercent(Double.parseDouble(physicalAdva.get(month.name())));
                physicalAvance.setPhysicalProgrammedAdvancePreFile(invPreFile);
                physicalAdvProgram.add(physicalAvance);
                mes++;               
            }
          
            InputPhysicalAndFinEntity aportations = new InputPhysicalAndFinEntity(); 
            if(this.inputPhysicalAndFinEntityId != null){
                aportations.setInputPhysicalAndFinEntityId(this.inputPhysicalAndFinEntityId);
            }else{
                 aportations.setInputPhysicalAndFinEntityId(new Long(0));
            }
            aportations.setFinantialToDate(Double.parseDouble(FormatNumber.removeFormat(getFinancialCurrent())));
            aportations.setFinantialToExec(Double.parseDouble(FormatNumber.removeFormat(getFinancialToExecute())));
            aportations.setFinantialToExecNextYear(Double.parseDouble(FormatNumber.removeFormat(getFinancialToExcuteNextYears())));
            aportations.setPhysicalToDate(Double.parseDouble(this.physicalCurrent));
            aportations.setPhysicalToExec(Double.parseDouble(this.physicalToExecute));
            aportations.setPhisicalToExecNextYear(Double.parseDouble(this.physicalToExcuteNextYears));

            PhysicalAndFinancialProgramDto phyAndFinProg = new PhysicalAndFinancialProgramDto();
            phyAndFinProg.setHeader(header);
            phyAndFinProg.setAdvProgram(physicalAdvProgram);
            phyAndFinProg.setAportations(aportations);          
            physicalAndFinancialProgramManagement.savePhysicalAndFinancialProgram(phyAndFinProg);
            if(btn){       
                List<FinancialAdvancedBudgetKeyDto> financialAdvProg1 = this.getFinancialAdvProg();
                List<MensualBudgetKeyEntity> budgetKeyListToSave = new ArrayList<MensualBudgetKeyEntity>();
                for(FinancialAdvancedBudgetKeyDto advancedBudgetKeyDto: financialAdvProg1){
                    if( advancedBudgetKeyDto.getOriginalAmount() != advancedBudgetKeyDto.getTotalCve()){
                       addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                                this.getMessage("ppp.planning.validateFinancialotal"),
                                this.getMessage("ppp.planning.validateFinancialotal"));
                        return;
                    }                    
                    BudgetKeyEntity budgetKey = new BudgetKeyEntity();
                    budgetKey.setBudgetKeyId(new Long(advancedBudgetKeyDto.getIdcvepresupuestal()));
                    FinancingSourceEntity financingSourceEntity = new FinancingSourceEntity();
                    financingSourceEntity.setFinancingSourceId(advancedBudgetKeyDto.getIdFinancingSource());
                    budgetKey.setFinancingSourceBudgetKey(financingSourceEntity);
                    budgetKey.setBudgetKeyBProgramaticKey(advancedBudgetKeyDto.getKey());
                    budgetKey.setOriginalAmount(Double.parseDouble(String.valueOf(advancedBudgetKeyDto.getOriginalAmount())));
                    BudgetKeyDefinitionEntity budgetKeyDefinitionEntity = new BudgetKeyDefinitionEntity();
                    budgetKeyDefinitionEntity.setPresupuestalKeyDefinitionId(advancedBudgetKeyDto.getIddefcvepresupuestal());
                    budgetKey.setBudgetKeyDefinitionBudgetKey(budgetKeyDefinitionEntity);
                    ObjectExpenseEntity expenseEntity = new ObjectExpenseEntity();
                    expenseEntity.setObjectExpenseId(advancedBudgetKeyDto.getIdObjectExpensive());
                    budgetKey.setObjectExpenseBudgetKey(expenseEntity);
                    Map<String, String> mensualValues = advancedBudgetKeyDto.getMensualBudgetKeyValue();
                    Map<String, Long> mensualIds = advancedBudgetKeyDto.getMensualBudgetKeyIds();
                    for(MonthsEnum month : months) {
                        MensualBudgetKeyEntity get = new MensualBudgetKeyEntity();//mensualBudgetKey.get(month.name());
                        String value = FormatNumber.removeFormat(mensualValues.get(month.name()));
                        Long id = mensualIds.get(month.name());
                        get.setMensualBudgetKeyId(id);
                        get.setMonth(month.name());
                        if("".equals(value)){
                            value = "0";
                        }
                        get.setOriginalAmount(Double.parseDouble(value));  
                        get.setBudgetKeyMensualBudgetKey(budgetKey);
                        budgetKeyListToSave.add(get);
                     }                    
                }
                physicalAndFinancialProgramManagement.saveFinancialAdvancedProg(budgetKeyListToSave);
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.planning.succesSave"),
                    this.getMessage("ppp.planning.succesSave"));
            }   
        }     
    }
    
    /**
     * Metodo encargado de consultar la informacion
     */
    @Override
    public void  searchData(){
       PhysicalAndFinancialProgramDto physicalAndFinancial = physicalAndFinancialProgramManagement.getPhysicalAndFinancialProgrambyPrefileId(this.theirPreFileId);
       if(physicalAndFinancial!= null){
            PhysicalAndFinancialProgramEntity header = physicalAndFinancial.getHeader();
            this.theirPhysicalFinancialEndDate = header.getProgPhysicFinancialEndDate();
            this.theirPhysicalFinancialStartDate = header.getProgPhysicFinancialStartDate();
            this.proPhysicFinancialId = header.getProgPhysicFinancialId();
            if(header.getProgPhysicFinancialType()==(PhysicalAndFinancialProgramType.INPROCESS)){
                this.processType = 2;
            }else{
                this.processType = 1;
            }            
            InputPhysicalAndFinEntity aportations = physicalAndFinancial.getAportations();
            this.inputPhysicalAndFinEntityId = aportations.getInputPhysicalAndFinEntityId();
            setFinancialCurrent(FormatNumber.formatNumber(String.valueOf(aportations.getFinantialToDate())));
            setFinancialToExecute(FormatNumber.formatNumber(String.valueOf(this.totalInitalAsigFinancialStruc)));                                  
            setFinancialToExcuteNextYears(FormatNumber.formatNumber(String.valueOf(aportations.getFinantialToExecNextYear())));
            this.physicalCurrent = String.valueOf((int)aportations.getPhysicalToDate());
            this.physicalToExecute = String.valueOf((int)aportations.getPhysicalToExec());
            this.physicalToExcuteNextYears = String.valueOf((int)aportations.getPhisicalToExecNextYear());
            if(this.theirPhysicalFinancialEndDate != null && this.theirPhysicalFinancialStartDate!=null){
                this.getCalcDuracionTime();
            }          
            List<PhysicalProgrammedAdvanceEntity> physicalAvanceProgram = physicalAndFinancial.getAdvProgram();                           
            if(!physicalAvanceProgram.isEmpty()){
                MonthsEnum[] months = MonthsEnum.values();
                int i = 0;
                for (MonthsEnum month : months){
                    this.physicalAdvanIds.put(month.name(), physicalAvanceProgram.get(i).getPhysicalProgrammedAdvanceId());
                    this.physicalAdvan.put(month.name(), String.valueOf((int)physicalAvanceProgram.get(i).getPhysicalProgrammedAdvancePercent()));                    
                    i++;
                }               
            }
           loadFinancialAdvancedProg();
           calculateTotalByMonths();
           selectOption();           
        }
       
     }  
    
    /**
     * Carga tabla de avance financiero programado con las claves presupuestales que se dieron de alta
     * en estructura financiera
     */
    public void loadFinancialAdvancedProg(){
        List<BudgetKeyEntity> financialAdvProg1 = physicalAndFinancialProgramManagement.getFinancialAdvProg(this.theirPreFileId);
        List<FinancialAdvancedBudgetKeyDto> financialAdv = new ArrayList<FinancialAdvancedBudgetKeyDto>();
        for(BudgetKeyEntity budget: financialAdvProg1){
            FinancialAdvancedBudgetKeyDto advancedBudgetKey = new FinancialAdvancedBudgetKeyDto();           
            String financingSourceDescription = budget.getFinancingSourceBudgetKey().getFinancingSourceKey();
            financingSourceDescription += " " + budget.getFinancingSourceBudgetKey().getFinancingSourceDescription();
            advancedBudgetKey.setFinancingSource(financingSourceDescription);
            advancedBudgetKey.setIdcvepresupuestal(budget.getBudgetKeyId());
            advancedBudgetKey.setKey(budget.getBudgetKeyBProgramaticKey());
            advancedBudgetKey.setOriginalAmount(budget.getOriginalAmount());
            advancedBudgetKey.setIddefcvepresupuestal(budget.getBudgetKeyDefinitionBudgetKey().getPresupuestalKeyDefinitionId());
            advancedBudgetKey.setIdObjectExpensive(budget.getObjectExpenseBudgetKey().getObjectExpenseId());
            advancedBudgetKey.setIdFinancingSource(budget.getFinancingSourceBudgetKey().getFinancingSourceId());
            Map<String, MensualBudgetKeyEntity> mensualAdv = new HashMap<String, MensualBudgetKeyEntity>();
            Map<String,String> mensualValues = new HashMap<String, String>();
            Map<String,Long> mensualIds = new HashMap<String, Long>();
            Set<MensualBudgetKeyEntity> mensualBudgetKeyBudgetKey = budget.getMensualBudgetKeyBudgetKey();
            if(mensualBudgetKeyBudgetKey.isEmpty()){
                MonthsEnum[] values = MonthsEnum.values();
                for(MonthsEnum value: values){
                    MensualBudgetKeyEntity mensualBudgetKeyEntity = new MensualBudgetKeyEntity();
                    mensualBudgetKeyEntity.setMonth(value.name());
                    mensualBudgetKeyEntity.setOriginalAmount(0D);
                    mensualAdv.put(value.name(), mensualBudgetKeyEntity);
                    mensualValues.put(value.name(), "0");
                    mensualIds.put(value.name(),new Long(0));
                }
            }
            double total = 0;
            for(MensualBudgetKeyEntity mensual: mensualBudgetKeyBudgetKey){
              mensualAdv.put(mensual.getMonth(),mensual);
              mensualValues.put(mensual.getMonth(), FormatNumber.formatNumber(String.valueOf(mensual.getOriginalAmount())));
              mensualIds.put(mensual.getMonth(),mensual.getMensualBudgetKeyId());
              total += mensual.getOriginalAmount();
            }
            advancedBudgetKey.setTotalCve(total);
            advancedBudgetKey.setDiff(advancedBudgetKey.getOriginalAmount() - total);            
            advancedBudgetKey.setMensualBudgetKeyIds(mensualIds);
            advancedBudgetKey.setMensualBudgetKeyValue(mensualValues);
            financialAdv.add(advancedBudgetKey);
        }
        this.financialAdvProg = financialAdv;
    }
    
    /**
     * Calcula el total por mes en avance financiero programado
     */
    public void calculateTotalByMonths(){
        this.totalMonths = new HashMap<String, BigDecimal>();
        MonthsEnum[] values = MonthsEnum.values();
        List<FinancialAdvancedBudgetKeyDto> financialAdv = this.getFinancialAdvProg();
        BigDecimal totalCves = new BigDecimal(0);
        for(FinancialAdvancedBudgetKeyDto advancedBudgetKeyDto : financialAdv){           
            Map<String, String> monthValues = advancedBudgetKeyDto.getMensualBudgetKeyValue();
            for(MonthsEnum month: values){                
                String value = monthValues.get(month.name());
                if("".equals(value)){
                    value = "0";
                }
                BigDecimal get = this.totalMonths.get(month.name());
                if(get != null){
                    BigDecimal sum = get.add(new BigDecimal(FormatNumber.removeFormat(value)));
                    this.totalMonths.put(month.name(),sum);
                }else{
                     this.totalMonths.put(month.name(),new BigDecimal(FormatNumber.removeFormat(value)));
                }
            }
            totalCves = totalCves.add(new BigDecimal(advancedBudgetKeyDto.getTotalCve()));
         }
         this.totalMonths.put("TOTAL",totalCves);
    }
    
    /**
     * metodo para calcular el total por clave presupuestal
     * @param financialAdvanced 
     */
    public void calculateCveTotals(){
        List<FinancialAdvancedBudgetKeyDto> financialAdv = this.getFinancialAdvProg();
        List<FinancialAdvancedBudgetKeyDto> financialProg = new ArrayList<FinancialAdvancedBudgetKeyDto>();//this.getFinancialAdvProg();
        MonthsEnum[] months = MonthsEnum.values();
        for(FinancialAdvancedBudgetKeyDto financialAdvanced : financialAdv){
            Double total = new Double(0);
            Double originalAmount;
            for(MonthsEnum month : months){
               String value = financialAdvanced.getMensualBudgetKeyValue().get(month.name());
               if("".equals(value)){
                   value = "0";
               }
               total += Double.parseDouble(FormatNumber.removeFormat(value));              
            }
            financialAdvanced.setTotalCve(total);
            financialAdvanced.setDiff(financialAdvanced.getOriginalAmount() - total);
            financialProg.add(financialAdvanced);
        }
        this.financialAdvProg = financialProg;
        calculateTotalByMonths();
    }
    /**
     * metodo para validar los campos de apotaciones segun los radioButtons de
     * nueva o en proceso y anual o multianual
     */
    @Override
    public void selectOption(){
        if(this.processType == 1){ 
            this.physicalCurrent = "0";
            this.financialCurrent = "0";
            this.enabledCurrent = true;
        }else{ 
            this.enabledCurrent = false;
        }
        if(this.duracion == 1){  
            this.physicalToExcuteNextYears = "0";
            this.financialToExcuteNextYears = "0";
            this.enabledToExecuteNextYears = true;
        }else if(this.duracion == 2){           
            this.enabledToExecuteNextYears = false;
        }
        calcTotalPhysicAdvanced();
        calcTotalFinancialAdvanced();        
    }
    /**
     * metodo que settea el valor seleccionado en la fecha de inicio
     * @param event
     */
    @Override
    public void handleDateSelectStartDate(DateSelectEvent event) {   
        this.theirPhysicalFinancialStartDate = event.getDate();     
        getCalcDuracionTime();
        MonthsEnum[] months = MonthsEnum.values();  
        for (MonthsEnum month : months) {
            this.physicalAdvan.put(month.name(),"");    
        }
        this.physicalAdvan.put("TOTAL",""); 
    }   
    
   /**
    * metodo que settea el valor seleccionado en la fecha final
    * @param event
    */
    @Override
   public void handleDateSelectEndDate(DateSelectEvent event) {       
        this.theirPhysicalFinancialEndDate = event.getDate();
        getCalcDuracionTime();
        MonthsEnum[] months = MonthsEnum.values();  
        for (MonthsEnum month : months) {
            this.physicalAdvan.put(month.name(),"");    
        }
        this.physicalAdvan.put("TOTAL",""); 
    }
    
    /**
     * metodo encargado de validar los campos obligatorios para guardar la inf.
     * @return
     */
    @Override
    public boolean validateInputs(){
        boolean result = true;
        MonthsEnum[] months = MonthsEnum.values();  
        if(this.processType == 0){           
              addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Nueva o En proceso"
                    }),
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Nueva o En proceso"
                    }));    
            result = false;
        }
        if(this.theirPhysicalFinancialEndDate == null){
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Fecha Fin"
                    }),
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Fecha Fin"
                    }));
            result = false;
        }
        if(this.theirPhysicalFinancialStartDate == null){
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Fecha Inicio"
                    }),
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Fecha Inicio"
                    }));
            result = false;
        }
        /*if(this.duracion == 0){            
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Duración"
                    }),
                    this.getMessage("ppp.planning.emptyField", new String[]{
                        "Duración"
                    }));
            result = false;
        }*/    
        for (MonthsEnum month : months) {
            String mes = this.physicalAdvan.get(month.name());
            if(mes == null || mes.trim().length()==0){               
                this.physicalAdvan.put(month.name(),"0");
                
            }
        }
        if ((this.financialCurrent != null && this.financialCurrent.trim().length()==0)){             
            if(this.processType == 2){
                this.financialCurrent ="0";                
            }  
            
        }
        if ((this.financialToExecute != null && this.financialToExecute.trim().length()==0)){
             this.financialToExecute = "0";            
            
        }
        if ((this.financialToExcuteNextYears != null && this.financialToExcuteNextYears.trim().length()==0)){
           
            if(this.duracion == 2)
               this.financialToExcuteNextYears="0";
            
        }
         if ((this.physicalCurrent != null && this.physicalCurrent.trim().length()==0)){
             
             if(this.processType == 2)              
                this.physicalCurrent="0";       
            
        }
        if ((this.physicalToExecute != null && this.physicalToExecute.trim().length()==0)){
            
           this.physicalToExecute="0";
            
        }
        if ((this.physicalToExcuteNextYears != null && this.physicalToExcuteNextYears.trim().length()==0)){
            
            if(this.duracion == 2)
                this.physicalToExcuteNextYears="0"; 
        }
        if(this.physicalTotal != null && this.physicalTotal.trim().length() == 0 ){
            this.physicalTotal ="0";
        }
        double physicTotal = Double.parseDouble(this.physicalTotal);
        if(physicTotal != 100){
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                        "Total Avance Físico en Aportaciones"
                    }),
                    this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                        "Total Avance Físico en Aportaciones"
                    }));
            result = false;
        } 
        if(this.physicalAdvan.get("TOTAL") != null && this.physicalAdvan.get("TOTAL").trim().length() == 0 ){
            this.physicalAdvan.put("TOTAL","0");
        }       
        double total = Double.parseDouble(this.physicalAdvan.get("TOTAL"));
         if(total != 100){
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                        "Total Avance Físico Programado"
                    }),
                    this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                        "Total Avance Físico Programado"
                    }));             
            result = false;
        }
        if(Double.parseDouble(FormatNumber.removeFormat(getFinancialToExecute())) != 0){
                    if(Double.parseDouble(FormatNumber.removeFormat(getFinancialToExecute())) != this.totalInitalAsigFinancialStruc){                  
                                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.programming.invprefile.validateFinancialAdvanced", new String[]{
                            theirOptionsController.getYear(),FormatNumber.formatNumber(String.valueOf(this.totalInitalAsigFinancialStruc))
                        }),
                        this.getMessage("ppp.programming.invprefile.validateFinancialAdvanced", new String[]{
                            theirOptionsController.getYear(),FormatNumber.formatNumber(String.valueOf(this.totalInitalAsigFinancialStruc))
                        })); 
                        result = false;
                    }
         }
        return result;
    }
    
    /**
     * metodo encargado de calcular el total del avance fisico en la
     * seccion de aportaciones
     */
    @Override
    public void calcTotalPhysicAdvanced(){
        double physicTotal = 0;
        if(this.physicalCurrent == null || this.physicalCurrent.trim().length()== 0)
            this.physicalCurrent = "0";
        if(this.physicalToExecute == null || this.physicalToExecute.trim().length()== 0)
            this.physicalToExecute ="0";
        if(this.physicalToExcuteNextYears == null || this.physicalToExcuteNextYears.trim().length()== 0)
            this.physicalToExcuteNextYears = "0";
        
        physicTotal = Double.parseDouble(this.physicalCurrent) + Double.parseDouble(this.physicalToExecute) + Double.parseDouble(this.physicalToExcuteNextYears);       
        this.physicalTotal = String.valueOf((int)physicTotal);
        if(physicTotal > 100){
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                    "Total Avance Físico"
                }),
                this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                    "Total Avance Físico"
                }));
        }
    }  
    
    /**
     * metodo encargado de calcular el total del avance financiero en la seccion de 
     * aportaciones
     */
    @Override
    public void calcTotalFinancialAdvanced(){  
        double financTotal = 0;        
        if(getFinancialCurrent() == null || getFinancialCurrent().trim().length()== 0)
            setFinancialCurrent("0");
        if(getFinancialToExecute() == null || getFinancialToExecute().trim().length()== 0)
            setFinancialToExecute("0");
        if(getFinancialToExcuteNextYears() == null || getFinancialToExcuteNextYears().trim().length()== 0)
            setFinancialToExcuteNextYears ("0");
        
        financTotal = Double.parseDouble(FormatNumber.removeFormat(getFinancialCurrent())) + 
                      Double.parseDouble(FormatNumber.removeFormat(getFinancialToExecute())) + 
                      Double.parseDouble(FormatNumber.removeFormat(getFinancialToExcuteNextYears()));
        if(Double.parseDouble(FormatNumber.removeFormat(getFinancialToExecute())) != 0){
            if(Double.parseDouble(FormatNumber.removeFormat(getFinancialToExecute())) != this.totalInitalAsigFinancialStruc){
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.programming.invprefile.validateFinancialAdvanced", new String[]{
                            theirOptionsController.getYear(),FormatNumber.formatNumber(String.valueOf(this.totalInitalAsigFinancialStruc))
                        }),
                        this.getMessage("ppp.programming.invprefile.validateFinancialAdvanced", new String[]{
                            theirOptionsController.getYear(),FormatNumber.formatNumber(String.valueOf(this.totalInitalAsigFinancialStruc))
                        }));   
            }
        }
        setFinancialTotal(FormatNumber.formatNumber(String.valueOf(financTotal)));            
    }       
    
    /**
     * metodo encargado de calcular el total del avance fisico programado
     */
    @Override
    public void calcTotalPhysicalAdvan() {
        Map<String, String> physicalAdva = this.physicalAdvan;
        MonthsEnum[] months = MonthsEnum.values();
        double total = 0;      
        for (MonthsEnum month : months) {
            String MonthValue = physicalAdva.get(month.name());
            if (MonthValue != null && !"".equals(MonthValue)) {
                if(reviewNumber(month.name(),physicalAdva)){
                     total = total + Double.parseDouble(MonthValue);
                }else{
                     physicalAdva.put(month.name(), "");
                     break;
                }                                 
            }
        }
        physicalAdva.put("TOTAL", String.valueOf((int)total));
        if(total > 100){
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                        "Total Avance Físico"
                    }),
                    this.getMessage("ppp.planning.validatePhysicalTotalCien", new String[]{
                        "Total Avance Físico"
                    })); 
        }
    }

    @Override
    public String getMaxDateEndDate() {
        String maxDate = "";
        String year = theirOptionsController.getYear();
        String years = String.valueOf(Integer.parseInt(year) + 10);
        if(this.executionPeriod == ExecutionPeriodType.ANNUAL){
             maxDate = "31/12/" + year;
        }else{
            maxDate = "31/12/" + years;
        }
        return maxDate;
    }

    @Override
    public String getMinDateEndDate() {
        String minDate = "";
        String year = theirOptionsController.getYear();
        if(this.executionPeriod == ExecutionPeriodType.MULTI_YEAR){
             minDate = "1/1/" + String.valueOf(Integer.parseInt(year)+1);
        }else{
             minDate = "1/1/" + year;
        }
        return minDate;
    }
}
