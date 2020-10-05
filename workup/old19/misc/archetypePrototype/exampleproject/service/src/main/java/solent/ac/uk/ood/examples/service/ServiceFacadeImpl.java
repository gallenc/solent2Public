/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.service;

import java.util.List;
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ThingDAO;
import solent.ac.uk.ood.examples.model.ServiceFacade;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImpl implements ServiceFacade {
    
    ThingDAO thingDAO = null;

    public void setThingDAO(ThingDAO thingDAO) {
        this.thingDAO = thingDAO;
    }

    @Override
    public void deleteAllThings() {
       thingDAO.deleteAllThings();
    }

    @Override
    public Thing createThing(Thing thing) {
        return thingDAO.createThing(thing);
    }

    @Override
    public boolean deleteThing(Integer id) {
        return thingDAO.deleteThing(id);
    }

    @Override
    public Thing retrieveThing(Integer id) {
        return thingDAO.retrieveThing(id);
    }

    @Override
    public List<Thing> retrieveAllThings() {
        return thingDAO.retrieveAllThings();
    }

    @Override
    public List<Thing> retrieveMatchingThings(Thing thingTempate) {
        return thingDAO.retrieveMatchingThings(thingTempate);
    }

    @Override
    public Thing updateThing(Thing thing) {
        return thingDAO.updateThing(thing);
    }
    
}
