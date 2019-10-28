/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jaxb;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalList;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

/**
 *
 * @author gallenc
 */
public class AnimalDaoJaxbImpl extends AnimalDaoImpl implements AnimalDao {
    // SETS UP LOGGING 
    final static Logger LOG = LogManager.getLogger(AnimalDaoJaxbImpl.class);

    private String filePath = null;
    private File file;

    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;
    private Unmarshaller jaxbUnMarshaller;

    public AnimalDaoJaxbImpl(String filePath) {

        super();

        file = new File(filePath);
        LOG.info("jaxb dao using file=" + file.getAbsolutePath());

        // create JAXB contexts and marshallers
        try {
            // this contains a list of Jaxb annotated classes for the context to parse
            // NOTE you must also have a jaxb.index or ObjectFactory in the same classpath
            jaxbContext = JAXBContext.newInstance("org.solent.com504.factoryandfacade.model.dto");

            jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbUnMarshaller = jaxbContext.createUnmarshaller();

        } catch (JAXBException e) {
            throw new RuntimeException("problem setting up jaxb marshalling", e);
        }

        this.filePath = filePath;
        load();
    }

    /**
     * loads xml into dao using jaxb
     */
    private void load() {

        // create a new file if file doesn't exist
        if (!file.exists()) {
            LOG.info("file does not exist. New file created=" + file.getAbsolutePath());
            save();
            return;
        }

        // load file
        try {
            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter

            // read in the file to animal list
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            this.animalList = (AnimalList) jaxbUnMarshaller.unmarshal(file);

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }

    }

    /**
     * saves dao as xml using jaxb
     */
    private void save() {
        // savefile
        try {
            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(this.animalList, file);

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }

    }

    @Override
    public synchronized Animal retrieve(long id) {
        return super.retrieve(id);
    }

    @Override
    public synchronized Animal updateOrSave(Animal animal) {
        Animal updatedAnimal = super.updateOrSave(animal);
        save(); // saves updated list
        return updatedAnimal;
    }

    @Override
    public synchronized boolean delete(long id) {
        boolean deleted = super.delete(id);
        save(); // saves updated list
        return deleted;
    }

    @Override
    public synchronized List<Animal> retrieve(Animal animalTemplate) {
        List<Animal> returnedAnimals = super.retrieve(animalTemplate);
        return returnedAnimals;
    }

    @Override
    public synchronized List<Animal> retrieveAll() {
        List<Animal> returnedAnimals = super.retrieveAll();
        return returnedAnimals;
    }

    @Override
    public synchronized void deleteAll() {
        super.deleteAll();
        save();
    }

    @Override
    public Animal create(AnimalType animalType) {
        return super.create(animalType);
    }

}
