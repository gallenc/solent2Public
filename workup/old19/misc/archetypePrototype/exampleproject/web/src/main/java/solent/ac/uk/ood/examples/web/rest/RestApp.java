package solent.ac.uk.ood.examples.web.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("solent.ac.uk.ood.examples.web.rest");
    }
}
