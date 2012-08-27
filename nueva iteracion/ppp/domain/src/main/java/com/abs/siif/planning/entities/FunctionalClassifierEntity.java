package com.abs.siif.planning.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "SIIFPPPClasifFuncional")
public class FunctionalClassifierEntity  implements
        Serializable, Comparable<FunctionalClassifierEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdClasifFuncional")
    private Long functionalClassifierId;
    @ManyToOne
    @JoinColumn(name = "IdNivClasifFuncional", nullable = false)
    private FunctionalLevelClassifier functionalLevelClassifier;
    @Column(name = "Clave", length= 20)
    private String functionalClassifierKey;
    @Column(name = "Descripcion", length = 150)
    private String functionalClassifierDescription;
    @Column(name = "DefinicionCONAC", length = 150)
    private String functionalClassifierDefinitionCONAC;
    @ManyToOne
    @JoinColumn(name = "IdPadre", nullable = true)
    private FunctionalClassifierEntity functionalClassifierFather;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "functionalClassifierFather")
    private Set<FunctionalClassifierEntity> functionalClassifierChilds;
    @ManyToMany
    @JoinTable(name = "SIIFPPPEncObjClasifFunc", joinColumns = {
        @JoinColumn(name = "IdClasifFuncional", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdObjetivo",
        nullable = false, updatable = false)})
    private Set<ObjectiveEntity> funtionalClassifierObjectives;

    public Set<ObjectiveEntity> getFuntionalClassifierObjectives() {
        return funtionalClassifierObjectives;
    }

    public void setFuntionalClassifierObjectives(Set<ObjectiveEntity> funtionalClassifierObjectives)
    {
        this.funtionalClassifierObjectives = funtionalClassifierObjectives;
    }

    public FunctionalClassifierEntity getFather() {
        return functionalClassifierFather;
    }

    public void setFather(FunctionalClassifierEntity aFather) {
        this.functionalClassifierFather = aFather;
    }

    public String getFunctionalClassifierDefinitionCONAC() {
        return functionalClassifierDefinitionCONAC != null ? functionalClassifierDefinitionCONAC.trim() : functionalClassifierDefinitionCONAC;
    }

    public void setFunctionalClassifierDefinitionCONAC(String 
            aFunctionalClassifierDefinitionCONAC) {
        this.functionalClassifierDefinitionCONAC =
                aFunctionalClassifierDefinitionCONAC;
    }

    public String getFunctionalClassifierDescription() {
        return functionalClassifierDescription != null ? functionalClassifierDescription.trim() : functionalClassifierDescription;
    }

    public void setFunctionalClassifierDescription(String 
            aFunctionalClassifierDescription) {
        this.functionalClassifierDescription = aFunctionalClassifierDescription;
    }

    public Long getFunctionalClassifierId() {
        return functionalClassifierId;
    }

    public void setFunctionalClassifierId(Long aFunctionalClassifierId) {
        this.functionalClassifierId = aFunctionalClassifierId;
    }

    public String getFunctionalClassifierKey() {
        return functionalClassifierKey != null ? functionalClassifierKey.trim() : functionalClassifierKey;
    }

    public void setFunctionalClassifierKey(String aFunctionalClassifierKey) {
        this.functionalClassifierKey = aFunctionalClassifierKey;
    }

    public FunctionalLevelClassifier getFunctionalLevelClassifier() {
        return functionalLevelClassifier;
    }

    public void setFunctionalLevelClassifier(FunctionalLevelClassifier 
            aFunctionalLevelClassifier) {
        this.functionalLevelClassifier = aFunctionalLevelClassifier;
    }

    public Set<FunctionalClassifierEntity> getFunctionalClassifierChilds() {
        return functionalClassifierChilds;
    }

    public void setFunctionalClassifierChilds(Set<FunctionalClassifierEntity> functionalClassifierChilds)
    {
        this.functionalClassifierChilds = functionalClassifierChilds;
    }
    
    @Override
    public int compareTo(FunctionalClassifierEntity anOtherEntity) {
        int result = -1;
        if (this.functionalClassifierId != null
                && anOtherEntity.functionalClassifierId != null) {
            result = this.functionalClassifierId.compareTo(
                    anOtherEntity.functionalClassifierId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof FunctionalClassifierEntity
                && this.functionalClassifierId != null
                && ((FunctionalClassifierEntity) obj).functionalClassifierId != null) {
            result = this.functionalClassifierId.equals(
                    ((FunctionalClassifierEntity) obj).functionalClassifierId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.functionalClassifierId != null
                ? this.functionalClassifierId.hashCode() : 0);
        hash = 23 * hash + (this.functionalClassifierKey != null
                ? this.functionalClassifierKey.hashCode() : 0);
        return hash;
    }
    
    public String getCompositeFuncClassifierKey() {
        return this.functionalClassifierKey + " " + this.functionalClassifierDescription;
    }
}
