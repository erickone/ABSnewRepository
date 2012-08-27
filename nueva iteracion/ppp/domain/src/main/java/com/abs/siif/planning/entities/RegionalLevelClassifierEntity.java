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
 * @author Miguel Baizabal Aguirre
 */
@Entity
@Table(name = "siifabsnivclasifregional")
public class RegionalLevelClassifierEntity  implements
        Comparable<RegionalLevelClassifierEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdNivClasifRegional")
    private Long regionalLevelClassifierId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String regionalLevelClassifierKey;
    @Column(name = "Descripcion", length = 150)
    private String regionalLevelClassifierDescription;
    @Column(name = "Nivel")
    private int regionalLevelClassifierLevel;
    @Column(name = "Pais")
    private boolean regionalLevelClassifierIsCountry;
    @Column(name = "Estado")
    private boolean regionalLevelClassifierIsState;
    @Column(name = "Region")
    private boolean regionalLevelClassifierIsRegion;
    @Column(name = "Municipio")
    private boolean regionalLevelClassifierIsMunicipality;
    @Column(name = "Localidad")
    private boolean regionalLevelClassifierIsLocation;
    @Column(name = "Colonia")
    private boolean regionalLevelClassifierIsColony;
    @Column(name = "CodPostal")
    private boolean regionalLevelClassifierIsPostalCode;
    @Column(name = "GenColectiva")
    private boolean regionalLevelClassifierIsColectiveGenerator;
    @Column(name = "INEGI")
    private boolean regionalLevelClassifierIsINEGI;
    @Column(name = "Ambito")
    private boolean regionalLevelClassifierIsScope;
    @Column(name = "SolicitaGenero")
    private boolean regionalLevelClassifierIsGender;
    @Column(name = "Censo")
    private boolean regionalLevelClassifierIsCensus;
    @Column(name = "Anio")
    private Integer regionalLevelClassifierYear;


    @OneToMany(mappedBy = "regionalLevelClassifier")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<RegionalClassifierEntity> regionalClassifiers;

    public String getRegionalLevelClassifierDescription() {
        return regionalLevelClassifierDescription != null ? regionalLevelClassifierDescription.trim() : regionalLevelClassifierDescription;
    }

    public void setRegionalLevelClassifierDescription(String 
            aRegionalLevelClassifierDescription) {
        this.regionalLevelClassifierDescription = 
                aRegionalLevelClassifierDescription;
    }

    public Long getRegionalLevelClassifierId() {
        return regionalLevelClassifierId;
    }

    public void setRegionalLevelClassifierId(Long aRegionalLevelClassifierId) {
        this.regionalLevelClassifierId = aRegionalLevelClassifierId;
    }

    public boolean isRegionalLevelClassifierIsColectiveGenerator() {
        return regionalLevelClassifierIsColectiveGenerator;
    }

    public void setRegionalLevelClassifierIsColectiveGenerator(boolean 
            aRegionalLevelClassifierIsColectiveGenerator) {
        this.regionalLevelClassifierIsColectiveGenerator = 
                aRegionalLevelClassifierIsColectiveGenerator;
    }

    public boolean isRegionalLevelClassifierIsColony() {
        return regionalLevelClassifierIsColony;
    }

    public void setRegionalLevelClassifierIsColony(boolean 
            aRegionalLevelClassifierIsColony) {
        this.regionalLevelClassifierIsColony = aRegionalLevelClassifierIsColony;
    }

    public boolean isRegionalLevelClassifierIsCountry() {
        return regionalLevelClassifierIsCountry;
    }

    public void setRegionalLevelClassifierIsCountry(boolean 
            aRegionalLevelClassifierIsCountry) {
        this.regionalLevelClassifierIsCountry = aRegionalLevelClassifierIsCountry;
    }

    public boolean isRegionalLevelClassifierIsINEGI() {
        return regionalLevelClassifierIsINEGI;
    }

    public void setRegionalLevelClassifierIsINEGI(boolean 
            aRegionalLevelClassifierIsINEGI) {
        this.regionalLevelClassifierIsINEGI = aRegionalLevelClassifierIsINEGI;
    }

    public boolean isRegionalLevelClassifierIsLocation() {
        return regionalLevelClassifierIsLocation;
    }

    public void setRegionalLevelClassifierIsLocation(boolean 
            aRegionalLevelClassifierIsLocation) {
        this.regionalLevelClassifierIsLocation = 
                aRegionalLevelClassifierIsLocation;
    }

    public boolean isRegionalLevelClassifierIsMunicipality() {
        return regionalLevelClassifierIsMunicipality;
    }

    public void setRegionalLevelClassifierIsMunicipality(boolean 
            aRegionalLevelClassifierIsMunicipality) {
        this.regionalLevelClassifierIsMunicipality = 
                aRegionalLevelClassifierIsMunicipality;
    }

    public boolean isRegionalLevelClassifierIsPostalCode() {
        return regionalLevelClassifierIsPostalCode;
    }

    public void setRegionalLevelClassifierIsPostalCode(boolean 
            aRegionalLevelClassifierIsPostalCode) {
        this.regionalLevelClassifierIsPostalCode = 
                aRegionalLevelClassifierIsPostalCode;
    }

    public boolean isRegionalLevelClassifierIsRegion() {
        return regionalLevelClassifierIsRegion;
    }

    public void setRegionalLevelClassifierIsRegion(boolean 
            aRegionalLevelClassifierIsRegion) {
        this.regionalLevelClassifierIsRegion = aRegionalLevelClassifierIsRegion;
    }

    public boolean isRegionalLevelClassifierIsState() {
        return regionalLevelClassifierIsState;
    }

    public void setRegionalLevelClassifierIsState(boolean 
            aRegionalLevelClassifierIsState) {
        this.regionalLevelClassifierIsState = aRegionalLevelClassifierIsState;
    }

    public String getRegionalLevelClassifierKey() {
        return regionalLevelClassifierKey != null ? regionalLevelClassifierKey.trim() : regionalLevelClassifierKey;
    }

    public void setRegionalLevelClassifierKey(String 
            aRegionalLevelClassifierKey) {
        this.regionalLevelClassifierKey = aRegionalLevelClassifierKey;
    }

    public int getRegionalLevelClassifierLevel() {
        return regionalLevelClassifierLevel;
    }

    public void setRegionalLevelClassifierLevel(int 
            aRegionalLevelClassifierLevel) {
        this.regionalLevelClassifierLevel = aRegionalLevelClassifierLevel;
    }

    public boolean isRegionalLevelClassifierIsScope() {
        return regionalLevelClassifierIsScope;
    }

    public void setRegionalLevelClassifierIsScope(boolean 
            aRegionalLevelClassifierIsScope) {
        this.regionalLevelClassifierIsScope = aRegionalLevelClassifierIsScope;
    }

    /*
     * public Set<RegionalClassifierEntity> getRegionalClassifiers() { return
     * regionalClassifiers; }
     *
     * public void setRegionalClassifiers(Set<RegionalClassifierEntity>
     * regionalClassifiers) { this.regionalClassifiers = regionalClassifiers; }
     */

    /**
     * @return the regionalClassifiers
     */
    public Set<RegionalClassifierEntity> getRegionalClassifiers() {
        return regionalClassifiers;
    }

    /**
     * @param regionalClassifiers the regionalClassifiers to set
     */
    public void setRegionalClassifiers(
            Set<RegionalClassifierEntity> regionalClassifiers) {
        this.regionalClassifiers = regionalClassifiers;
    }
    
   
    @Override
    public int compareTo(RegionalLevelClassifierEntity obj)
    {
        int result = -1;
        if (this.regionalLevelClassifierId != null && obj.regionalLevelClassifierId != null) {
        result = this.regionalLevelClassifierId.compareTo(obj.regionalLevelClassifierId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof RegionalLevelClassifierEntity && this.regionalLevelClassifierId != null 
                 && ((RegionalLevelClassifierEntity) obj).regionalLevelClassifierId != null) {
            result = this.regionalLevelClassifierId.equals(
                    ((RegionalLevelClassifierEntity) obj).regionalLevelClassifierId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.regionalLevelClassifierId != null 
                ? this.regionalLevelClassifierId.hashCode() : 0);
        return hash;
    }

    public boolean isRegionalLevelClassifierIsCensus()
    {
        return regionalLevelClassifierIsCensus;
    }

    public void setRegionalLevelClassifierIsCensus(boolean regionalLevelClassifierIsCensus)
    {
        this.regionalLevelClassifierIsCensus = regionalLevelClassifierIsCensus;
    }

    public boolean isRegionalLevelClassifierIsGender()
    {
        return regionalLevelClassifierIsGender;
    }

    public void setRegionalLevelClassifierIsGender(boolean regionalLevelClassifierIsGender)
    {
        this.regionalLevelClassifierIsGender = regionalLevelClassifierIsGender;
    }

    public Integer getRegionalLevelClassifierYear()
    {
        return regionalLevelClassifierYear;
    }

    public void setRegionalLevelClassifierYear(Integer regionalLevelClassifierYear)
    {
        this.regionalLevelClassifierYear = regionalLevelClassifierYear;
    }

}
