/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.dao.jaxbimpl.test;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.dao.jaxbimpl.ThingDAOJaxbImpl;
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ThingDAO;

/**
 * tests for thingDao.createThing(thing) thingDao.deleteThing(Id) thingDao.retrieveAllThings() thingDao.retrieveThing(Id)
 * thingDao.retrieveMatchingEntites(thingTempate) thingDao.updateThing(thing)
 *
 * @author cgallen
 */
public class ThingDAOJaxbImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(ThingDAOJaxbImplTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    @Test
    public void testDestinationsDAOJaxb() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        ThingDAO thingDao = new ThingDAOJaxbImpl(TEST_DATA_FILE_LOCATION);

        // check that new file created
        assertTrue(file.exists());

        // check there are no things
        assertTrue(thingDao.retrieveAllThings().isEmpty());

        // add a 3 things
        int ENTITY_NUMBER = 4;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Thing thing = new Thing();
            thing.setField_A("field_A_" + intityId);
            thing.setField_B("field_B_" + intityId);;
            thing.setField_C("field_C_" + intityId);;

            LOG.debug("adding thing:" + thing);
            Thing e = thingDao.createThing(thing);
            assertNotNull(e);
        }

        // check 3 things added
        assertTrue(ENTITY_NUMBER == thingDao.retrieveAllThings().size());

        // check return false for delete unknown thing
        assertFalse(thingDao.deleteThing(Integer.SIZE));

        // find an thing to delete
        List<Thing> elist = thingDao.retrieveAllThings();
        Integer idToDelete = elist.get(1).getId();
        LOG.debug("deleting  thing:" + idToDelete);

        // check found and deleted
        assertTrue(thingDao.deleteThing(idToDelete));

        // check no longer found after deletion
        assertNull(thingDao.retrieveThing(idToDelete));

        // check things size decremeted
        List<Thing> elist2 = thingDao.retrieveAllThings();
        assertTrue(ENTITY_NUMBER - 1 == elist2.size());

        // update thing
        Thing thingToUpdate = elist2.get(1);
        LOG.debug("updating thing: " + thingToUpdate);

        // add 3 newProperties for thing
        thingToUpdate.setField_A("field_A_Update");
        thingToUpdate.setField_B("field_B_Update");
        thingToUpdate.setField_C(null); // do not update field C
        LOG.debug("update template: " + thingToUpdate);

        Thing updatedThing = thingDao.updateThing(thingToUpdate);
        LOG.debug("updated thing: " + updatedThing);
        assertNotNull(updatedThing);

        // check thing updated
        Thing retrievedThing = thingDao.retrieveThing(updatedThing.getId());
        LOG.debug("retreived thing: " + retrievedThing);
        assertEquals(thingToUpdate.getField_A(), retrievedThing.getField_A());
        assertEquals(thingToUpdate.getField_A(), retrievedThing.getField_A());
        assertNotEquals(thingToUpdate.getField_C(), retrievedThing.getField_C());

        // test retrieve matching things
        List<Thing> thingList = thingDao.retrieveAllThings();
        Thing searchfor = thingList.get(2);
        LOG.debug("searching for: " + searchfor);

        Thing template = new Thing();
        template.setField_B(searchfor.getField_B());
        LOG.debug("using template : " + template);

        List<Thing> retrievedList = thingDao.retrieveMatchingThings(template);
        assertEquals(1, retrievedList.size());

        LOG.debug("found : " + retrievedList.get(0));
        assertEquals(searchfor, retrievedList.get(0));

    }

}
