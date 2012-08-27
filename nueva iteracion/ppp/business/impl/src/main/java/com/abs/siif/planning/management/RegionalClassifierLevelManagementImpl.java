/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalClassifierLevelManagementImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.RegionalLevelClassifierDao;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.planning.exception.RegionalClassifierLevelBusinessException;
import com.abs.siif.planning.validator.RegClassifLevelBusinessValidator;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("regionalClassifierLevelManagement")
public class RegionalClassifierLevelManagementImpl 
    implements RegionalClassifierLevelManagement, Serializable{

    @Resource(name = "regionalLevelClassifierDao")
    private RegionalLevelClassifierDao itsRegionalLevelClassifierDao;
    @Resource(name = "regClassifLevelBusinessValidator")
    private RegClassifLevelBusinessValidator itsRegClassifLevelBusinessValidator;
    
    @Override
    public RegionalLevelClassifierEntity getRegionalClassifierLevelByLevel(int aFCLevel)
    {
        return itsRegionalLevelClassifierDao.getRegionalClassifierLevelByLevel(aFCLevel);
    }

    @Override
    public List<RegionalLevelClassifierEntity> getAllRegionalClassifierLevels()
    {
        return (List<RegionalLevelClassifierEntity>) itsRegionalLevelClassifierDao.getAllRegionalLevelClassifier();
    }

    @Override
    public Long saveOrUpdate(RegionalLevelClassifierEntity anEntity, SaveType aSaveType)
    {
        if (!itsRegClassifLevelBusinessValidator.validateRegClassifLevelInSequence(anEntity)) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.notSecuency");
        }

        if (itsRegClassifLevelBusinessValidator.existRegClassifLevelWithCensusActive(anEntity)) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.uniqueCensus");
        }
        if (itsRegClassifLevelBusinessValidator.existRegClassifLevelWithGenderActive(anEntity)) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.uniqueGender");
        }
        if (itsRegClassifLevelBusinessValidator.existRegClassifLevelWithStateActive(anEntity)) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.uniqueState");
        }
        if (itsRegClassifLevelBusinessValidator.existRegClassifLevelWithMunicipalityActive(anEntity)) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.uniqueMunicipality");
        }
        if (itsRegClassifLevelBusinessValidator.existRegClassifLevelWithPostalCodeActive(anEntity)) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.uniquePostalCode");
        }
        
        if (aSaveType == SaveType.SAVE)
            if (!itsRegClassifLevelBusinessValidator.isRegClassifKeyValid(anEntity)) {
                throw new RegionalClassifierLevelBusinessException("ppp.planning.repeatedKey");
            }
        
        Long myKey = itsRegionalLevelClassifierDao.saveOrUpdate(anEntity);

        return myKey;
    }

    @Override
    public void deleteRegionalClassifier(List<RegionalLevelClassifierEntity> anEntityList)
    {
        if (!itsRegClassifLevelBusinessValidator.canDeleteRegClassifLevels(anEntityList)) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.notSecuencyDelete");
        }

        itsRegionalLevelClassifierDao.delete(anEntityList);
    }

  @Override
  public RegionalLevelClassifierEntity getRegionalLevelClassifierById(RegionalLevelClassifierEntity aRegionalLevelClassifierEntity)
  {
    return itsRegionalLevelClassifierDao.getRegionalLevelClassifierById
                (aRegionalLevelClassifierEntity.getRegionalLevelClassifierId());
  }

}
