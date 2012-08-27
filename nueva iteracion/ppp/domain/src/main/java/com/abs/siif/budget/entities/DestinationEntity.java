package com.abs.siif.budget.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Luis Agustin Carreón
 */
@Entity
@Table(name = "SIIFPPPDestino")
public class DestinationEntity implements
        Comparable<DestinationEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdDestino")
    private Long destinationId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String destinationKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String destinationDescription;
    @ManyToMany
    @JoinTable(name = "siifpppObjGastoDestino", joinColumns = {
        @JoinColumn(name = "IdDestino", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdObjetoGasto",
        nullable = false, updatable = false)})
    private List<ObjectExpenseEntity> objectExpenseDestinitaion;

    public List<ObjectExpenseEntity> getObjectExpenseDestinitaion() {
        return objectExpenseDestinitaion;
    }

    public void setObjectExpenseDestinitaion(List<ObjectExpenseEntity> objectExpenseDestinitaion) {
        this.objectExpenseDestinitaion = objectExpenseDestinitaion;
    }

    public String getDestinationDescription() {
        return destinationDescription != null ? destinationDescription.trim() :
               destinationDescription;
    }

    public void setDestinationDescription(String destinationDescription) {
        this.destinationDescription = destinationDescription;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationKey() {
        return destinationKey != null ? destinationKey.trim() :
               destinationKey;
    }

    public void setDestinationKey(String destinationKey) {
        this.destinationKey = destinationKey;
    }

    @Override
    public int compareTo(DestinationEntity anOtherEntity) {
        return this.destinationId.compareTo(
                anOtherEntity.destinationId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DestinationEntity) {
            return this.destinationId.equals(
                    ((DestinationEntity) obj).destinationId);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.destinationId != null
                ? this.destinationId.hashCode() : 0);
        return hash;
    }
}
