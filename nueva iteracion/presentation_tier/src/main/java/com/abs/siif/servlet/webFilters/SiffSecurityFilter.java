/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ContextWeb
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.servlet.webFilters;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Israel Ruiz
 */
public class SiffSecurityFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
      
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        HttpSession session;
        session = httpRequest.getSession(true);
        SIIFContextBase.initLocal();
        if (session != null) {
            SIIFContextBase.getLocalcontext().get().put(KeyContextEnum.SESSION, session);
        }
        
        if (!path.contains("login.abs") 
                && (!path.contains(".css"))
                && (!path.contains(".png"))
                && (!path.contains(".js"))) {

            if (SIIFContextBase.getParameterSession(SessionKeyEnum.USER) == null){
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(httpRequest.getContextPath() + "/login.abs");
                return;
            }else if (SIIFContextBase.getParameterSession(SessionKeyEnum.ROLE) == null
                && (!path.contains("optionsProfile.abs"))){
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(httpRequest.getContextPath() + "/autenticado/optionsProfile.abs");
                return;
            }
        } 

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
