#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package}.web.rest.client.test.manual;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import ${package}.model.Entity;
import ${package}.model.ReplyMessage;
import ${package}.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class RestClientJerseyImplTest {

    String baseUrl = "http://localhost:8680/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void restClientRetreiveTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        // try to retreive an unknown entity
        ReplyMessage replyMessage = restClient.retrieveEntity(Integer.SIZE);
        assertNotNull(replyMessage);
        assertTrue(replyMessage.getEntityList().getEntities().isEmpty());

        // try to retreive entity with id 1
        ReplyMessage replyMessage2 = restClient.retrieveEntity(1);
        assertNotNull(replyMessage2);
        assertEquals(1, replyMessage2.getEntityList().getEntities().size());

        Entity entity = replyMessage2.getEntityList().getEntities().get(0);
        System.out.println("Received Entity: " + entity);

    }

    @Test
    public void restClientRetreiveTemplateTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        Entity entityTempate = new Entity();
        entityTempate.setField_A("abcd");

        // try to retreive an unknown entity
        ReplyMessage replyMessage = restClient.retrieveMatchingEntites(entityTempate);
        assertNotNull(replyMessage);

        List<Entity> entityList =  replyMessage.getEntityList().getEntities();
        System.out.println("Received "
                + entityList.size()
                + " Entities");
        
       for(Entity e: entityList){
           System.out.println("   "+ e);
       }
        
    }
}
