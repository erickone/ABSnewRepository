/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UserGeneralDataController
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * Clase que se encarga de lo relativo a la vista "User Header"
 * @version 1.0 19-Junio-2012
 * @author Francisco Luna
 */
@Controller("userHeaderController")
@Scope("session")
public class UserHeaderController {    
    
    public void initViewElements(){}
    
}
