/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.web;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ServletContextListeneer executes code on web app startup and shutdown
 * https://www.deadcoderising.com/execute-code-on-webapp-startup-and-shutdown-using-servletcontextlistener/
 * https://blog.georgovassilis.com/2014/01/15/tomcat-spring-and-memory-leaks-when-undeploying-or-redeploying-an-web-application/
 * Tomcat, Spring and memory leaks when undeploying or redeploying an web
 * application
 *
 * @author gallenc
 */
@WebListener
public class DatabaseInitialiser implements ServletContextListener {

    final static Logger LOG = LogManager.getLogger(DatabaseInitialiser.class);

    final static String TMP_DIR = System.getProperty("java.io.tmpdir");

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        destroy();
    }

    public void init() {
        // this is needed to allow Derby to work as in embedded server
        String derbyHome = TMP_DIR + File.separator + "derby";
        LOG.debug("setting derby.system.home=" + derbyHome);

        System.setProperty("derby.system.home", derbyHome);
    }

    public void destroy() {
        shutdownDerby();
    }

    // code to shutdown derby 
    // based on https://github.com/nuzayats/derby-shutdown-listener
    private static void shutdownDerby() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:derby:;shutdown=true");
            LOG.debug("Derby shutdown failed (no exception occurred).");
        } catch (SQLException e) {
            if ("XJ015".equals(e.getSQLState())) {
                LOG.info("Derby shutdown succeeded. SQLState={0}, message={1}",
                        new Object[]{e.getSQLState(), e.getMessage()});
                // LOG.debug( "Derby shutdown exception", e);
            } else {
                LOG.info("Derby shutdown failed or may not yet loaded. message: {0}", e.getMessage());
                LOG.debug("Derby shutdown failed", e);
            }
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    LOG.warn("Database closing error", e);
                }
            }
        }
        // unregister any jdbc drivers
        LOG.info("Unregistering any JDBC drivers ");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            LOG.info("Unregistering JDBC driver " + driver);
            try {
                java.sql.DriverManager.deregisterDriver(driver);
            } catch (Throwable t) {
            }
        }
    }

}
