/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service;

import org.solent.com504.project.impl.dao.spring.PersistenceJPAConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author cgallen
 */
@Configuration
//@ComponentScan
@ComponentScan(basePackages = {"org.solent.com504.project.impl.party.service",
    "org.solent.com504.project.impl.service",
    "org.solent.com504.project.impl.user.service"})
@Import({PersistenceJPAConfig.class, SecurityServiceConfiguration.class})
public class ServiceConfiguration {

    @Bean
    public HelloWorld helloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage("application starting");
        return helloWorld;
    }

}
