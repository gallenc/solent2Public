/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.spring.web;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author cgallen
 */
public class BasicAuthCoder {

    /**
     * Encodes HTTP basic authentication string
     *
     * @param username
     * @param password
     * @return
     */
    public static String encode(String username, String password) {
        String usernameColonPassword = username + ":" + password;
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
        return basicAuthPayload;
    }

    /**
     * Decodes HTTP basic authentication string
     *
     * @param basicAuthPayload
     * @return String[0] username String[1] password
     */
    public static String[] decode(String basicAuthPayload) {
        if (!basicAuthPayload.toLowerCase().startsWith("basic")) {
            throw new IllegalArgumentException("authentication header must be Basic");
        }
        String base64Credentials = basicAuthPayload.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        final String[] values = credentials.split(":", 2);
        if (values.length != 2) {
            throw new IllegalArgumentException("Basic authentication header contain encoded username:password");
        }
        return values;
    }

}
