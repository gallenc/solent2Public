package org.solent.com504.factoryandfacade.impl.service;

import java.util.ArrayList;
import java.util.List;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;

public class FarmFacadeImpl implements FarmFacade {

    private AnimalDao animalDao = null;

    private AnimalTypeDao animalTypeDao = null;

    // setters for DAOs
    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    public void setAnimalTypeDao(AnimalTypeDao animalTypeDao) {
        this.animalTypeDao = animalTypeDao;
    }

    // Farm facade methods
    @Override
    public List<Animal> getAllAnimals() {
        return animalDao.retrieveAll();
    }

    @Override
    public Animal addAnimal(String animalTypeStr, String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty ");
        }
        if (animalTypeStr == null) {
            throw new IllegalArgumentException("animalTypeStr cannot be null");
        }
        // note this code prevents duplicate animal names
        if (getAnimal(name) != null) {
            throw new IllegalArgumentException("animal name is duplicated but must be unique. name=" + name);
        }

        AnimalType animalType = animalTypeDao.getAnimalType(animalTypeStr);
        Animal animal = animalDao.create(animalType);
        animal.setName(name);
        animalDao.updateOrSave(animal);
        return animal;
    }

    @Override
    public List<Animal> getAnimalsOfType(String animalTypeStr) {
        if (animalTypeStr == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        AnimalType animalType = animalTypeDao.getAnimalType(animalTypeStr);
        Animal animalTemplate = new Animal();
        animalTemplate.setAnimalType(animalType);
        List<Animal> animals = animalDao.retrieve(animalTemplate);
        return animals;
    }

    @Override
    public Animal getAnimal(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        Animal animalTemplate = new Animal();
        animalTemplate.setName(name);
        List<Animal> animals = animalDao.retrieve(animalTemplate);
        if (animals.isEmpty()) {
            return null;
        }
        return animals.get(0);
    }

    @Override
    public boolean removeAnimal(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        Animal animal = getAnimal(name);
        if (animal != null) {
            long id = animal.getId();
            return animalDao.delete(id);
        }
        return false;
    }

    @Override
    public List<String> getSupportedAnimalTypes() {
        List<String> typeStrings = new ArrayList<String>();
        List<AnimalType> animalTypes = animalTypeDao.getSupportedAnimalTypes();
        for (AnimalType atype : animalTypes) {
            String aTypeStr = atype.getType();
            typeStrings.add(aTypeStr);
        }
        return typeStrings;
    }
}
