/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.dto.InvPreFileDto;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public interface InvPreFileDao extends SIIFBaseDao<InvPreFileEntity, Long>{
    /**
     * Verifica si la información de la prioridad se encuentra registrada
     * se manda un prefijo que pertenece a la UP
     * @param invPreFileEntity
     * @param prefix
     * @return 
     */
    public boolean findPriorityEqual(InvPreFileEntity invPreFileEntity,
            String prefix);
    /**
     * Obtiene el siguiente valor que corresponde a al pre ficha respecto
     * a las ingresadas por Unidad Responsable, regresa el siguiente consecutivo
     * @param invPreFileEntity 
     */
    public String getNexNumberInvPreFile(InvPreFileEntity invPreFileEntity);
    /**
     * 
     * @param invPreFileEntity
     * @return 
     */
    public InvPreFileEntity saveInvPreFile(InvPreFileEntity invPreFileEntity);
    
    public List<InvPreFileDto> getFilteredInvPrefileDTO(InvPreFileEntity InvPreFileEntityWithParams);
    public InvPreFileEntity getInvPreFileEntityById(Long anInvPreFileEntity);
    /**
     * Method to delete a Inv Pre File
     * @param invPreFile 
     */
    public void deleteInvPreFile(InvPreFileEntity invPreFile);
    
}
