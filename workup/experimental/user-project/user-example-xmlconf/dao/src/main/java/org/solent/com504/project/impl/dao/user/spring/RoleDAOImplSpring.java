/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.user.spring;

import java.util.List;
import java.util.Optional;
import org.solent.com504.project.impl.dao.user.springdata.RoleRepository;
import org.solent.com504.project.model.user.dao.RoleDAO;
import org.solent.com504.project.model.user.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class RoleDAOImplSpring implements RoleDAO {

    @Autowired
    private RoleRepository roleRepository = null;

    @Override
    public Role findById(Long id) {
        Optional<Role> o = roleRepository.findById(id);
        if (o.isPresent()) {
            return o.get();
        }
        return null;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public Role findByRoleName(String rolename) {
        // there should only be one of each role
        List<Role> roles = roleRepository.findByName(rolename);
        if (roles.isEmpty()) {
            return null;
        }
        return roles.get(0);
    }

}
