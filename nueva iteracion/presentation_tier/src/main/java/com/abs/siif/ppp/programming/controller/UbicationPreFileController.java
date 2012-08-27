/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  DraftManagementController
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.ppp.programming.api.controller.UbicationPreFileControllerApi;
import com.abs.siif.ppp.programming.uihelpers.UbicationPeopleHelper;
import com.abs.siif.programming.dto.RegionalClassifierDTO;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.management.UbicationManagement;
import com.abs.siif.support.FormatNumber;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Clase que se encarge de proporcionar los datos que refieren a la Ubicación de
 * la preficha
 *
 * @version 1.0 30 Mayo 2012
 * @author Israel Ruiz, Francisco Luna
 */
@Controller("ubicationPreFileController")
@Scope("session")
public class UbicationPreFileController extends SIIFControllerBase
        implements Serializable, UbicationPreFileControllerApi {

    @Resource(name = "ubicationManagement")
    private transient UbicationManagement ubicationManagement;
    private Long itsSelectedAmbto = new Long(0);
    private List<RegionalLevelClassifierEntity> itsItemsAmbitoCbo;
    private String itsMuniTotal;
    private String itsTotalpeople;
    private String itsTotalWomen;
    private String itsTotalMen;
    private String itsRepPorc;
    private List<RegionalClassifierEntity> itsUbications;
    private String tilteAmbito;
    private Long itsSelectedUbication;
    private List<String> itsSelectedUbications;
    private transient List<UbicationPeopleHelper> ubicationPeople;
    private transient List<UbicationPeopleHelper> ubicationPeopleBack;
    private boolean visibleList;
    private List<RegionalClassifierEntity> regionalEntities;
    private double total = 0.0;
    private boolean btnSaveVisibility;
    private boolean btnSrvSave = false;
    private String itsRepInePorc;
    private Long theirInvPreFileId;
    private Long tempId = new Long(0);
    private Long currentId = new Long(0);
    private Long itsIdDraftProject;
    private String titleAmbSelected;
    private String lblSelected;

    @Override
    public void initUbication() {

        if (theirInvPreFileId == null
                && itsIdDraftProject == null) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.messageError"),
                    this.getMessage("ppp.progr.messageError"));
        } else {
            currentId = theirInvPreFileId != null ? theirInvPreFileId
                    : itsIdDraftProject;
        }
        //TODO Falta hacer la consulta inicial para cuando haya datos
        //if (!tempId.equals(currentId)) {
        //  tempId = currentId;
        visibleList = false;
        ubicationPeopleBack = new ArrayList<UbicationPeopleHelper>();
        setItsItemsAmbitoCbo(new ArrayList<RegionalLevelClassifierEntity>());
        setUbicationScope(ubicationManagement.getUbicationScope());
        setItsUbications(new ArrayList<RegionalClassifierEntity>());
        setItsSelectedUbications(new ArrayList<String>());
        setUbicationPeople(new ArrayList<UbicationPeopleHelper>());
        enableDisableBtnSave();
        clearBottomTable();
        changeListenerCboUbication();
        //}
    }

    @Override
    public void initAmbiCbo() {
        setItsItemsAmbitoCbo(new ArrayList<RegionalLevelClassifierEntity>());
        setUbicationScope(ubicationManagement.getUbicationScope());
        checkSavedInfo();

    }

    @Override
    public void selectedListenerCboUbication() {
        //solo para actualizar el dato a nivel controller
    }

    @Override
    public void cleanFromAnteProj() {
        itsSelectedAmbto = new Long(0);
        theirInvPreFileId = null;
        visibleList = false;
        ubicationPeopleBack = new ArrayList<UbicationPeopleHelper>();
        setItsItemsAmbitoCbo(new ArrayList<RegionalLevelClassifierEntity>());
        setUbicationScope(ubicationManagement.getUbicationScope());
        setItsUbications(new ArrayList<RegionalClassifierEntity>());
        setItsSelectedUbications(new ArrayList<String>());
        setUbicationPeople(new ArrayList<UbicationPeopleHelper>());
        enableDisableBtnSave();
        clearBottomTable();
        //changeListenerCboUbication();
    }

    @Override
    public void cleanFromPreFicha() {
        itsSelectedAmbto = new Long(0);
        itsIdDraftProject = null;
        visibleList = false;
        ubicationPeopleBack = new ArrayList<UbicationPeopleHelper>();
        setItsItemsAmbitoCbo(new ArrayList<RegionalLevelClassifierEntity>());
        setUbicationScope(ubicationManagement.getUbicationScope());
        setItsUbications(new ArrayList<RegionalClassifierEntity>());
        setItsSelectedUbications(new ArrayList<String>());
        setUbicationPeople(new ArrayList<UbicationPeopleHelper>());
        enableDisableBtnSave();
        clearBottomTable();
        //changeListenerCboUbication();
    }

    /**
     * @return the prefileId
     */
    @Override
    public Long getTheirInvPreFileId() {
        return theirInvPreFileId;
    }

    /**
     * @param theirInvPreFileId the preFileId to set
     */
    @Override
    public void setTheirInvPreFileId(Long theirInvPreFileId) {
        this.theirInvPreFileId = theirInvPreFileId;
    }

    /**
     * @return the titleAmbSelected
     */
    @Override
    public String getTitleAmbSelected() {
        return titleAmbSelected;
    }

    /**
     * @param titleAmbSelected the titleAmbSelected to set
     */
    @Override
    public void setTitleAmbSelected(Long id) {
        titleAmbSelected = "";
        if (id == 2) {
            titleAmbSelected = this.getMessage("ppp.progr.Ubication.TitleAmbSelected.States");
            setLblSelected(this.getMessage("ppp.progr.Ubication.Selected.States"));
        } else if (id == 3) {
            titleAmbSelected = this.getMessage("ppp.progr.Ubication.TitleAmbSelected.Regions");
            setLblSelected(this.getMessage("ppp.progr.Ubication.Selected.Regions"));
        } else if (id == 4) {
            titleAmbSelected = this.getMessage("ppp.progr.Ubication.TitleAmbSelected.Mun");
            setLblSelected(this.getMessage("ppp.progr.municipiosSelII"));
        }
    }

    /**
     * @return the lblSelected
     */
    @Override
    public String getLblSelected() {
        return lblSelected;
    }

    /**
     * @param lblSelected the lblSelected to set
     */
    @Override
    public void setLblSelected(String lblSelected) {
        this.lblSelected = lblSelected;
    }

    /**
     * @return the itsSelectedAmbto
     */
    @Override
    public Long getItsSelectedAmbto() {
        return itsSelectedAmbto;
    }

    /**
     * @param itsSelectedAmbto the itsSelectedAmbto to set
     */
    @Override
    public void setItsSelectedAmbto(Long itsSelectedAmbto) {
        this.itsSelectedAmbto = itsSelectedAmbto;
    }

    /**
     * @return the itsItemsAmbitoCbo
     */
    @Override
    public List<RegionalLevelClassifierEntity> getItsItemsAmbitoCbo() {
        return itsItemsAmbitoCbo;
    }

    /**
     * @param itsItemsAmbitoCbo the itsItemsAmbitoCbo to set
     */
    @Override
    public void setItsItemsAmbitoCbo(List<RegionalLevelClassifierEntity> itsItemsAmbitoCbo) {
        this.itsItemsAmbitoCbo = itsItemsAmbitoCbo;
    }

    /**
     * @return the itsMuniTotal
     */
    @Override
    public String getItsMuniTotal() {
        return itsMuniTotal;
    }

    /**
     * @param itsMuniTotal the itsMuniTotal to set
     */
    @Override
    public void setItsMuniTotal(String itsMuniTotal) {
        itsMuniTotal = FormatNumber.formatNumber(itsMuniTotal);
        this.itsMuniTotal = itsMuniTotal;
    }

    /**
     * @return the itsTotalpeople
     */
    @Override
    public String getItsTotalpeople() {
        return itsTotalpeople;
    }

    /**
     * @param itsTotalpeople the itsTotalpeople to set
     */
    @Override
    public void setItsTotalpeople(String itsTotalpeople) {
        itsTotalpeople = FormatNumber.formatNumber(itsTotalpeople);
        this.itsTotalpeople = itsTotalpeople;
    }

    /**
     * @return the itsTotalWomen
     */
    @Override
    public String getItsTotalWomen() {
        return itsTotalWomen;
    }

    /**
     * @param itsTotalWomen the itsTotalWomen to set
     */
    @Override
    public void setItsTotalWomen(String itsTotalWomen) {
        itsTotalWomen = FormatNumber.formatNumber(itsTotalWomen);
        this.itsTotalWomen = itsTotalWomen;
    }

    /**
     * @return the itsTotalMen
     */
    @Override
    public String getItsTotalMen() {
        return itsTotalMen;
    }

    /**
     * @param itsTotalMen the itsTotalMen to set
     */
    @Override
    public void setItsTotalMen(String itsTotalMen) {
        itsTotalMen = FormatNumber.formatNumber(itsTotalMen);
        this.itsTotalMen = itsTotalMen;
    }

    /**
     * @return the itsRepPorc
     */
    @Override
    public String getItsRepPorc() {
        return itsRepPorc;
    }

    /**
     * @param itsRepPorc the itsRepPorc to set
     */
    @Override
    public void setItsRepPorc(String itsRepPorc) {
        this.itsRepPorc = itsRepPorc;
    }

    /**
     * @return the itsUbications
     */
    @Override
    public List<RegionalClassifierEntity> getItsUbications() {
        return itsUbications;
    }

    /**
     * @param itsUbications the itsUbications to set
     */
    @Override
    public void setItsUbications(List<RegionalClassifierEntity> itsUbications) {
        this.itsUbications = itsUbications;
    }

    /**
     * @return the tilteAmbito
     */
    @Override
    public String getTilteAmbito() {
        return tilteAmbito;
    }

    /**
     * @param tilteAmbito the tilteAmbito to set
     */
    @Override
    public void setTilteAmbito(String tilteAmbito) {
        this.tilteAmbito = tilteAmbito;
    }

    /**
     * @return the itsSelectedUbication
     */
    @Override
    public Long getItsSelectedUbication() {
        return itsSelectedUbication;
    }

    /**
     * @param itsSelectedUbication the itsSelectedUbication to set
     */
    @Override
    public void setItsSelectedUbication(Long itsSelectedUbication) {
        this.itsSelectedUbication = itsSelectedUbication;
    }

    /**
     * @return the itsSelectedUbications
     */
    @Override
    public List<String> getItsSelectedUbications() {
        return itsSelectedUbications;
    }

    /**
     * @param itsSelectedUbications the itsSelectedUbications to set
     */
    @Override
    public void setItsSelectedUbications(List<String> itsSelectedUbications) {
        this.itsSelectedUbications = itsSelectedUbications;
    }

    /**
     * @return the ubicationPeople
     */
    @Override
    public List<UbicationPeopleHelper> getUbicationPeople() {
        return ubicationPeople;
    }

    /**
     * @param ubicationPeople the ubicationPeople to set
     */
    @Override
    public void setUbicationPeople(List<UbicationPeopleHelper> ubicationPeople) {
        this.ubicationPeople = ubicationPeople;
    }

    @Override
    public void changeListenerCboUbication() {
        ubicationPeople.clear();
        itsSelectedUbications.clear();
        List<Long> ubicationsSelected = new ArrayList<Long>();
        RegionalLevelClassifierEntity entityToFind = new RegionalLevelClassifierEntity();
        InvPreFileEntity preFileEntity = new InvPreFileEntity();
        DraftProjectEntity drafProjectEntity = new DraftProjectEntity();

        //Valida la selección del combo de nivel de región
        //if (itsSelectedAmbto > 0) {
            checkSavedInfo();
        //}
        entityToFind.setRegionalLevelClassifierId(itsSelectedAmbto);
        //entityToFind = SearchList.findObjectList(getItsUbications(), entityToFind);
        getItsUbications().clear();
        getUbicationPeople().clear();
        regionalEntities =
                ubicationManagement.getUbicationsByScope(entityToFind);
        if (this.currentId != null) {
            if (getTheirInvPreFileId() != null
                    && getTheirInvPreFileId() > 0) {

                preFileEntity.setInvPreFileId(getTheirInvPreFileId());
                ubicationsSelected = ubicationManagement.getUbicationsSelected(preFileEntity);

            } else if (itsIdDraftProject != null
                    && itsIdDraftProject > 0) {
                currentId = itsIdDraftProject;
                drafProjectEntity.setDraftProjectId(currentId);
                ubicationsSelected = ubicationManagement.getDraftProjectUbications(drafProjectEntity);
            }

            for (Long saved : ubicationsSelected) {
                for (RegionalClassifierEntity founded : regionalEntities) {
                    if (saved.equals(founded.getRegionalClassifierId())) {
                        itsSelectedUbications.add(founded.getRegionalClassifierId().toString());
                        break;
                    }
                }
            }
        }
        getItsUbications().addAll(regionalEntities);

        UbicationPeopleHelper ubPeople;
        for (RegionalClassifierEntity entity : regionalEntities) {
            ubPeople = new UbicationPeopleHelper();
            ubPeople.setUbication(entity);
            ubicationPeople.add(ubPeople);
        }

        if (itsSelectedAmbto > 0) {
            visibleList = true;
        } else {
            visibleList = false;
            clearBottomTable();
        }
        for (UbicationPeopleHelper ubPeople1 : this.ubicationPeople) {
            int men = ubPeople1.getUbication().getRegionalClassifierMenNumber();
            int women = ubPeople1.getUbication().
                    getRegionalClassifierWomenNumber();
            if (total != 0) {
                double rep = ((men + women) / total) * 100;
                BigDecimal procent = new BigDecimal(String.format("%.2f", rep));
                ubPeople1.setDistribution(procent.toString());
            }
        }
        ubicationPeopleBack.clear();
        ubicationPeopleBack.addAll(ubicationPeople);
        ubicationPeople.clear();
        clearBottomTable();
        changeListenerLstUbication();
        setTitleAmbSelected(itsSelectedAmbto);
    }

    @Override
    public void changeListenerLstUbication() {
        RegionalClassifierEntity entityToFind;
        InvPreFileEntity invPreFile = null;
        ubicationPeople.clear();
        UbicationPeopleHelper ubHel = null;
        Map<Long, List<RegionalClassifierDTO>> savedUbication = new java.util.HashMap<Long, List<RegionalClassifierDTO>>();

        total = 0.0;
        int totalMen = 0;
        int totalWomen = 0;
        int totalMenAux = 0;
        int totalWomenAux = 0;

        
            if (itsSelectedUbications.size() >= 0 && this.currentId != null) {

                if (getTheirInvPreFileId() != null
                        && getTheirInvPreFileId() > 0) {

                    invPreFile = new InvPreFileEntity();
                    invPreFile.setInvPreFileId(getTheirInvPreFileId());

                    savedUbication =
                            ubicationManagement.saveInvPreFileReg(itsSelectedUbications, invPreFile);

                } else if (itsIdDraftProject != null
                        && itsIdDraftProject > 0) {
                    
                    if(getBtnSrvSave()){
                        
                         addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.security.errorUbication"),
                            this.getMessage("ppp.security.errorUbication"));
                        return; // si esta deshabilitado no realiza el guardado de la información 
                        /**
                         * Este procedimiento se hace en base a configuración de seguridad
                         * 
                         */
                    }
                    
                    currentId = itsIdDraftProject;
                    savedUbication =
                            ubicationManagement.saveDraftProjectUbication(itsSelectedUbications, currentId);
                }




                for (String id : itsSelectedUbications) {
                    List<RegionalClassifierDTO> ubiList = savedUbication.get(Long.valueOf(id));
                    for (RegionalClassifierDTO dto : ubiList) {
                        totalMen = 0;
                        totalWomen = 0;
                        ubHel = new UbicationPeopleHelper();
                        ubHel.setUbication(dto.getUbication());
                        totalMen = dto.getnumhombres();
                        totalWomen = dto.getnummujeres();
                        ubHel.setTotalPeople(String.valueOf((totalMen + totalWomen)));
                        ubicationPeople.add(ubHel);
                        totalMenAux = totalMen + totalMenAux;
                        totalWomenAux = totalWomen + totalWomenAux;
                    }
                }


                setItsMuniTotal(String.valueOf(itsSelectedUbications.size()));
                setItsTotalWomen(String.valueOf(totalWomenAux));
                setItsTotalMen(String.valueOf(totalMenAux));
                setItsTotalpeople(String.valueOf(totalWomenAux + totalMenAux));
                total = totalWomenAux + totalMenAux;
                itsRepInePorc = "100";

                for (UbicationPeopleHelper ubPeople1 : this.ubicationPeople) {
                    int men = ubPeople1.getUbication().getRegionalClassifierMenNumber();
                    int women = ubPeople1.getUbication().
                            getRegionalClassifierWomenNumber();
                    double rep = ((men + women) / total) * 100;
                    BigDecimal porcent = new BigDecimal(String.format("%.2f", rep));
                    ubPeople1.setInegiDistribution(porcent.toString());
                    //ubPeople1.setDistribution(procent.toString());
                }
            } else {
                clearBottomTable();
            }
        
    }

    @Override
    public void calculateTotal() {
    }

    /**
     * @return the visibleList
     */
    @Override
    public boolean isVisibleList() {
        return visibleList;
    }

    /**
     * @param visibleList the visibleList to set
     */
    @Override
    public void setVisibleList(boolean visibleList) {
        this.visibleList = visibleList;
    }

    /**
     * @return the BtnSaveVisibility
     */
    @Override
    public boolean getBtnSaveVisibility() {
        return btnSaveVisibility;
    }

    /**
     * @param BtnSaveVisibility
     */
    @Override
    public void setBtnSaveVisibility(boolean btnVisibility) {
        this.btnSaveVisibility = btnVisibility;
    }

    private void setUbicationScope(List<RegionalLevelClassifierEntity> ubicationScope) {
        getItsItemsAmbitoCbo().addAll(ubicationScope);
    }

    @Override
    public String getItsRepInePorc() {
        return itsRepInePorc;
    }

    @Override
    public void setItsRepInePorc(String itsRepInePorc) {
        this.itsRepInePorc = itsRepInePorc;
    }

    //Method that enable or disable button save
    @Override
    public void enableDisableBtnSave() {
        if (ubicationPeople.size() > 0) {
            final UbicationPeopleHelper ubication = ubicationPeople.get(0);
            final String totalPeople = ubication.getTotalPeople();
            if (totalPeople != null) {
                if (!"".equals(totalPeople)) {
                    btnSaveVisibility = false;
                }
            }
        } else {
            btnSaveVisibility = true;
        }
    }

    @Override
    public void clearBottomTable() {
        itsMuniTotal = "";
        itsTotalpeople = "";
        itsTotalWomen = "";
        itsTotalMen = "";
        itsRepInePorc = "";
        itsRepPorc = "";
    }

    /**
     * @return the itsIdDraftProject
     */
    @Override
    public Long getItsIdDraftProject() {
        return itsIdDraftProject;
    }

    /**
     * @param itsIdDraftProject the itsIdDraftProject to set
     */
    @Override
    public void setItsIdDraftProject(Long itsIdDraftProject) {
        this.itsIdDraftProject = itsIdDraftProject;
    }

    @Override
    public boolean getBtnSrvSave() {
        return btnSrvSave;
    }

    @Override
    public void setBtnSrvSave(boolean btnSrvSave) {
        this.btnSrvSave = btnSrvSave;
    }

    /**
     * Método que recibe un Id de alguna región guardada y retorna el nivel del
     * padre
     */
    private void checkSavedInfo() {

        if (this.currentId != null) {

            if (getTheirInvPreFileId() != null
                    && getTheirInvPreFileId() > 0) {

                InvPreFileEntity preFileEntity = new InvPreFileEntity();
                preFileEntity.setInvPreFileId(getTheirInvPreFileId());
                List<Long> ubicationsSelected = ubicationManagement.getUbicationsSelected(preFileEntity);
                if (ubicationsSelected.size() > 0) {
                    Long elem = ubicationsSelected.get(0);
                    Long regionalClassifier = ubicationManagement.findFather(elem);
                    itsSelectedAmbto = regionalClassifier;
                }

            } else if (itsIdDraftProject != null && itsIdDraftProject > 0) {
                currentId = itsIdDraftProject;
                DraftProjectEntity drafProjectEntity = new DraftProjectEntity();
                drafProjectEntity.setDraftProjectId(currentId);
                List<Long> ubicationsSelected = ubicationManagement.getDraftProjectUbications(drafProjectEntity);
                if (ubicationsSelected.size() > 0) {
                    Long elem = ubicationsSelected.get(0);
                    Long regionalClassifier = ubicationManagement.findFather(elem);
                    itsSelectedAmbto = regionalClassifier;
                }
            }
        }
    }
}
