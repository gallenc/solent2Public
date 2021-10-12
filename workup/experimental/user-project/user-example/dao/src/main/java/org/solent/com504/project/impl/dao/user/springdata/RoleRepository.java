package org.solent.com504.project.impl.dao.user.springdata;

import java.util.List;
import org.solent.com504.project.model.user.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    public List<Role> findByName(@Param("rolename") String rolename);
    
}
