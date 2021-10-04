/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.dao.test;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.solent.com504.oodd.dao.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import org.solent.com504.oodd.user.model.dto.User;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes=DAOTestConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class UserRepositoryTest {
      private static final Logger LOG = LogManager.getLogger(UserRepositoryTest.class);
    
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUser() {
        LOG.debug("****************** starting test");
        
        userRepository.deleteAll();
        
        User user1 = new User();
        user1.setFirstName("craig");
        user1.setSecondName("gallen");
        user1 = userRepository.save(user1);
        
        assertEquals(1, userRepository.count());
        
        Optional<User> optional = userRepository.findById(user1.getId());
        User foundUser = optional.get();
        
        LOG.debug("found user: "+foundUser);
        
        LOG.debug("****************** test complete");
    }
    
}
