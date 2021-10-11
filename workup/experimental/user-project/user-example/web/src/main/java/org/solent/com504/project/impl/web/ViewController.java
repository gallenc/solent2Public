/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.web;

import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gallenc
 */
@Controller
@RequestMapping("/mvc")
public class ViewController {

    final static Logger LOG = LogManager.getLogger(ViewController.class);

    {
        LOG.debug("ViewController created");
    }

    // This serviceFacade object is injected by Spring
    @Autowired(required = true)
    @Qualifier("serviceFacade")
    ServiceFacade serviceFacade = null;
    
    @RequestMapping("/testHeartbeat")
    public String testHeartbeat(Model m) {

        LOG.debug("testHeartbeat called");
        if (serviceFacade == null) {
            throw new RuntimeException("serviceFacade==null and has not been initialised");
        }

        m.addAttribute("serviceFacade", serviceFacade);

        // add error / response messages to page
        String errorMessage = "";
        String message = "";
        m.addAttribute("errorMessage", errorMessage);
        m.addAttribute("message", message);

        // render view with jsp
        return "testHeartbeat";
    }
    

}
