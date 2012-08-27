/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.common.entities.RegClassifEntity;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface RegClassifDao 
{
     List<RegClassifEntity> getListByFatherId(Long anIdObjective);
}
