/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.model.Entity;

/**
 *
 * @author cgallen
 */
public class ModelControllerDummyImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerDummyImpl.class);

    private EntityListTableModel entityListTableModel = null;

    private void initialiseTableModel() {

        entityListTableModel = new EntityListTableModel();
        List<Entity> elist = new ArrayList<Entity>();

        int ENTITY_NUMBER = 40;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Entity entity = new Entity();
            entity.setId(intityId);
            entity.setField_A("field_A_" + intityId);
            entity.setField_B("field_B_" + intityId);;
            entity.setField_C("field_C_" + intityId);;
            elist.add(entity);
        }
        entityListTableModel.setEntities(elist);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        if (entityListTableModel == null) {
            synchronized (ModelControllerDummyImpl.class) {
                if (entityListTableModel == null) {
                    initialiseTableModel();
                }
            }
        }
        return entityListTableModel;

    }
    


    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");
    }

    @Override
    public void findMatchingSearch(Entity templateEntity) {
        LOG.debug("find matching with templateEntity="+templateEntity);
    }

    @Override
    public boolean forceReloadData() {
        return false;
    }



}
