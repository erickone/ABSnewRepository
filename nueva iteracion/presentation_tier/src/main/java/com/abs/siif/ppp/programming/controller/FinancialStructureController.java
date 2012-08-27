/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  EncuadreController
 *  Purpose:   Controlar la Relación de Programas con Unidad Ejecutora del 
 *             Gasto (UEG)
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.dto.BudgetDispURDto;
import com.abs.siif.budget.entities.*;
import com.abs.siif.budget.management.BudgetKeyConfigurationManagement;
import com.abs.siif.budget.management.BudgetKeyDefinitionManagement;
import com.abs.siif.budget.management.CeilingBudgetManagement;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.api.controller.FinancialStructureControllerApi;
import com.abs.siif.ppp.programming.dto.ApplicationBudgetDto;
import com.abs.siif.ppp.programming.dto.RelationDto;
import com.abs.siif.programming.dto.InitBudgetKeyPreFileDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InputEntity;
import com.abs.siif.programming.entities.InvPreFileDetailEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.management.BudgetingManagement;
import com.abs.siif.programming.management.FinancialStructureManagment;
import com.abs.siif.support.FormatNumber;
import com.abs.siif.support.InputTypeEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Lizeth Pazos
 */
@Controller("financialStructureController")
@Scope("session")
public class FinancialStructureController extends SIIFControllerBase implements Serializable, FinancialStructureControllerApi {

    @Resource(name = "financialStructureManagment")
    private transient FinancialStructureManagment financialStructureManagment;
    @Resource(name = "budgetKeyConfigurationManagement")
    private transient BudgetKeyConfigurationManagement theirbuBudgetKeyConfigurationManagement;
    @Resource(name = "budgetingManagement")
    private transient BudgetingManagement budgetingManagement;
    @Resource(name = "ceillingBudgetManagement")
    private transient CeilingBudgetManagement ceilingBudgetManagement;
    @Resource(name = "budgetKeyDefinitionManagement")
    private transient BudgetKeyDefinitionManagement theirBudgetKeyDefinitionManagement;
    List<ObjectExpenseEntity> objectExpenseEntitys;
    private Long theirInvPreFileId;
    private String asigFed = "0";
    private String asigEst = "0";
    private String asigMun = "0";
    private String asigPart = "0";
    private String asigEsp = "0";
    private String asigTotal = "0";
    private String adicionalFed = "0";
    private String adicionalEst = "0";
    private String adicionalMun = "0";
    private String adicionalPart = "0";
    private String adicionalEsp = "0";
    private String adicionalTotal = "0";
    private String totalFederal = "0";
    private String totalEstatal = "0";
    private String totalMunicipal = "0";
    private String totalParticular = "0";
    private String totalEspecie = "0";
    private String totalTotal = "0";
    private String concept;
    private List<ObjectExpenseEntity> itsItemsPartidaCbo;
    private List<DependenceEntity> itsItemsUEGCbo;
    private boolean enableAcceptButton;
    private boolean enableAddRow;
    private boolean enableAddRelation;
    private ApplicationBudgetDto applicationBudgetList;
    private ApplicationBudgetDto[] selectesAppBudgetDto;
    private ApplicationBudgetDto appDto;
    private RelationDto relationDto;
    private List<Long> idsAppBudget;
    private List<RelationDto> relationList;
    private boolean finish;
    private String cvePresupuestal;
    private Long objetoGasto;
    private boolean checked;
    private DraftProjectEntity theirDraftProjectEntity;
    private RelationDto theirDeleteRelation;
    private RelationDto theirEditRelation;

    @Override
    public boolean btnDisabledDelete(int index) {
        return relationList.get(index).isDisableDelete();
    }
    @Override
    public boolean btnDisabledEdit(int index) {
        return relationList.get(index).isDisableEdit();
    }

    @Override
    public DraftProjectEntity getTheirDraftProjectEntity() {
        return theirDraftProjectEntity;
    }

