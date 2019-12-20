/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient.test.manual;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ReplyMessage;
import solent.ac.uk.ood.examples.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class RestClientJerseyImplTest {

    String baseUrl = "http://localhost:8084/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void restClientRetreiveTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        // try to retreive an unknown thing
        ReplyMessage replyMessage = restClient.retrieveThing(Integer.SIZE);
        assertNotNull(replyMessage);
        assertTrue(replyMessage.getThingList().getThings().isEmpty());

        // try to retreive thing with id 1
        ReplyMessage replyMessage2 = restClient.retrieveThing(1);
        assertNotNull(replyMessage2);
        assertEquals(1, replyMessage2.getThingList().getThings().size());

        Thing thing = replyMessage2.getThingList().getThings().get(0);
        System.out.println("Received Thing: " + thing);

    }

    @Test
    public void restClientRetreiveTemplateTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        Thing thingTempate = new Thing();
        thingTempate.setField_A("abcd");

        // try to retreive an unknown thing
        ReplyMessage replyMessage = restClient.retrieveMatchingEntites(thingTempate);
        assertNotNull(replyMessage);

        List<Thing> thingList =  replyMessage.getThingList().getThings();
        System.out.println("Received "
                + thingList.size()
                + " Things");
        
       for(Thing e: thingList){
           System.out.println("   "+ e);
       }
        
    }
}
