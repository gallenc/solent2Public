/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.user.springdata.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.impl.dao.spring.test.DAOTestConfiguration;
import org.solent.com504.project.model.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.impl.dao.user.springdata.UserRepository;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the DAOTestConfiguration class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class UserRepositoryTest {

    final static Logger LOG = LogManager.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository = null;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(userRepository);
        LOG.debug("before test complete");
    }

    
    @Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        User user1 = new User();
        user1 = userRepository.save(user1);
        System.out.println("user1=" + user1);

        Long id = user1.getId();
        User user2 = userRepository.getOne(id);
        System.out.println("user2=" + user2);
        LOG.debug("end of test1");
    }
}
