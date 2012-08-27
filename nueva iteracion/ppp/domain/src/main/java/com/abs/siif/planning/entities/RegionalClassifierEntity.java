package com.abs.siif.planning.entities;

import com.abs.siif.programming.entities.DraftProjRegionalClassifierEntity;
import com.abs.siif.programming.entities.InvPreFileRegionalClassifierEntity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Entity
@Table(name = "siifabsclasifregional")
public class RegionalClassifierEntity implements
        Comparable<RegionalClassifierEntity>, Serializable {

    @Id    
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdClasifRegional")
    private Long regionalClassifierId;
    @ManyToOne
    @JoinColumn(name = "IdNivClasifRegional", nullable = true)
    private RegionalLevelClassifierEntity regionalLevelClassifier;
    @Column(name = "Clave", length = 20)
    private String regionalClassifierKey;
    @Column(name = "Descripcion", length = 150)
    private String regionalClassifierDescription;
    @Column(name = "Fronterizo")
    private Boolean regionalClassifierIsBorderLine;
    @Column(name = "CodPostal")
    private Integer regionalClassifierPostalCode;
    @Column(name = "NumHombres")
    private Integer regionalClassifierMenNumber;
    @Column(name = "NumMujeres")
    private Integer regionalClassifierWomenNumber;
    @Column(name = "ClaveINEGI", length = 20)
    private String regionalClassifierINEGIKey;
    @Column(name = "AnioINEGI")
    private Integer regionalClassifierINEGIYear;
    @ManyToOne
    @JoinColumn(name = "IdPadre", nullable = true)
    private RegionalClassifierEntity regionalClassifierFather;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionalClassifierFather")
    private Set<RegionalClassifierEntity> regionalClassifierChilds;
    @OneToMany(mappedBy = "regionalClassifierPlan")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<RegionalPlanEntity> regionalPlan;
    // Clasificadores Regionales con PreFichas
    @OneToMany(mappedBy = "invPreFileRegClasifRegClasif")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<InvPreFileRegionalClassifierEntity> invPreFileRegClasifRegClasif;
    @OneToMany(mappedBy = "draftProjectRegionalClassifierRegClassif")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjRegionalClassifierEntity> draftProjRegClassifRegClassif;

    public String getRegionalClassifierDescription() {
        return regionalClassifierDescription != null ? regionalClassifierDescription.trim() : regionalClassifierDescription;
    }

    public void setRegionalClassifierDescription(String aRegionalClassifierDescription) {
        this.regionalClassifierDescription = aRegionalClassifierDescription;
    }

    public String getRegionalClassifierINEGIKey() {
        return regionalClassifierINEGIKey != null ? regionalClassifierINEGIKey.trim() : regionalClassifierINEGIKey;
    }

    public void setRegionalClassifierINEGIKey(String aRegionalClassifierINEGIKey) {
        this.regionalClassifierINEGIKey = aRegionalClassifierINEGIKey;
    }

    public Integer getRegionalClassifierINEGIYear() {
        return regionalClassifierINEGIYear;
    }

    public void setRegionalClassifierINEGIYear(int aRegionalClassifierINEGIYear) {
        this.regionalClassifierINEGIYear = aRegionalClassifierINEGIYear;
    }

    public Long getRegionalClassifierId() {
        return regionalClassifierId;
    }

    public void setRegionalClassifierId(Long aRegionalClassifierId) {
        this.regionalClassifierId = aRegionalClassifierId;
    }

    public Boolean getRegionalClassifierIsBorderLine() {
        return regionalClassifierIsBorderLine;
    }

    public void setRegionalClassifierIsBorderLine(Boolean aRegionalClassifierIsBorderLine) {
        this.regionalClassifierIsBorderLine = aRegionalClassifierIsBorderLine;
    }

    public String getRegionalClassifierKey() {
        return regionalClassifierKey != null ? regionalClassifierKey.trim() : regionalClassifierKey;
    }

    public void setRegionalClassifierKey(String aRegionalClassifierKey) {
        this.regionalClassifierKey = aRegionalClassifierKey;
    }

    public Integer getRegionalClassifierMenNumber() {
        return regionalClassifierMenNumber;
    }

    public void setRegionalClassifierMenNumber(Integer aRegionalClassifierMenNumber) {
        this.regionalClassifierMenNumber = aRegionalClassifierMenNumber;
    }

    public Integer getRegionalClassifierPostalCode() {
        return regionalClassifierPostalCode;
    }

    public void setRegionalClassifierPostalCode(Integer aRegionalClassifierPostalCode) {
        this.regionalClassifierPostalCode = aRegionalClassifierPostalCode;
    }

    public Integer getRegionalClassifierWomenNumber() {
        return regionalClassifierWomenNumber;
    }

    public void setRegionalClassifierWomenNumber(Integer aRegionalClassifierWomenNumber) {
        this.regionalClassifierWomenNumber = aRegionalClassifierWomenNumber;
    }

    public Set<RegionalClassifierEntity> getRegionalClassifierChilds() {
        return regionalClassifierChilds;
    }

    public void setRegionalClassifierChilds(Set<RegionalClassifierEntity> aRegionalClassifierChilds) {
        this.regionalClassifierChilds = aRegionalClassifierChilds;
    }

    public RegionalLevelClassifierEntity getRegionalLevelClassifier() {
        return regionalLevelClassifier;
    }

    public void setRegionalLevelClassifier(RegionalLevelClassifierEntity aRegionalLevelClassifier) {
        this.regionalLevelClassifier = aRegionalLevelClassifier;
    }

    public Set<RegionalPlanEntity> getRegionalPlan() {
        return regionalPlan;
    }

    public void setRegionalPlan(Set<RegionalPlanEntity> aRegionalPlan) {
        this.regionalPlan = aRegionalPlan;
    }

    public RegionalClassifierEntity getRegionalclasiffather() {
        return regionalClassifierFather;
    }

    public void setRegionalclasiffather(
            RegionalClassifierEntity aRegionalclasiffather) {
        this.regionalClassifierFather = aRegionalclasiffather;
    }

    @Override
    public int compareTo(RegionalClassifierEntity obj) {
        int result = -1;
        if (this.regionalClassifierId != null && obj.regionalClassifierId != null) {
            result = this.regionalClassifierId.compareTo(obj.regionalClassifierId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof RegionalClassifierEntity && this.regionalClassifierId != null
                && ((RegionalClassifierEntity) obj).regionalClassifierId != null) {
            result = this.regionalClassifierId.equals(
                    ((RegionalClassifierEntity) obj).regionalClassifierId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.regionalClassifierId != null
                ? this.regionalClassifierId.hashCode() : 0);
        return hash;
    }
}
