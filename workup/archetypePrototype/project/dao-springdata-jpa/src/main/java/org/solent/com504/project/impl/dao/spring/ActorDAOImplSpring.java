/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.spring;

import java.util.List;
import org.solent.com504.project.model.dao.springdata.ActorDAOSpringData;
import org.solent.com504.project.model.dto.Actor;
import org.solent.com504.project.model.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.solent.com504.project.model.dao.ActorDAO;


/**
 *
 * @author gallenc
 */
@Component
public class ActorDAOImplSpring implements ActorDAO {
    
    @Autowired
    private ActorDAOSpringData actorDAOspring = null;

    @Override
    public Actor findById(Long id) {
        return actorDAOspring.getOne(id);
    }

    @Override
    public Actor save(Actor actor) {
        return actorDAOspring.save(actor);
    }

    @Override
    public List<Actor> findAll() {
        return actorDAOspring.findAll();
    }

    @Override
    public void deleteById(long id) {
        actorDAOspring.deleteById(id);
    }

    @Override
    public Actor delete(Actor actor) {
        actorDAOspring.delete(actor);
        return null; // TODO SHOULD CHANGE INTERFACE TO RETURN NULL
    }

    @Override
    public void deleteAll() {
        actorDAOspring.deleteAll();
    }

    @Override
    public List<Actor> findByRole(Role role) {
        return actorDAOspring.findByRole(role);
    }

    @Override
    public List<Actor> findByName(String firstName, String secondName) {
        return actorDAOspring.findByName(firstName, secondName);
    }
    
}
