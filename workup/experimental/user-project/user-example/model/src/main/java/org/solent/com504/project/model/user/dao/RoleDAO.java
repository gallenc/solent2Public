/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.user.dao;

import java.util.List;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;

/**
 *
 * @author cgallen
 */
public interface RoleDAO {

    public Role findById(Long id);

    public Role save(Role role);

    public List<Role> findAll();

    public void deleteById(long id);

    public void delete(Role role);

    public void deleteAll();

    public Role findByRoleName(String rolename);


}
