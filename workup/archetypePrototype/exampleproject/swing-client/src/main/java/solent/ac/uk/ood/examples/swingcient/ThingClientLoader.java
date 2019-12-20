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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ThingDAO;
import solent.ac.uk.ood.examples.model.ReplyMessage;
import solent.ac.uk.ood.examples.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class ThingClientLoader {

    private static final Logger LOG = LoggerFactory.getLogger(ThingClientLoader.class);

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> schedulerHandle = null;

    private ThingDAO thingDAO = null;

    private String baseUrl = "http://localhost:8084/";

    private MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    private RestClientJerseyImpl restClient = null;

    private AtomicInteger totalClientRetrieveSuccessful = new AtomicInteger();
    private AtomicInteger totalClientRetrieveTries = new AtomicInteger();

    public int getTotalClientRetrieveTries() {
        return totalClientRetrieveTries.get();
    }

    public int getTotalClientRetrieveSuccessful() {
        return totalClientRetrieveSuccessful.get();
    }

    public ThingClientLoader(ThingDAO thingDAO, String baseUrl) {
        this.thingDAO = thingDAO;
        this.baseUrl = baseUrl;
        restClient = new RestClientJerseyImpl(baseUrl, mediaType);
    }

    // synchronized so that can only run one call at a time
    public synchronized boolean restClientRetrieveAll() {

        Thing thingTempate = new Thing();
        // you could add a filter value here but leaving values null retrieves all values
        // e.g thingTempate.setField_A("xxxx");

        // try to retreive a list of things
        try {
            ReplyMessage replyMessage = restClient.retrieveMatchingEntites(thingTempate);
            if (replyMessage.getCode() == 200) {

                List<Thing> thingList = replyMessage.getThingList().getThings();

                if (LOG.isDebugEnabled()) {
                    StringBuffer sb = new StringBuffer("Received " + thingList.size() + " Things");
                    for (Thing e : thingList) {
                        sb.append("\n   " + e);
                    }
                    LOG.debug(sb.toString());
                }

                // note that each thing will have a new id which is unique to local dao instance
                thingDAO.deleteAllThings();
                for (Thing newThing : thingList) {
                    thingDAO.createThing(newThing);
                }

                return true;
            } else {
                LOG.error("problem retrieving things from " + baseUrl
                        + " " + replyMessage.toString());
            }

        } catch (Exception e) {
            LOG.error("problem retrieving things from " + baseUrl, e);
        }
        // some problem retreiving data
        return false;
    }

    public synchronized void scheduleLoadData(Integer scheduleIntervalSeconds, Integer retryAttempts, Integer retryIntervalSeconds) {
        if (scheduleIntervalSeconds == null) {
            throw new IllegalArgumentException("scheduleIntervalSeconds cannot be null");
        }
        if (retryAttempts == null) {
            throw new IllegalArgumentException("retryAttempts cannot be null");
        }
        if (retryIntervalSeconds == null) {
            throw new IllegalArgumentException("retryIntervalSeconds cannot be null");
        }
        LOG.info("load data starting scheduleIntervalSeconds=" + scheduleIntervalSeconds
                + " retryAttempts=" + retryAttempts
                + " retryIntervalSecond=" + retryIntervalSeconds);
        if (schedulerHandle == null) {

            final Runnable loadData = new Runnable() {
                public void run() {
                    boolean keepTrying = true;
                    int gettries = 0;

                    while (keepTrying && gettries < retryAttempts) {
                        gettries++;
                        LOG.debug("retrieving data from service attempt:" + gettries);

                        totalClientRetrieveTries.incrementAndGet();
                        try {
                            // try to retrieve data                 
                            keepTrying = !restClientRetrieveAll();
                            if (!keepTrying) {
                                totalClientRetrieveSuccessful.getAndIncrement();
                                LOG.debug("retrieving data successful. Total retrieve success=" + totalClientRetrieveSuccessful.get());
                            } else {
                                LOG.debug("retrieving data unsuccessful (Current retry=" + gettries
                                        + ") Total try attempts " + totalClientRetrieveTries.get());
                            }
                            Thread.sleep(retryIntervalSeconds * 1000);
                        } catch (InterruptedException ex) {
                            keepTrying = false;
                            LOG.debug("retrieving data interrupted");
                        }
                    }
                    LOG.debug("retrieving data thread finished (Current retry=" + gettries + ")");
                }

            };
            // scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
            schedulerHandle = scheduler.scheduleAtFixedRate(loadData, 0, scheduleIntervalSeconds, TimeUnit.SECONDS);
            LOG.debug("load data scheduled");
        }

    }

    public synchronized void cancelLoadDataSchedule() {
        LOG.debug("cancelling scheduled load");
        if (schedulerHandle != null) {
            schedulerHandle.cancel(true);
            schedulerHandle = null;
        }

    }
}
