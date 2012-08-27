/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ServConstrainsManagement
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.management;

import com.abs.siif.security.viewenum.ViewConstrainsEnum;
import java.util.List;

/**
 * Interface que define las operaciones para obtener 
 * la lista de componentes visuales que no tendra acceso 
 * el perfil del usuario
 *
 * @author Israel Ruiz
 */
public interface ServConstrainsManagement {
    /**
     * Obtien la lista de componentes visuales a los cuales no tendra accesso
     * el rol (perfil) que esta operando el sistema
     * @return Lista de componentes visuales que no tiene acceso
     */
     public List<ViewConstrainsEnum> getViewConstrainsUIComponent();
    
}
