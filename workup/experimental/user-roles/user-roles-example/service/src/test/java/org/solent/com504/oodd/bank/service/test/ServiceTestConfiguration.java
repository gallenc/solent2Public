/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.service.test;

import org.solent.com504.oodd.bank.service.ServiceConfiguration;
import org.solent.com504.oodd.dao.impl.PersistenceJPAConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;


/**
 *
 * @author cgallen
 */
@Configuration
@Import(ServiceConfiguration.class)
@PropertySource("classpath:persistence-test.properties")
public class ServiceTestConfiguration {
    
}
