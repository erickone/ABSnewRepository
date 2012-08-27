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
@Table(name = "siifpppConceptoObra")
public class BuildingConceptEntity
    implements Comparable<BuildingConceptEntity>, Serializable{
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdConceptoObra")
    private Long buildingConceptId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String buildingConceptKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String buildingConceptDescription;
    
    /*
    // Relación de Concepto de Obra con Concepto General (Many To One)
    // Identificador del Concepto General
    @ManyToOne(cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="IdConceptoGral", nullable=true)
    private GeneralConceptEntity gralConceptBuildingConcept;
    * 
    */
    
    /*
    // Relación de Concepto de Obra con Acción de Beneficios y Metas (One To Many)
    @OneToMany(mappedBy = "buildingConceptActionGB")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<ActionGBEntity> actionGBBuildingConcept;
    * 
    */

    @OneToMany(mappedBy = "buildingConceptBenefAndGoals")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<BenefAndGoalsEntity> buildingConceptBenefAndGoals;    
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "siifpppconceptogralobraaccion", 
            joinColumns = { @JoinColumn(name = "IdConceptoObra") }, 
            inverseJoinColumns = { @JoinColumn(name = "idconceptogral") })
    Set<GeneralConceptEntity> generalConceptEntity;
    
    
    public String getBuildingConceptDescription() {
        return buildingConceptDescription != null ? buildingConceptDescription.trim() : buildingConceptDescription;
    }

    public void setBuildingConceptDescription(String buildingConceptDescription) {
        this.buildingConceptDescription = buildingConceptDescription;
    }

    public Long getBuildingConceptId() {
        return buildingConceptId;
    }

    public void setBuildingConceptId(Long buildingConceptId) {
        this.buildingConceptId = buildingConceptId;
    }

    public String getBuildingConceptKey() {
        return buildingConceptKey != null ? buildingConceptKey.trim() : buildingConceptKey;
    }

    public void setBuildingConceptKey(String buildingConceptKey) {
        this.buildingConceptKey = buildingConceptKey;
    }

    @Override
    public int compareTo(BuildingConceptEntity anOtherEntity) {
        return this.buildingConceptId.compareTo(
                anOtherEntity.buildingConceptId);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BuildingConceptEntity) {
            return this.buildingConceptId.equals(
                    ((BuildingConceptEntity) obj).buildingConceptId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.buildingConceptId != null
                ? this.buildingConceptId.hashCode() : 0);
        return hash;
    }

}
