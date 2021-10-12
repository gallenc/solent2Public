package org.solent.devops.message.jms;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class JmsExceptionListener implements ExceptionListener {

    final static Logger LOG = LogManager.getLogger(JmsExceptionListener.class);

    public void onException(final JMSException e) {
        LOG.error("JMS exception: ", e);
    }
}
