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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifpppcomponente")
public class ComponentEntity implements
        Comparable<ComponentEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdComponente")
    private Long componentId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "IdEntregable", nullable = false)
    private DeliveryEntity delivery;
    @Column(name = "Descripcion", length = 255, nullable = false)
    private String compDesc;
    // (COMMON)
    @ManyToOne
    @JoinColumn(name = "IdUniMedida", nullable = false)
    private UnitMeasureEntity unitMeasure;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "component")
    private Set<MensualComponentEntity> mensualComponents;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "component")
    private Set<ActivityEntity> activities;
    
    @Column(name = "PondeRecurso", nullable = false)
    private int weighingResource;
    @Column(name = "PondeImportancia", nullable = false)
    private int weighingImport;
    @Column(name = "NombreIndicador", length = 255, nullable = false)
    private String indicatorName; //Indicador
    @Column(name = "DescIndicador", length = 255, nullable = false)
    private String indicatorDescription;
    @Column(name = "Responsable", length = 150, nullable = false)
    private String reponsable;
    
    
    private transient ActivityEntity currentActivity;

    private transient String idProposit;

    private transient java.util.Map<String, String>mapGolPro;
    
    private transient String number;
    
    public DeliveryEntity getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryEntity aDelivery) {
        this.delivery = aDelivery;
    }

    public String getIndicatorname() {
        return indicatorName != null ? indicatorName.trim() : indicatorName;
    }

    public void setIndicatorname(String anIndicatorName) {
        this.indicatorName = anIndicatorName;
    }

  

    public String getIndicatordescription() {
        return indicatorDescription != null ? indicatorDescription.trim() : indicatorDescription;
    }

    public void setIndicatordescription(String anIndicatorDescription) {
        this.indicatorDescription = anIndicatorDescription;
    }

    public UnitMeasureEntity getUnitmeasure() {
        return unitMeasure;
    }

    public void setUnitmeasure(UnitMeasureEntity aUnitMeasure) {
        this.unitMeasure = aUnitMeasure;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long aComponentId) {
        this.componentId = aComponentId;
    }

    /**
     * @return the currentActivity
     */
    public ActivityEntity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * @param currentActivity the currentActivity to set
     */
    public void setCurrentActivity(ActivityEntity aCurrentActivity) {
        this.currentActivity = aCurrentActivity;
    }

    /**
     * @return the mensualcomponents
     */
    public Set<MensualComponentEntity> getMensualcomponents() {
        return mensualComponents;
    }

    /**
     * @param mensualcomponents the mensualcomponents to set
     */
    public void setMensualcomponents(Set<MensualComponentEntity> mensualComponents) {
        this.mensualComponents = mensualComponents;
    }

    /**
     * @return the activities
     */
    public Set<ActivityEntity> getActivities() {
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(Set<ActivityEntity> activities) {
        this.activities = activities;
    }

    /**
     * @return the idProposit
     */
    public String getIdProposit() {
        return idProposit != null ? idProposit.trim() : idProposit;
    }

    /**
     * @param idProposit the idProposit to set
     */
    public void setIdProposit(String anIdProposit) {
        this.idProposit = anIdProposit;
    }

    /**
     * @return the compDesc
     */
    public String getCompDesc() {
        return compDesc != null ? compDesc.trim() : compDesc;
    }

    /**
     * @param compDesc the compDesc to set
     */
    public void setCompDesc(String aCompDesc) {
        this.compDesc = aCompDesc;
    }

    /**
     * @return the mapGolPro
     */
    public java.util.Map<String, String> getMapGolPro() {
        return mapGolPro;
    }

    /**
     * @param mapGolPro the mapGolPro to set
     */
    public void setMapGolPro(java.util.Map<String, String> aMapGolPro) {
        this.mapGolPro = aMapGolPro;
    }

    @Override
    public int compareTo(ComponentEntity obj)
    {
        int result = -1;
        if (this.componentId != null && obj.componentId != null) {
            result = this.componentId.compareTo(obj.componentId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
         boolean result = false;
         if (obj instanceof ComponentEntity && this.componentId != null
                 && ((ComponentEntity) obj).componentId != null) {
            result = this.componentId.equals(
                    ((ComponentEntity) obj).componentId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 47 * hash + (this.componentId != null
                ? this.componentId.hashCode() : 0);
        return hash;
    }

    /**
     * @return the weighingResource
     */
    public int getWeighingResource() {
        return weighingResource;
    }

    /**
     * @param weighingResource the weighingResource to set
     */
    public void setWeighingResource(int weighingResource) {
        this.weighingResource = weighingResource;
    }

    /**
     * @return the weighingImport
     */
    public int getWeighingImport() {
        return weighingImport;
    }

    /**
     * @param weighingImport the weighingImport to set
     */
    public void setWeighingImport(int weighingImport) {
        this.weighingImport = weighingImport;
    }

    /**
     * @return the reponsable
     */
    public String getReponsable() {
        return reponsable != null ? reponsable.trim() : reponsable;
    }

    /**
     * @param reponsable the reponsable to set
     */
    public void setReponsable(String reponsable) {
        this.reponsable = reponsable;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number != null ? number.trim() : number; 
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }


}
