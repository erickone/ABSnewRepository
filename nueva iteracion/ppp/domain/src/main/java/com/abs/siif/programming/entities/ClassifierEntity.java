/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author FENIX-02
 */
@Entity
@Table(name = "SIIFPPPClasificacion")
public class ClassifierEntity implements
        Serializable, Comparable<ClassifierEntity>
{
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdClasificacion")
    private Long classifierId;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdFinalidad", nullable = false)
    private FunctionalClassifierEntity finalidad;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdFuncion", nullable = false)
    private FunctionalClassifierEntity funcion;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdSubFuncion", nullable = false)
    private FunctionalClassifierEntity subFuncion;
    
     /**
     * Anteproyecto con el cual se relaciona
     */
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdAnteProyecto", nullable = false)
    private DraftProjectEntity classifierDraftProject;

    public Long getClassifierId()
    {
        return classifierId;
    }

    public void setClassifierId(Long aClassifierId)
    {
        this.classifierId = aClassifierId;
    }

    public FunctionalClassifierEntity getFinalidad()
    {
        return finalidad;
    }

    public void setFinalidad(FunctionalClassifierEntity aFinalidad)
    {
        this.finalidad = aFinalidad;
    }

    public FunctionalClassifierEntity getFuncion()
    {
        return funcion;
    }

    public void setFuncion(FunctionalClassifierEntity aFuncion)
    {
        this.funcion = aFuncion;
    }

    public FunctionalClassifierEntity getSubFuncion()
    {
        return subFuncion;
    }

    public void setSubFuncion(FunctionalClassifierEntity aSubFuncion)
    {
        this.subFuncion = aSubFuncion;
    }

    public DraftProjectEntity getClassifierDraftProject()
    {
        return classifierDraftProject;
    }

    public void setClassifierDraftProject(DraftProjectEntity classifierDraftProject)
    {
        this.classifierDraftProject = classifierDraftProject;
    }

    @Override
    public int compareTo(ClassifierEntity anOtherEntity)
    {
        int result = -1;
        if (this.classifierId != null
                && anOtherEntity.classifierId != null) {
            result = this.classifierId.compareTo(
                    anOtherEntity.classifierId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ClassifierEntity
                && this.classifierId != null
                && ((ClassifierEntity) obj).classifierId != null) {
            result = this.classifierId.equals(
                    ((ClassifierEntity) obj).classifierId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.classifierId != null
                ? this.classifierId.hashCode() : 0);
        return hash;
    }
    
}
