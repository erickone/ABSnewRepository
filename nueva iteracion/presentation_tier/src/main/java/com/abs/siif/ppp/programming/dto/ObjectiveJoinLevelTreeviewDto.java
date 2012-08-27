package com.abs.siif.ppp.programming.dto;

import java.io.Serializable;


/**
 *
 * @author Erick Leija
 */
public class ObjectiveJoinLevelTreeviewDto implements Serializable
{

    private String itsNodeText;
    private String itsNodeTooltip;
    private Long itsObjectiveId;
    private Long itsObjectiveLevelId;
    private String itsObjectiveName;
    private String itsObjectiveDefinition;
    private Short itsObjectivePriority;
    private Long itsFatherId;
    private short itsObjectiveLevel;
    private String itsHasProgramaticLink;
    private String finallyText;

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

    public Long getItsFatherId() {
        return itsFatherId;
    }

    public String getItsObjectiveDefinition() {
        return itsObjectiveDefinition;
    }

    public Long getItsObjectiveId() {
        return itsObjectiveId;
    }

    public Long getItsObjectiveLevelId() {
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

    public ObjectiveJoinLevelTreeviewDto(String aNodeText, String aNodeTooltip, Long anObjectiveId,
            Long objectiveLevelId, String objectiveName, String objectiveDefinition, 
            Short objectivePriority, Long fatherId,short anObjectiveLevel,String aProgrammingLink) {
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
    
    public String getFinallyText(){
        return (itsNodeTooltip+" "+itsNodeText);
    }
 
     public void setFinallyText(String text){
        finallyText = text;
    }
}
