/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.clientservice.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com528.project.model.dto.Ticket;
import solent.ac.uk.com504.examples.ticketgate.crypto.AsymmetricCryptography;
import solent.ac.uk.com504.examples.ticketgate.crypto.GenerateKeys;

/**
 *
 * @author cgallen
 */
public class TicketEncoderImpl extends TicketEncoder{

    private static final String PUBLIC_KEY_FILE = "publicKey";
    private static final String PRIVATE_KEY_FILE = "privateKey";

    final static Logger LOG = LogManager.getLogger(TicketEncoderImpl.class);

    // RETURNS NULL IF CANNOT ENCODE TICKET
    public static String encodeTicket(Ticket ticket) {

        try {
            //marshal ticket to xml
            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com528.project.model.dto");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(ticket, sw1);

            String ticketXml = sw1.toString();
            LOG.debug("Ticket to encode: \n" + ticketXml);

            // hash the ticket
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(ticketXml.getBytes());
            byte[] digest = md.digest();
            String ticketXmlHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            LOG.debug("Unencoded ticket MD5 hash: \n" + ticketXmlHash);

            // encode the hash using private key
            AsymmetricCryptography ac = new AsymmetricCryptography();
            PrivateKey privateKey = ac.getPrivateFromClassPath(PRIVATE_KEY_FILE);

            String encodedTicketHash = ac.encryptText(ticketXmlHash, privateKey);

            LOG.debug("Encrypted ticket MD5 hash: " + encodedTicketHash);

            ticket.setEncryptedHash(encodedTicketHash);

            StringWriter ticketXmlEncoded = new StringWriter();
            jaxbMarshaller.marshal(ticket, ticketXmlEncoded);

            LOG.debug("ticket with encrypted hash: " + ticketXmlEncoded.toString());
            return ticketXmlEncoded.toString();

        } catch (Exception ex) {
            LOG.error("problem encrypting ticket", ex);
        }
        return null;
    }

    // returns false if cannot validate ticket
    public static boolean validateTicket(String encodedTicket) {

        try {
            LOG.debug("validating ticket : \n" + encodedTicket);
            
            //unmarshal encodedTicket ticket to xml
            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com528.project.model.dto");
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            StringReader sr = new StringReader(encodedTicket);
            Ticket decodedTicket = (Ticket) jaxbUnMarshaller.unmarshal(sr);

            // get the encodedTicketHash and set encodedTicketHash null
            String encodedTicketHash = decodedTicket.getEncryptedHash();
            decodedTicket.setEncryptedHash(null);

            // regenerate the ticket xml string without the encodedTicketHash
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(decodedTicket, sw1);
            String ticketXml = sw1.toString();

            // hash the regenerated ticket so we can compare hashes
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(ticketXml.getBytes());
            byte[] digest = md.digest();
            String ticketXmlHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            LOG.debug("regenerated ticket xml hash: " + ticketXmlHash);

            // decode encodedTicketHash
            AsymmetricCryptography ac = new AsymmetricCryptography();
            PublicKey publicKey = ac.getPublicFromClassPath(PUBLIC_KEY_FILE);

            String decryptedTicketHash = ac.decryptText(encodedTicketHash, publicKey);

            LOG.debug("decryptedTicketHash        : " + decryptedTicketHash);

            if (ticketXmlHash.equals(decryptedTicketHash)) {
                LOG.debug("ticket is valid");
                return true;
            }

        } catch (Exception ex) {
            LOG.error("problem with validating ticket", ex);
        }
        LOG.debug("ticket is not valid");
        return false;

    }

}
