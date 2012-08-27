/*
 *  Copyright (C) 2012 Advance Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ServletInitContext
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advance Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advance 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.servlet;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Israel Ruiz
 */
public class ServletInitContext extends HttpServlet {

    @Override
    public void init(ServletConfig config) {

        String myContextPath = config.getServletContext().getInitParameter(
                "uploadDirectory");

        String myResourcePath = config.getServletContext().getInitParameter(
                "pathResourceBundle");
        String[] myPaths = myResourcePath.split(",");
        if (myPaths != null) {
            for (int i = 0; i < myPaths.length; i++) {
                myPaths[i]= myPaths[i].trim();
            }
        }

        String myDefaultLocale = config.getServletContext().getInitParameter(
                "defaultLocale");

        SIIFContextBase.setParamContext(KeyContextEnum.PATH, myContextPath.trim());
        SIIFContextBase.setParamContext(KeyContextEnum.LOCALE, myDefaultLocale.trim());
        SIIFContextBase.setParamContext(KeyContextEnum.RESOURCE, myPaths);

    }
}
