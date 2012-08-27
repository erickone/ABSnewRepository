package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Israel Ruiz
 */
@Entity
@Table(name = "siifpppAccionMetaBenef")
public class ActionGBEntity
        implements Comparable<ActionGBEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdAccionMetaBenef")
    private Long actionGBId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String actionGBKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String actionGBDescription;
    
    /*
    // Relación de Acción de Beneficios y Metas con Concepto de Obra con Concepto General (Many To One)
    // Identificador del Concepto de Obra
    @ManyToOne(cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "IdConceptoObra", nullable = true)
    private BuildingConceptEntity buildingConceptActionGB;
    * 
    */
    
    @OneToMany(mappedBy = "actionGBBenefAndGoals")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<BenefAndGoalsEntity> actionGBBenefAndGoals;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "siifpppconceptogralobraaccion", 
            joinColumns = { @JoinColumn(name = "idaccionmetabenef") }, 
            inverseJoinColumns = { @JoinColumn(name = "IdConceptoObra") })
    Set<BuildingConceptEntity> buildingConceptEntity;
    

    public String getActionGBDescription() {
        return actionGBDescription != null ? actionGBDescription.trim() : actionGBDescription;
    }

    public void setActionGBDescription(String actionGBDescription) {
        this.actionGBDescription = actionGBDescription;
    }

    public Long getActionGBId() {
        return actionGBId;
    }

    public void setActionGBId(Long actionGBId) {
        this.actionGBId = actionGBId;
    }

    public String getActionGBKey() {
        return actionGBKey != null ? actionGBKey.trim() : actionGBKey;
    }

    public void setActionGBKey(String actionGBKey) {
        this.actionGBKey = actionGBKey;
    }

    @Override
    public int compareTo(ActionGBEntity anOtherEntity) {
        return this.actionGBId.compareTo(
                anOtherEntity.actionGBId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ActionGBEntity) {
            return this.actionGBId.equals(
                    ((ActionGBEntity) obj).actionGBId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.actionGBId != null
                ? this.actionGBId.hashCode() : 0);
        return hash;
    }
}
