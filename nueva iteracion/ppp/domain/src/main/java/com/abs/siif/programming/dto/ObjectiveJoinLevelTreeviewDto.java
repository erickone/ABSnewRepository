package com.abs.siif.programming.dto;

import java.io.Serializable;


/**
 *
 * @author Erick Leija
 */
public class ObjectiveJoinLevelTreeviewDto implements Serializable
{

    private String itsNodeText;
    private String itsNodeTooltip;
    private String itsObjectiveId;
    private String itsObjectiveLevelId;
    private String itsObjectiveName;
    private String itsObjectiveDefinition;
    private Short itsObjectivePriority;
    private String itsFatherId;
    private short itsObjectiveLevel;
    private String itsHasProgramaticLink;

    public String getItsHasProgramaticLink()
    {
        return itsHasProgramaticLink;
    }

    public void setItsHasProgramaticLink(String itsHasProgramaticLink)
    {
        this.itsHasProgramaticLink = itsHasProgramaticLink;
    }

    public short getItsObjectiveLevel() {
        return itsObjectiveLevel;
    }

    public void setItsObjectiveLevel(short anObjectiveLevel) {
        this.itsObjectiveLevel = anObjectiveLevel;
    }

    public String getItsFatherId() {
        return itsFatherId;
    }

    public String getItsObjectiveDefinition() {
        return itsObjectiveDefinition;
    }

    public String getItsObjectiveId() {
        return itsObjectiveId;
    }

    public String getItsObjectiveLevelId() {
        return itsObjectiveLevelId;
    }

    public String getItsObjectiveName() {
        return itsObjectiveName;
    }

    public Short getItsObjectivePriority() {
        return itsObjectivePriority;
    }

    public ObjectiveJoinLevelTreeviewDto() {
    }    

    public ObjectiveJoinLevelTreeviewDto(String aNodeText, String aNodeTooltip, String anObjectiveId,
            String objectiveLevelId, String objectiveName, String objectiveDefinition, 
            Short objectivePriority, String fatherId,short anObjectiveLevel,String aProgrammingLink) {
        this.itsNodeText = aNodeText;
        this.itsNodeTooltip = aNodeTooltip;
        this.itsObjectiveId = anObjectiveId;
        this.itsObjectiveLevelId = objectiveLevelId;
        this.itsObjectiveName = objectiveName;
        this.itsObjectiveDefinition = objectiveDefinition;
        this.itsObjectivePriority = objectivePriority;
        this.itsFatherId = fatherId;
        this.itsObjectiveLevel=anObjectiveLevel;
        this.itsHasProgramaticLink=aProgrammingLink;
    }

    public String getItsNodeText() {
        return itsNodeText;
    }

    public String getItsNodeTooltip() {
        return itsNodeTooltip;
    }

    public void setItsNodeText(String aNodeText) {
       itsNodeText=aNodeText;
    }
}
