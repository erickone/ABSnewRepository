/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalPlansOfPED
 *  Purpose:  [ entidad es la encargada de guardar los ID's de los
 *  Planes Regionales que Seleccionados en la Vinculación al PED]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.RegionalPlanEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */
@Entity
@Table(name = "siifpppanteproyplanreg")
public class RegionalPlansOfPEDEntity
implements Comparable<RegionalPlansOfPEDEntity>, 
Serializable
{
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idanteproyplanregional")
    private Long PEDregionalPlanId;

    //Esta es la Relación al Anteproyecto
    @ManyToOne
    @JoinColumn(name="idanteproyecto",nullable = false)
    private DraftProjectEntity PEDdraftProjectId;
    
    //Esta es la Relación con los Planes Regionales
    @ManyToOne
    @JoinColumn(name="idplanregional", nullable = false)
    private RegionalPlanEntity regionalPlansOfPED;
    
    //Aqui inician los Getters 

    public DraftProjectEntity getPEDdraftProjectId() {
        return PEDdraftProjectId;
    }

    public Long getPEDregionalPlanId() {
        return PEDregionalPlanId;
    }

    public RegionalPlanEntity getRegionalPlansOfPED() {
        return regionalPlansOfPED;
    }
    
    //Aqui inician los Setters 

    public void setPEDdraftProjectId(DraftProjectEntity PEDdraftProjectId) {
        this.PEDdraftProjectId = PEDdraftProjectId;
    }

    public void setPEDregionalPlanId(Long PEDregionalPlanId) {
        this.PEDregionalPlanId = PEDregionalPlanId;
    }

    public void setRegionalPlansOfPED(RegionalPlanEntity regionalPlanOfPED) {
        this.regionalPlansOfPED = regionalPlanOfPED;
    }
    
    
    
    //Estos son los metodos que se heradan del comparable
    @Override
    public int compareTo(RegionalPlansOfPEDEntity obj)
    {
        int result = -1;
        if (this.PEDregionalPlanId != null && obj.PEDregionalPlanId != null) {
        result = this.PEDregionalPlanId.compareTo(obj.PEDregionalPlanId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof RegionalPlansOfPEDEntity 
                && this.PEDregionalPlanId != null
                && ((RegionalPlansOfPEDEntity) obj).PEDregionalPlanId != null) 
        {
            result = this.PEDregionalPlanId.equals(
                    ((RegionalPlansOfPEDEntity) obj).PEDregionalPlanId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.PEDregionalPlanId != null 
                ? this.PEDregionalPlanId.hashCode() : 0);
        return hash;
    }
    
    
}
