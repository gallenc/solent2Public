/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jdbc;

/**
 *
 * @author cgallen
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {

    final static Logger LOG = LogManager.getLogger(ConnectionFactory.class);

    private String driverClassName = null;
    private String connectionUrl = null;
    private String dbUser = null;
    private String dbPwd = null;

    private Properties prop = new Properties();
    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {

        try {
            //load a properties file from class path, inside static method
            prop.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties"));

            //get the property value and print it out
            driverClassName = prop.getProperty("javax.persistence.jdbc.driver");
            connectionUrl = prop.getProperty("javax.persistence.jdbc.url");
            dbUser = prop.getProperty("javax.persistence.jdbc.user");
            dbPwd = prop.getProperty("djavax.persistence.jdbc.password");
            LOG.debug("connection factory loading properties javax.persistence.jdbc.driver=" + driverClassName);
            LOG.debug("connection factory loading properties javax.persistence.jdbc.url=" + connectionUrl);

            // this will load the driver class 
           Class.forName(driverClassName);

        } catch (Exception e) {
            LOG.error("connection factory problem loading config", e);
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            synchronized (ConnectionFactory.class) {
                if (connectionFactory == null) {
                    connectionFactory = new ConnectionFactory();
                }
            }
        }
        return connectionFactory;
    }
}
