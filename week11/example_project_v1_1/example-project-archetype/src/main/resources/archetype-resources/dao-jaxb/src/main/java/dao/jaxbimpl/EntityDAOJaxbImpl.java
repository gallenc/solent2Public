#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package}.dao.jaxbimpl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ${package}.model.Entity;
import ${package}.model.EntityDAO;
import ${package}.model.EntityList;

/**
 *
 * @author cgallen
 */
public class EntityDAOJaxbImpl implements EntityDAO {

    private static final Logger LOG = LoggerFactory.getLogger(EntityDAOJaxbImpl.class);

    // jaxb context needs jaxb.index jaxbFile to be in same classpath
    // this path contains a list of Jaxb annotated classes for the context to parse
    private static final String CONTEXT_PATH = "${package}.model";

    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();

    private String dataFileLocation = null;

    private File jaxbFile = null;

    private EntityList entityList = null;

    private JAXBContext jaxbContext = null;

    public EntityDAOJaxbImpl(String dataFileLocation) {
        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }
        this.dataFileLocation = dataFileLocation;
        load();
    }

    @Override
    public Entity createEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity cannot be null");
        }
        synchronized (Lock) {
            // set initial id if not set or increment by 1
            Integer id = (entityList.getLastEntityId() == null) ? 1 : entityList.getLastEntityId() + 1;

            entityList.setLastEntityId(id);
            Entity ecopy = copy(entity);
            ecopy.setId(id);
            entityList.getEntities().add(ecopy);
            save();
            return ecopy;
        }
    }

    @Override
    public boolean deleteEntity(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            Iterator<Entity> it = entityList.getEntities().iterator();
            while (it.hasNext()) {
                Entity en = it.next();
                if (id.equals(en.getId())) {
                    it.remove();
                    save();
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void deleteAllEntities() {
        synchronized (Lock) {
            entityList.getEntities().clear();
        }
    }

    @Override
    public Entity retrieveEntity(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            for (Entity en : entityList.getEntities()) {
                if (id.equals(en.getId())) {
                    return copy(en);
                }
            }
        }
        return null;
    }

    @Override
    public List<Entity> retrieveAllEntities() {
        synchronized (Lock) {
            List<Entity> returnList = new ArrayList<Entity>();
            for (Entity entity : entityList.getEntities()) {
                returnList.add(copy(entity));
            };
            return returnList;
        }
    }

    /**
     * Returns a list of all Entities which match all of the set (i.e. not null)
     * fields of entityTemplate
     *
     * @param entityTemplate
     * @return
     */
    @Override
    public List<Entity> retrieveMatchingEntities(Entity entityTemplate) {
        if (entityTemplate == null) {
            throw new IllegalArgumentException("entityTemplate cannot be null");
        }
        List<Entity> returnList = new ArrayList<Entity>();
        synchronized (Lock) {
            for (Entity entity : entityList.getEntities()) {
                boolean match = true;
                if (entityTemplate.getId() != null) {
                    if (!entityTemplate.getId().equals(entity.getId())) {
                        match = false;
                    }
                };
                if (entityTemplate.getField_A() != null) {
                    if (!entityTemplate.getField_A().equals(entity.getField_A())) {
                        match = false;
                    }
                };
                if (entityTemplate.getField_B() != null) {
                    if (!entityTemplate.getField_B().equals(entity.getField_B())) {
                        match = false;
                    }
                };
                if (entityTemplate.getField_C() != null) {
                    if (!entityTemplate.getField_C().equals(entity.getField_C())) {
                        match = false;
                    }
                };
                if (match) {
                    returnList.add(copy(entity));
                }
            }
            return returnList;
        }
    }

    @Override
    public Entity updateEntity(Entity entityTemplate) {
        if (entityTemplate == null) {
            throw new IllegalArgumentException("entity cannot be null");
        }
        synchronized (Lock) {
            for (Entity en : entityList.getEntities()) {
                if (entityTemplate.getId().equals(en.getId())) {
                    boolean changedfield = false;

                    // update properties fields if only if entityTemplate field is set
                    if (entityTemplate.getField_A() != null) {
                        en.setField_A(entityTemplate.getField_A());
                        changedfield = true;
                    }
                    if (entityTemplate.getField_B() != null) {
                        en.setField_B(entityTemplate.getField_B());
                        changedfield = true;
                    }
                    if (entityTemplate.getField_C() != null) {
                        en.setField_C(entityTemplate.getField_C());
                        changedfield = true;
                    }
                    // save if anything changed
                    if (changedfield) {
                        save();
                    }
                    return copy(en);
                }

            }
        }
        return null; //entity not found
    }

    /**
     * copies new Entity data transfer objects to create detached object and so
     * avoid problems with indirect object modification
     *
     * @param entity
     * @return independent copy of Entity
     */
    private Entity copy(Entity entity) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(entity, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Entity newAccount = (Entity) jaxbUnMarshaller.unmarshal(sr1);
            return newAccount;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

    /**
     * loads jaxb file at beginning of program
     */
    private void load() {

        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance(CONTEXT_PATH);

            // try to load dataFileLocation
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());

            if (jaxbFile.exists()) {
                LOG.debug("dataFile exists loading:" + jaxbFile.getAbsolutePath());
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                entityList = (EntityList) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                LOG.debug("dataFile does not exist creating new " + jaxbFile.getAbsolutePath());

                entityList = new EntityList();

                // make directories if dont exist
                jaxbFile.getParentFile().mkdirs();

                // save empty data to new file
                save();
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }

    }

    /**
     * saves data to datafile on updates
     */
    private void save() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entityList, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(entityList, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem persisting dao", ex);
        }
    }

}
