#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package}.swingcient;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ${package}.model.Entity;
import ${package}.model.EntityDAO;

/**
 *
 * @author cgallen
 */
public class ModelControllerImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerImpl.class);

    private EntityDAO entityDAO = null;

    private EntityListTableModel entityListTableModel = new EntityListTableModel();

    private EntityClientLoader entityClientLoader = null;

    public ModelControllerImpl(EntityDAO entityDAO, EntityClientLoader entityClientLoader) {
        this.entityClientLoader = entityClientLoader;
        this.entityDAO = entityDAO;
        List<Entity> entities = entityDAO.retrieveAllEntities();
        entityListTableModel.setEntities(entities);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        return entityListTableModel;
    }

    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");

        List<Entity> entities = entityDAO.retrieveAllEntities();
        entityListTableModel.setEntities(entities);
    }

    @Override
    public void findMatchingSearch(Entity templateEntity) {
        LOG.debug("find matching with templateEntity=" + templateEntity);

        List<Entity> entities = entityDAO.retrieveMatchingEntities(templateEntity);
        LOG.debug("found " + entities.size() + " matching with templateEntity=" + templateEntity);
        entityListTableModel.setEntities(entities);

    }

    @Override
    public boolean forceReloadData() {
        LOG.debug("forceReloadData called");
        boolean success = false;
        if (entityClientLoader != null) {
            success = entityClientLoader.restClientRetrieveAll();
        }
        LOG.debug("forceReloadData result=" + success);
        return success;
    }

}
