/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObjectiveIndicatorEntity
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */

@Entity
@Table(name = "siifpppobjindicador")
public class ObjectiveIndicatorEntity  implements
Comparable<ObjectiveIndicatorEntity>, Serializable 
{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid",
    parameters = {
        @org.hibernate.annotations.Parameter(name = "separator", value = "-")})
    @Column(name = "idobjindicador")
    private String objIndicatorId;
    
    @ManyToOne
    @JoinColumn(name = "idobjetivo", nullable = false)
    private ObjectiveEntity objective;
    
    @Column(name="descripcion", nullable = false, length = 255)
    private String objIndicatorDescription;


    //Aqui empiezan los setters
    public void setObjIndicatorDescription(String objIndicatorDescription) {
        this.objIndicatorDescription = objIndicatorDescription;
    }

    public void setObjIndicatorId(String objIndicatorId) {
        this.objIndicatorId = objIndicatorId;
    }

    public void setObjective(ObjectiveEntity objIndicatorObjective) {
        this.objective = objIndicatorObjective;
    }
    
    //Aqui empiezan los Getters

    public String getObjIndicatorDescription() {
        return objIndicatorDescription != null ? objIndicatorDescription.trim() : objIndicatorDescription;
    }

    public String getObjIndicatorId() {
        return objIndicatorId != null ? objIndicatorId.trim() : objIndicatorId;
    }

    public ObjectiveEntity getObjective() {
        return this.objective;
    }
    
    
    //Aqui empiezan los metodo que son implementaciones del comparable
    @Override
    public int compareTo(ObjectiveIndicatorEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    
    
}
