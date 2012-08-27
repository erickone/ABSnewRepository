package com.abs.siif.planning.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 * 
 * MBA 20120613 : Se quita la relación ManyToMany entre ObjectiveEntity e InstitutionalPlanEntity
 * 
 */
@Entity
@Table(name = "siifpppPlanInstitucional")
public class InstitutionalPlanEntity  implements 
        Comparable<InstitutionalPlanEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdPlanInstitucional")
    private Long institutionalPlanId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "IdDependencia")
    private DependenceEntity dependence;
    @Column(name = "NumPerOficial", length = 30)
    private String institutionalNumPerOficial;
    @Column(name = "FechaPubPerOficial")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date institutionalOfficialPubOnDailyDate;
    @Column(name = "FechaIniActividades")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date institutionalStartDateOfActivities;
    @Column(name = "FechaExtincion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date institutionalDateOfExtintion;
    @Column(name = "FormaExtincion", length = 100)
    private String institutionalFormOfExtintion;
    @Column(name = "NumPerOfiUltModif", length = 30)
    private String institutionalNumberOfLastModif;
    @Column(name = "FechaPubPerUltModif")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date institutionalDateOfLastModif;
    @Column(name = "NumPerOfiRegInterno", length = 30)
    private String institutionalInternalReg;
    @Column(name = "FechaPubRegInterno")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date institutionalInternalRegDate;
    @Column(name = "FechaActOrganigrama")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date institutionalActualDate;
    @Column(name = "Organigrama", length = 500)
    private String institutionalOrganizational;
    @Column(name = "FundamentoLegal", length = 100)
    private String institutionalLegalBasis;
    @Column(name = "Mision", length = 250)
    private String institutionalMission;
    @Column(name = "Vision", length = 250)
    private String institutionalVision;
    @Column(name = "Diagnostico", length = 250)
    private String institutionalDiag;
    @Column(name = "ObjetivoGeneral", length = 250)
    private String institutionalGeneralObjective;
    @Column(name = "EjecInversion")
    private boolean hasInstitutionalInvesEjec;
    @Column(name = "NormaInversion")
    private boolean hasInstitutionalInvesNorm;
    @Column(name = "Bloqueado")
    private boolean hasInstitutionaBlock;
    @Column(name = "Organismo")
    private boolean hasInstitutionalOrganism;
    
    public DependenceEntity getDependence() {
        return dependence;
    }

    public void setDependence(DependenceEntity aDependence) {
        this.dependence = aDependence;
    }

    public boolean getInstitutionaBlock() {
        return hasInstitutionaBlock;
    }

    public void setInstitutionaBlock(boolean anHasInstitutionaBlock) {
        this.hasInstitutionaBlock = anHasInstitutionaBlock;
    }

    public Date getInstitutionalActualDate() {
        return institutionalActualDate;
    }

    public void setInstitutionalActualDate(Date anInstitutionalActualDate) {
        this.institutionalActualDate = anInstitutionalActualDate;
    }

    public Date getInstitutionalDateOfExtintion() {
        return institutionalDateOfExtintion;
    }

    public void setInstitutionalDateOfExtintion(Date 
            anInstitutionalDateOfExtintion) {
        this.institutionalDateOfExtintion = anInstitutionalDateOfExtintion;
    }

    public Date getInstitutionalDateOfLastModif() {
        return institutionalDateOfLastModif;
    }

    public void setInstitutionalDateOfLastModif(Date 
            anInstitutionalDateOfLastModif) {
        this.institutionalDateOfLastModif = anInstitutionalDateOfLastModif;
    }

    public String getInstitutionalDiag() {
        return institutionalDiag != null ? institutionalDiag.trim() : institutionalDiag;
    }

    public void setInstitutionalDiag(String anInstitutionalDiag) {
        this.institutionalDiag = anInstitutionalDiag;
    }

    public String getInstitutionalFormOfExtintion() {
        return institutionalFormOfExtintion != null ? institutionalFormOfExtintion.trim() : institutionalFormOfExtintion;
    }

    public void setInstitutionalFormOfExtintion(String 
            anInstitutionalFormOfExtintion) {
        this.institutionalFormOfExtintion = anInstitutionalFormOfExtintion;
    }

    public String getInstitutionalGeneralObjective() {
        return institutionalGeneralObjective != null ? institutionalGeneralObjective.trim() : institutionalGeneralObjective;
    }

    public void setInstitutionalGeneralObjective(String 
            anInstitutionalGeneralObjective) {
        this.institutionalGeneralObjective = anInstitutionalGeneralObjective;
    }

    public String getInstitutionalInternalReg() {
        return institutionalInternalReg != null ? institutionalInternalReg.trim() : institutionalInternalReg;
    }

    public void setInstitutionalInternalReg(String anInstitutionalInternalReg) {
        this.institutionalInternalReg = anInstitutionalInternalReg;
    }

    public Date getInstitutionalInternalRegDate() {
        return institutionalInternalRegDate;
    }

    public void setInstitutionalInternalRegDate(Date 
            anInstitutionalInternalRegDate) {
        this.institutionalInternalRegDate = anInstitutionalInternalRegDate;
    }

    public boolean getHasInstitutionalInvesEjec() {
        return hasInstitutionalInvesEjec;
    }

    public void setHasInstitutionalInvesEjec(boolean 
            anHasInstitutionalInvesEjec) {
        this.hasInstitutionalInvesEjec = anHasInstitutionalInvesEjec;
    }

    public boolean getHasInstitutionalInvesNorm() {
        return hasInstitutionalInvesNorm;
    }

    public void setHasInstitutionalInvesNorm(boolean 
            anHasInstitutionalInvesNorm) {
        this.hasInstitutionalInvesNorm = anHasInstitutionalInvesNorm;
    }

    public String getInstitutionalLegalBasis() {
        return institutionalLegalBasis != null ? institutionalLegalBasis.trim() : institutionalLegalBasis;
    }

    public void setInstitutionalLegalBasis(String anInstitutionalLegalBasis) {
        this.institutionalLegalBasis = anInstitutionalLegalBasis;
    }

    public String getInstitutionalMission() {
        return institutionalMission != null ? institutionalMission.trim() : institutionalMission;
    }

    public void setInstitutionalMission(String anInstitutionalMission) {
        this.institutionalMission = anInstitutionalMission;
    }

    public String getInstitutionalNumPerOficial() {
        return institutionalNumPerOficial != null ? institutionalNumPerOficial.trim() : institutionalNumPerOficial;
    }

    public void setInstitutionalNumPerOficial(String 
            anInstitutionalNumPerOficial) {
        this.institutionalNumPerOficial = anInstitutionalNumPerOficial;
    }

    public String getInstitutionalNumberOfLastModif() {
        return institutionalNumberOfLastModif != null ? institutionalNumberOfLastModif.trim() : institutionalNumberOfLastModif;
    }

    public void setInstitutionalNumberOfLastModif(String 
            anInstitutionalNumberOfLastModif) {
        this.institutionalNumberOfLastModif = anInstitutionalNumberOfLastModif;
    }

    public Date getInstitutionalOfficialPubOnDailyDate() {
        return institutionalOfficialPubOnDailyDate;
    }

    public void setInstitutionalOfficialPubOnDailyDate(Date 
            anInstitutionalOfficialPubOnDailyDate) {
        this.institutionalOfficialPubOnDailyDate = anInstitutionalOfficialPubOnDailyDate;
    }

    public boolean getHasInstitutionalOrganism() {
        return hasInstitutionalOrganism;
    }

    public void setHasInstitutionalOrganism(boolean 
            anHasInstitutionalOrganism) {
        this.hasInstitutionalOrganism = anHasInstitutionalOrganism;
    }

    public String getInstitutionalOrganizational() {
        return institutionalOrganizational != null ? institutionalOrganizational.trim() : institutionalOrganizational;
    }

    public void setInstitutionalOrganizational(String 
            anInstitutionalOrganizational) {
        this.institutionalOrganizational = anInstitutionalOrganizational;
    }

    public Long getInstitutionalPlanId() {
        return institutionalPlanId;
    }

    public void setInstitutionalPlanId(Long anInstitutionalPlanId) {
        this.institutionalPlanId = anInstitutionalPlanId;
    }

    public String getInstitutionalVision() {
        return institutionalVision != null ? institutionalVision.trim() : institutionalVision;
    }

    public void setInstitutionalVision(String anInstitutionalVision) {
        this.institutionalVision = anInstitutionalVision;
    }

    public Date getInstitutionalstartDateOfActivities() {
        return institutionalStartDateOfActivities;
    }

    public void setInstitutionalstartDateOfActivities(Date 
            anInstitutionalstartDateOfActivities) {
        this.institutionalStartDateOfActivities = 
                anInstitutionalstartDateOfActivities;
    }

    public void AssignDependence(DependenceEntity aDependence) {
        this.dependence = aDependence;
        aDependence.AddInstitutionalPlans(this);
    }

    @Override
    public int compareTo(InstitutionalPlanEntity obj)
    {
        int result = -1;
        if (this.institutionalPlanId != null 
                && obj.institutionalPlanId != null) {
        result = this.institutionalPlanId.compareTo(obj.institutionalPlanId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof InstitutionalPlanEntity 
                 && this.institutionalPlanId != null 
                 && ((InstitutionalPlanEntity) obj).institutionalPlanId != null) {
            result = this.institutionalPlanId.equals(
                    ((InstitutionalPlanEntity) obj).institutionalPlanId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + (this.institutionalPlanId != null 
                ? this.institutionalPlanId.hashCode() : 0);
        return hash;
    }
}
