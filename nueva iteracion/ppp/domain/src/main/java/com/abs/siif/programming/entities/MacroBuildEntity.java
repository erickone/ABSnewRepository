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
@Table(name = "siifpppMacroObra")
public class MacroBuildEntity
    implements Comparable<MacroBuildEntity>, Serializable{
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdMacroObra")
    private Long macroBuildId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String macroBuildKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String macroBuildDescription;

    @OneToMany(mappedBy = "macroBuildBenefAndGoals")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<BenefAndGoalsEntity> macroBuildBenefAndGoals;    
    
    public String getMacroBuildDescription() {
        return macroBuildDescription != null ? macroBuildDescription.trim() : macroBuildDescription;
    }

    public void setMacroBuildDescription(String macroBuildDescription) {
        this.macroBuildDescription = macroBuildDescription;
    }

    public Long getMacroBuildId() {
        return macroBuildId;
    }

    public void setMacroBuildId(Long macroBuildId) {
        this.macroBuildId = macroBuildId;
    }

    public String getMacroBuildKey() {
        return macroBuildKey != null ? macroBuildKey.trim() : macroBuildKey;
    }

    public void setMacroBuildKey(String macroBuildKey) {
        this.macroBuildKey = macroBuildKey;
    }

    @Override
    public int compareTo(MacroBuildEntity anOtherEntity) {
        int result = -1;
        if (this.macroBuildId != null 
                && anOtherEntity.macroBuildId != null) {
            result = this.macroBuildId.compareTo(
                anOtherEntity.macroBuildId);
        }
        return result;
    }
    
    @Override 
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof MacroBuildEntity 
                 && this.macroBuildId != null
                 && ((MacroBuildEntity) obj).macroBuildId != null) {
            result = this.macroBuildId.equals(
                    ((MacroBuildEntity) obj).macroBuildId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.macroBuildId != null 
                ? this.macroBuildId.hashCode() : 0);
        return hash;
    }

    
}
