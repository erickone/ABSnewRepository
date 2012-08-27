package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifpppentregable")
public class DeliveryEntity
    implements Comparable<DeliveryEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdEntregable")
    private Long deliveryId;
    @Column(name = "proposito", nullable = false, length = 150)
    private String proposit;

    @ManyToOne
    @JoinColumn(name = "IdAnteproyecto", nullable = false)
    private DraftProjectEntity draftProject;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delivery")
    private Set<ComponentEntity> components;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long aDeliveryId) {
        this.deliveryId = aDeliveryId;
    }

    public String getProposit() {
        return proposit != null ? proposit.trim() : proposit;
    }

    public void setProposit(String aProposit) {
        this.proposit = aProposit;
    }

    public DraftProjectEntity getDraftProject() {
        return draftProject;
    }

    public void setDraftProject(DraftProjectEntity aDraftProject) {
        this.draftProject = aDraftProject;
    }

    /**
     * @return the components
     */
    public Set<ComponentEntity> getComponents() {
        return components;
    }

    /**
     * @param components the components to set
     */
    public void setComponents(Set<ComponentEntity> aComponents) {
        this.components = aComponents;
    }

    @Override
    public int compareTo(DeliveryEntity obj) {
        int result = -1;
        if (this.deliveryId != null && obj.getDeliveryId() != null) {
            result = deliveryId.compareTo(obj.getDeliveryId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof DeliveryEntity && this.deliveryId != null 
                && ((DeliveryEntity)obj).getDeliveryId() != null){
            result = deliveryId.equals(((DeliveryEntity)obj).getDeliveryId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.deliveryId != null ? this.deliveryId.hashCode() : 0);
        return hash;
    }

}
