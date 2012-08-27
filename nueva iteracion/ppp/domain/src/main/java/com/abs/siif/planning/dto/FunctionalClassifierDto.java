/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierDto
 *  Purpose:  DTO for Functional Classifier.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.planning.dto;

import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author FENIX-02
 */
public class FunctionalClassifierDto implements Serializable {

    private String itsNodeText;
    private String itsNodeTooltip;
    private Long itsFuncClassifierId;
    private Long itsFuncClassifierLevelId;
    private String itsFuncClassifierDefinition;
    private String itsFuncClassifierDescription;
    private Long itsFatherId;
    private String itsFuncClassifierKey;
    private int itsFuncClassifierLevel;
    private Set<ObjectiveEntity> funtionalClassifierObjectives;

    public FunctionalClassifierDto() {
    }
    
    public FunctionalClassifierDto(String aNodeText) {
        this.itsNodeText = aNodeText;
        this.itsFatherId = 0L;
    }
    
    public FunctionalClassifierDto(String aNodeText, String aNodeTooltip, Long aFuncClassifierId,
            Long aFuncClassifierLevelId, String aFuncClassifierDesc, String aFuncClassifierDefinition, 
            Long fatherId, int aFuncClassifierLevel, String aKey, Set<ObjectiveEntity> funtionalClassifierObjectives) {
        this.itsNodeText = aNodeText;
        this.itsNodeTooltip = aNodeTooltip;
        this.itsFuncClassifierId = aFuncClassifierId;
        this.itsFuncClassifierLevelId = aFuncClassifierLevelId;
        this.itsFuncClassifierDescription = aFuncClassifierDesc;
        this.itsFuncClassifierDefinition = aFuncClassifierDefinition;
        this.itsFuncClassifierLevel = aFuncClassifierLevel;
        this.itsFatherId = fatherId;
        this.itsFuncClassifierKey = aKey;
        this.funtionalClassifierObjectives = funtionalClassifierObjectives;
    }
    public FunctionalClassifierEntity getFunctionalClassifierEntity(){
        FunctionalClassifierEntity myFCEntity = new FunctionalClassifierEntity();
        myFCEntity.setFunctionalClassifierDefinitionCONAC(this.itsFuncClassifierDefinition);
        myFCEntity.setFunctionalClassifierDescription(this.itsFuncClassifierDescription);
        myFCEntity.setFunctionalClassifierId(this.itsFuncClassifierId);
        myFCEntity.setFunctionalClassifierKey(itsFuncClassifierKey);
        return myFCEntity;
    }
    public Long getItsFatherId()
    {
        return itsFatherId;
    }

    public void setItsFatherId(Long itsFatherId)
    {
        this.itsFatherId = itsFatherId;
    }

    public String getItsFuncClassifierDefinition()
    {
        return itsFuncClassifierDefinition;
    }

    public void setItsFuncClassifierDefinition(String itsFuncClassifierDefinition)
    {
        this.itsFuncClassifierDefinition = itsFuncClassifierDefinition;
    }

    public String getItsFuncClassifierDescription()
    {
        return itsFuncClassifierDescription;
    }

    public void setItsFuncClassifierDescription(String itsFuncClassifierDescription)
    {
        this.itsFuncClassifierDescription = itsFuncClassifierDescription;
    }

    public Long getItsFuncClassifierId()
    {
        return itsFuncClassifierId;
    }

    public void setItsFuncClassifierId(Long itsFuncClassifierId)
    {
        this.itsFuncClassifierId = itsFuncClassifierId;
    }

    public Long getItsFuncClassifierLevelId()
    {
        return itsFuncClassifierLevelId;
    }

    public void setItsFuncClassifierLevelId(Long itsFuncClassifierLevelId)
    {
        this.itsFuncClassifierLevelId = itsFuncClassifierLevelId;
    }

    public String getItsNodeText()
    {
        return itsNodeText;
    }

    public void setItsNodeText(String itsNodeText)
    {
        this.itsNodeText = itsNodeText;
    }

    public String getItsNodeTooltip()
    {
        return itsNodeTooltip;
    }

    public void setItsNodeTooltip(String itsNodeTooltip)
    {
        this.itsNodeTooltip = itsNodeTooltip;
    }

    public int getItsFuncClassifierLevel()
    {
        return itsFuncClassifierLevel;
    }

    public void setItsFuncClassifierLevel(int itsFuncClassifierLevel)
    {
        this.itsFuncClassifierLevel = itsFuncClassifierLevel;
    }

    public String getItsFuncClassifierKey()
    {
        return itsFuncClassifierKey;
    }

    public void setItsFuncClassifierKey(String itsKey)
    {
        this.itsFuncClassifierKey = itsKey;
    }

    public Set<ObjectiveEntity> getFuntionalClassifierObjectives()
    {
        return funtionalClassifierObjectives;
    }

    public void setFuntionalClassifierObjectives(Set<ObjectiveEntity> funtionalClassifierObjectives)
    {
        this.funtionalClassifierObjectives = funtionalClassifierObjectives;
    }
    
}
