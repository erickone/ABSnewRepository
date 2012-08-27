package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.planning.api.controller.OptionsControllerApi;
import com.abs.siif.programming.dto.UrlConnectionReportDTO;
import com.abs.siif.security.entities.ProfileEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author abs71
 */
@Scope("session")
@Controller("optionsController")
public class OptionsController extends SIIFControllerBase implements Serializable, OptionsControllerApi {

    private String year = "2013";
    private List<String> theirYears;
    private String href = "#";
    private boolean programinmain = false;
    private boolean objective = false;
    private boolean institutionalPlanHeader = false;
    private boolean objectiveLevelDetails = false;
    private boolean regionalPlan = false;
    private boolean draftProjectSearch = false;
    private boolean invPrfeFileSearch = false;
    private boolean invPreFileManagement = false;
    private boolean configurationCatalog = false;
    private boolean CeilingBudget = false;
    private boolean reportCeilingBudget = false;

    @Override
    public void init() {
        //Coloca el año en la session;
        SIIFContextBase.setParameterSession(SessionKeyEnum.YEAR, year);
        this.optionsInit();
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public List<String> getYearsList() {
        Long dateLong = System.currentTimeMillis();
        Date date = new Date(dateLong);
        int year = date.getYear() + 1900;
        theirYears = new ArrayList<String>();
        for (int i = 1; i < 7; i++) {
            theirYears.add(String.valueOf(year));
            year += 1;
        }
        return theirYears;
    }

    @Override
    public void setYearsLits(List<String> aYearsList) {
        this.theirYears = aYearsList;
    }

    @Override
    public void changeListenerCboYear() {
    }

    @Override
    public void programminClick() {
    }

    @Override
    public void optionsInit() {
        programinmain = false;
        objective = false;
        institutionalPlanHeader = false;
        objectiveLevelDetails = false;
        regionalPlan = false;
        draftProjectSearch = false;
        invPrfeFileSearch = false;
        invPreFileManagement = false;
        CeilingBudget = false;
        configurationCatalog = false;
        reportCeilingBudget = false;

        ProfileEntity myProfile = getRole();

        UrlConnectionReportDTO urlReporte = (UrlConnectionReportDTO) SIIFContextBase.getAppContext().getBean("urlreporte");

        SIIFContextBase.setParamContext(KeyContextEnum.URLSERVERREPORT, urlReporte);

        if (myProfile.getProfileDescription().toUpperCase().equals("Capturador".toUpperCase())
                || myProfile.getProfileDescription().toUpperCase().equals("Analista de Planeación / Valida Seplan".toUpperCase())) {
            objective = true;
            institutionalPlanHeader = true;
            objectiveLevelDetails = true;
            regionalPlan = true;
            configurationCatalog = true;
            CeilingBudget = true;
            reportCeilingBudget = true;
        } else if (myProfile.getProfileDescription().toUpperCase().equals("Enlace/Administrador POA".toUpperCase())) {
            objective = true;
            objectiveLevelDetails = true;
            regionalPlan = true;
            configurationCatalog = true;
            CeilingBudget = true;
        } else if (myProfile.getProfileDescription().toUpperCase().equals("Administrador SEPLAN".toUpperCase())) {
            configurationCatalog = true;
            CeilingBudget = true;
            reportCeilingBudget = true;
        }else if (myProfile.getProfileDescription().toUpperCase().equals("Analista Financiero / Valida Sefin".toUpperCase())) {
            objective = true;
            institutionalPlanHeader = true;
            objectiveLevelDetails = true;
            regionalPlan = true;
            configurationCatalog = true;
            CeilingBudget = true;
        }
    }

    @Override
    public boolean getCeilingBudget() {
        return CeilingBudget;
    }

    @Override
    public void setCeilingBudget(boolean CeilingBudget) {
        this.CeilingBudget = CeilingBudget;
    }

    @Override
    public boolean getConfigurationCatalog() {
        return configurationCatalog;
    }

    @Override
    public void setConfigurationCatalog(boolean configurationCatalog) {
        this.configurationCatalog = configurationCatalog;
    }

    @Override
    public boolean isDraftProjectSearch() {
        return draftProjectSearch;
    }

    @Override
    public void setDraftProjectSearch(boolean draftProjectSearch) {
        this.draftProjectSearch = draftProjectSearch;
    }

    @Override
    public boolean isInstitutionalPlanHeader() {
        return institutionalPlanHeader;
    }

    @Override
    public void setInstitutionalPlanHeader(boolean institutionalPlanHeader) {
        this.institutionalPlanHeader = institutionalPlanHeader;
    }

    @Override
    public boolean isInvPreFileManagement() {
        return invPreFileManagement;
    }

    @Override
    public void setInvPreFileManagement(boolean invPreFileManagement) {
        this.invPreFileManagement = invPreFileManagement;
    }

    @Override
    public boolean isInvPrfeFileSearch() {
        return invPrfeFileSearch;
    }

    @Override
    public void setInvPrfeFileSearch(boolean invPrfeFileSearch) {
        this.invPrfeFileSearch = invPrfeFileSearch;
    }

    @Override
    public boolean isObjective() {
        return objective;
    }

    @Override
    public void setObjective(boolean objective) {
        this.objective = objective;
    }

    @Override
    public boolean isObjectiveLevelDetails() {
        return objectiveLevelDetails;
    }

    @Override
    public void setObjectiveLevelDetails(boolean objectiveLevelDetails) {
        this.objectiveLevelDetails = objectiveLevelDetails;
    }

    @Override
    public boolean isPrograminmain() {
        return programinmain;
    }

    @Override
    public void setPrograminmain(boolean programinmain) {
        this.programinmain = programinmain;
    }

    @Override
    public boolean isRegionalPlan() {
        return regionalPlan;
    }

    @Override
    public void setRegionalPlan(boolean regionalPlan) {
        this.regionalPlan = regionalPlan;
    }

    @Override
    public boolean isReportCeilingBudget() {
        return reportCeilingBudget;
    }

    @Override
    public void setReportCeilingBudget(boolean reportCeilingBudget) {
        this.reportCeilingBudget = reportCeilingBudget;
    }
    
}
