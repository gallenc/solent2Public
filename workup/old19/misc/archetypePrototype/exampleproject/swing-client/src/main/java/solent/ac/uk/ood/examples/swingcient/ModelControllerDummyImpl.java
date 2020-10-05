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
import solent.ac.uk.ood.examples.model.Thing;

/**
 *
 * @author cgallen
 */
public class ModelControllerDummyImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerDummyImpl.class);

    private ThingListTableModel thingListTableModel = null;

    private void initialiseTableModel() {

        thingListTableModel = new ThingListTableModel();
        List<Thing> elist = new ArrayList<Thing>();

        int ENTITY_NUMBER = 40;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Thing thing = new Thing();
            thing.setId(intityId);
            thing.setField_A("field_A_" + intityId);
            thing.setField_B("field_B_" + intityId);;
            thing.setField_C("field_C_" + intityId);;
            elist.add(thing);
        }
        thingListTableModel.setThings(elist);
    }

    @Override
    public ThingListTableModel getThingListTableModel() {
        if (thingListTableModel == null) {
            synchronized (ModelControllerDummyImpl.class) {
                if (thingListTableModel == null) {
                    initialiseTableModel();
                }
            }
        }
        return thingListTableModel;

    }
    


    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");
    }

    @Override
    public void findMatchingSearch(Thing templateThing) {
        LOG.debug("find matching with templateThing="+templateThing);
    }

    @Override
    public boolean forceReloadData() {
        return false;
    }



}
