/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.json;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalList;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author gallenc
 */
public class AnimalDaoJsonImpl extends AnimalDaoImpl implements AnimalDao {

    // SETS UP LOGGING 
    final static Logger LOG = LogManager.getLogger(AnimalDaoJsonImpl.class);

    private String filePath = null;
    private File file;

    private ObjectMapper objectMapper;

    public AnimalDaoJsonImpl(String filePath) {

        super();

        file = new File(filePath);
        LOG.info("json dao using file=" + file.getAbsolutePath());

        // create JSON ObjectMapper
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.findAndRegisterModules()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        this.filePath = filePath;
        load();
    }

    /**
     * loads json into dao using Jackson
     */
    private void load() {

        // create a new file if file doesn't exist
        if (!file.exists()) {
            LOG.info("file does not exist. New file created=" + file.getAbsolutePath());
            save();
            return;
        }

        try {
            // load file
            this.animalList = objectMapper.readValue(file, AnimalList.class);

        } catch (IOException ex) {
            throw new RuntimeException("problem testing loading json file", ex);
        }

    }

    /**
     * saves dao as json using Jackson
     */
    private void save() {
        try {
            // savefile
            // create json file from the object
            objectMapper.writeValue(file, this.animalList);

        } catch (IOException ex) {
            throw new RuntimeException("problem saving json file", ex);
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
