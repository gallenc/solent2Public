#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.swingcient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import ${package}.swingcient.gui.AppMainJFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ${package}.dao.jaxbimpl.EntityDAOJaxbImpl;
import ${package}.model.EntityDAO;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static final Logger TRANSACTIONLOG = LoggerFactory.getLogger("transaction-log");

    public static final String DEFAULT_BASE_URL = "http://localhost:8680/";

    public static final String DEFAULT_DAO_FILE = "./localDataFile.xml";

    public static final String DEFAULT_PROPERTIES_FILE = "./config.properties";

    public static final String DEFAULT_SCHEDULE_INTERVAL_SECONDS = "120";

    public static final String DEFAULT_RETRY_ATTEMPTS = "4";

    public static final String DEFAULT_RETRY_INTERVAL_SECONDS = "10";

    public static final String DEFAULT_SCHEDULE_ENABLED = "false";

    public static final String CONFIG_FILE_NAME = "config.properties";

    public static void main(String[] args) {
        // just testing logging works
        System.out.println("MainApp started");
        LOG.info("MainApp started");
        TRANSACTIONLOG.info("MainApp started");

        String filename = CONFIG_FILE_NAME;
        String fileURI = null;

        // check if properties in command line e.g. -p config.properties
        if (args.length > 0) {
            String cmdLine = "Command Line Args: ";
            for (String arg : args) {
                cmdLine = cmdLine + arg + " ";
            }
            LOG.info(cmdLine);
            if ("-p".equals(args[0])) {
                fileURI = args[1];
            }
        }

        // load properties
        Properties properties = new Properties();
        InputStream input = null;

        try {

            // load from uri
            if (fileURI != null && !fileURI.isEmpty()) {
                File f = new File(fileURI);
                LOG.debug("loading properties from file: "+ f.getAbsolutePath());
                input = new FileInputStream(f);
            } else {
                // load from class path
                input = MainApp.class.getClassLoader().getResourceAsStream(filename);
            }

            if (input == null) {
                LOG.info("Sorry, using default configuration unable to find properties file " + filename);
            } else {
                LOG.info("loading properties from " + filename);
                properties.load(input);
            }
        } catch (IOException ex) {
            LOG.error("problem loading properties", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }

        // setting up properties
        String dataFileLocation = properties.getProperty("dataFileLocation", DEFAULT_DAO_FILE);
        String baseUrl = properties.getProperty("baseUrl", DEFAULT_BASE_URL);
        Integer scheduleIntervalSeconds = Integer.parseInt(properties.getProperty("scheduleIntervalSeconds", DEFAULT_SCHEDULE_INTERVAL_SECONDS));
        Integer retryAttempts = Integer.parseInt(properties.getProperty("retryAttempts", DEFAULT_RETRY_ATTEMPTS));
        Integer retryIntervalSeconds = Integer.parseInt(properties.getProperty("retryIntervalSeconds", DEFAULT_RETRY_INTERVAL_SECONDS));
        boolean scheduleEnabled = Boolean.valueOf(properties.getProperty("scheduleEnabled", DEFAULT_SCHEDULE_ENABLED));

        String configmsg = "Starting with configuration {"
                + "dataFileLocation=" + dataFileLocation
                + ", baseUrl=" + baseUrl
                + ", scheduleIntervalSeconds=" + scheduleIntervalSeconds
                + ", retryAttempts=" + retryAttempts
                + ", retryIntervalSeconds=" + retryIntervalSeconds
                + ", scheduleEnabled=" + scheduleEnabled + '}';
        LOG.info(configmsg);

        //setting up dao
        EntityDAO entityDAO = new EntityDAOJaxbImpl(dataFileLocation);

        // setting up client loader
        EntityClientLoader entityClientLoader = new EntityClientLoader(entityDAO, baseUrl);

        // schedule entity client loader
        if (scheduleEnabled) {
            entityClientLoader.scheduleLoadData(scheduleIntervalSeconds, retryAttempts, retryIntervalSeconds);
        }

        ModelController controller = new ModelControllerImpl(entityDAO, entityClientLoader);

        //  AppMainJFrame mainJFrame = new AppMainJFrame(controller);
        //  mainJFrame.pack();
        //  mainJFrame.setVisible(true);
        // starting the gui in seperate thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppMainJFrame(controller).setVisible(true);
            }
        });

    }

}
