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
import solent.ac.uk.ood.examples.dao.jaxbimpl.EntityDAOJaxbImpl;
import solent.ac.uk.ood.examples.model.Entity;
import solent.ac.uk.ood.examples.model.EntityDAO;

/**
 * tests for entityDao.createEntity(entity) entityDao.deleteEntity(Id) entityDao.retrieveAllEntities() entityDao.retrieveEntity(Id)
 * entityDao.retrieveMatchingEntites(entityTempate) entityDao.updateEntity(entity)
 *
 * @author cgallen
 */
public class EntityDAOJaxbImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(EntityDAOJaxbImplTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    @Test
    public void testDestinationsDAOJaxb() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        EntityDAO entityDao = new EntityDAOJaxbImpl(TEST_DATA_FILE_LOCATION);

        // check that new file created
        assertTrue(file.exists());

        // check there are no entities
        assertTrue(entityDao.retrieveAllEntities().isEmpty());

        // add a 3 entities
        int ENTITY_NUMBER = 4;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Entity entity = new Entity();
            entity.setField_A("field_A_" + intityId);
            entity.setField_B("field_B_" + intityId);;
            entity.setField_C("field_C_" + intityId);;

            LOG.debug("adding entity:" + entity);
            Entity e = entityDao.createEntity(entity);
            assertNotNull(e);
        }

        // check 3 entities added
        assertTrue(ENTITY_NUMBER == entityDao.retrieveAllEntities().size());

        // check return false for delete unknown entity
        assertFalse(entityDao.deleteEntity(Integer.SIZE));

        // find an entity to delete
        List<Entity> elist = entityDao.retrieveAllEntities();
        Integer idToDelete = elist.get(1).getId();
        LOG.debug("deleting  entity:" + idToDelete);

        // check found and deleted
        assertTrue(entityDao.deleteEntity(idToDelete));

        // check no longer found after deletion
        assertNull(entityDao.retrieveEntity(idToDelete));

        // check entities size decremeted
        List<Entity> elist2 = entityDao.retrieveAllEntities();
        assertTrue(ENTITY_NUMBER - 1 == elist2.size());

        // update entity
        Entity entityToUpdate = elist2.get(1);
        LOG.debug("updating entity: " + entityToUpdate);

        // add 3 newProperties for entity
        entityToUpdate.setField_A("field_A_Update");
        entityToUpdate.setField_B("field_B_Update");
        entityToUpdate.setField_C(null); // do not update field C
        LOG.debug("update template: " + entityToUpdate);

        Entity updatedEntity = entityDao.updateEntity(entityToUpdate);
        LOG.debug("updated entity: " + updatedEntity);
        assertNotNull(updatedEntity);

        // check entity updated
        Entity retrievedEntity = entityDao.retrieveEntity(updatedEntity.getId());
        LOG.debug("retreived entity: " + retrievedEntity);
        assertEquals(entityToUpdate.getField_A(), retrievedEntity.getField_A());
        assertEquals(entityToUpdate.getField_A(), retrievedEntity.getField_A());
        assertNotEquals(entityToUpdate.getField_C(), retrievedEntity.getField_C());

        // test retrieve matching entities
        List<Entity> entityList = entityDao.retrieveAllEntities();
        Entity searchfor = entityList.get(2);
        LOG.debug("searching for: " + searchfor);

        Entity template = new Entity();
        template.setField_B(searchfor.getField_B());
        LOG.debug("using template : " + template);

        List<Entity> retrievedList = entityDao.retrieveMatchingEntities(template);
        assertEquals(1, retrievedList.size());

        LOG.debug("found : " + retrievedList.get(0));
        assertEquals(searchfor, retrievedList.get(0));

    }

}
