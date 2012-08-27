/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.planning.data.DraftFileType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.FederalURRegulatoryEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.PromoterEntity;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase contien las Dependencias u Organizmos Involucrados
 * @author Israel Ruiz
 */
public class DependenciesInvPreFileDto implements Serializable {
    /**
     * UR Ejecutora
     * 
     */
    private DependenceEntity urExecuting;
    /**
     * Unidades ejecutoras del gasto
     */
    private List<DependenceEntity>  unitExeExpend;
    /**
     * UR Normativas
     */
    private List<FederalURRegulatoryEntity> urNorm;
    /**
     * Catalogo de Promotores
     */
    private List<PromoterEntity> promoters;
    /**
     * Components from DraftProject
     */
    private List<ComponentEntity> components;
    
    /**
     * Indica el tipo de Anteproyecto que se tiene
     * 
     */
    private DraftFileType draftFileType;
    
    /**
     * Pre Ficha de Inversion, cuando es nueva es null
     * si esta en modo de edición se provee la entidad
     */
    private InvPreFileEntity invPreFileEntity;
    /**
     * Unidad ejecutora de gasto que proviene de la ficha madre.
     */
    private DependenceEntity urExecutingFicha;
    /**
     * Componentes que fueron isertados en el anteproyecto
     * @return Lista de Componentes
     */
    public List<ComponentEntity> getComponents()
    {
        return components;
    }
    /**
     * Setea los componentes del anteproyecto
     * @param components 
     */
    public void setComponents(List<ComponentEntity> components)
    {
        this.components = components;
    }
    /**
     * Obtiene el catalogo de Promotes
     * @return 
     */
    public List<PromoterEntity> getPromoters()
    {
        return promoters;
    }
    /**
     * Coloca el catalogo de promotores
     * @param promoters 
     */
    public void setPromoters(List<PromoterEntity> promoters)
    {
        this.promoters = promoters;
    }
    /**
     * Obtiene la unidad Ejecutora del Gasto
     * @return 
     */
    public List<DependenceEntity> getUnitExeExpend()
    {
        return unitExeExpend;
    }
    /**
     * Se coloca la unidad Ejecutora del Gasto
     * @param unitExeExpend 
     */
    public void setUnitExeExpend(List<DependenceEntity> unitExeExpend)
    {
        this.unitExeExpend = unitExeExpend;
    }
    /**
     * Se obtien la Unidad Responsable Ejecutora
     * @return 
     */
    public DependenceEntity getUrExecuting()
    {
        return urExecuting;
    }
    /**
     * Se coloca la unidad ejecutora
     * @param urExecuting 
     */
    public void setUrExecuting(DependenceEntity urExecuting)
    {
        this.urExecuting = urExecuting;
    }
    /**
     * Obtiene la lista de Unidades Normativas
     * @return 
     */
    public List<FederalURRegulatoryEntity> getUrNorm()
    {
        return urNorm;
    }
    /**
     * Se coloca las unidades normativas
     * @param urNorm 
     */
    public void setUrNorm(List<FederalURRegulatoryEntity> urNorm)
    {
        this.urNorm = urNorm;
    }

    /**
     * Se obtiene el tipo de Anteproyecto
     * @return the draftFileType
     */
    public DraftFileType getDraftFileType() {
        return draftFileType;
    }

    /**
     * @param draftFileType the draftFileType to set
     */
    public void setDraftFileType(DraftFileType draftFileType) {
        this.draftFileType = draftFileType;
    }

    /**
     * @return the invPreFileEntity
     */
    public InvPreFileEntity getInvPreFileEntity() {
        return invPreFileEntity;
    }

    /**
     * @param invPreFileEntity the invPreFileEntity to set
     */
    public void setInvPreFileEntity(InvPreFileEntity invPreFileEntity) {
        this.invPreFileEntity = invPreFileEntity;
    }

    /**
     * 
     * @return 
     */
    public DependenceEntity getUrExecutingFicha()
    {
        return urExecutingFicha;
    }

    /**
     * 
     * @param urExecutingFicha 
     */
    public void setUrExecutingFicha(DependenceEntity urExecutingFicha)
    {
        this.urExecutingFicha = urExecutingFicha;
    }
    
}
