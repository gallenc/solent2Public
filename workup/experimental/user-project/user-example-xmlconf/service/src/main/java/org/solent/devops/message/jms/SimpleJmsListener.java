package org.solent.devops.message.jms;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SimpleJmsListener implements MessageListener {

    final static Logger LOG = LogManager.getLogger(SimpleJmsListener.class);
    
    private  AtomicLong messagesReceived = new AtomicLong();

    // can be concurrently accessed
    List<StringMessageHandler> messageHandlers = new CopyOnWriteArrayList<StringMessageHandler>();

    public void onMessage(final Message message) {
        long receivedcount = messagesReceived.incrementAndGet();
        if (message instanceof TextMessage) {
            final TextMessage textMessage = (TextMessage) message;
            try {
                LOG.info("received JMS message: "+receivedcount
                        + " " + textMessage.getText());
                for (StringMessageHandler messageHandler : messageHandlers) {
                    try {
                        messageHandler.onMessage(textMessage.getText());
                    } catch (Exception ex) {
                        LOG.error("problem processing message: " + textMessage.getText(), ex);
                    }
                }
            } catch (final JMSException e) {
                LOG.error("problem receiving JMS message", e);
            }
        }
    }

    public void addMessageHandler(StringMessageHandler stringMessageHandler) {
        messageHandlers.add(stringMessageHandler);
    }

    public void removeMessageHandler(StringMessageHandler stringMessageHandler) {
        messageHandlers.remove(stringMessageHandler);
    }

    @PreDestroy
    public void clearAllMessageHandlers() {
        LOG.debug("shutting down message listner - clearing message handlers");
        messageHandlers.clear();
    }
}
