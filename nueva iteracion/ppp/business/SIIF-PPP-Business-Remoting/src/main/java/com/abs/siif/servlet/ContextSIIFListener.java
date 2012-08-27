/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ContextSIIFListener
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.servlet;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Israel Ruiz
 */
public class ContextSIIFListener implements
        javax.servlet.ServletContextListener {

    Logger log = Logger.getLogger(this.getClass());

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                log.log(Level.INFO, String.format(
                        "deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                log.log(Level.ERROR, String.format(
                        "Error deregistering driver %s", driver), e);
            }
        }

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       
    }
}
