/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.com504.examples.ticketgate.crypto.test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.NoSuchPaddingException;
import org.junit.Test;
import solent.ac.uk.com504.examples.ticketgate.crypto.AsymmetricCryptography;
import solent.ac.uk.com504.examples.ticketgate.crypto.GenerateKeys;

/**
 *
 * @author cgallen
 */
public class AsymmetricCryptographyTest {
    
    final static Logger LOG = LogManager.getLogger(AsymmetricCryptographyTest.class);

    @Test
    public void generateAsymmetricCryptographyTest() throws Exception {
        
        String PUBLIC_KEY_FILE ="./target/TestKeyPair/publicKey";
        String PRIVATE_KEY_FILE ="./target/TestKeyPair/privateKey";

        GenerateKeys gk;
        try {
            gk = new GenerateKeys(1024);
            gk.createKeys();
            gk.writeToFile(PUBLIC_KEY_FILE, gk.getPublicKey().getEncoded());
            gk.writeToFile(PRIVATE_KEY_FILE, gk.getPrivateKey().getEncoded());
        } catch ( Exception  e) {
            LOG.error(e);
        }

        try {
            AsymmetricCryptography ac = new AsymmetricCryptography();
            PrivateKey privateKey = ac.getPrivate(PRIVATE_KEY_FILE);
            PublicKey publicKey = ac.getPublic(PUBLIC_KEY_FILE);

            String msg = "Cryptography is fun!";
            String encrypted_msg = ac.encryptText(msg, privateKey);
            String decrypted_msg = ac.decryptText(encrypted_msg, publicKey);
            LOG.info("Original Message: " + msg
                    + "\nEncrypted Message: " + encrypted_msg
                    + "\nDecrypted Message: " + decrypted_msg);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            LOG.error("problem with test",ex);

        }
    }

}
