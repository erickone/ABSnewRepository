/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ServConstrainsManagementImpl
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

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.viewenum.ViewConstrainsEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Esta clase se ocupa de obtener las restricciones de seguridad respecto a los
 * servicios que seran permitidos accesar al usurio, dado su perfil
 *
 * @author Israel Ruiz
 */
@Service("servConstrainsManagement")
public class ServConstrainsManagementImpl implements ServConstrainsManagement
{

    /**
     * Obtien la lista de componentes visuales a los cuales no tendra accesso el
     * rol (perfil) que esta operando el sistema
     *
     * @return Lista de componentes visuales que no tiene acceso
     */
    @Override
    public List<ViewConstrainsEnum> getViewConstrainsUIComponent()
    {
        List<ViewConstrainsEnum> constrains =
                new ArrayList<ViewConstrainsEnum>();

        ProfileEntity userProfile = (ProfileEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.ROLE);

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan")
                || userProfile.getProfileDescription().
                trim().equals("Analista Financiero / Valida Sefin")
                || userProfile.getProfileDescription().
                trim().equals("Capturador"))
        {
            constrains.add(ViewConstrainsEnum.DELETE_DRAF_PROJECT);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_CLASSIF_DRAFT);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_PED_DRAFT);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_DELIVERABLES);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_UBICATION);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_BUDGETING);
        }
 
        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_CLASSIF_DRAFT);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Enlace/Administrador POA")                
                || userProfile.getProfileDescription().
                trim().equals("Capturador")
                || userProfile.getProfileDescription().
                trim().equals("Analista Financiero / Valida Sefin"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_SEPLAN);
            constrains.add(ViewConstrainsEnum.CHANGE_SEPLAN);
        }
        
        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan"))
        {
            constrains.add(ViewConstrainsEnum.SAVE_LOGIC_FRAME);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan")
                || userProfile.getProfileDescription().
                trim().equals("Capturador"))
        {
            constrains.add(ViewConstrainsEnum.PRIORITIZE_INVPREFILE);
        }
        
        if (userProfile.getProfileDescription().
                trim().equals("Enlace/Administrador POA")
                || userProfile.getProfileDescription().
                trim().equals("Capturador"))
        {
            constrains.add(ViewConstrainsEnum.OBSERVATIONS_INVPREFILE);
        }

        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan")
                || userProfile.getProfileDescription().
                trim().equals("Analista Financiero / Valida Sefin")
                || userProfile.getProfileDescription().
                trim().equals("Capturador")
                || userProfile.getProfileDescription().
                trim().equals("Enlace/Administrador POA"))
        {
            constrains.add(ViewConstrainsEnum.DELETE_OBSERVATIONS);
        }
        
        if (userProfile.getProfileDescription().
                trim().equals("Administrador SEPLAN")
                || userProfile.getProfileDescription().
                trim().equals("Analista de Planeación / Valida Seplan")
                || userProfile.getProfileDescription().
                trim().equals("Analista Financiero / Valida Sefin")
                || userProfile.getProfileDescription().
                trim().equals("Capturador")
                || userProfile.getProfileDescription().
                trim().equals("Enlace/Administrador POA"))
        {
            constrains.add(ViewConstrainsEnum.FIN_STRUCT_INVPREFILE);
        }
        
        return constrains;
    }
}
