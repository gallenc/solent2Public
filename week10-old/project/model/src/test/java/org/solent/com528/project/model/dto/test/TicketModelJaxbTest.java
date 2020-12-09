
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.model.dto.test;


import java.io.File;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.solent.com528.project.model.dto.Ticket;

public class TicketModelJaxbTest {

    @Test
    public void testTransactionJaxb() {

        try {

            // test file we will write and read. 
            // Note in target folder so that will be deleted on each run and will not be saved to git
            File file = new File("target/testTicketTransactionData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // this contains a list of Jaxb annotated classes for the context to parse
            // NOTE you must also have a jaxb.index or ObjectFactory in the same classpath
            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com528.project.model.dto");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Ticket ticket = new Ticket();
            double cost = 1.5;
            ticket.setCost(cost);

            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(ticket, System.out);
            jaxbMarshaller.marshal(ticket, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(ticket, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Ticket receivedTransactionResult = (Ticket) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedTransactionResult, sw2);

            // check transmitted and recieved messages are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }
}
