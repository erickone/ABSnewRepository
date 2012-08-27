/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  renumerationManagement
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.programming.dto.RenumerationDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author luis.carreon
 */
@Service("renumerationManagement")
public class RenumerationManagementImpl implements RenumerationManagement {

    @Override
    public List<RenumerationDto> getDraftprojectsByUP(Long aDependenceId) {
        List<RenumerationDto> myResult = new ArrayList<RenumerationDto>();
        RenumerationDto myRenumerationDto = new RenumerationDto(1L, "001", "", "Proyecto 1", "Proyecto 1 laskjdflsa");
        myResult.add(myRenumerationDto);
        myRenumerationDto = new RenumerationDto(2L, "002", "", "Proyecto 2", "Proyecto 2 laskjdflsa");
        myResult.add(myRenumerationDto);
        myRenumerationDto = new RenumerationDto(3L, "003", "", "Proyecto 3", "Proyecto 3 laskjdflsa");
        myResult.add(myRenumerationDto);
        myRenumerationDto = new RenumerationDto(4L, "004", "", "Proyecto 4", "Proyecto 4 laskjdflsa");
        myResult.add(myRenumerationDto);
        return myResult;
    }

    @Override
    public List<RenumerationDto> validateKeysChanged(List<RenumerationDto> aRenumerationList) {
        String myKey;
        boolean myErrorFlag = false;
        List<String> myKeysList = new ArrayList<String>();
        List<Integer> myWrongRows = new ArrayList<Integer>();
        for (int i = 0; i < aRenumerationList.size(); i++) {
            if (myKeysList.contains(aRenumerationList.get(i).getNewDraftprojectNumber().trim())) {
                myWrongRows.add(i);
                aRenumerationList.get(i).setIsCorrect(false);
                int myIndex = myKeysList.indexOf(aRenumerationList.get(i).getNewDraftprojectNumber().trim());
                aRenumerationList.get(myIndex).setIsCorrect(false);
            } else {
                aRenumerationList.get(i).setIsCorrect(true);
                if (!aRenumerationList.get(i).getNewDraftprojectNumber().trim().equals("")) {
                    myKeysList.add(aRenumerationList.get(i).getNewDraftprojectNumber().trim());
                }else{
                    myKeysList.add("-");
                }
            }
        }
        if (myWrongRows.size() > 0) {
            throw new BaseBusinessException("ppp.progr.renumeration.keysDuplicated");
        }
        for (int i = 0; i < aRenumerationList.size(); i++) {
            myKey = aRenumerationList.get(i).getNewDraftprojectNumber().trim();
            for (RenumerationDto myRenumeration : aRenumerationList) {
                if (myRenumeration.getDraftProjectId() != aRenumerationList.get(i).getDraftProjectId()) {
                    if (myRenumeration.getDraftprojectNumber().trim().equals(myKey)) {
                        if (myRenumeration.getNewDraftprojectNumber().trim().equals(myKey)
                                || myRenumeration.getNewDraftprojectNumber().trim().length() == 0) {
                            aRenumerationList.get(i).setIsCorrect(false);
                            myErrorFlag = true;
                        } else {
                            aRenumerationList.get(i).setIsCorrect(true);
                        }
                    }
                }
            }
        }
        if (myErrorFlag) {
            throw new BaseBusinessException("ppp.progr.renumeration.validationError");
        }
        return aRenumerationList;
    }
}
