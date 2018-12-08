/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.model.Entity;
import solent.ac.uk.ood.examples.model.EntityDAO;
import solent.ac.uk.ood.examples.model.ReplyMessage;
import solent.ac.uk.ood.examples.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class EntityClientLoader {

    private static final Logger LOG = LoggerFactory.getLogger(EntityClientLoader.class);

    private EntityDAO entityDAO = null;

    private String baseUrl = "http://localhost:8680/";

    private MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    private RestClientJerseyImpl restClient = null;

    public EntityClientLoader(EntityDAO entityDAO, String baseUrl) {
        this.entityDAO = entityDAO;
        this.baseUrl = baseUrl;
        restClient = new RestClientJerseyImpl(baseUrl, mediaType);
    }

    // synchronized so that can only run one call at a time
    public synchronized void restClientRetrieveAll() {

        Entity entityTempate = new Entity();
        // you could add a filter value here but leaving values null retrieves all values
        // e.g entityTempate.setField_A("xxxx");

        // try to retreive a list of entities
        try {
            ReplyMessage replyMessage = restClient.retrieveMatchingEntites(entityTempate);
            if (replyMessage.getCode() == 200) {

                List<Entity> entityList = replyMessage.getEntityList().getEntities();

                if (LOG.isDebugEnabled()) {
                    StringBuffer sb = new StringBuffer("Received " + entityList.size() + " Entities");
                    for (Entity e : entityList) {
                        sb.append("\n   " + e);
                    }
                    LOG.debug(sb.toString());
                }

                // note that each entity will have a new id which is unique to local dao instance
                entityDAO.deleteAllEntities();
                for (Entity newEntity : entityList) {
                    entityDAO.createEntity(newEntity);
                }
            } else {
                LOG.error("problem retrieving entities from " + baseUrl
                        + " " + replyMessage.toString());
            }

        } catch (Exception e) {
            LOG.error("problem retrieving entities from " + baseUrl, e);
        }

    }

    // todo
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void scheduleLoadData() {
        System.out.println("load data starting");
        final Runnable loadData = new Runnable() {
            public void run() {
                System.out.println("load data");
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(loadData, 10, 10, SECONDS);
        System.out.println("load data finished");
    }
}
