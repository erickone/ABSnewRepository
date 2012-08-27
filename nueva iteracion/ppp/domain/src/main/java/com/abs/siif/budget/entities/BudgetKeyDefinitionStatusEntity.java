package com.abs.siif.budget.entities;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Luis Agustin Carreón
 */
@Entity
@Table(name = "siifpppdefcveestado")
public class BudgetKeyDefinitionStatusEntity  implements
        Serializable, Comparable<BudgetKeyDefinitionStatusEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdDefcveestado")
    private Long definitionKeyStatusEntityId;
    @Column(name="Clave", length= 20)
    private String keyDefinitionStatusEntityKey;
    @Column(name="Descripcion", length= 150)
    private String keyDefinitionStatusEntityDescription;

    public String getKeyDefinitionStatusEntityDescription() {
        return keyDefinitionStatusEntityDescription != null ? keyDefinitionStatusEntityDescription.trim() :
               keyDefinitionStatusEntityDescription;
    }

    public void setKeyDefinitionStatusEntityDescription(String keyDefinitionStatusEntityDescription) {
        this.keyDefinitionStatusEntityDescription = keyDefinitionStatusEntityDescription;
    }

    public Long getDefinitionKeyStatusEntityId() {
        return definitionKeyStatusEntityId;
    }

    public void setDefinitionKeyStatusEntityId(Long definitionKeyStatusEntityId) {
        this.definitionKeyStatusEntityId = definitionKeyStatusEntityId;
    }

    public String getKeyDefinitionStatusEntityKey() {
        return keyDefinitionStatusEntityKey != null ? keyDefinitionStatusEntityKey.trim() : 
               keyDefinitionStatusEntityKey;
    }

    public void setKeyDefinitionStatusEntityKey(String keyDefinitionStatusEntityKey) {
        this.keyDefinitionStatusEntityKey = keyDefinitionStatusEntityKey;
    }

    @Override
    public int compareTo(BudgetKeyDefinitionStatusEntity anOtherEntity) {
        return this.definitionKeyStatusEntityId.compareTo(
                anOtherEntity.definitionKeyStatusEntityId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DestinationEntity) {
            return this.definitionKeyStatusEntityId.equals(
                    ((BudgetKeyDefinitionStatusEntity) obj).definitionKeyStatusEntityId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.definitionKeyStatusEntityId != null
                ? this.definitionKeyStatusEntityId.hashCode() : 0);
        return hash;
    }
}
