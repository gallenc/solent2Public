package org.solent.com504.project.model.service;

import java.util.List;
import org.solent.com504.project.model.dto.Actor;
import org.solent.com504.project.model.dto.Role;

public interface ServiceFacade {

    public String getHeartbeat();

    /**
     * find all actors in database by role if role is null return all actors
     *
     * @param role
     * @return list of actor objects
     */
    public List<Actor> findByRole(Role role);

}
