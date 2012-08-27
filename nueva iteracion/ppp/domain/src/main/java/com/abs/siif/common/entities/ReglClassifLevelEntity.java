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
@Table(name="SIIFAbsNivClasifRegional")
public class ReglClassifLevelEntity implements Serializable {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name="IdNivClasifRegional")
    private Long RegClassifLevelId;
    @Column(name="Clave")
    private String RegClassifKey;
    @Column(name="Descripcion")
    private String RegClassifDescription;
    @Column(name="Pais")
    private boolean RegClassifCountry;
    @Column(name="Estado")
    private boolean RegClassifState;
    @Column(name="Region")
    private boolean RegClassifRegion;
    @Column(name="Municipio")
    private boolean RegClassifTownShip;

    public boolean isRegClassifCountry() {
        return RegClassifCountry;
    }

    public void setRegClassifCountry(boolean RegClassifCountry) {
        this.RegClassifCountry = RegClassifCountry;
    }

    public String getRegClassifDescription() {
        return RegClassifDescription;
    }

    public void setRegClassifDescription(String RegClassifDescription) {
        this.RegClassifDescription = RegClassifDescription;
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

    public boolean isRegClassifRegion() {
        return RegClassifRegion;
    }

    public void setRegClassifRegion(boolean RegClassifRegion) {
        this.RegClassifRegion = RegClassifRegion;
    }

    public boolean isRegClassifState() {
        return RegClassifState;
    }

    public void setRegClassifState(boolean RegClassifState) {
        this.RegClassifState = RegClassifState;
    }

    public boolean isRegClassifTownShip() {
        return RegClassifTownShip;
    }

    public void setRegClassifTownShip(boolean RegClassifTownShip) {
        this.RegClassifTownShip = RegClassifTownShip;
    }
    
    
    
    
}
