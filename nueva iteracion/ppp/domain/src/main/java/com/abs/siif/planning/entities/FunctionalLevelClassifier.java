package com.abs.siif.planning.entities;

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
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name="SIIFPPPNivClasifFunc")
public class FunctionalLevelClassifier  implements 
        Serializable, Comparable<FunctionalLevelClassifier> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdNivClasifFuncional")
    private Long functionalLevelClassifierId;
    @Column(name = "Descripcion", length = 150)
    private String functionalLevelClassifierDescription;
    @Column(name = "Nivel")
    private int functionalLevelClassifier;
    @Column(name = "Finalidad")
    private boolean functionalLevelClassifierIsFinality;
    @Column(name = "Funcion")
    private boolean functionalLevelClassifierIsFunction;
    @Column(name = "SubFuncion")
    private boolean functionalLevelClassifierIsSubFunction;
    @Column(name = "anio")
    private Integer functionalLevelClassifierYear;
    @Column(name = "Clave")
    private String functionalLevelClassifierKey;
    @Column(name = "EncPlanEst ")
    private boolean functionalLevelClassifierIsEncPlaneacion;
    @OneToMany(mappedBy = "functionalLevelClassifier")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<FunctionalClassifierEntity> functionalClassifiers;
    
    @OneToMany(mappedBy = "functionalClassifier")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<ObjectiveLevelEntity> objectiveLevels;

    public Set<ObjectiveLevelEntity> getObjectiveLevels() {
        return objectiveLevels;
    }
    
    

    public Set<FunctionalClassifierEntity> getFunctionalClassifiers() {
        return functionalClassifiers;
    }

    public int getFunctionalLevelClassifier() {
        return functionalLevelClassifier;
    }

    public void setFunctionalLevelClassifier(int aFunctionalLevelClassifier) {
        this.functionalLevelClassifier = aFunctionalLevelClassifier;
    }

    public String getFunctionalLevelClassifierDescription() {
        return functionalLevelClassifierDescription != null ? functionalLevelClassifierDescription.trim() : functionalLevelClassifierDescription;
    }

    public void setFunctionalLevelClassifierDescription(String 
            aFunctionalLevelClassifierDescription) {
        this.functionalLevelClassifierDescription = 
                aFunctionalLevelClassifierDescription;
    }

    public Long getFunctionalLevelClassifierId() {
        return functionalLevelClassifierId;
    }

    public void setFunctionalLevelClassifierId(Long 
            aFunctionalLevelClassifierId) {
        this.functionalLevelClassifierId = aFunctionalLevelClassifierId;
    }

    public boolean isFunctionalLevelClassifierIsFinality() {
        return functionalLevelClassifierIsFinality;
    }

    public void setFunctionalLevelClassifierIsFinality(boolean 
            aFunctionalLevelClassifierIsFinality) {
        this.functionalLevelClassifierIsFinality = 
                aFunctionalLevelClassifierIsFinality;
    }

    public boolean isFunctionalLevelClassifierIsFunction() {
        return functionalLevelClassifierIsFunction;
    }

    public void setFunctionalLevelClassifierIsFunction(boolean 
            aFunctionalLevelClassifierIsFunction) {
        this.functionalLevelClassifierIsFunction = 
                aFunctionalLevelClassifierIsFunction;
    }

    public boolean isFunctionalLevelClassifierIsSubFunction() {
        return functionalLevelClassifierIsSubFunction;
    }

    public void setFunctionalLevelClassifierIsSubFunction(boolean 
            aFunctionalLevelClassifierIsSubFunction) {
        this.functionalLevelClassifierIsSubFunction = 
                aFunctionalLevelClassifierIsSubFunction;
    }

    public boolean isFunctionalLevelClassifierIsEncPlaneacion()
    {
        return functionalLevelClassifierIsEncPlaneacion;
    }

    public void setFunctionalLevelClassifierIsEncPlaneacion(boolean 
            functionalLevelClassifierIsEncPlaneacion)
    {
        this.functionalLevelClassifierIsEncPlaneacion = 
                functionalLevelClassifierIsEncPlaneacion;
    }

    public String getFunctionalLevelClassifierKey()
    {
        return functionalLevelClassifierKey;
    }

    public void setFunctionalLevelClassifierKey(String 
            functionalLevelClassifierKey)
    {
        this.functionalLevelClassifierKey = functionalLevelClassifierKey;
    }

    public Integer getFunctionalLevelClassifierYear()
    {
        return functionalLevelClassifierYear;
    }

    public void setFunctionalLevelClassifierYear(Integer 
            functionalLevelClassifierYear)
    {
        this.functionalLevelClassifierYear = functionalLevelClassifierYear;
    }
    
    @Override
    public int compareTo(FunctionalLevelClassifier anOtherEntity) {
        return this.functionalLevelClassifierId.compareTo(
                anOtherEntity.functionalLevelClassifierId);
    }
    
    @Override
    public boolean equals(Object obj){
         if (obj instanceof FunctionalLevelClassifier) {
            return this.functionalLevelClassifierId.equals(
                    ((FunctionalLevelClassifier) 
                    obj).functionalLevelClassifierId);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 89 * hash + (this.functionalLevelClassifierId != null 
                ? this.functionalLevelClassifierId.hashCode() : 0);
        return hash;
    }
}
