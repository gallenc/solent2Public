/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.user.springdata.test;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.user.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.impl.dao.user.springdata.RoleRepository;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class RoleRepositoryTest {

    final static Logger LOG = LogManager.getLogger(RoleRepositoryTest.class);

    @Autowired
    private RoleRepository roleRepository = null;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(roleRepository);
        LOG.debug("before test complete");
    }

    @Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        String rolename1 = "testrole1";
        Role role1 = new Role();
        role1.setName(rolename1);

        role1 = roleRepository.save(role1);
        LOG.debug("role1 id=" + role1.getId() + " " + role1.getName());

        String rolename2 = "testrole2";
        Role role2 = new Role();
        role2.setName(rolename2);

        role2 = roleRepository.save(role2);
        LOG.debug("role2 id=" + role2.getId() + " " + role2.getName());

        // find by ID
        Long id = role1.getId();
        Optional<Role> o = roleRepository.findById(id);
        Role role3 = o.get();
        LOG.debug("found role3 id="  + role3.getId() + " " + role3.getName());

        // find by name
        List<Role> roles = roleRepository.findByName(rolename1);
        LOG.debug("findByName("+rolename1+ ") found " + roles.size());
        for (Role r : roles) {
            LOG.debug("found role id  " + r.getId() +  " name " + r.getName());
        }
        
        assertEquals(role1.getName(),roles.get(0).getName());

        LOG.debug("end of test1");
    }
}
