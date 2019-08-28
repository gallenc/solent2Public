/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.service;

import java.util.List;
import solent.ac.uk.ood.examples.model.Entity;
import solent.ac.uk.ood.examples.model.EntityDAO;
import solent.ac.uk.ood.examples.model.ServiceFacade;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImpl implements ServiceFacade {
    
    EntityDAO entityDAO = null;

    public void setEntityDAO(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    @Override
    public void deleteAllEntities() {
       entityDAO.deleteAllEntities();
    }

    @Override
    public Entity createEntity(Entity entity) {
        return entityDAO.createEntity(entity);
    }

    @Override
    public boolean deleteEntity(Integer id) {
        return entityDAO.deleteEntity(id);
    }

    @Override
    public Entity retrieveEntity(Integer id) {
        return entityDAO.retrieveEntity(id);
    }

    @Override
    public List<Entity> retrieveAllEntities() {
        return entityDAO.retrieveAllEntities();
    }

    @Override
    public List<Entity> retrieveMatchingEntities(Entity entityTempate) {
        return entityDAO.retrieveMatchingEntities(entityTempate);
    }

    @Override
    public Entity updateEntity(Entity entity) {
        return entityDAO.updateEntity(entity);
    }
    
}
