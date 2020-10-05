/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * This is simply a class to print out hello and prove spring application
 * context is loading
 *
 * @author gallenc
 */
public class HelloWorld {

    final static Logger LOG = LogManager.getLogger(HelloWorld.class);

    @Autowired
    private ApplicationContext applicationContext;

    private String message = "";

    public void setMessage(String message) {
        this.message = message;
    }

    public void init() {
        LOG.debug("init() Application context started HelloWorld " + message);

        // this simply prints out the contents of the applicationContext
        String[] beannames = applicationContext.getBeanDefinitionNames();

        String msg = "******* beans in applicationContext " + beannames.length + " names: ";
        for (String b : Arrays.asList(beannames)) {
            msg = msg + "\n " + b + ", ";
        }
        LOG.debug(msg);

    }

    public void destroy() {
        LOG.debug("destroy() Application context stopped HelloWorld " + message);

    }

}
