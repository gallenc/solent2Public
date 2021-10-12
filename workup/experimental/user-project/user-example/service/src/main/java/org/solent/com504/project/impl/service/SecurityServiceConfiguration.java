/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service;

import java.util.List;
import org.solent.com504.project.impl.dao.spring.PersistenceJPAConfig;
import org.solent.com504.project.impl.user.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author cgallen
 */
@Configuration
@EnableGlobalAuthentication
public class SecurityServiceConfiguration {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);         // strength = 11

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

//    @Bean("userDetailsServiceImpl")
//    public UserDetailsService userDetailsSvc() {
//        userDetailsService = new UserDetailsServiceImpl();
//        return userDetailsService;
//    }
    @Bean("encoder")
    public BCryptPasswordEncoder encoder() {
        return encoder;
    }

}