    @Override
    public void setTheirDraftProjectEntity(DraftProjectEntity theirDraftProjectEntity) {
        this.theirDraftProjectEntity = theirDraftProjectEntity;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public Long getObjetoGasto() {
        return objetoGasto;
    }

    @Override
    public void setObjetoGasto(Long objetoGasto) {
        this.objetoGasto = objetoGasto;
    }

    @Override
    public String getCvePresupuestal() {
        return cvePresupuestal;
    }

    @Override
    public void setCvePresupuestal(String cvePresupuestal) {
        this.cvePresupuestal = cvePresupuestal;
    }

    @Override
    public List<DependenceEntity> getItsItemsUEGCbo() {
        return itsItemsUEGCbo;
    }

    @Override
    public void setItsItemsUEGCbo(List<DependenceEntity> itsItemsUEGCbo) {
        this.itsItemsUEGCbo = itsItemsUEGCbo;
    }

    /**
     * si el metodo calcular termino
     *
     * @param calculateFinish
     */
    @Override
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * si el metodo calcular termino
     *
     * @return calculateFinish
     */
    @Override
    public boolean isFinish() {
        return finish;
    }

    private DependenceEntity getUEGSelected() {
        DependenceEntity dependenceEntity = new DependenceEntity();
        for (DependenceEntity dep : itsItemsUEGCbo) {
            if (dep.getDependenceId().equals(appDto.getItsSelectedUEG())) {
                dependenceEntity = dep;
            }
        }
        return dependenceEntity;
    }

    /**
     * Metodo init de la vista
     */
    @Override
    public void initFinancialStructureController() {
        cleanView();

        objectExpenseEntitys = new ArrayList<ObjectExpenseEntity>();
        enableAcceptButton = true;
        enableAddRow = true;
        enableAddRelation = true;

        relationDto = new RelationDto();
        relationList = new ArrayList<RelationDto>();

        List<DependenceEntity> itsItemsFuenteCbo;

        appDto = new ApplicationBudgetDto();
        setItsItemsPartidaCbo(objectExpenseEntitys);
        setItsItemsUEGCbo(itsItemsUEGCbo);
        appDto.setEnableD(true);
        applicationBudgetList = appDto;

        List<InputEntity> inputEntitys = financialStructureManagment.getAllInputByPreFile(getTheirInvPreFileId());
        if (inputEntitys != null && !inputEntitys.isEmpty()) {
            List<Long> ids = new ArrayList<Long>();
            for (int i = 0; i < 2; i++) {
                InputEntity get = inputEntitys.get(i);
                if (get.getInputType().equals(InputTypeEnum.ASIGNACION.name())) {
                    setAsigFed(String.valueOf((int) get.getInputFederal()));
                    setAsigEst(String.valueOf((int) get.getInputState()));
                    setAsigEsp(String.valueOf((int) get.getInputSpecie()));
                    setAsigMun(String.valueOf((int) get.getInputMunicipality()));
                    setAsigPart(String.valueOf((int) get.getInputParticular()));
                    ids.add(get.getInputId());
                } else {
                    setAdicionalFed(String.valueOf((int) get.getInputFederal()));
                    setAdicionalEst(String.valueOf((int) get.getInputState()));
                    setAdicionalEsp(String.valueOf((int) get.getInputSpecie()));
                    setAdicionalMun(String.valueOf((int) get.getInputMunicipality()));
                    setAdicionalPart(String.valueOf((int) get.getInputParticular()));
                    ids.add(get.getInputId());
                }
            }
            setIdsAppBudget(ids);
            calculateTotals();
        }

        //Carga las claves que ya se encuentran configuradas
        InvPreFileEntity invPreFileEntity = new InvPreFileEntity();
        invPreFileEntity.setInvPreFileId(theirInvPreFileId);
        List<InitBudgetKeyPreFileDto> initBudgetKeyPreFile = financialStructureManagment.getInitBudgetKeyPreFile(invPreFileEntity);

        List<RelationDto> list = new ArrayList<RelationDto>();
        if (initBudgetKeyPreFile != null && !initBudgetKeyPreFile.isEmpty()) {
            for (InitBudgetKeyPreFileDto dto : initBudgetKeyPreFile) {
                RelationDto relationDto = new RelationDto();
                relationDto.setObjectId(Long.parseLong(String.valueOf(dto.getObjetoId())));
                relationDto.setCveid(Long.parseLong(String.valueOf(dto.getCveid())));
                if (dto.getAsignadoEstatal() != null) {
                    relationDto.setAsignadoEstatal(dto.getAsignadoEstatal().toString());
                }

                if (dto.getAsignadoAdicional() != null) {
                    relationDto.setAsignadoAdicional(dto.getAsignadoAdicional().toString());
                }
                relationDto.setClavePresupuestal(dto.getClavePresupuestal());

                if (dto.getFuenteFinanciamiento() != null) {
                    relationDto.setFuenteFinanciamiento(dto.getFuenteFinanciamiento());
                    List<FinancingSourceEntity> removeFinSourceAss = removeFinSourceAss(financialStructureManagment.getAllFinancingSource(), dto.getClavePresupuestal());
                    relationDto.setItsItemsFuenteCbo(removeFinSourceAss);
                    FinancingSourceEntity fteFinanciaminetoEntitySelected = getFteFinanciaminetoEntitySelected(removeFinSourceAss, dto.getFuenteFinanciamiento(), Boolean.TRUE);
                    relationDto.setItsSelectedFuente(fteFinanciaminetoEntitySelected.getFinancingSourceId().toString());
                    relationDto.setVencimiento(fteFinanciaminetoEntitySelected.getDate());
                    BudgetKeyEntity budgetKeyEntity = new BudgetKeyEntity();
                    budgetKeyEntity.setFinancingSourceBudgetKey(fteFinanciaminetoEntitySelected);
                    relationDto.setTechoCve(financialStructureManagment.getSumaryTechoCveFuente(budgetKeyEntity).toString());
                }
                relationDto.setDisableAditional(Boolean.TRUE);
                relationDto.setDisableAll(Boolean.TRUE);
                relationDto.setDisableEstatal(Boolean.TRUE);
                list.add(relationDto);

            }
            setRelationList(list);
        }
    }

    /**
     * Limpia la vista
     */
    @Override
    public void cleanView() {
        setAsigEsp("0");
        setAsigEst("0");
        setAsigFed("0");
        setAsigMun("0");
        setAsigPart("0");
        setAsigTotal("0");
        setAdicionalEsp("0");
        setAdicionalEst("0");
        setAdicionalFed("0");
        setAdicionalMun("0");
        setAdicionalPart("0");
        setAdicionalTotal("0");
        setTotalEspecie("0");
        setTotalEstatal("0");
        setTotalFederal("0");
        setTotalMunicipal("0");
        setTotalParticular("0");
        setTotalTotal("0");
        setIdsAppBudget(new ArrayList<Long>());
    }

    /**
     * @return the itsItemsAmbitoCbo
     */
    @Override
    public List<ObjectExpenseEntity> getItsItemsPartidaCbo() {
        return itsItemsPartidaCbo;
    }

    /**
     * @param itsItemsAmbitoCbo the itsItemsAmbitoCbo to set
     */
    @Override
    public void setItsItemsPartidaCbo(List<ObjectExpenseEntity> itsItemsPartidaCbo) {
        this.itsItemsPartidaCbo = itsItemsPartidaCbo;
    }

    /**
     * @return the list
     */
    @Override
    public ApplicationBudgetDto getAppDto() {
        return appDto;
    }

    /**
     * @param list the list to set
     */
    @Override
    public void setAppDto(ApplicationBudgetDto appDto) {
        this.appDto = appDto;
    }

    /**
     * @return the list
     */
    @Override
    public ApplicationBudgetDto getApplicationBudgetList() {
        ApplicationBudgetDto get = applicationBudgetList;
        if (get.getConcept() == null) {
            get.setConcept("");
        }
        if (get.getTecho() == null) {
            get.setTecho("");
        }
        if (get.getAsigned() == null) {
            get.setAsigned("");
        }
        if (get.getForAsigned() == null) {
            get.setForAsigned("");
        }
        return applicationBudgetList;
    }

    /**
     * @param list the list to set
     */
    @Override
    public void setApplicationBudgetList(ApplicationBudgetDto applicationBudgetList) {
        this.applicationBudgetList = applicationBudgetList;
    }

    /**
     * @return the relationList
     */
    @Override
    public List<RelationDto> getRelationList() {
        return relationList;
    }

    /**
     * @param relationList the relationList to set
     */
    @Override
    public void setRelationList(List<RelationDto> relationList) {
        this.relationList = relationList;
    }

    /**
     * @return the asigFed
     */
    @Override
    public String getAsigFed() {
        return asigFed;
    }

    /**
     * @param asigFed the asigFed to set
     */
    @Override
    public void setAsigFed(String asigFed) {
        asigFed = FormatNumber.formatNumber(asigFed);
        this.asigFed = asigFed;
    }

    /**
     * @return the asigEst
     */
    @Override
    public String getAsigEst() {
        return asigEst;
    }

    /**
     * @param setAsigEst the setAsigEst to set
     */
    @Override
    public void setAsigEst(String asigEst) {
        asigEst = FormatNumber.formatNumber(asigEst);
        this.asigEst = asigEst;
    }

    /**
     * @return the asigMun
     */
    @Override
    public String getAsigMun() {
        return asigMun;
    }

    /**
     * @param asigMun the asigMun to set
     */
    @Override
    public void setAsigMun(String asigMun) {
        asigMun = FormatNumber.formatNumber(asigMun);
        this.asigMun = asigMun;
    }

    /**
     * @return the asigPart
     */
    @Override
    public String getAsigPart() {
        return asigPart;
    }

    /**
     * @param asigMun the asigMun to set
     */
    @Override
    public void setAsigPart(String asigPart) {
        asigPart = FormatNumber.formatNumber(asigPart);
        this.asigPart = asigPart;
    }

    /**
     * @return the asigEsp
     */
    @Override
    public String getAsigEsp() {
        return asigEsp;
    }

    /**
     * @param asigEsp the asigEsp to set
     */
    @Override
    public void setAsigEsp(String asigEsp) {
        asigEsp = FormatNumber.formatNumber(asigEsp);
        this.asigEsp = asigEsp;
    }

    /**
     * @return the asigTotal
     */
    @Override
    public String getAsigTotal() {
        return asigTotal;
    }

    /**
     * @param asigTotal the asigTotal to set
     */
    @Override
    public void setAsigTotal(String asigTotal) {
        this.asigTotal = asigTotal;
    }

    /**
     * @return the adicionalFed
     */
    @Override
    public String getAdicionalFed() {
        return adicionalFed;
    }

    /**
     * @param adicionalFed the adicionalFed to set
     */
    @Override
    public void setAdicionalFed(String adicionalFed) {
        adicionalFed = FormatNumber.formatNumber(adicionalFed);
        this.adicionalFed = adicionalFed;
    }

    /**
     * @return the adicionalEst
     */
    @Override
    public String getAdicionalEst() {
        return adicionalEst;
    }

    /**
     * @param adicionalEst the adicionalEst to set
     */
    @Override
    public void setAdicionalEst(String adicionalEst) {
        adicionalEst = FormatNumber.formatNumber(adicionalEst);
        this.adicionalEst = adicionalEst;
    }

    /**
     * @return the adicionalMun
     */
    @Override
    public String getAdicionalMun() {
        return adicionalMun;
    }

    /**
     * @param adicionalMun the adicionalMun to set
     */
    @Override
    public void setAdicionalMun(String adicionalMun) {
        adicionalMun = FormatNumber.formatNumber(adicionalMun);
        this.adicionalMun = adicionalMun;
    }

    /**
     * @return the adicionalPart
     */
    @Override
    public String getAdicionalPart() {
        return adicionalPart;
    }

    /**
     * @param adicionalPart the adicionalPart to set
     */
    @Override
    public void setAdicionalPart(String adicionalPart) {
        adicionalPart = FormatNumber.formatNumber(adicionalPart);
        this.adicionalPart = adicionalPart;
    }

    /**
     * @return the adicionalEsp
     */
    @Override
    public String getAdicionalEsp() {
        return adicionalEsp;
    }

    /**
     * @param adicionalEsp the adicionalEsp to set
     */
    @Override
    public void setAdicionalEsp(String adicionalEsp) {
        adicionalEsp = FormatNumber.formatNumber(adicionalEsp);
        this.adicionalEsp = adicionalEsp;
    }

    /**
     * @return the adicionalTotal
     */
    @Override
    public String getAdicionalTotal() {
        return adicionalTotal;
    }

    /**
     * @param adicionalTotal the adicionalTotal to set
     */
    @Override
    public void setAdicionalTotal(String adicionalTotal) {
        this.adicionalTotal = adicionalTotal;
    }

    /**
     * @return the totalFederal
     */
    @Override
    public String getTotalFederal() {
        return totalFederal;
    }

    /**
     * @param totalFederal the totalFederal to set
     */
    @Override
    public void setTotalFederal(String totalFederal) {
        this.totalFederal = totalFederal;
    }

    /**
     * @return the totalEstatal
     */
    @Override
    public String getTotalEstatal() {
        return totalEstatal;
    }

    /**
     * @param totalFederal the totalFederal to set
     */
    @Override
    public void setTotalEstatal(String totalEstatal) {
        this.totalEstatal = totalEstatal;
    }

    /**
     * @return the totalMunicipal
     */
    @Override
    public String getTotalMunicipal() {
        return totalMunicipal;
    }

    /**
     * @param totalMunicipal the totalMunicipal to set
     */
    @Override
    public void setTotalMunicipal(String totalMunicipal) {
        this.totalMunicipal = totalMunicipal;
    }

    /**
     * @return the totalParticular
     */
    @Override
    public String getTotalParticular() {
        return totalParticular;
    }

    /**
     * @param totalParticular the totalParticular to set
     */
    @Override
    public void setTotalParticular(String totalParticular) {
        this.totalParticular = totalParticular;
    }

    /**
     * @return the totalEspecie
     */
    @Override
    public String getTotalEspecie() {
        return totalEspecie;
    }

    /**
     * @param totalEspecie the totalEspecie to set
     */
    @Override
    public void setTotalEspecie(String totalEspecie) {
        this.totalEspecie = totalEspecie;
    }

    /**
     * @return the totalTotal
     */
    @Override
    public String getTotalTotal() {
        return totalTotal;
    }

    /**
     * @param totalTotal the totalTotal to set
     */
    @Override
    public void setTotalTotal(String totalTotal) {
        this.totalTotal = totalTotal;
    }

    private Long getDetinoId() {
        Long id = new Long(0);
        for (DestinyObjectExpenseRUBUEntity ent : appDto.getItsItemsDCbo()) {
            if (ent.getDestinyObjectEpenseRUBUDestiny().equals(appDto.getItsSelectedD())) {
                id = ent.getDestinyObjectExpenseRUBUId();
            }
        }
        return id;
    }

    /**
     * Click al boton de agregar una nueva fila ala tabla de asignacion
     * presupuestal
     */
    @Override
    public void addApplicationBudgetRow() {

        setObjetoGasto(Long.parseLong(appDto.getItsSelectedPartida()));

        if (getRelationList() != null && !getRelationList().isEmpty()) {
            for (int i = 0; i < getRelationList().size(); i++) {
                getRelationList().get(i).setDisableAll(Boolean.TRUE);
                getRelationList().get(i).setDisableAditional(Boolean.TRUE);
                getRelationList().get(i).setDisableEstatal(Boolean.TRUE);
                getRelationList().get(i).setDisableEdit(Boolean.TRUE);
                getRelationList().get(i).setDisableDelete(Boolean.TRUE);
            }
        }

        relationDto = new RelationDto();
        relationDto.setObjectId(Long.parseLong(appDto.getItsSelectedPartida()));
        relationDto.setDisableEdit(Boolean.TRUE);
        relationDto.setDisableDelete(Boolean.TRUE);
        if(appDto.getTecho()== null || appDto.getTecho().equals("0") || appDto.getTecho().isEmpty())
            relationDto.setDisableEstatal(Boolean.TRUE);

        //Genera CLave Presupuestal
        Map<String, Long> myMap = new HashMap<String, Long>();
        myMap.put("iddependencia", appDto.getItsSelectedUEG());
        myMap.put("idproyecto", getTheirDraftProjectEntity().getDraftProjectId());
        myMap.put("idobjetogasto", Long.valueOf(appDto.getItsSelectedPartida()));

        String budgetKey = "";
        Map<String, List<BudgetDetailsKeyEntity>> myConcreteIds = theirbuBudgetKeyConfigurationManagement.getBudgetKeyWithBudgetKeyItems(myMap);
        for (Map.Entry<String, List<BudgetDetailsKeyEntity>> entry : myConcreteIds.entrySet()) {
            budgetKey = entry.getKey() + appDto.getItsSelectedD();
            relationDto.setBudgetDetailsKeyList(entry.getValue());

        }

        relationDto.setClavePresupuestal(budgetKey);

        BudgetKeyEntity budgetKeyEntity = new BudgetKeyEntity();
        budgetKeyEntity.setBudgetKeyBProgramaticKey(budgetKey);

        relationDto.setItsItemsFuenteCbo(removeFinSourceAss(financialStructureManagment.getAllFinancingSource(), budgetKey));

        //Llena combo de relaciones
        relationList.add(relationDto);

        appDto.setEnablePartida(true);
        enableAddRelation = false;

        enableAddRow = true;
        appDto.setEnableD(true);
        appDto.setDisableAllCbo(Boolean.TRUE);

    }

    /**
     * Click al boton de agregar una nueva fila ala tabla de asignacion
     * presupuestal
     */
    @Override
    public void addRelationRow() {
    }

    public ObjectExpenseEntity getPartidaSelected() {
        ObjectExpenseEntity objectExpenseEntity = new ObjectExpenseEntity();
        for (ObjectExpenseEntity obj : getItsItemsPartidaCbo()) {
            if (obj.getObjectExpenseId() == Long.parseLong(appDto.getItsSelectedPartida())) {
                objectExpenseEntity = obj;
            }
        }
        return objectExpenseEntity;
    }

    /**
     * Change selection of Partida combo
     */
    @Override
    public void changeListenerCboPartida() {
        if (!appDto.getItsSelectedPartida().equals("")) {
            List<DestinyObjectExpenseRUBUEntity> destinationEntitys = budgetingManagement.getTheBudgetingDestinationByObjectInv(getUEGSelected(), getPartidaSelected());

            for (int i = 0; i < itsItemsPartidaCbo.size(); i++) {
                if (itsItemsPartidaCbo.get(i).getObjectExpenseId().toString().equals(appDto.getItsSelectedPartida())) {
                    appDto.setConcept(itsItemsPartidaCbo.get(i).getObjectExpenseDescription());
                    break;
                }
            }
            appDto.setItsItemsDCbo(destinationEntitys);

            if (appDto.getItsSelectedD() != null && !appDto.getItsSelectedD().isEmpty()) {
                changeListenerCboD();
            }
        } else {
            appDto.setItsItemsDCbo(new ArrayList<DestinyObjectExpenseRUBUEntity>());
            appDto.setItsSelectedD("");
            appDto.setConcept("");
            changeListenerCboD();
        }
    }

    /**
     * @return the enableAcceptButton
     */
    @Override
    public boolean isEnableAcceptButton() {
        return enableAcceptButton;
    }

    /**
     * @param enableAcceptButton the enableAcceptButton to set
     */
    @Override
    public void setEnableAcceptButton(boolean enableAcceptButton) {
        this.enableAcceptButton = enableAcceptButton;
    }

    /**
     * Change selection of ubication combo
     */
    @Override
    public void changeListenerCboD() {
        String techo = "0";
        boolean enableBtnAdd = true;
        BigDecimal amountAssigned = new BigDecimal(0);
        BigDecimal forAssig = new BigDecimal(0);
        String partidaKey = "";
        if (appDto.getItsSelectedD() != null && !appDto.getItsSelectedD().isEmpty()) {
            enableBtnAdd = false;
            for (ObjectExpenseEntity entity : itsItemsPartidaCbo) {
                if (entity.getObjectExpenseId().toString().equals(
                        applicationBudgetList.getItsSelectedPartida())) {
                    partidaKey = String.valueOf(entity.getObjectExpenseKey());
                    break;
                }
            }
            DependenceEntity dep = new DependenceEntity();
            for (DependenceEntity dependenceEntity : getItsItemsUEGCbo()) {
                if (dependenceEntity.getDependenceId().equals(appDto.getItsSelectedUEG())) {
                    dep = dependenceEntity;
                    break;
                }
            }
            setCvePresupuestal(dep.getFather().getDependenceKey().replaceAll(" ", "") + " "
                    + partidaKey + " "
                    + String.valueOf(applicationBudgetList.getItsSelectedD()));

        }
        setEnableAddRow(enableBtnAdd);
    }

    /**
     * @return the enableAddRow
     */
    @Override
    public boolean isEnableAddRow() {
        return enableAddRow;
    }

    /**
     * @param enableAddRow the enableAddRow to set
     */
    @Override
    public void setEnableAddRow(boolean enableAddRow) {
        this.enableAddRow = enableAddRow;
    }

    /**
     * @return the concept
     */
    @Override
    public String getConcept() {
        return concept;
    }

    /**
     * @param concept the concept to set
     */
    @Override
    public void setConcept(String concept) {
        this.concept = concept;
    }

    /**
     * @return the selectesAppBudgetDto
     */
    @Override
    public ApplicationBudgetDto[] getSelectesAppBudgetDto() {
        return selectesAppBudgetDto;
    }

    /**
     * @param selectesAppBudgetDto the selectesAppBudgetDto to set
     */
    @Override
    public void setSelectesAppBudgetDto(ApplicationBudgetDto[] selectesAppBudgetDto) {
        this.selectesAppBudgetDto = selectesAppBudgetDto;
    }

    /**
     * Calculates the total allocation
     */
    @Override
    public void calculateTotals() {
        asigTotal = sumFormat(getAsigEsp(), getAsigEst(), getAsigFed(), getAsigMun(), getAsigPart());
        adicionalTotal = sumFormat(getAdicionalEsp(), getAdicionalEst(), getAdicionalFed(), getAdicionalMun(), getAdicionalPart());
        totalEspecie = sumFormat(getAsigEsp(), getAdicionalEsp());
        totalEstatal = sumFormat(getAsigEst(), getAdicionalEst());
        totalFederal = sumFormat(getAsigFed(), getAdicionalFed());
        totalMunicipal = sumFormat(getAsigMun(), getAdicionalMun());
        totalParticular = sumFormat(getAsigPart(), getAdicionalPart());
        totalTotal = sumFormat(getAsigTotal().replace(",", ""), getAdicionalTotal().replace(",", ""));
        setFinish(true);
    }

    /**
     * Realiza la suma de los totales y formatea el resultado
     *
     * @param values valores a sumar
     * @return resultado de la suma formateada
     */
    @Override
    public String sumFormat(String... values) {
        BigDecimal sum = new BigDecimal(0);
        for (String v : values) {
            BigDecimal valueOf = BigDecimal.valueOf(Double.parseDouble(FormatNumber.removeFormat(v)));
            sum = sum.add(valueOf);
        }
        return FormatNumber.formatNumber(sum.toString());
    }

    /**
     * Id Pre-Ficha
     *
     * @return
     */
    @Override
    public Long getTheirInvPreFileId() {
        return theirInvPreFileId;
    }

    /**
     * Id Pre-Ficha
     *
     * @param theirInvPreFileId
     */
    @Override
    public void setTheirInvPreFileId(Long theirInvPreFileId) {
        this.theirInvPreFileId = theirInvPreFileId;
    }

    /**
     * @return the enableAddRelation
     */
    @Override
    public boolean isEnableAddRelation() {
        return enableAddRelation;
    }

    /**
     * @param enableAddRelation the enableAddRelation to set
     */
    @Override
    public void setEnableAddRelation(boolean enableAddRelation) {
        this.enableAddRelation = enableAddRelation;
    }

    /**
     * Save a FinancialStucture
     */
    @Override
    public void saveFinancialStrcuture() {
        Collection<InputEntity> inputEntitys;
        InvPreFileEntity invPreFileEntity;
        InputEntity input;
        List<Long> saveInputEntitys;
        try {
            invPreFileEntity = new InvPreFileEntity();
            saveInputEntitys = getIdsAppBudget();
            invPreFileEntity.setInvPreFileId(getTheirInvPreFileId());

            inputEntitys = new ArrayList<InputEntity>();

            input = new InputEntity();
            input.setInputType(InputTypeEnum.ADICIONAL.name());
            input.setInputFederal(Double.parseDouble(FormatNumber.removeFormat(adicionalFed)));
            input.setInputMunicipality(Double.parseDouble(FormatNumber.removeFormat(adicionalMun)));
            input.setInputParticular(Double.parseDouble(FormatNumber.removeFormat(adicionalPart)));
            input.setInputSpecie(Double.parseDouble(FormatNumber.removeFormat(adicionalEsp)));
            input.setInputState(Double.parseDouble(FormatNumber.removeFormat(adicionalEst)));
            input.setInputInvPreFile(invPreFileEntity);
            if (saveInputEntitys != null && !saveInputEntitys.isEmpty()) {
                input.setInputId(saveInputEntitys.get(0));
            }
            inputEntitys.add(input);

            input = new InputEntity();

            input.setInputType(InputTypeEnum.ASIGNACION.name());
            input.setInputFederal(Double.parseDouble(FormatNumber.removeFormat(asigFed)));
            input.setInputMunicipality(Double.parseDouble(FormatNumber.removeFormat(asigMun)));
            input.setInputParticular(Double.parseDouble(FormatNumber.removeFormat(asigPart)));
            input.setInputSpecie(Double.parseDouble(FormatNumber.removeFormat(asigEsp)));
            input.setInputState(Double.parseDouble(FormatNumber.removeFormat(asigEst)));
            input.setInputInvPreFile(invPreFileEntity);
            if (saveInputEntitys != null && !saveInputEntitys.isEmpty()) {
                input.setInputId(saveInputEntitys.get(1));
            }
            inputEntitys.add(input);

            if (Double.parseDouble(FormatNumber.removeFormat(getAsigEst())) >= sumAsignacionEstatal()) {
                if (Double.parseDouble(FormatNumber.removeFormat(getAdicionalEst())) >= sumAsignacionAdicional()) {
                    saveInputEntitys = financialStructureManagment.saveInputEntitys(inputEntitys);
                    setIdsAppBudget(saveInputEntitys);

                    addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                            this.getMessage("pp.progr.Msg.Save"),
                            this.getMessage("pp.progr.Msg.Save"));
                }
                else {
                    initFinancialStructureController();
                    addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                            this.getMessage("ppp.progr.FinancualStrcuture.ErrorEditAssign]Aditional"),
                            this.getMessage("ppp.progr.FinancualStrcuture.ErrorEditAssign]Aditional"));
                }
            } else {
                initFinancialStructureController();
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.FinancualStrcuture.ErrorEditAssignState"),
                        this.getMessage("ppp.progr.FinancualStrcuture.ErrorEditAssignState"));
            }
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * @param ids the ids to set
     */
    @Override
    public void setIdsAppBudget(List<Long> idsAppBudget) {
        this.idsAppBudget = idsAppBudget;
    }

