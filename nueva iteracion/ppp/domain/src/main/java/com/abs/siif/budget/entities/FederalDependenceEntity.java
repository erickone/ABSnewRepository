package com.abs.siif.budget.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Luis Agustin Carreón
 */
@Entity
@Table(name = "SIIFPPPDepFederal")
public class FederalDependenceEntity implements Serializable, Comparable<FederalDependenceEntity> {
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdDepFederal")
    private Long federalDependenceId;
    @Column(name = "Descripcion", length = 150)
    private String federalDependenceDescription;
    @OneToMany(mappedBy = "federalDependenceId")
    private Set<PreInvRequestEntity> preInvRequests;

    public String getFederalDependenceDescription() {
        return federalDependenceDescription != null ? federalDependenceDescription.trim() :
               federalDependenceDescription;
    }

    public void setFederalDependenceDescription(String federalDependenceDescription) {
        this.federalDependenceDescription = federalDependenceDescription;
    }

    public Long getFederalDependenceId() {
        return federalDependenceId;
    }

    public void setFederalDependenceId(Long federalDependenceId) {
        this.federalDependenceId = federalDependenceId;
    }



    @Override
    public int compareTo(FederalDependenceEntity anOtherEntity) {
        return this.federalDependenceId.compareTo(
                anOtherEntity.federalDependenceId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FederalDependenceEntity) {
            return this.federalDependenceId.equals(
                    ((FederalDependenceEntity) obj).federalDependenceId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.federalDependenceId != null
                ? this.federalDependenceId.hashCode() : 0);
        return hash;
    }

}
