/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ThingDAO;

/**
 *
 * @author cgallen
 */
public class ModelControllerImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerImpl.class);

    private ThingDAO thingDAO = null;

    private ThingListTableModel thingListTableModel = new ThingListTableModel();

    private ThingClientLoader thingClientLoader = null;

    public ModelControllerImpl(ThingDAO thingDAO, ThingClientLoader thingClientLoader) {
        this.thingClientLoader = thingClientLoader;
        this.thingDAO = thingDAO;
        List<Thing> things = thingDAO.retrieveAllThings();
        thingListTableModel.setThings(things);
    }

    @Override
    public ThingListTableModel getThingListTableModel() {
        return thingListTableModel;
    }

    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");

        List<Thing> things = thingDAO.retrieveAllThings();
        thingListTableModel.setThings(things);
    }

    @Override
    public void findMatchingSearch(Thing templateThing) {
        LOG.debug("find matching with templateThing=" + templateThing);

        List<Thing> things = thingDAO.retrieveMatchingThings(templateThing);
        LOG.debug("found " + things.size() + " matching with templateThing=" + templateThing);
        thingListTableModel.setThings(things);

    }

    @Override
    public boolean forceReloadData() {
        LOG.debug("forceReloadData called");
        boolean success = false;
        if (thingClientLoader != null) {
            success = thingClientLoader.restClientRetrieveAll();
        }
        LOG.debug("forceReloadData result=" + success);
        return success;
    }

}
