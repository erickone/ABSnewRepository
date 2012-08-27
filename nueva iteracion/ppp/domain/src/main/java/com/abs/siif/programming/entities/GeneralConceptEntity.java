package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Israel Ruiz
 */
@Entity
@Table(name = "siifpppConceptoGral")
public class GeneralConceptEntity
        implements Comparable<GeneralConceptEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdConceptoGral")
    private Long generalConceptId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String generalConceptKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String generalConceptDescription;
    
    /*
    // Relación con Conceptos de Obra (OneToMany)
    @OneToMany(mappedBy = "gralConceptBuildingConcept")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<BuildingConceptEntity> gralConceptBuildingConcept;
    * 
    */
    
    @OneToMany(mappedBy = "generalConceptBenefAndGoals")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<BenefAndGoalsEntity> generalConceptBenefAndGoals;

    public String getGeneralConceptDescription() {
        return generalConceptDescription != null ? generalConceptDescription.trim() : generalConceptDescription;
    }

    public void setGeneralConceptDescription(String generalConceptDescription) {
        this.generalConceptDescription = generalConceptDescription;
    }

    public Long getGeneralConceptId() {
        return generalConceptId;
    }

    public void setGeneralConceptId(Long generalConceptId) {
        this.generalConceptId = generalConceptId;
    }

    public String getGeneralConceptKey() {
        return generalConceptKey != null ? generalConceptKey.trim() : generalConceptKey;
    }

    public void setGeneralConceptKey(String generalConceptKey) {
        this.generalConceptKey = generalConceptKey;
    }

    @Override
    public int compareTo(GeneralConceptEntity anOtherEntity) {
        int result = -1;
        if (this.generalConceptId != null 
                && anOtherEntity.generalConceptId != null) {
            result = this.generalConceptId.compareTo(
                anOtherEntity.generalConceptId);
        }
        return result;
    }
    
    @Override 
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof GeneralConceptEntity 
                 && this.generalConceptId != null
                 && ((GeneralConceptEntity) obj).generalConceptId != null) {
            result = this.generalConceptId.equals(
                    ((GeneralConceptEntity) obj).generalConceptId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.generalConceptId != null 
                ? this.generalConceptId.hashCode() : 0);
        return hash;
    }
}
