/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.dao.jaxbimpl;

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
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ThingDAO;
import solent.ac.uk.ood.examples.model.ThingList;

/**
 *
 * @author cgallen
 */
public class ThingDAOJaxbImpl implements ThingDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ThingDAOJaxbImpl.class);

    // jaxb context needs jaxb.index jaxbFile to be in same classpath
    // this path contains a list of Jaxb annotated classes for the context to parse
    private static final String CONTEXT_PATH = "solent.ac.uk.ood.examples.model";

    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();

    private String dataFileLocation = null;

    private File jaxbFile = null;

    private ThingList thingList = null;

    private JAXBContext jaxbContext = null;

    public ThingDAOJaxbImpl(String dataFileLocation) {
        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }
        this.dataFileLocation = dataFileLocation;
        load();
    }

    @Override
    public Thing createThing(Thing thing) {
        if (thing == null) {
            throw new IllegalArgumentException("thing cannot be null");
        }
        synchronized (Lock) {
            // set initial id if not set or increment by 1
            Integer id = (thingList.getLastThingId() == null) ? 1 : thingList.getLastThingId() + 1;

            thingList.setLastThingId(id);
            Thing ecopy = copy(thing);
            ecopy.setId(id);
            thingList.getThings().add(ecopy);
            save();
            return ecopy;
        }
    }

    @Override
    public boolean deleteThing(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            Iterator<Thing> it = thingList.getThings().iterator();
            while (it.hasNext()) {
                Thing en = it.next();
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
    public void deleteAllThings() {
        synchronized (Lock) {
            thingList.getThings().clear();
        }
    }

    @Override
    public Thing retrieveThing(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            for (Thing en : thingList.getThings()) {
                if (id.equals(en.getId())) {
                    return copy(en);
                }
            }
        }
        return null;
    }

    @Override
    public List<Thing> retrieveAllThings() {
        synchronized (Lock) {
            List<Thing> returnList = new ArrayList<Thing>();
            for (Thing thing : thingList.getThings()) {
                returnList.add(copy(thing));
            };
            return returnList;
        }
    }

    /**
     * Returns a list of all Things which match all of the set (i.e. not null)
     * fields of thingTemplate
     *
     * @param thingTemplate
     * @return
     */
    @Override
    public List<Thing> retrieveMatchingThings(Thing thingTemplate) {
        if (thingTemplate == null) {
            throw new IllegalArgumentException("thingTemplate cannot be null");
        }
        List<Thing> returnList = new ArrayList<Thing>();
        synchronized (Lock) {
            for (Thing thing : thingList.getThings()) {
                boolean match = true;
                if (thingTemplate.getId() != null) {
                    if (!thingTemplate.getId().equals(thing.getId())) {
                        match = false;
                    }
                };
                if (thingTemplate.getField_A() != null) {
                    if (!thingTemplate.getField_A().equals(thing.getField_A())) {
                        match = false;
                    }
                };
                if (thingTemplate.getField_B() != null) {
                    if (!thingTemplate.getField_B().equals(thing.getField_B())) {
                        match = false;
                    }
                };
                if (thingTemplate.getField_C() != null) {
                    if (!thingTemplate.getField_C().equals(thing.getField_C())) {
                        match = false;
                    }
                };
                if (match) {
                    returnList.add(copy(thing));
                }
            }
            return returnList;
        }
    }

    @Override
    public Thing updateThing(Thing thingTemplate) {
        if (thingTemplate == null) {
            throw new IllegalArgumentException("thing cannot be null");
        }
        synchronized (Lock) {
            for (Thing en : thingList.getThings()) {
                if (thingTemplate.getId().equals(en.getId())) {
                    boolean changedfield = false;

                    // update properties fields if only if thingTemplate field is set
                    if (thingTemplate.getField_A() != null) {
                        en.setField_A(thingTemplate.getField_A());
                        changedfield = true;
                    }
                    if (thingTemplate.getField_B() != null) {
                        en.setField_B(thingTemplate.getField_B());
                        changedfield = true;
                    }
                    if (thingTemplate.getField_C() != null) {
                        en.setField_C(thingTemplate.getField_C());
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
        return null; //thing not found
    }

    /**
     * copies new Thing data transfer objects to create detached object and so
     * avoid problems with indirect object modification
     *
     * @param thing
     * @return independent copy of Thing
     */
    private Thing copy(Thing thing) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(thing, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Thing newAccount = (Thing) jaxbUnMarshaller.unmarshal(sr1);
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
                thingList = (ThingList) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                LOG.debug("dataFile does not exist creating new " + jaxbFile.getAbsolutePath());

                thingList = new ThingList();

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
            jaxbMarshaller.marshal(thingList, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(thingList, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem persisting dao", ex);
        }
    }

}
