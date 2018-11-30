/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exampleproject.dao.jaxbimpl.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.exampleproject.dao.jaxbimpl.EntityDAOJaxbImpl;
import solent.ac.uk.ood.examples.exampleproject.model.Entity;
import solent.ac.uk.ood.examples.exampleproject.model.EntityDAO;
import solent.ac.uk.ood.examples.exampleproject.model.Property;

/**
 * tests for entityDao.createEntity(entity) 
 * entityDao.deleteEntity(Id) 
 * entityDao.retrieveAllEntities() 
 * entityDao.retrieveEntity(Id)
 * entityDao.retrieveMatchingEntites(entityTempate)
 * entityDao.updateEntity(entity)
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
        int ENTITY_NUMBER = 3;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Entity entity = new Entity();
            entity.setId(intityId);
            // add 3 newProperties for entity
            List<Property> properties = entity.getProperties();
            for (int propertyId = 0; propertyId < 3; propertyId++) {
                Property property = new Property();
                property.setName("name_" + propertyId);
                property.setValue("value_" + propertyId);
                properties.add(property);
            }
            LOG.debug("adding entity:" + entity);
            entityDao.createEntity(entity);
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
        List<Property> newProperties = new ArrayList<Property>();
        for (int propertyId = 0; propertyId < 3; propertyId++) {
            Property property = new Property();
            property.setName("NEWname_" + propertyId);
            property.setValue("NEWvalue_" + propertyId);
            newProperties.add(property);
        }
        entityToUpdate.setProperties(newProperties);
        entityDao.updateEntity(entityToUpdate);

        // check entity updated
        Entity retrievedEntity = entityDao.retrieveEntity(entityToUpdate.getId());
        assertEquals(entityToUpdate.getProperties().get(0).getName(), retrievedEntity.getProperties().get(0).getName());
        assertEquals(entityToUpdate.getProperties().get(0).getValue(), retrievedEntity.getProperties().get(0).getValue());
        
        // test retreive matching entities
        

    }

}
