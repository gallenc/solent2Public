/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is simply a class to print out hello and prove spring application
 * context is loading
 *
 * @author gallenc
 */
public class HelloWorld {

    final static Logger LOG = LogManager.getLogger(HelloWorld.class);
    
    private String message="";

    public void setMessage(String message) {
        this.message = message;
    }

    public void init() {
        LOG.debug("init() Application context started HelloWorld "+message);
    }

    public void destroy() {
        LOG.debug("destroy() Application context stopped HelloWorld "+message);

    }

}
