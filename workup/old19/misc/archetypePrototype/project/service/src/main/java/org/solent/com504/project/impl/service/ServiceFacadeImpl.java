package org.solent.com504.project.impl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.solent.com504.project.model.dto.Actor;
import org.solent.com504.project.model.dto.Role;
import org.solent.com504.project.model.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.solent.com504.project.model.dao.ActorDAO;

// note we give the bean this name so it is picked up later in application context
@Component("serviceFacade")
public class ServiceFacadeImpl implements ServiceFacade {

        @Autowired
    private ActorDAO actorDao = null;



    // used to concurently count heartbeat requests
    private static AtomicInteger heartbeatRequests = new AtomicInteger();

    // setters for DAOs
    public void setActorDao(ActorDAO actorDao) {
        this.actorDao = actorDao;
    }


    // Service facade methods
    @Override
    public String getHeartbeat() {
        return "heartbeat number " + heartbeatRequests.getAndIncrement() + " " + new Date().toString();

    }

    @Override
    public List<Actor> findByRole(Role role) {
        if (role == null) {
            return actorDao.findAll();
        } else {
            return actorDao.findByRole(role);
        }
    }

}
