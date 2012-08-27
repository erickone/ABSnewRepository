/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.common.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */
@Entity
@Table(name="SIIFAbsClasifRegional")
public class RegClassifEntity implements Serializable {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name="IdClasifRegional")
    private Long RegClassifId;
    @Column(name="IdNivClasifRegional")
    private Long RegClassifLevelId;
    @Column(name="Clave", length=100)
    private String RegClassifKey;
    @Column(name="Descripcion")
    private String RegClassifDescription;
    

    public String getRegClassifDescription() {
        return RegClassifDescription;
    }

    public void setRegClassifDescription(String RegClassifDescription) {
        this.RegClassifDescription = RegClassifDescription;
    }

    public Long getRegClassifId() {
        return RegClassifId;
    }

    public void setRegClassifId(Long RegClassifId) {
        this.RegClassifId = RegClassifId;
    }

    public String getRegClassifKey() {
        return RegClassifKey;
    }

    public void setRegClassifKey(String RegClassifKey) {
        this.RegClassifKey = RegClassifKey;
    }

    public Long getRegClassifLevelId() {
        return RegClassifLevelId;
    }

    public void setRegClassifLevelId(Long RegClassifLevelId) {
        this.RegClassifLevelId = RegClassifLevelId;
    }
    
    
}
