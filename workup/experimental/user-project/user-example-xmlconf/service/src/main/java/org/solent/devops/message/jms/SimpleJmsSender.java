package org.solent.devops.message.jms;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleJmsSender {
    
    final static Logger LOG = LogManager.getLogger(SimpleJmsSender.class);

    private final JmsTemplate jmsTemplate;
    
    private final String outputQueueName;
    
    private AtomicLong messagesSent = new AtomicLong();

    public Long getMessagesSent() {
        return messagesSent.get();
    }

    @Autowired
    public SimpleJmsSender(final JmsTemplate jmsTemplate, final String outputQueueName) {
        this.jmsTemplate = jmsTemplate;
        this.outputQueueName = outputQueueName;
    }

    public void send(final String message) {
        long sentcount = messagesSent.incrementAndGet();
        LOG.debug("jms sending message "+sentcount
                + " to queue :"+outputQueueName+" "+message);

        jmsTemplate.convertAndSend(outputQueueName , message);
    }
}
