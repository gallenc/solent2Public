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
    

//    @RequestMapping("/farmhome")
//    public String farmhome(Model m,
//            @RequestParam(value = "animalName", required = false) String animalName,
//            @RequestParam(value = "animalType", required = false) String animalType) {
//
//        LOG.debug("farmhome called animalType=" + animalType + " animalName=" + animalName);
//        if (serviceFacade == null) {
//            throw new RuntimeException("serviceFacade==null and has not been initialised");
//        }
//
//        List<Animal> animalsList = serviceFacade.getAllAnimals();
//        List<String> supportedAnimalTypes = serviceFacade.getSupportedAnimalTypes();
//        m.addAttribute("animalsList", animalsList);
//        m.addAttribute("supportedAnimalTypes", supportedAnimalTypes);
//
//        // add error / response messages to page
//        String errorMessage = "";
//        String message = "";
//        m.addAttribute("errorMessage", errorMessage);
//        m.addAttribute("message", message);
//
//        // render view with jsp
//        return "farmlist";
//    }
//
//    @RequestMapping("/deleteAnimal")
//    public String deleteAnimal(Model m,
//            @RequestParam(value = "animalName", required = false) String animalName,
//            @RequestParam(value = "animalType", required = false) String animalType) {
//        LOG.debug("deleteAnimal called animalType=" + animalType + " animalName=" + animalName);
//        if (serviceFacade == null) {
//            throw new RuntimeException("serviceFacade==null and has not been initialised");
//        }
//
//        String errorMessage = "";
//        String message = "";
//
//        if (animalName == null || animalName.isEmpty()) {
//            errorMessage = "ERROR: animalName must be set when deleting animal.";
//        } else {
//            message = "Deleting animal name=" + animalName;
//            serviceFacade.removeAnimal(animalName);
//        }
//
//        m.addAttribute("errorMessage", errorMessage);
//        m.addAttribute("message", message);
//
//        // render view with jsp
//        return "redirect:/mvc/farmhome";//will redirect to farmhome request mapping    
//    }
//
//    @RequestMapping("/createAnimal")
//    public String createAnimal(Model m,
//            @RequestParam(value = "animalName", required = false) String animalName,
//            @RequestParam(value = "animalType", required = false) String animalType) {
//        LOG.debug("createAnimal called animalType=" + animalType + " animalName=" + animalName);
//        if (serviceFacade == null) {
//            throw new RuntimeException("serviceFacade==null and has not been initialised");
//        }
//
//        m.addAttribute("animalType", animalType);
//        m.addAttribute("animalName", animalName);
//
//        // add error / response messages to page
//        String errorMessage = "";
//        String message = "";
//
//        if (animalType == null || animalType.isEmpty()) {
//            errorMessage = "ERROR: animalType must be set when adding animal.";
//        }
//
//        m.addAttribute("errorMessage", errorMessage);
//        m.addAttribute("message", message);
//
//        // render view with jsp
//        return "reviewAnimal";
//    }
//
//    @RequestMapping("/addAnimal")
//    public String addAnimal(Model m,
//            @RequestParam(value = "animalName", required = false) String animalName,
//            @RequestParam(value = "animalType", required = false) String animalType) {
//        LOG.debug("addAnimal called animalType=" + animalType + " animalName=" + animalName);
//        if (serviceFacade == null) {
//            throw new RuntimeException("serviceFacade==null and has not been initialised");
//        }
//        // add error / response messages to page
//        String errorMessage = "";
//        String message = "";
//
//        m.addAttribute("animalType", animalType);
//        m.addAttribute("animalName", animalName);
//
//        if (animalName == null || animalName.isEmpty() || animalType == null || animalType.isEmpty()) {
//            errorMessage = "ERROR: animalType and animalName must both be set when adding animal.";
//        } else {
//            if (serviceFacade.getAnimal(animalName) != null) {
//                errorMessage = "ERROR: you cannot have dupicate animal names (" + animalName + ")";
//            } else {
//                serviceFacade.addAnimal(animalType, animalName);
//                return "redirect:/mvc/farmhome";//will redirect to farmhome request mapping  
//            }
//        }
//
//        m.addAttribute("errorMessage", errorMessage);
//        m.addAttribute("message", message);
//
//        // render view with jsp
//        return "reviewAnimal";//will redirect to farmhome request mapping    
//    }

}
