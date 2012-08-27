/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.api.controller;

import com.abs.siif.catalog.entities.ConfigLevelGenericEntities;
import com.abs.siif.catalog.entities.TypeConfigCatalogEntities;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author absvalenzuela
 */
public interface CatalogLevelGenericControllerApi {
    
    public void init();
    
    
    /*** Metodos para desabilitar los botones y las opciones correspondientes ***/
    public boolean getDisabledButtonDelete();

    public void setDisabledButtonDelete(boolean enable);
    
    public boolean getDisabledButtonNew();

    public void setDisabledButtonNew(boolean enable);

    public boolean getDisabledButtonSave();

    public void setDisabledButtonSave(boolean enable);
    
    public boolean getDisabledTextLongitud();

    public void setDisabledTextLongitud(boolean enable);
    
    public boolean getDisabledTextAcronym();

    public void setDisabledTextAcronym(boolean enable);
    
    public boolean getDisabledTextFillingChar();

    public void setDisabledTextFillingChar(boolean enable);
    
    public boolean getDisabledPanelOptions();

    public void setDisabledPanelOptions(boolean enable);
    
    public List<Long> getLstYears();
    
    public void setLstYears(List<Long> years);
    
    public List<TypeConfigCatalogEntities> getLstConfigCatalog();
    
    public void setLstConfigCatalog(List<TypeConfigCatalogEntities> object);
    
    public List<ConfigLevelGenericEntities> getLstConfigLevelGeneric();
    
    public void setLstConfigLevelGeneric(List<ConfigLevelGenericEntities> object);
    
    public ConfigLevelGenericEntities[] getSelectedConfigLevelGeneric();
    
    public void setSelectedConfigLevelGeneric(ConfigLevelGenericEntities[] object);
    
    public ConfigLevelGenericEntities getSelectForEditConfigLevelGeneric();
    
    public void setSelectForEditConfigLevelGeneric(ConfigLevelGenericEntities object);
    
    public void onRowSelect(SelectEvent event);
    
    public void onRowUnselect(UnselectEvent event);
    
    public Long getDescriptionId();
    
    public void setDescriptionId(Long object);
    
    public Integer getYearUser();
    
    public void setYearUser(Integer object);
    
    public Integer getLevelUser();
    
    public void setLevelUser(Integer object);
    
    public void newRowCatLevelGeneric();
    
    public void addRowConfig();

    public void removeRowConfig();
    
    public String navigateToMainMenu();    
    
    public void setDisableTextPage(Long iElement);
    
}
