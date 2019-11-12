/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.web;

import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.rest.RestService;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * initialises a web application context see
 * https://psamsotha.github.io/jersey/2015/10/19/why-use-spring-with-jersey.html
 *
 * @author gallenc
 */
@Order(1)
public class XmlWebAppInitializer implements WebApplicationInitializer {

    final static Logger LOG = LogManager.getLogger(XmlWebAppInitializer.class);

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // this stops jersey from loading applicationContext
        // container.setInitParameter("contextConfigLocation", "noop");
        container.setInitParameter("contextConfigLocation", "classpath:servlet-context.xml");

        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocation("classpath:servlet-context.xml");

        container.addListener(new ContextLoaderListener(rootContext));
        container.addListener(new RequestContextListener());

        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        Set<String> mappings = dispatcher.addMapping("/");
//        
//        if (LOG.isDebugEnabled()) {
//            StringBuffer msg = new StringBuffer("dispatcher mappings:");
//            for (String servletMapping : mappings) {
//                msg.append("\n    "+servletMapping);
//            }
//            LOG.debug(msg.toString());
//        }
    }
}
