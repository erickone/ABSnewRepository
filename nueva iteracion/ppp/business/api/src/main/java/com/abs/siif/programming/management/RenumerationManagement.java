/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  renumerationController
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

import com.abs.siif.programming.dto.RenumerationDto;
import java.util.List;

/**
 *
 * @author luis.carreon
 */

public interface RenumerationManagement {
    
    List<RenumerationDto> getDraftprojectsByUP(Long aDependenceId);
    
    List<RenumerationDto> validateKeysChanged(List<RenumerationDto> aRenumerationList);

}
