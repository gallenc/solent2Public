/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * initialises a web application context 
 * @author gallenc
 */
public class XmlWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
       // XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
       // rootContext.setConfigLocation("/WEB-INF/config/root-context.xml");

       // container.addListener(new ContextLoaderListener(rootContext));

        XmlWebApplicationContext dispatcherContext = new XmlWebApplicationContext();
        dispatcherContext.setConfigLocation("/WEB-INF/config/servlet-context.xml");

        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}