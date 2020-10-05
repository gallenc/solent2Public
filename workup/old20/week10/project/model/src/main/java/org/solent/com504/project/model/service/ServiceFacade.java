package org.solent.com504.project.model.service;

import java.util.List;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;

public interface ServiceFacade {

    public String getHeartbeat();

    /**
     * find all persons in database by role if role is null return all persons
     *
     * @param role
     * @return list of person objects
     */
    public List<Person> findByRole(Role role);

}
