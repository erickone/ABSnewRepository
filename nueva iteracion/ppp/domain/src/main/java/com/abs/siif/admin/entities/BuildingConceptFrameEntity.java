package com.abs.siif.admin.entities;

import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.programming.entities.ActionGBEntity;
import com.abs.siif.programming.entities.BuildingConceptEntity;
import com.abs.siif.programming.entities.GeneralConceptEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ABS
 */
@Entity
@Table(name = "siifpppconceptogralobraaccion")
public class BuildingConceptFrameEntity implements Comparable<BuildingConceptFrameEntity>, Serializable {
    
    @Id
    @Column(name = "idconceptogral")
    private Long generalConceptId;
    
    @Id
    @Column(name = "idconceptoobra")
    private Long buildingConceptId;
    
    @Id
    @Column(name = "idaccionmetabenef")
    private Long actionId;
    
    @Id
    @Column(name = "idobjetogasto")
    private Long objectExpenseId;      
    
    @ManyToOne(fetch= FetchType.LAZY)    
    @JoinColumn(name = "IdConceptoGral", nullable = true)
    private GeneralConceptEntity generalConceptEntity;
    
    @ManyToOne(fetch= FetchType.LAZY)    
    @JoinColumn(name = "idconceptoobra", nullable = true)
    private BuildingConceptEntity buildingConceptEntity;
    
    @ManyToOne(fetch= FetchType.LAZY)    
    @JoinColumn(name = "idaccionmetabenef", nullable = true)
    private ActionGBEntity actionGBEntity;
    
    @ManyToOne(fetch= FetchType.LAZY)    
    @JoinColumn(name = "idobjetogasto", nullable = true)
    private ObjectExpenseEntity objectExpenseEntity;

    public ActionGBEntity getActionGBEntity() {
        return actionGBEntity;
    }

    public void setActionGBEntity(ActionGBEntity actionGBEntity) {
        this.actionGBEntity = actionGBEntity;
    }

    public Long getActionId() {
        if(actionId == null){
            actionId = new Long(0);
        }
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Long getObjectExpenseId() {
        if(objectExpenseId == null){
            objectExpenseId = new Long(0);
        }
        return objectExpenseId;
    }

    public void setObjectExpenseId(Long objectExpenseId) {
        this.objectExpenseId = objectExpenseId;
    }

    public BuildingConceptEntity getBuildingConceptEntity() {
        return buildingConceptEntity;
    }

    public void setBuildingConceptEntity(BuildingConceptEntity buildingConceptEntity) {
        this.buildingConceptEntity = buildingConceptEntity;
    }

    public Long getBuildingConceptId() {
        if(buildingConceptId == null){
            buildingConceptId = new Long(0);
        }
        return buildingConceptId;
    }

    public void setBuildingConceptId(Long buildingConceptId) {        
        this.buildingConceptId = buildingConceptId;
    }

    public GeneralConceptEntity getGeneralConceptEntity() {
        return generalConceptEntity;
    }

    public void setGeneralConceptEntity(GeneralConceptEntity generalConceptEntity) {
        this.generalConceptEntity = generalConceptEntity;
    }

    public Long getGeneralConceptId() {
        if(generalConceptId == null){
            generalConceptId = new Long(0);
        }
        return generalConceptId;
    }

    public void setGeneralConceptId(Long generalConceptId) {
        this.generalConceptId = generalConceptId;
    }

    public ObjectExpenseEntity getObjectExpenseEntity() {
        return objectExpenseEntity;
    }

    public void setObjectExpenseEntity(ObjectExpenseEntity objectExpenseEntity) {
        this.objectExpenseEntity = objectExpenseEntity;
    }    
                    
    @Override
    public int compareTo(BuildingConceptFrameEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean equals(BuildingConceptFrameEntity o){
        return this.getBuildingConceptId() == o.getBuildingConceptId()
                && this.getBuildingConceptId() == o.getBuildingConceptId()
                && this.getActionId() == o.getActionId()
                && this.getObjectExpenseId() == o.getObjectExpenseId();
    }
        
    
}
