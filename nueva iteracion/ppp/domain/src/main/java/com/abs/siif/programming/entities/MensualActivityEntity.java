package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
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
@Table(name = "siifpppactividadmens")
public class MensualActivityEntity implements
        Comparable<MensualActivityEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdActividadMens")
    private Long mensualActivityId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "IdActividad", nullable = false)
    private ActivityEntity activity;
    @Column(name = "Distribucion", nullable = false)
    private double distribution;
    @Column(name = "Mes", length = 10, nullable = false)
    private String mes;

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double aDistribution) {
        this.distribution = aDistribution;
    }

    public Long getMensualActivityId() {
        return mensualActivityId;
    }

    public void setMensualActivityId(Long aMensualActivityId) {
        this.mensualActivityId = aMensualActivityId;
    }

    public String getMes() {
        return mes != null ? mes.trim() : mes;
    }

    public void setMes(String aMes) {
        this.mes = aMes;
    }

    /**
     * @return the activity
     */
    public ActivityEntity getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(ActivityEntity activity) {
        this.activity = activity;
    }
    
    @Override
    public int compareTo(MensualActivityEntity obj) {
        int result = -1;
        if (this.mensualActivityId != null && obj.mensualActivityId != null) {
            result = this.mensualActivityId.compareTo(obj.mensualActivityId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof MensualActivityEntity
                && this.mensualActivityId != null
                && ((MensualActivityEntity) obj).mensualActivityId != null) {

            result = this.mensualActivityId.equals(
                    ((MensualActivityEntity) obj).mensualActivityId);

        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.mensualActivityId != null
                ? this.mensualActivityId.hashCode() : 0);
        return hash;
    }
}
