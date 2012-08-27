package com.abs.siif.ppp.programming.controller;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.UbicationControllerApi;
import com.abs.siif.ppp.programming.uihelpers.UbicationPeopleHelper;
import com.abs.siif.programming.management.UbicationManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Clase que se encarge de proporcionar los datos que refieren a la Ubicación
 *
 * @author Israel Ruiz
 */
@Controller("ubicationController")
@Scope("session")
public class UbicationController implements Serializable, UbicationControllerApi {

    @Resource(name = "ubicationManagement")
    private transient UbicationManagement ubicationManagement;
    @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi darftProject;
    private Long itsSelectedAmbto;
    private List<RegionalLevelClassifierEntity> itsItemsAmbitoCbo;
    private String itsMuniTotal;
    private String itsTotalpeople;
    private String itsTotalWomen;
    private String itsTotalMen;
    private String itsRepPorc;
    private List<RegionalClassifierEntity> itsUbications;
    private String tilteAmbito;
    private String itsSelectedUbication;
    private List<Long> itsSelectedUbications;
    private List<String> itsremoveUbications;
    private Set<Long> ubicationsClone;
    private List<UbicationPeopleHelper> ubicationPeople;
    private List<UbicationPeopleHelper> ubicationPeopleBack;
    private boolean visibleList;
    private List<RegionalClassifierEntity> regionalEntities;
    private double total = 0.0;
    private boolean btnSaveVisibility;
    private Long idDraftProject;
    private Long idDraftProjectCurrent;

    public UbicationController() {
    }

