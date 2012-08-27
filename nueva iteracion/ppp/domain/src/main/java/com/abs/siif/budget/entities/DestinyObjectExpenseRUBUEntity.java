/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DestinyObjectExpenseRUBU
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Esta es la entidad del encuadre de la UP, UR, Objeto-Gasto y Destino
 * @author Erick Leija
 */
@Entity
@Table(name="siifpppEncDependenciaObjDestino")
public class DestinyObjectExpenseRUBUEntity implements Serializable,
        Comparable<DestinyObjectExpenseRUBUEntity>
{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdEncDependenciaObjDestino")
    private Long destinyObjectExpenseRUBUId;
     
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdDependencia", nullable = false)
    private DependenceEntity destinyObjectExpenseRUBUDependence;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdObjetoGasto", nullable = false)
    private ObjectExpenseEntity destinyObjectEpenseRUBUObject;
    
    @Column(name = "ClaveDestino")
    private String destinyObjectEpenseRUBUDestiny;
    
    @Column(name = "DescDestino")
    private String destinyObjectEpenseRUBUDestinyDesc;

    @Column(name= "Basica")
    private boolean destinyObjectEpenseRUBUBasic;
    
    @Column(name = "Inversion")
    private boolean destinyObjectEpenseRUBUInvest;
    
    
    //Aquí empiezan los setters*************************************************
    
    public void setDestinyObjectEpenseRUBUDestinyDesc(String destinyObjectEpenseRUBUDestinyDesc)
    {
        this.destinyObjectEpenseRUBUDestinyDesc = destinyObjectEpenseRUBUDestinyDesc;
    }

    public void setDestinyObjectEpenseRUBUBasic(boolean destinyObjectEpenseRUBUBasic)
    {
        this.destinyObjectEpenseRUBUBasic = destinyObjectEpenseRUBUBasic;
    }

    public void setDestinyObjectEpenseRUBUInvest(boolean destinyObjectEpenseRUBUInvest)
    {
        this.destinyObjectEpenseRUBUInvest = destinyObjectEpenseRUBUInvest;
    }
       
    public void setDestinyObjectEpenseRUBUDestiny(String destinyObjectEpenseRUBUDestiny)
    {
        this.destinyObjectEpenseRUBUDestiny = destinyObjectEpenseRUBUDestiny;
    }

    public void setDestinyObjectEpenseRUBUObject(ObjectExpenseEntity destinyObjectEpenseRUBUObject)
    {
        this.destinyObjectEpenseRUBUObject = destinyObjectEpenseRUBUObject;
    }

    public void setDestinyObjectExpenseRUBUDependence(DependenceEntity destinyObjectExpenseRUBUDependence)
    {
        this.destinyObjectExpenseRUBUDependence = destinyObjectExpenseRUBUDependence;
    }

    public void setDestinyObjectExpenseRUBUId(Long destinyObjectExpenseRUBUId)
    {
        this.destinyObjectExpenseRUBUId = destinyObjectExpenseRUBUId;
    } 
    //**************************************************************************
    
    
    //Aquí empiezan los getters*************************************************

     public String getDestinyObjectEpenseRUBUDestinyDesc()
    {
        return destinyObjectEpenseRUBUDestinyDesc;
    }

    public boolean isDestinyObjectEpenseRUBUBasic()
    {
        return destinyObjectEpenseRUBUBasic;
    }

    public boolean isDestinyObjectEpenseRUBUInvest()
    {
        return destinyObjectEpenseRUBUInvest;
    }
     
     
    public String getDestinyObjectEpenseRUBUDestiny()
    {
        return destinyObjectEpenseRUBUDestiny;
    }

    public ObjectExpenseEntity getDestinyObjectEpenseRUBUObject()
    {
        return destinyObjectEpenseRUBUObject;
    }

    public DependenceEntity getDestinyObjectExpenseRUBUDependence()
    {
        return destinyObjectExpenseRUBUDependence;
    }

    public Long getDestinyObjectExpenseRUBUId()
    {
        return destinyObjectExpenseRUBUId;
    }
    
    
    
    //**************************************************************************
    
    /**
      * Estos son los metodos implementados de la interfaz comparable
      * 
      */
    @Override
    public int compareTo(DestinyObjectExpenseRUBUEntity anOtherEntity) {
        return this.destinyObjectExpenseRUBUId.compareTo(
                anOtherEntity.destinyObjectExpenseRUBUId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DestinyObjectExpenseRUBUEntity) {
            return this.destinyObjectExpenseRUBUId.equals(
                    ((DestinyObjectExpenseRUBUEntity) obj).destinyObjectExpenseRUBUId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.destinyObjectExpenseRUBUId != null
                ? this.destinyObjectExpenseRUBUId.hashCode() : 0);
        return hash;
    }
}
