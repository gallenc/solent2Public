/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jdbc.test;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.impl.dao.jdbc.ConnectionFactory;

/**
 *
 * @author cgallen
 */
public class ConnectionFactoryTest {

    @Test
    public void testConnection() {

        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            conn.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
