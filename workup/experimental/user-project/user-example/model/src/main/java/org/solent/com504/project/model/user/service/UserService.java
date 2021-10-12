package org.solent.com504.project.model.user.service;

import java.util.List;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;

public interface UserService {

    void create(User user);

    User findByUsername(String username);

    List<User> findAll();

    User save(User user);
    
    User updateUserRoles(String username, List<String> roleNames);
    
    List<Role> getAvailableUserRoles();
    
    List<String> getAvailableUserRoleNames();

}
