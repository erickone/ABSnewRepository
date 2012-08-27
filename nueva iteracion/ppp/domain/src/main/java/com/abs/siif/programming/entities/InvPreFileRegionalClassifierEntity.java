package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal
 */

@Entity
@Table(name = "siifpppprefichaclasifreg")
public class InvPreFileRegionalClassifierEntity
    implements Comparable<InvPreFileRegionalClassifierEntity>, Serializable{

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdPreFichaClasifReg")
    private Long idPreFichaClasifReg;

    // Identificador de la PreFicha
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity invPreFileEntity;

    // Identificador del Clasificador Regional
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdClasifRegional", nullable = false)
    private RegionalClassifierEntity invPreFileRegClasifRegClasif;

    // Ponderación o Porcentaje
    @Column(name = "Porcentaje",  nullable = true)
    private double percent;

    public Long getIdPreFichaClasifReg() {
        return idPreFichaClasifReg;
    }

    public void setIdPreFichaClasifReg(Long idPreFichaClasifReg) {
        this.idPreFichaClasifReg = idPreFichaClasifReg;
    }

    public InvPreFileEntity getInvPreFileEntity() {
        return invPreFileEntity;
    }

    public void setInvPreFileEntity(InvPreFileEntity invPreFileEntity) {
        this.invPreFileEntity = invPreFileEntity;
    }

    public RegionalClassifierEntity getInvPreFileRegClasifRegClasif() {
        return invPreFileRegClasifRegClasif;
    }

    public void setInvPreFileRegClasifRegClasif(RegionalClassifierEntity invPreFileRegClasifRegClasif) {
        this.invPreFileRegClasifRegClasif = invPreFileRegClasifRegClasif;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    
    @Override
    public int compareTo(InvPreFileRegionalClassifierEntity obj) {
       return this.idPreFichaClasifReg.compareTo(obj.getIdPreFichaClasifReg());
    }

    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if (obj instanceof InvPreFileRegionalClassifierEntity){
            this.idPreFichaClasifReg.equals(((InvPreFileRegionalClassifierEntity)obj).getIdPreFichaClasifReg());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idPreFichaClasifReg != null ? 
                this.idPreFichaClasifReg.hashCode() : 0);
        return hash;
    }
   
}
