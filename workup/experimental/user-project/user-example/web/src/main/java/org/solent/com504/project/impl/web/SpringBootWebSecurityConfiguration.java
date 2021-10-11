package org.solent.com504.project.impl.web;

import org.solent.com504.project.impl.service.ServiceConfiguration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringBootWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/",
                        "/home",
                        "/contact",
                        "/about",
                        "/index.html",
                        "/resources/**",
                        "/images/**",
                        "/swagger-ui/**",
                        "/registration",
                        "/rest/openapi.json"
                ).permitAll()
                .antMatchers("/mvc/**"
                ).hasRole("USER") // ROLE_USER 
                .antMatchers("/users").hasRole("GLOBAL_ADMIN") // ROLE_GLOBAL_ADMIN
                .antMatchers("/rest/**").hasAnyRole("REST_USER","GLOBAL_ADMIN") // ROLE_GLOBAL_ADMIN
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
}
