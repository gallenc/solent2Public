package solent.ac.uk.ood.examples.swingcient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import solent.ac.uk.ood.examples.swingcient.gui.AppMainJFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.dao.jaxbimpl.EntityDAOJaxbImpl;
import solent.ac.uk.ood.examples.model.EntityDAO;
import static java.util.concurrent.TimeUnit.*;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static final Logger TRANSACTIONLOG = LoggerFactory.getLogger("transaction-log");

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // just testing logging works
        System.out.println("MainApp started");
        LOG.info("MainApp started");
        TRANSACTIONLOG.info("MainApp started");

        String dataFileLocation = "./localDataFile.xml";

        EntityDAO entityDAO = new EntityDAOJaxbImpl(dataFileLocation);

        ModelController controller = new ModelControllerImpl(entityDAO);

//        AppMainJFrame mainJFrame = new AppMainJFrame(controller);
//        mainJFrame.pack();
//        mainJFrame.setVisible(true);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppMainJFrame(controller).setVisible(true);
            }
        });

    }

    

}
