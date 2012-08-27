/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.InvPreFileRegionalClassifierEntity;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public interface InvPreFileRegionalClassifierDao 
    extends SIIFBaseDao<InvPreFileRegionalClassifierEntity, Long>{
    /**
     * Obtiene las ubicaciones que fueron guardadas en la DB
     * @param invPreFile
     * @return 
     */
    public List<InvPreFileRegionalClassifierEntity> findUBications(
            InvPreFileEntity invPreFile);

    public void deleteByInvPreFile(InvPreFileEntity invPreFile);

}
