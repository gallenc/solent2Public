/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.com504.examples.ticketgate.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// based on  https://mkyong.com/java/java-asymmetric-cryptography-example/
public class GenerateKeys {

    final static Logger LOG = LogManager.getLogger(GenerateKeys.class);

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
    }

    public void createKeys() {
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public void writeToFile(String path, byte[] key) {
        FileOutputStream fos = null;
        try {
            File f = new File(path);
            LOG.debug("writing key.length "+key.length
                    + " bytes to file:"+f.getAbsolutePath());

            f.getParentFile().mkdirs();

            fos = new FileOutputStream(f);
            fos.write(key);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    // this is run as part of maven build to generate the key files in the jar class path
    public static void createKeys(String privateKeyFile, String publicKeyFile) {
        try {
            GenerateKeys gk = new GenerateKeys(1024);
            gk.createKeys();
            gk.writeToFile(privateKeyFile, gk.getPrivateKey().getEncoded());
            gk.writeToFile(publicKeyFile,gk.getPublicKey().getEncoded());
        } catch (Exception ex) {
            System.out.println("problem writing keys to privateKeyFile=" + privateKeyFile
                    + " publicKeyFile=" + publicKeyFile + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String privateKeyFile = args[0];
        String publicKeyFile = args[1];
        createKeys(privateKeyFile, publicKeyFile);
    }

}
