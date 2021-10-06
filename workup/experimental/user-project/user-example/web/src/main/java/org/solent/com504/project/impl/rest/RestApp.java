/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class RestApp extends ResourceConfig {

    // produces http://localhost:8080/project-web/rest/openapi.json 
    // see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started
    public RestApp() {
        packages("org.solent.com504.project.impl.rest",
                "io.swagger.v3.jaxrs2.integration.resources"
        );
        // configureSwagger();
    }

    // swagger 1.5
    // see https://stackoverflow.com/questions/40480131/how-to-use-swagger-with-resourceconfig-in-jersey
//    private void configureSwagger() {
//        this.register(ApiListingResource.class);
//        this.register(SwaggerSerializers.class);
//        BeanConfig config = new BeanConfig();
//        config.setConfigId("spring-jaxrs-swagger");
//        config.setTitle("Spring Jersey jaxrs swagger integration");
//        config.setVersion("v1.0");
//        config.setBasePath("/swagger");
//        config.setResourcePackage("org.solent.com504.project.impl.rest");
//        config.setPrettyPrint(true);
//        config.setScan(true);
    // http://localhost:8080/project-web/rest/swagger/v1.0/swagger.json
    // http://localhost:8080/swagger/v1.0/swagger.json
    // }
}

// alternatve if using jaxrs directly
//public class RestApp extends Application {
//  public Set<Class<?>> getClasses() {
//    return new HashSet<Class<?>>(Arrays.asList(RestService.class));
//  }
//}
