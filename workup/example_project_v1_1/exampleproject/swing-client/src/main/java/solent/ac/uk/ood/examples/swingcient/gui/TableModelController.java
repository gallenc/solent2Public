/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient.gui;

import java.util.ArrayList;
import java.util.List;
import solent.ac.uk.ood.examples.model.Entity;

/**
 *
 * @author cgallen
 */
public class TableModelController {

    private static EntityListTableModel entityListTableModel = null;

    private static void initialiseTableModel() {

        entityListTableModel = new EntityListTableModel();
        // add a 3 entities
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

    public static EntityListTableModel getTableModel() {
        if (entityListTableModel == null) {
            synchronized (TableModelController.class) {
                if (entityListTableModel == null) {
                    initialiseTableModel();
                }
            }
        }
        return entityListTableModel;

    }

}