    /**
     * @return the ids
     */
    @Override
    public List<Long> getIdsAppBudget() {
        return idsAppBudget;
    }

    @Override
    public String convertToString(int value) {
        return String.valueOf(value);
    }

    /**
     * Cambia la seleccion del combo de UEG
     */
    @Override
    public void changeListenerCboUEG() {
        if (appDto.getItsSelectedUEG() != null && appDto.getItsSelectedUEG().compareTo(0L) != 0) {
            List<DepencenceDto> dependciesIsRespUnitByDependIdRelated = financialStructureManagment.getDependciesIsRespUnitByDependIdRelated(getUEGSelected().getDependenceId());
            DepencenceDto depencenceDto = null;
            for (DepencenceDto dto : dependciesIsRespUnitByDependIdRelated) {
                depencenceDto = dto;
            }
            if (depencenceDto != null) {
                
                objectExpenseEntitys = financialStructureManagment.getBudgetingFramming(getUEGSelected().getFather(), getTheirInvPreFileId());
                setItsItemsPartidaCbo(objectExpenseEntitys);
                
                Long summatoryTotalOfCeilingsInvestObjects = financialStructureManagment.getSummatoryTotalOfCeilingsInvestObjects(Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()), depencenceDto.getClave().replace(" ", ""));
                if (summatoryTotalOfCeilingsInvestObjects != 0 && summatoryTotalOfCeilingsInvestObjects != 0L) {
//                    objectExpenseEntitys = financialStructureManagment.getBudgetingFramming(getUEGSelected().getFather(), getTheirInvPreFileId());
                    appDto.setTecho(summatoryTotalOfCeilingsInvestObjects.toString());
//                    setItsItemsPartidaCbo(objectExpenseEntitys);
                    Long budgetAvailableForInvPreFile = ceilingBudgetManagement.getBudgetAvailableForInvPreFile(getUEGSelected());
                    getApplicationBudgetList().setAsigned(budgetAvailableForInvPreFile.toString());
                    Long forAssig = summatoryTotalOfCeilingsInvestObjects - budgetAvailableForInvPreFile;
                    getApplicationBudgetList().setForAsigned(forAssig.toString());
                } else {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                            this.getMessage("ppp.progr.FinancialStructure.NoMontoUR"),
                            this.getMessage("ppp.progr.FinancialStructure.NoMontoUR"));
                }
            }
        } else {
            appDto.setItsItemsDCbo(new ArrayList<DestinyObjectExpenseRUBUEntity>());
            setItsItemsPartidaCbo(new ArrayList<ObjectExpenseEntity>());
            appDto.setItsSelectedPartida("");
            appDto.setItsSelectedD("");
            appDto.setAsigned("");
            appDto.setForAsigned("");
            appDto.setTecho("");
            changeListenerCboPartida();
        }


    }

    /**
     * Sumatoria de las asignaciones estatales configuradas
     *
     * @return sumatoria
     */
    public double sumAsignacionEstatal() {
        double suma = 0;
        for (RelationDto dto : relationList) {
            String asignadoEstatal = dto.getAsignadoEstatal();
            if(asignadoEstatal==null || asignadoEstatal.isEmpty()){
                asignadoEstatal="0";
            }
            suma += Double.parseDouble(FormatNumber.removeFormat(asignadoEstatal));
        }
        return suma;
    }
    
    /**
     * Sumatoria de las asignaciones adicional configuradas
     *
     * @return sumatoria
     */
    public double sumAsignacionAdicional() {
        double suma = 0;
        for (RelationDto dto : relationList) {
            String asignadoAdicional = dto.getAsignadoAdicional();
            if(asignadoAdicional==null || asignadoAdicional.isEmpty()){
                asignadoAdicional="0";
            }
            suma += Double.parseDouble(FormatNumber.removeFormat(asignadoAdicional));
        }
        return suma;
    }


    /**
     * Guarda la Clave Presupuestal
     *
     * @param clave - Clave Presupuestal
     * @param monto - Asignacion Estatal
     * @param adicional - AsignacionAdiciona
     * @param fuenteFin - id Fuente de Financiamientoo
     * @param obj - Id Objeto de Gasto/Partida
     * @param cveId - Id de la Clave Presupuestal en caso de ser una
     * actualizacion si no es null
     * @param update - Bandera para actualizacion
     */
    public void saveCvePresupuestal(String clave, Double monto, Double adicional, Long fuenteFin, Long obj, Long cveId, boolean update) {
        BudgetKeyEntity budgetKeyEntity = new BudgetKeyEntity();
        budgetKeyEntity.setBudgetKeyBProgramaticKey(clave);

        budgetKeyEntity.setOriginalAmount(monto);
        budgetKeyEntity.setAdditionalAmount(adicional);
        budgetKeyEntity.setBudgetKeyNoBasic(monto.longValue());
        budgetKeyEntity.setBudgetKeyBasic(0L);
        FinancingSourceEntity financingSourceEntity = new FinancingSourceEntity();
        financingSourceEntity.setFinancingSourceId(fuenteFin);
        budgetKeyEntity.setFinancingSourceBudgetKey(financingSourceEntity);
        ObjectExpenseEntity objectExpenseEntity = new ObjectExpenseEntity();
        objectExpenseEntity.setObjectExpenseId(obj);
        budgetKeyEntity.setObjectExpenseBudgetKey(objectExpenseEntity);


        BudgetKeyDefinitionEntity myConfToSave = theirBudgetKeyDefinitionManagement.getBudgetDefinitionByYear(Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));
        budgetKeyEntity.setBudgetKeyDefinitionBudgetKey(myConfToSave);

        BudgetDispURDto budgetDispURDto = new BudgetDispURDto();
        budgetDispURDto.setDependenceEntity(getUEGSelected());
        budgetDispURDto.setFinancingSourceEntity(financingSourceEntity);
        budgetDispURDto.setObjectExpenseEntity(objectExpenseEntity);

        if (!update) {

            if (relationDto.isDisableEstatal()  
                    || (Long.parseLong(FormatNumber.removeFormat(appDto.getForAsigned())) > Long.parseLong(FormatNumber.removeFormat(relationDto.getAsignadoEstatal())))) {
                for (BudgetDetailsKeyEntity myItem : relationDto.getBudgetDetailsKeyList()) {
                    myItem.setBudgetKey(budgetKeyEntity);
                }

                budgetDispURDto.setDesting(getDetinoId().toString());

                budgetKeyEntity.setBudgetDetailKeys(relationDto.getBudgetDetailsKeyList());
                Long saveBudgetKey = budgetingManagement.saveBudgetKey(budgetKeyEntity, budgetDispURDto, Boolean.FALSE);
                relationDto.setCveid(saveBudgetKey);

                InvPreFileDetailEntity invPreFileDetailEntity = new InvPreFileDetailEntity();
                InvPreFileEntity invPreFileEntity = new InvPreFileEntity();
                invPreFileEntity.setInvPreFileId(theirInvPreFileId);
                invPreFileDetailEntity.setInvPreFileDetailPreFile(invPreFileEntity);
                invPreFileDetailEntity.setInvPreFileDetailId(theirInvPreFileId);
                invPreFileDetailEntity.setInvPreFileDetailBudgetKey(budgetKeyEntity);
                financialStructureManagment.SaveInvPreFileDetailDao(invPreFileDetailEntity);

                messageSave();
                setTheirEditRelation(null);
            } else {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.FinancialStructure.NoMonto"),
                        this.getMessage("ppp.progr.FinancialStructure.NoMonto"));
            }
        } else {
            budgetKeyEntity.setBudgetKeyId(cveId);
            budgetingManagement.updateABudgetKeyEntity(budgetKeyEntity, budgetDispURDto);
            financialStructureManagment.updatebudgetKeyMonthly(budgetKeyEntity);

            messageSave();
            setTheirEditRelation(null);
        }
    }

    /**
     * Cancelar la generacion de Clave Presupuestalm y asignacion de presupuesto
     */
    @Override
    public void cancelRelation() {
        if (appDto.isDisableAllCbo()) {
            getRelationList().remove(getRelationList().size() - 1);
            appDto.setEnableD(Boolean.TRUE);
            appDto.setEnablePartida(Boolean.TRUE);
            enableAddRelation = Boolean.FALSE;
            applicationBudgetList = appDto;
            enableAddRow = Boolean.FALSE;
            appDto.setEnableD(Boolean.TRUE);
            appDto.setDisableAllCbo(Boolean.FALSE);
        } else {
            initFinancialStructureController();
        }
        for (int i = 0; i < getRelationList().size(); i++) {
            getRelationList().get(i).setDisableDelete(Boolean.FALSE);
            getRelationList().get(i).setDisableEdit(Boolean.FALSE);
        }
    }

    /**
     * Guardar la asignacion y clave Presupuestal
     */
    @Override
    public void saveRelation() {
        try {

            if (getTheirEditRelation() == null) {
                if (relationDto.getItsSelectedFuente() != null && !relationDto.getItsSelectedFuente().isEmpty()) {
                    if ((relationDto.getAsignadoEstatal() != null
                            && !relationDto.getAsignadoEstatal().isEmpty()
                            && Integer.parseInt(FormatNumber.removeFormat(relationDto.getAsignadoEstatal())) != 0) 
                            || (relationDto.isDisableEstatal())) {
                        
                        //Valida que no sobrepase el monto de asignacion estatal configurado
                        if (relationDto.isDisableEstatal() 
                                || (Double.parseDouble(FormatNumber.removeFormat(getAsigEst())) - sumAsignacionEstatal() >= 0)) {
                            
                            //Valida que no sobrepase el monto adicionla configurado
                            if(Double.parseDouble(FormatNumber.removeFormat(getAdicionalEst())) - sumAsignacionAdicional() >= 0){
                                
                            
                                String asignadoEstatal = relationList.get(relationList.size() - 1).getAsignadoEstatal();
                                if (asignadoEstatal != null && !asignadoEstatal.isEmpty()) {
                                    asignadoEstatal = FormatNumber.removeFormat(relationList.get(relationList.size() - 1).getAsignadoEstatal());
                                } else {
                                    asignadoEstatal = "0";
                                }
                                String asignadoAdicional = relationList.get(relationList.size() - 1).getAsignadoAdicional();
                                if (asignadoAdicional != null && !asignadoAdicional.isEmpty()) {
                                    asignadoAdicional = FormatNumber.removeFormat(relationList.get(relationList.size() - 1).getAsignadoAdicional());
                                } else {
                                    asignadoAdicional = "0";
                                }
                                saveCvePresupuestal(relationList.get(relationList.size() - 1).getClavePresupuestal(),
                                        Double.parseDouble(asignadoEstatal),
                                        Double.parseDouble(asignadoAdicional),
                                        Long.valueOf(relationList.get(relationList.size() - 1).getItsSelectedFuente()),
                                        getObjetoGasto(), null,
                                        Boolean.FALSE);
                            }
                            else{
                                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                                        this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigAditional"),
                                        this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigAditional"));
                                
                            }
                        } else {
                            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                                    this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigState"),
                                    this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigState"));
                        }
                    } else {
                        addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                                this.getMessage("ppp.progr.FinancualStrcuture.Assign"),
                                this.getMessage("ppp.progr.FinancualStrcuture.Assign"));

                    }
                } else {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                            this.getMessage("ppp.progr.FinancualStrcuture.SelectFinancialSource"),
                            this.getMessage("ppp.progr.FinancualStrcuture.SelectFinancialSource"));
                }
            } else {
                if (relationDto.isDisableEstatal() 
                                || (getTheirEditRelation().getAsignadoEstatal()!= null 
                                    && !getTheirEditRelation().getAsignadoEstatal().isEmpty())) {
                    
                    //Valida que no sobrepase el monto de asignacion estatal configurado
                    if (relationDto.isDisableEstatal() 
                                || (Double.parseDouble(FormatNumber.removeFormat(getAsigEst())) - sumAsignacionEstatal() >= 0)) {
                        
                          //Valida que no sobrepase el monto adicionla configurado
                            if(Double.parseDouble(FormatNumber.removeFormat(getAdicionalEst())) - sumAsignacionAdicional() >= 0){
                              
                            RelationDto relationDto = getTheirEditRelation();
                            String aditional= relationDto.getAsignadoAdicional();
                            if(aditional==null || aditional.isEmpty())
                                aditional="0";
                            String state=relationDto.getAsignadoEstatal();
                            if(state==null || state.isEmpty())
                                state="0";
                            saveCvePresupuestal(relationDto.getClavePresupuestal(),
                                    Double.parseDouble(FormatNumber.removeFormat(state)),
                                    Double.parseDouble(FormatNumber.removeFormat(aditional)),
                                    Long.parseLong(relationDto.getFuenteFinanciamiento()),
                                    relationDto.getObjectId(),
                                    relationDto.getCveid(),
                                    Boolean.TRUE);
                            }
                            else{
                                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                                        this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigAditional"),
                                        this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigAditional"));
                                
                            }
                            
                    } else {

                        addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigState"),
                                this.getMessage("ppp.progr.FinancualStrcuture.ErrorAsigState"));
                    }
                }
            }


        } catch (Exception ex) {
            setTheirEditRelation(null);
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.FinancualStrcuture.ErrorSave"),
                    this.getMessage("ppp.progr.FinancualStrcuture.ErrorSave"));
        }

    }

    /**
     * Habilita la parte de aportaciones y manda mensaje de confirmacion
     */
    public void messageSave() {

        if (getRelationList() != null && !getRelationList().isEmpty()) {
            for (int i = 0; i < getRelationList().size(); i++) {
                getRelationList().get(i).setDisableAll(Boolean.TRUE);
                getRelationList().get(i).setDisableAditional(Boolean.TRUE);
                getRelationList().get(i).setDisableEstatal(Boolean.TRUE);
                getRelationList().get(i).setDisableDelete(Boolean.FALSE);
                getRelationList().get(i).setDisableEdit(Boolean.FALSE);
            }
        }
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                this.getMessage("pp.progr.Msg.Save"),
                this.getMessage("pp.progr.Msg.Save"));

        //Se limpia y llenas los campos de Aplicacion Presupuestal
        appDto.setEnableD(Boolean.TRUE);
        appDto.setEnablePartida(Boolean.TRUE);
        enableAddRelation = Boolean.FALSE;
        appDto = new ApplicationBudgetDto();
        appDto.setItsItemsPartidaCbo(objectExpenseEntitys);
        appDto.setItsItemsUEGCbo(itsItemsUEGCbo);
        applicationBudgetList = appDto;
        enableAddRow = Boolean.TRUE;
        appDto.setEnableD(Boolean.TRUE);
        appDto.setDisableAllCbo(Boolean.FALSE);
    }

    /**
     * Cambia la seleccion dela vombo Fuente de Financiamiento
     */
    @Override
    public void changeListenerCboFinancialSource() {

        if (relationDto.getItsSelectedFuente() != null && !relationDto.getItsSelectedFuente().isEmpty()) {
            BudgetKeyEntity budgetKeyEntity = new BudgetKeyEntity();
            FinancingSourceEntity financingSourceEntity = new FinancingSourceEntity();
            financingSourceEntity.setFinancingSourceId(Long.parseLong(relationDto.getItsSelectedFuente()));
            budgetKeyEntity.setFinancingSourceBudgetKey(financingSourceEntity);
            budgetKeyEntity.setBudgetKeyBProgramaticKey(relationDto.getClavePresupuestal());
            BigDecimal sumaryTechoCveFuente = financialStructureManagment.getSumaryTechoCveFuente(budgetKeyEntity);
            FinancingSourceEntity fteFinanciaminetoEntitySelected = getFteFinanciaminetoEntitySelected(relationDto.getItsItemsFuenteCbo(), relationDto.getItsSelectedFuente(), Boolean.FALSE);

            relationDto.setFuenteFinanciamiento(fteFinanciaminetoEntitySelected.getFinancingSourceKey());
            relationDto.setVencimiento(fteFinanciaminetoEntitySelected.getDate());
            relationDto.setTechoCve(sumaryTechoCveFuente.toString());
        } else {
            relationDto.setVencimiento(null);
            relationDto.setTechoCve("");
        }


    }

    /**
     * Regresa el entity seleccionado en fuente
     *
     * @param list
     * @param finSourceKeySelected
     * @return
     */
    public FinancingSourceEntity getFteFinanciaminetoEntitySelected(List<FinancingSourceEntity> list, String finSourceKeySelected, boolean keyOrId) {
        FinancingSourceEntity ret = null;
        if (keyOrId) {
            for (FinancingSourceEntity entity : list) {
                if (entity.getFinancingSourceKey().equals(finSourceKeySelected)) {
                    ret = entity;
                    break;
                }
            }
        } else {
            for (FinancingSourceEntity entity : list) {
                if (entity.getFinancingSourceId().toString().equals(finSourceKeySelected)) {
                    ret = entity;
                    break;
                }
            }
        }

        return ret;
    }

    /**
     * Quita del combo Fuente de Financiamiento las cuales ya fueron
     * seleccionadas para una cve presupuestal
     *
     * @param listFinancingSourceEntitys
     * @param cve
     * @return
     */
    public List<FinancingSourceEntity> removeFinSourceAss(List<FinancingSourceEntity> listFinancingSourceEntitys, String cve) {
        int i = 0;
        List<FinancingSourceEntity> copyList = new ArrayList<FinancingSourceEntity>(listFinancingSourceEntitys);
        for (FinancingSourceEntity financingSourceEntity : listFinancingSourceEntitys) {
            for (RelationDto relDto : relationList) {
                if (financingSourceEntity.getFinancingSourceId().equals(Long.parseLong(relDto.getItsSelectedFuente())) && relDto.getClavePresupuestal().equals(cve)) {
                    copyList.remove(i);
                    i--;
                    break;
                }
            }
            i++;
        }

        return copyList;
    }

    /**
     * get theirDeleteRelation - Extrae El DTO a eliminar
     *
     * @return theirDeleteRelation
     */
    @Override
    public RelationDto getTheirDeleteRelation() {
        return theirDeleteRelation;
    }

    /**
     * Setea el Dto a Eliminar
     *
     * @param theirDeleteRelation
     */
    @Override
    public void setTheirDeleteRelation(RelationDto theirDeleteRelation) {
        this.theirDeleteRelation = theirDeleteRelation;
    }

    /**
     * get theirEditRelation - Extrae el Dto a Editar
     *
     * @return theirEditRelation
     */
    @Override
    public RelationDto getTheirEditRelation() {
        return theirEditRelation;
    }

    /**
     * Setea el Dto a Editar
     *
     * @param theirEditRelation
     */
    @Override
    public void setTheirEditRelation(RelationDto theirEditRelation) {
        this.theirEditRelation = theirEditRelation;
    }

    /**
     * Elimina una clave Presupuestal y su asignacion
     */
    @Override
    public void deleteRelation() {
        try {
            BudgetKeyEntity entity = new BudgetKeyEntity();
            entity.setBudgetKeyId(getTheirDeleteRelation().getCveid());
            financialStructureManagment.deletebudgetKey(entity);
            initFinancialStructureController();
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Editar el monto asignado y estatal de la clave Presupouestal
     */
    @Override
    public void editRelation() {
        try {
            if(getTheirEditRelation().getAsignadoEstatal()!=null 
                    && !getTheirEditRelation().getAsignadoEstatal().isEmpty()
                    && !getTheirEditRelation().getAsignadoEstatal().equals("0"))
            {
                getTheirEditRelation().setDisableEstatal(Boolean.FALSE);
            }
            else
            {                
                getTheirEditRelation().setDisableEstatal(Boolean.TRUE);
            }
            getTheirEditRelation().setDisableAditional(Boolean.FALSE);

            for (RelationDto dto : getRelationList()) {
                if (dto.getClavePresupuestal().equals(getTheirEditRelation().getClavePresupuestal())
                        && dto.getFuenteFinanciamiento().equals(getTheirEditRelation().getFuenteFinanciamiento())) {
                    dto = getTheirEditRelation();
                } else {
                    dto.setDisableAditional(Boolean.TRUE);
                    dto.setDisableEstatal(Boolean.TRUE);
                    dto.setDisableEdit(Boolean.TRUE);
                }
            }
            setEnableAddRelation(Boolean.FALSE);
            setRelationList(getRelationList());
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
