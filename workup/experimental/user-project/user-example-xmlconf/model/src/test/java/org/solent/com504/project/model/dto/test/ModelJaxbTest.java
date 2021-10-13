
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dto.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;

import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.dto.PartyStatus;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.model.user.dto.UserRoles;

public class ModelJaxbTest {

    final static Logger LOG = LogManager.getLogger(ModelJaxbTest.class);

    public JAXBContext jaxbContext;

    @Before
    public void setup() {
        // this contains a list of Jaxb annotated classes for the context to parse, seperated by :
        // NOTE you must also have a jaxb.index or jaxb ObjectFactory in the same classpath
        try {
            jaxbContext = JAXBContext.newInstance(
                    "org.solent.com504.project.model.dto"
                    + ":org.solent.com504.project.model.user.dto"
                    + ":org.solent.com504.project.model.party.dto");
        } catch (JAXBException e) {
            throw new RuntimeException("problem creating jaxb context", e);
        }
    }

    @Test
    public void testModelJaxb1() {

        try {

            // test file we will write and read. 
            // Note in target folder so that will be deleted on each run and will not be saved to git
            File file = new File("target/testTransactionData.xml");
            LOG.debug("writing test file to " + file.getAbsolutePath());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ReplyMessage replyMessage = new ReplyMessage();

            List<Party> partyList = new ArrayList<Party>();
            replyMessage.setPartyList(partyList);
            Party party = new Party();
            party.setPartyStatus(PartyStatus.ACTIVE);
            party.setPartyRole(PartyRole.UNDEFINED);
            Address address = new Address();
            address.setAddressLine1("home for me");
            party.setAddress(address);
            partyList.add(party);

            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(replyMessage, System.out);
            jaxbMarshaller.marshal(replyMessage, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            ReplyMessage receivedTransactionResult = (ReplyMessage) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedTransactionResult, sw2);

            // check transmitted and recieved messages are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

    @Test
    public void testUserModelJaxb() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create objects to marshal
            User user = new User();
            Role role1 = new Role(UserRoles.ROLE_USER);
            Role role2 = new Role(UserRoles.ROLE_GLOBAL_ADMIN);
            user.setFirstName("firstName");
            user.setSecondName("secondName");
            user.setPassword("password");
            user.setPasswordConfirm("password");
            Set<Role> roles = new HashSet(Arrays.asList(role1, role2));
            user.setRoles(roles);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(user, sw1);

            LOG.debug("marshaled code" + sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(sw1.toString().getBytes(StandardCharsets.UTF_8));

            User receiveduser = (User) jaxbUnMarshaller.unmarshal(stream);
            LOG.debug("receiveduser=" + receiveduser);

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }
    

}
