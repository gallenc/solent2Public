/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.password.test;


import org.junit.Test;
import static org.junit.Assert.*;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solent.com504.oodd.password.PasswordUtils;


/**
 *
 * @author gallenc
 */
public class PasswordUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordUtilsTest.class);
    
    @Test
    public void testPasswordUtils(){
        
        String TEST_PASSWORD = "1234567890";
        String WRONG_PASSWORD="abcd";
        
        String hashed = PasswordUtils.hashPassword(TEST_PASSWORD);
        LOG.debug("password=" + TEST_PASSWORD + " hashedpw=" + hashed);

        // Check that an unencrypted password matches one that has
        // previously been hashed
        if (PasswordUtils.checkPassword(TEST_PASSWORD, hashed)) {
            LOG.debug("password " + TEST_PASSWORD + " matches");
        } else {
            LOG.debug("password " + TEST_PASSWORD + " does not match");
        }
        assertTrue(PasswordUtils.checkPassword(TEST_PASSWORD, hashed));
        
        if (PasswordUtils.checkPassword(WRONG_PASSWORD, hashed)) {
            LOG.debug("password " + TEST_PASSWORD + " matches");
        } else {
            LOG.debug("password " + TEST_PASSWORD + " does not match");
        }
        assertFalse(PasswordUtils.checkPassword(WRONG_PASSWORD, hashed));

    }

   
}
