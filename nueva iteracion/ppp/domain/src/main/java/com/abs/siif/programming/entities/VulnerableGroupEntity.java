package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Israel Ruiz
 */

@Entity
@Table(name = "siifAbsGpoVulnerable")
public class VulnerableGroupEntity implements
        Comparable<VulnerableGroupEntity>, Serializable {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdGpoVulnerable")
    private Long vulnerableGroupId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String vulnerableGroupKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String vulnerableGroupDescription;

    @OneToMany(mappedBy = "vulnerableGroupBenefAndGoals")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<BenefAndGoalsEntity> vulnerableGroupBenefAndGoals;    
    
    // Generación de estructura para relacionar anteproyecto con grupos vulnerables
    @ManyToMany
    @JoinTable(name = "siifpppanteproygpovulner", joinColumns = {
        @JoinColumn(name = "idgpovulnerable", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "idanteproyecto",
        nullable = false, updatable = false)})
    private List<DraftProjectEntity> vulnerableGroupDraftProject;
    
    public String getVulnerableGroupDescription() {
        return vulnerableGroupDescription != null ? vulnerableGroupDescription.trim():vulnerableGroupDescription;
    }

    public void setVulnerableGroupDescription(String vulnerableGroupDescription) {
        this.vulnerableGroupDescription = vulnerableGroupDescription;
    }

    public Long getVulnerableGroupId() {
        return vulnerableGroupId;
    }

    public void setVulnerableGroupId(Long vulnerableGroupId) {
        this.vulnerableGroupId = vulnerableGroupId;
    }

    public String getVulnerableGroupKey() {
        return vulnerableGroupKey != null ?vulnerableGroupKey.trim():vulnerableGroupKey;
    }

    public void setVulnerableGroupKey(String vulnerableGroupKey) {
        this.vulnerableGroupKey = vulnerableGroupKey;
    }

    @Override
    public int compareTo(VulnerableGroupEntity obj) {
        int result = -1;
        if (this.vulnerableGroupId != null && obj.getVulnerableGroupId() != null) {
            result = this.vulnerableGroupId.compareTo(obj.getVulnerableGroupId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof VulnerableGroupEntity && this.vulnerableGroupId != null 
                && ((VulnerableGroupEntity)obj).getVulnerableGroupId() != null){
            result = this.vulnerableGroupId.equals(
                    ((VulnerableGroupEntity)obj).getVulnerableGroupId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.vulnerableGroupId != null ? this.vulnerableGroupId.hashCode() : 0);
        return hash;
    }
}
