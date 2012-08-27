/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dto.DependenciesInvPreFileDto;
import com.abs.siif.programming.dto.InvPreFileDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.FederalURRegulatoryEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public interface InvPreFileManagement {
    /**
     * Obtiene los catalogos requeridos para generar los 
     * generales de la Pre Ficha de Inversi�n
     * @param draftProject
     * @return DTO con los datos utilizados en los generales de la
     * pre ficha
     */
    public DependenciesInvPreFileDto getDepenInvPreFile(
            DraftProjectEntity  draftProject);
    /**
     * Salva los datos de la preficha de Inversi�n
     * @param invPreFileEntity
     * @return 
     */
    public InvPreFileEntity saveGeneralDataInvPreFile(InvPreFileEntity 
            invPreFileEntity);
    
    /*
     * Eliminar preficha de Inversi�n, revisar que reglas aplica
     * durante la eliminaci�n de los datos
     */
    public void deleteInvPreFile(InvPreFileEntity invPreFileEntity);
    /**
     * Obtiene los catalogos e Informacion de la Preficha de inversion
     * para su edici�n
     * @param invPreFileEntity
     * @return Depencnecias, dentro de esta se encuentra la preficha de
     * inversi�n
     */
    public DependenciesInvPreFileDto getInvPreFileEdit(
            InvPreFileEntity invPreFileEntity);
    
    /**
     * Este m�todo obtiene todas la pre-fichas de acuerdo a los filtros establecidos.
     */
    public List<InvPreFileDto> getFilteredInvPrefileDTO(InvPreFileEntity InvPreFileEntityWithParams);
    public InvPreFileEntity getInvPreFileById(Long anInvPrefileId);
    public void savePriority(InvPreFileEntity invPreFileEntity);
    public FederalURRegulatoryEntity getDefaultFederalURN();
}
