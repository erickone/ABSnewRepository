/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  OptionsControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.api.controller;

import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface OptionsControllerApi {

    void changeListenerCboYear();

    String getHref();

    String getYear();

    List<String> getYearsList();

    void init();

    void programminClick();

    void setHref(String href);

    void setYear(String year);

    void setYearsLits(List<String> aYearsList);

    void optionsInit();

    boolean isDraftProjectSearch();

    void setDraftProjectSearch(boolean draftProjectSearch);

    boolean isInstitutionalPlanHeader();

    void setInstitutionalPlanHeader(boolean institutionalPlanHeader);

    boolean isInvPreFileManagement();

    void setInvPreFileManagement(boolean invPreFileManagement);

    boolean isInvPrfeFileSearch();

    void setInvPrfeFileSearch(boolean invPrfeFileSearch);

    boolean isObjective();

    void setObjective(boolean objective);

    boolean isObjectiveLevelDetails();

    void setObjectiveLevelDetails(boolean objectiveLevelDetails);

    boolean isPrograminmain();

    void setPrograminmain(boolean programinmain);

    boolean isRegionalPlan();

    void setRegionalPlan(boolean regionalPlan);

    boolean getCeilingBudget();

    void setCeilingBudget(boolean CeilingBudgetKey);

    boolean getConfigurationCatalog();

    void setConfigurationCatalog(boolean ImportCeilingBudget);
    
    boolean isReportCeilingBudget();
    
    void setReportCeilingBudget(boolean reportCeilingBudget);
}
