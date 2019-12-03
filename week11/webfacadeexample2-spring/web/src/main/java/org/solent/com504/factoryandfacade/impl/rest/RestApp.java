/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/rest")
public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("org.solent.com504.factoryandfacade.impl.rest");
        //register(RequestContextFilter.class);
        // property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}

// alternatve if using jaxrs directly
//public class RestApp extends Application {
//  public Set<Class<?>> getClasses() {
//    return new HashSet<Class<?>>(Arrays.asList(RestService.class));
//  }
//}
