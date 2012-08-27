package com.abs.siif.budget.entities;

import com.abs.siif.programming.entities.FederalURRegulatoryEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Luis Agustin Carreón
 */
@Entity
@Table(name = "SIIFPPPReqPreinversion")
public class PreInvRequestEntity implements
        Serializable, Comparable<PreInvRequestEntity> {
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdReqPreinversion")
    private Long preInvRequestId;
    @Column(name="ActaIntegComiteObra", nullable=true)
    private boolean integrationAct;
    @Column(name="ActaAceptComunidad", nullable=true)
    private boolean acceptanceAct;
    @Column(name="DictamenFactibilidad", nullable=true)
    private boolean feasibilityDictum;
    @Column(name="DictamenImpAmb", nullable=true)
    private boolean envImpactDictum;
    @Column(name="AcredPropiedadPredio", nullable=true)
    private boolean accreditationOfProperty;
    @Column(name="AutorizacionUsoSuelo", nullable=true)
    private boolean authoOfUse;
    @Column(name="EstudioMecanicaSuelos", nullable=true)
    private boolean soilMechanics;
    @Column(name="ProyectoEjecutivo", nullable=true)
    private boolean executiveProject;
    @Column(name="EstudioCostoBeneficio", nullable=true)
    private boolean costBenefitStudy;
    @Column(name="OtroTipoDeRequerimiento", nullable=true)
    private boolean advanceLevel;
    @Column(name="OtrosEstudios", nullable=true, length = 100)
    private String otherStudiesDescription;
    @ManyToOne
    @JoinColumn(name = "idUrNormativaFed", nullable = true)
    private FederalURRegulatoryEntity federalDependenceId;
    @Column(name="NumeroAutorizacion", nullable=true, length=10)
    private String auhtNumber;
    // Identificador de la PreFicha llave foranea
    @ManyToOne
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity preInvRequestPreFile;
    /**
     * Archivos cargados por el usuario, puede ser uno o varios
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "siifppparchivoreqpreinv", joinColumns = {
        @JoinColumn(name = "IdReqPreinversion", nullable = false, updatable = false)
    },
    inverseJoinColumns = {
        @JoinColumn(name = "idarchivo",
        nullable = false, updatable = false)
    })
    private List<RequestUploadFilesEntity> uploadedFiles;

    public InvPreFileEntity getPreInvRequestPreFile() {
        return preInvRequestPreFile;
    }

    public void setPreInvRequestPreFile(InvPreFileEntity preInvRequestPreFile) {
        this.preInvRequestPreFile = preInvRequestPreFile;
    }

    public FederalURRegulatoryEntity getFederalDependenceId() {
        return federalDependenceId;
    }

    public void setFederalDependenceId(FederalURRegulatoryEntity federalDependenceId) {
        this.federalDependenceId = federalDependenceId;
    }


    public boolean isAcceptanceAct() {
        return acceptanceAct;
    }

    public void setAcceptanceAct(boolean acceptanceAct) {
        this.acceptanceAct = acceptanceAct;
    }

    public boolean isAccreditationOfProperty() {
        return accreditationOfProperty;
    }

    public void setAccreditationOfProperty(boolean accreditationOfProperty) {
        this.accreditationOfProperty = accreditationOfProperty;
    }

    public boolean isAdvanceLevel() {
        return advanceLevel;
    }

    public void setAdvanceLevel(boolean advanceLevel) {
        this.advanceLevel = advanceLevel;
    }

    public String getAuhtNumber() {
        return auhtNumber != null ? auhtNumber.trim() : auhtNumber;
    }

    public void setAuhtNumber(String auhtNumber) {
        this.auhtNumber = auhtNumber;
    }

    public boolean isAuthoOfUse() {
        return authoOfUse;
    }

    public void setAuthoOfUse(boolean authoOfUse) {
        this.authoOfUse = authoOfUse;
    }

    public boolean isCostBenefitStudy() {
        return costBenefitStudy;
    }

    public void setCostBenefitStudy(boolean costBenefitStudy) {
        this.costBenefitStudy = costBenefitStudy;
    }

    public boolean isEnvImpactDictum() {
        return envImpactDictum;
    }

    public void setEnvImpactDictum(boolean envImpactDictum) {
        this.envImpactDictum = envImpactDictum;
    }

    public boolean isExecutiveProject() {
        return executiveProject;
    }

    public void setExecutiveProject(boolean executiveProject) {
        this.executiveProject = executiveProject;
    }

    public boolean isFeasibilityDictum() {
        return feasibilityDictum;
    }

    public void setFeasibilityDictum(boolean feasibilityDictum) {
        this.feasibilityDictum = feasibilityDictum;
    }

    public FederalURRegulatoryEntity getFederalDependence() {
        return federalDependenceId;
    }

    public void setFederalDependence(FederalURRegulatoryEntity federalDependence) {
        this.federalDependenceId = federalDependence;
    }

    public boolean isIntegrationAct() {
        return integrationAct;
    }

    public void setIntegrationAct(boolean integrationAct) {
        this.integrationAct = integrationAct;
    }

    public String getOtherStudiesDescription() {
        return otherStudiesDescription != null ? otherStudiesDescription.trim() :
               otherStudiesDescription;
    }

    public void setOtherStudiesDescription(String otherStudiesDescription) {
        this.otherStudiesDescription = otherStudiesDescription;
    }

    public Long getPreInvRequestId() {
        return preInvRequestId;
    }

    public void setPreInvRequestId(Long preInvRequestId) {
        this.preInvRequestId = preInvRequestId;
    }

    public boolean isSoilMechanics() {
        return soilMechanics;
    }

    public void setSoilMechanics(boolean soilMechanics) {
        this.soilMechanics = soilMechanics;
    }

    public List<RequestUploadFilesEntity> getUploadedFiles()
    {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<RequestUploadFilesEntity> uploadedFiles)
    {
        this.uploadedFiles = uploadedFiles;
    }
    
    @Override
    public int compareTo(PreInvRequestEntity anOtherEntity) {
        return this.preInvRequestId.compareTo(
                anOtherEntity.preInvRequestId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PreInvRequestEntity) {
            return this.preInvRequestId.equals(
                    ((PreInvRequestEntity) obj).preInvRequestId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.preInvRequestId != null
                ? this.preInvRequestId.hashCode() : 0);
        return hash;
    }

}
