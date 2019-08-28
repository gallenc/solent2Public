#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("${package}.web.rest");
    }
}
