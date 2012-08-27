package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifpppactividad")
public class ActivityEntity implements
        Comparable<ActivityEntity>, Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdActividad")
    private Long activityId;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String description;
    @Column(name = "Responsable", length = 50, nullable = false)
    private String responsible;
    @Column(name = "FechaInicio", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column(name = "FechaFin", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "IdComponente", nullable = false)
    private ComponentEntity component;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private Set<MensualActivityEntity> mensualActivities;
    @Column(name = "Cantidad", nullable = false)
    private double quantity;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long anActivityId) {
        this.activityId = anActivityId;
    }

    public String getDescription() {
        return description != null ? description.trim() : description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date anEndDate) {
        this.endDate = anEndDate;
    }

    public String getResponsible() {
        return responsible != null ? responsible.trim() : responsible;
    }

    public void setResponsible(String aResponsible) {
        this.responsible = aResponsible;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date aStartDate) {
        this.startDate = aStartDate;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double aQuantity) {
        this.quantity = aQuantity;
    }

    /**
     * @return the mensualactivities
     */
    public Set<MensualActivityEntity> getMensualactivities() {
        return mensualActivities;
    }

    /**
     * @param mensualactivities the mensualactivities to set
     */
    public void setMensualactivities(Set<MensualActivityEntity> mensualactivities) {
        this.mensualActivities = mensualactivities;
    }

    /**
     * @return the component
     */
    public ComponentEntity getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(ComponentEntity component) {
        this.component = component;
    }
    
    @Override
    public int compareTo(ActivityEntity obj)
    {
        int result = -1;
        if (this.activityId != null && obj.activityId != null) {
        result = this.activityId.compareTo(obj.activityId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
         boolean result = false;
         if (obj instanceof ActivityEntity && this.activityId != null 
                 && ((ActivityEntity) obj).activityId != null) {
            result = this.activityId.equals(
                    ((ActivityEntity) obj).activityId);
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 23 * hash + (this.activityId != null
                ? this.activityId.hashCode() : 0);
        return hash;
    }

}