    @Override
    public void initUbication() {

        idDraftProject = darftProject.getTheirCurrentDraftProjectId();

        if (idDraftProject == null || idDraftProjectCurrent == null
                || !idDraftProjectCurrent.equals(idDraftProject)) {
            idDraftProjectCurrent = idDraftProject;
            visibleList = false;
            btnSaveVisibility = true;
            //TODO
            //Obtener de la BD los datos seleccionados previamente
            ubicationPeopleBack = new ArrayList<UbicationPeopleHelper>();
            setItsItemsAmbitoCbo(new ArrayList<RegionalLevelClassifierEntity>());
            setUbicationScope(ubicationManagement.getUbicationScope());
            itsremoveUbications = new ArrayList<String>();
            setItsUbications(new ArrayList<RegionalClassifierEntity>());
            setItsSelectedUbications(new ArrayList<Long>());
            setUbicationPeople(new ArrayList<UbicationPeopleHelper>());
            itsMuniTotal = "0";
            itsTotalpeople = "0";
            itsTotalWomen = "0";
            itsTotalMen = "0";
            itsRepPorc = "0";

        }
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
    public String getItsSelectedUbication() {
        return itsSelectedUbication;
    }

    /**
     * @param itsSelectedUbication the itsSelectedUbication to set
     */
    @Override
    public void setItsSelectedUbication(String itsSelectedUbication) {
        this.itsSelectedUbication = itsSelectedUbication;
    }

    /**
     * @return the itsSelectedUbications
     */
    @Override
    public List<Long> getItsSelectedUbications() {
        return itsSelectedUbications;
    }

    /**
     * @param itsSelectedUbications the itsSelectedUbications to set
     */
    @Override
    public void setItsSelectedUbications(List<Long> itsSelectedUbications) {
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
        RegionalLevelClassifierEntity entityToFind = new RegionalLevelClassifierEntity();
        entityToFind.setRegionalLevelClassifierId(itsSelectedAmbto);
        //entityToFind = SearchList.findObjectList(getItsUbications(), entityToFind);
        getItsUbications().clear();
        getUbicationPeople().clear();

        regionalEntities =
                ubicationManagement.getUbicationsByScope(entityToFind);
        getItsUbications().addAll(regionalEntities);
        itsMuniTotal = "0"; //+ regionalEntities.size();

        int totalMen = 0;
        int totalWomen = 0;
        total = 0.0;
        itsRepPorc = "0";
        itsSelectedUbications.clear();
        UbicationPeopleHelper ubPeople;
        for (RegionalClassifierEntity entity : regionalEntities) {
            totalWomen = 0;
            totalMen = 0;
            totalMen = entity.getRegionalClassifierMenNumber() + totalMen;
            totalWomen = entity.getRegionalClassifierWomenNumber() + totalWomen;
            ubPeople = new UbicationPeopleHelper();
            ubPeople.setUbication(entity);
            ubPeople.setTotalPeople("" + (totalWomen + totalMen));
            ubicationPeople.add(ubPeople);
//            //Agrega las llaves a la lista para que salgan seleccionados
//            //itsSelectedUbications.add("" + entity.getRegionalClassifierId());
        }
        ubicationsClone = new HashSet<Long>(itsSelectedUbications);
        total = totalMen + totalWomen;
        itsTotalpeople = "0";// + (totalMen + totalWomen);
        itsTotalWomen = "0"; //+ totalWomen;
        itsTotalMen = "0"; // + totalMen;
        visibleList = true;

        for (UbicationPeopleHelper ubPeople1 : this.ubicationPeople) {
            int men = ubPeople1.getUbication().getRegionalClassifierMenNumber();
            int women = ubPeople1.getUbication().
                    getRegionalClassifierWomenNumber();
            double rep = ((men + women) / total) * 100;
            BigDecimal procent = new BigDecimal(String.format("%.2f", rep));
            ubPeople1.setDistribution(procent.toString());
        }
        ubicationPeopleBack.clear();
        ubicationPeopleBack.addAll(ubicationPeople);
        ubicationPeople.clear();
    }

    @Override
    public void changeListenerLstUbication() {

        RegionalClassifierEntity entityToFind;
        ubicationPeople.clear();
        UbicationPeopleHelper ubHel = null;
        total = 0.0;
        int totalMen = 0;
        int totalWomen = 0;
        int totalMenAux = 0;
        int totalWomenAux = 0;
        itsRepPorc = "100";
        for (Long id : itsSelectedUbications) {
            totalMen = 0;
            totalWomen = 0;
            entityToFind = new RegionalClassifierEntity();
            ubHel = new UbicationPeopleHelper();
            entityToFind.setRegionalClassifierId(id);
            ubHel.setUbication(entityToFind);
            //entityToFind = ubicationManagement.getRegionalClassiById(entityToFind);
            //ubHel.setUbication(entityToFind);

            ubHel = SearchList.findObjectList(ubicationPeopleBack, ubHel);
            if (ubHel != null) {

                entityToFind = ubHel.getUbication();
                totalMen = entityToFind.getRegionalClassifierMenNumber()
                        + totalMen;
                totalWomen = entityToFind.getRegionalClassifierWomenNumber()
                        + totalWomen;
                ubHel.setTotalPeople("" + (totalMen + totalWomen));
                ubicationPeople.add(ubHel);
                totalMenAux = totalMen + totalMenAux;
                totalWomenAux = totalWomen + totalWomenAux;
            }

        }
        itsMuniTotal = "" + ubicationPeople.size();
        itsTotalWomen = "" + totalWomenAux;
        itsTotalMen = "" + totalMenAux;
        itsTotalpeople = "" + (totalWomenAux + totalMenAux);
        total = totalWomenAux + totalMenAux;

        for (UbicationPeopleHelper ubPeople1 : this.ubicationPeople) {
            int men = ubPeople1.getUbication().getRegionalClassifierMenNumber();
            int women = ubPeople1.getUbication().
                    getRegionalClassifierWomenNumber();
            double rep = ((men + women) / total) * 100;
            BigDecimal procent = new BigDecimal(String.format("%.2f", rep));
            ubPeople1.setDistribution(procent.toString());
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

    /**
     * Method that
     */
    @Override
    public void saveUbications() {
    }

    private void setUbicationScope(List<RegionalLevelClassifierEntity> ubicationScope) {
        getItsItemsAmbitoCbo().addAll(ubicationScope);
        /*
         *
         * RegionalLevelClassifierEntity regionLevel = new
         * RegionalLevelClassifierEntity();
         * regionLevel.setRegionalLevelClassifierDescription("ESTADO");
         * regionLevel.setRegionalLevelClassifierId("1");
         * this.getItsItemsAmbitoCbo().add(regionLevel);
         * RegionalLevelClassifierEntity regionLevel1 = new
         * RegionalLevelClassifierEntity();
         * regionLevel1.setRegionalLevelClassifierDescription("MUNICIPIOS");
         * regionLevel1.setRegionalLevelClassifierId("2");
         * this.getItsItemsAmbitoCbo().add(regionLevel1);
         *
         */
    }

    /**
     * @return the idDraftProject
     */
    @Override
    public Long getIdDraftProject() {
        return idDraftProject;
    }

    /**
     * @param idDraftProject the idDraftProject to set
     */
    @Override
    public void setIdDraftProject(Long idDraftProject) {
        this.idDraftProject = idDraftProject;
    }
    
}
