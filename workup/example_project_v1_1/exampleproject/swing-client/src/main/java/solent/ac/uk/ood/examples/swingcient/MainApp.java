package solent.ac.uk.ood.examples.swingcient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainApp {
	
    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);
    
    public static final Logger TRANSACTIONLOG = LoggerFactory.getLogger("transaction-log");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// just testing logging works
		System.out.println("MainApp started");
		LOG.info("MainApp started");
		TRANSACTIONLOG.info("MainApp started");
		
	}

}
