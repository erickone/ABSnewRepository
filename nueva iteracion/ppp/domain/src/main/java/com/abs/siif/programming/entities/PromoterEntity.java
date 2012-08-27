package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *Entidad utilizada dentro de la generación de la preficha
 * @author Israel Ruiz
 * 
 * Miguel Baizabal Aguirre : Mapeado
 */

@Entity
@Table(name = "siifppppromotor")
public class PromoterEntity
    implements Comparable<PromoterEntity>, Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdPromotor" )
    private Long idPromoter;
    
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String description;

    /**
     * @return the idPromoter
     */
    public Long getIdPromoter() {
        return idPromoter;
    }

    /**
     * @param idPromoter the idPromoter to set
     */
    public void setIdPromoter(Long idPromoter) {
        this.idPromoter = idPromoter;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description != null ? description.trim() : description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(PromoterEntity obj) {
        return idPromoter.compareTo(obj.getIdPromoter());
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof PromoterEntity){
           result = ((PromoterEntity)obj).getIdPromoter().
                   equals(this.idPromoter); 
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.idPromoter != null ? 
                this.idPromoter.hashCode() : 0);
        return hash;
    }
    
}
