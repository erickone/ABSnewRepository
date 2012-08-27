/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.controller;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.catalog.api.controller.CatalogLevelGenericControllerApi;
import com.abs.siif.catalog.entities.ConfigLevelGenericEntities;
import com.abs.siif.catalog.entities.TypeConfigCatalogEntities;
import com.abs.siif.catalog.management.ConfigLevelGenericManagement;
import com.abs.siif.catalog.management.TypeConfigCatalogManagement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author absvalenzuela
 */
@Scope("session")
@Controller("catalogLevelGenericController")
public class CatalogLevelGenericController extends SIIFControllerBase
        implements Serializable, CatalogLevelGenericControllerApi {

    private boolean disabledButtonDelete;

    private boolean disabledButtonSave;

    private boolean disabledButtonNew;

    private boolean disabledTextLongitud;

    private boolean disabledTextAcronym;

    private boolean disabledTextFillingChar;

    private boolean disabledPanelOptions;

    @Resource(name = "typeConfigCatalogManagement")
    private TypeConfigCatalogManagement itsConfigCatalogService;

    @Resource(name = "configLevelGenericManagement")
    private ConfigLevelGenericManagement itsConfigLevelGenericService;

    private List<Long> lstYears;

    private List<TypeConfigCatalogEntities> lstConfigCatalog;

    private List<ConfigLevelGenericEntities> lstConfigLevelGeneric;

    private ConfigLevelGenericEntities[] selectedConfigLevelGeneric;

    private ConfigLevelGenericEntities selectForEditConfigLevelGeneric;

    private Long descriptionId;

    private Integer yearUser;

    private Integer levelUser;

    private int iClaveManual;

    private boolean selectionMultiple;

    @Override
    public void init() {
        this.setDisabledButtonDelete(true);
        this.setDisabledButtonSave(true);

        this.setLstYears(createListYear());
        selectForEditConfigLevelGeneric = new ConfigLevelGenericEntities();
        descriptionId = null;
        selectionMultiple = false;

        yearUser = null;

        levelUser = null;

        this.setLstConfigCatalog(itsConfigCatalogService.getListTypeConfigCatalog());

        this.setLstConfigLevelGeneric(itsConfigLevelGenericService.getListLevelGeneric());
        this.setSelectedConfigLevelGeneric(null);
        this.setDisabledPanelOptions(true);
        this.setDisabledTextAcronym(true);
        this.setDisabledTextFillingChar(true);
        this.setDisabledTextLongitud(true);
    }

    private List<Long> createListYear() {
        List<Long> lstYearPlus = new ArrayList<Long>();

        Long year = Long.parseLong(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());

        for (long iIndex = year.longValue(); iIndex <= (year.longValue() + 20); iIndex++) {
            lstYearPlus.add(Long.valueOf(iIndex));
        }

        return lstYearPlus;
    }

    @Override
    public boolean getDisabledButtonDelete() {
        return disabledButtonDelete;
    }

    @Override
    public void setDisabledButtonDelete(boolean enable) {
        this.disabledButtonDelete = enable;
    }

    @Override
    public boolean getDisabledButtonNew() {
        return disabledButtonNew;
    }

    @Override
    public void setDisabledButtonNew(boolean enable) {
        this.disabledButtonNew = enable;
    }

    @Override
    public boolean getDisabledButtonSave() {
        return disabledButtonSave;
    }

    @Override
    public void setDisabledButtonSave(boolean enable) {
        this.disabledButtonSave = enable;
    }

    @Override
    public boolean getDisabledTextLongitud() {
        return this.disabledTextLongitud;
    }

    @Override
    public void setDisabledTextLongitud(boolean enable) {
        this.disabledTextLongitud = enable;
    }

    @Override
    public boolean getDisabledTextAcronym() {
        return this.disabledTextAcronym;
    }

    @Override
    public void setDisabledTextAcronym(boolean enable) {
        this.disabledTextAcronym = enable;
    }

    @Override
    public boolean getDisabledTextFillingChar() {
        return this.disabledTextFillingChar;
    }

    @Override
    public void setDisabledTextFillingChar(boolean enable) {
        this.disabledTextFillingChar = enable;
    }

    @Override
    public boolean getDisabledPanelOptions() {
        return this.disabledPanelOptions;
    }

    @Override
    public void setDisabledPanelOptions(boolean enable) {
        this.disabledPanelOptions = enable;
    }

    @Override
    public List<Long> getLstYears() {
        return this.lstYears;
    }

    @Override
    public void setLstYears(List<Long> years) {
        this.lstYears = years;
    }

    @Override
    public List<TypeConfigCatalogEntities> getLstConfigCatalog() {
        return this.lstConfigCatalog;
    }

    @Override
    public void setLstConfigCatalog(List<TypeConfigCatalogEntities> object) {
        this.lstConfigCatalog = object;
    }

    @Override
    public List<ConfigLevelGenericEntities> getLstConfigLevelGeneric() {
        return this.lstConfigLevelGeneric;
    }

    @Override
    public void setLstConfigLevelGeneric(List<ConfigLevelGenericEntities> object) {
        this.lstConfigLevelGeneric = object;
    }

    @Override
    public ConfigLevelGenericEntities[] getSelectedConfigLevelGeneric() {
        return this.selectedConfigLevelGeneric;
    }

    @Override
    public void setSelectedConfigLevelGeneric(ConfigLevelGenericEntities[] object) {
        this.selectedConfigLevelGeneric = object;

        if (object != null && object.length > 1) {
            selectionMultiple = true;
        } else {
            selectionMultiple = false;
        }

    }

    @Override
    public ConfigLevelGenericEntities getSelectForEditConfigLevelGeneric() {
        return this.selectForEditConfigLevelGeneric;
    }

    @Override
    public void setSelectForEditConfigLevelGeneric(ConfigLevelGenericEntities object) {
        this.selectForEditConfigLevelGeneric = object;
    }

    @Override
    public void onRowSelect(SelectEvent event) {

        if (!selectionMultiple) {
            ConfigLevelGenericEntities object = (ConfigLevelGenericEntities) event.getObject();
            this.setSelectForEditConfigLevelGeneric(object);

            this.descriptionId = this.selectForEditConfigLevelGeneric.getTypeConfigEntity().getTypeConfigId();
            this.yearUser = this.selectForEditConfigLevelGeneric.getYear().intValue();
            this.levelUser = this.selectForEditConfigLevelGeneric.getLevel();

            this.setDisabledButtonSave(false);
            this.setDisabledButtonNew(true);
            this.setDisabledPanelOptions(false);
            this.setDisabledTextAcronym(false);
            this.setDisabledTextFillingChar(false);
            this.setDisabledTextLongitud(false);
        } else {
            descriptionId = null;
            yearUser = null;
            levelUser = null;
        }
        this.setDisabledButtonDelete(false);
    }

    @Override
    public void onRowUnselect(UnselectEvent event) {
        this.setSelectForEditConfigLevelGeneric(null);
        this.setSelectedConfigLevelGeneric(null);
    }

    @Override
    public Long getDescriptionId() {
        return this.descriptionId;
    }

    @Override
    public void setDescriptionId(Long object) {
        this.descriptionId = object;
    }

    @Override
    public Integer getYearUser() {
        return this.yearUser;
    }

    @Override
    public void setYearUser(Integer object) {
        this.yearUser = object;
    }

    @Override
    public Integer getLevelUser() {
        return this.levelUser;
    }

    @Override
    public void setLevelUser(Integer object) {
        this.levelUser = object;
    }

    @Override
    public String navigateToMainMenu() {
        String page = "options";
        return page;
    }

    @Override
    public void newRowCatLevelGeneric() {
        this.setSelectForEditConfigLevelGeneric(null);
        this.setSelectedConfigLevelGeneric(null);

        init();
        this.selectForEditConfigLevelGeneric = new ConfigLevelGenericEntities();

        this.setDisabledButtonDelete(true);
        this.setDisabledButtonSave(false);
        this.setDisabledPanelOptions(false);
        this.setDisabledTextAcronym(false);
        this.setDisabledTextFillingChar(false);
        this.setDisabledTextLongitud(false);

    }

    @Override
    public void addRowConfig() {

        this.setDisabledButtonDelete(true);
        this.setDisabledButtonSave(true);
        this.setDisabledButtonNew(false);

        this.setDisabledTextAcronym(true);
        this.setDisabledTextFillingChar(true);
        this.setDisabledTextLongitud(true);
        this.setSelectForEditConfigLevelGeneric(new ConfigLevelGenericEntities());
        this.setSelectedConfigLevelGeneric(null);

        this.setDisabledPanelOptions(true);
        this.setDisabledTextAcronym(true);
        this.setDisabledTextFillingChar(true);
        this.setDisabledTextLongitud(true);
    }

    @Override
    public void removeRowConfig() {
        this.setDisabledButtonDelete(true);
        this.setDisabledButtonSave(true);

        this.setDisabledPanelOptions(true);
        this.setDisabledTextAcronym(true);
        this.setDisabledTextFillingChar(true);
        this.setDisabledTextLongitud(true);
    }

    @Override
    public void setDisableTextPage(Long iElement){
        switch (iElement.intValue()){
            case 1: this.setDisabledTextLongitud(!this.getSelectForEditConfigLevelGeneric().isLongitudPorNivel());
                break;
            case 2: this.setDisabledTextAcronym(!this.getSelectForEditConfigLevelGeneric().isConsecutivoPorNivel());
                break;
            case 3: this.setDisabledTextFillingChar(!this.getSelectForEditConfigLevelGeneric().isAutocompletar() );
                break;
        }
    }
    
    
}
