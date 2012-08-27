package com.abs.siif.planning.dto;

/**
 *
 * @author Juan Zavala
 */
public class ObjectiveTreeviewDto {

    private String itsNodeText;
    private String itsNodeTooltip;
    private Long itsObjectiveId;
    private Long itsObjectiveLevelId;
    private String itsObjectiveName;
    private String itsObjectiveDefinition;
    private Short itsObjectivePriority;
    private Long itsFatherId;
    private short itsObjectiveLevel;
    private String itsObjectiveKey;

    public String getItsObjectiveKey() {
        return itsObjectiveKey;
    }

    public void setItsObjectiveKey(String itsObjectiveKey) {
        this.itsObjectiveKey = itsObjectiveKey;
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

    public ObjectiveTreeviewDto(String aNodeText) {
        this.itsNodeText = aNodeText;
        //this.itsFatherId = "";
    }

    public String getItsObjectiveDefinition() {
        return itsObjectiveDefinition;
    }

    public Long getItsObjectiveId() {
        return itsObjectiveId;
    }

    public ObjectiveTreeviewDto(Long anObjectiveId, String aNodeText, String aNodeTooltip) {
        this.itsNodeText = aNodeText;
        this.itsNodeTooltip = aNodeTooltip;
        this.itsObjectiveId = anObjectiveId;
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

    public ObjectiveTreeviewDto() {
    }

    public ObjectiveTreeviewDto(String aNodeText, String aNodeTooltip, Long anObjectiveId,
            Long objectiveLevelId, String objectiveName, String objectiveDefinition,
            Short objectivePriority, Long fatherId, short anObjectiveLevel, String anObjectiveKey) {
        this.itsNodeText = aNodeText;
        this.itsNodeTooltip = aNodeTooltip;
        this.itsObjectiveId = anObjectiveId;
        this.itsObjectiveLevelId = objectiveLevelId;
        this.itsObjectiveName = objectiveName;
        this.itsObjectiveDefinition = objectiveDefinition;
        this.itsObjectivePriority = objectivePriority;
        this.itsFatherId = fatherId;
        this.itsObjectiveLevel = anObjectiveLevel;
        this.itsObjectiveKey=anObjectiveKey;
    }

    public String getItsNodeText() {
        return itsNodeText;
    }

    public String getItsNodeTooltip() {
        return itsNodeTooltip;
    }

    public void setItsNodeText(String aNodeText) {
        itsNodeText = aNodeText;
    }
}
