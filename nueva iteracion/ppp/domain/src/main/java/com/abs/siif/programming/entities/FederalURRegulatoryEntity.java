/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FederalURRegulatoryEntity
 *  Purpose:  Catalogo para la UR Normativa Federal.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author FENIX-02
 */
@Entity
@Table(name = "SIIFPPPUrNormativaFed")
public class FederalURRegulatoryEntity implements Serializable, Comparable<FederalURRegulatoryEntity>{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid",
    parameters = {
        @org.hibernate.annotations.Parameter(name = "separator", value = "-")})
    @Column(name = "IdUrNormativaFed", length = 36)
    private Long federalUrRegulatoryId;
    @Column(name = "Clave", length = 150)
    private String federalUrRegulatoryKey;
    @Column(name = "Descripcion", length = 150)
    private String federalUrRegulatoryDescription;

    public String getFederalUrRegulatoryDescription()
    {
        return federalUrRegulatoryDescription;
    }

    public void setFederalUrRegulatoryDescription(String federalUrRegulatoryDescription)
    {
        this.federalUrRegulatoryDescription = federalUrRegulatoryDescription;
    }

    public Long getFederalUrRegulatoryId()
    {
        return federalUrRegulatoryId;
    }

    public void setFederalUrRegulatoryId(Long federalUrRegulatoryId)
    {
        this.federalUrRegulatoryId = federalUrRegulatoryId;
    }

    public String getFederalUrRegulatoryKey()
    {
        return federalUrRegulatoryKey != null ? federalUrRegulatoryKey.trim() : federalUrRegulatoryKey;
    }

    public void setFederalUrRegulatoryKey(String federalUrRegulatoryKey)
    {
        this.federalUrRegulatoryKey = federalUrRegulatoryKey;
    }

    @Override
    public int compareTo(FederalURRegulatoryEntity obj)
    {
        return federalUrRegulatoryId.compareTo(obj.getFederalUrRegulatoryId());
    }
    
     @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof FederalURRegulatoryEntity){
           result = ((FederalURRegulatoryEntity)obj).getFederalUrRegulatoryId().
                   equals(this.federalUrRegulatoryId); 
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.federalUrRegulatoryId != null ? 
                this.federalUrRegulatoryId.hashCode() : 0);
        return hash;
    }
    
}
