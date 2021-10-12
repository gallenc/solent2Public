/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.service;

import org.solent.com504.oodd.dao.impl.PersistenceJPAConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author cgallen
 */
@Configuration
@ComponentScan
@Import(PersistenceJPAConfig.class)
public class ServiceConfiguration {
    
}
