/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author cgallen
 */
public class LoggingMessagesTest {
    
    public static final Logger LOG = LogManager.getLogger(LoggingMessagesTest.class);
    
    public static final Logger TRANSACTIONLOG = LogManager.getLogger("transaction-log");
    
    @Test
    public void hello() {
        LOG.debug("JUST SAYING HELLO DEBUG MESSAGE");
        LOG.warn("JUST SAYING HELLO WARN MESSAGE");
        LOG.info("JUST SAYING HELLO INFO MESSAGE");
        try {
            throw new RuntimeException("this is a test exception deliberately thrown");
        } catch (Exception ex) {
            LOG.error("JUST SAYING HELLO ERROR MESSAGE - the following stack trace is deliberate: ", ex);
        }
    }
    
    @Test
    public void testTransactionLog() {
        TRANSACTIONLOG.info("door opened");
    }
}
