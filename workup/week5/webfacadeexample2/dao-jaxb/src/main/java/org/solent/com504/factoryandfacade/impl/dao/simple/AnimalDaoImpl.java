package org.solent.com504.factoryandfacade.impl.dao.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalList;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

public class AnimalDaoImpl implements AnimalDao {

    protected AnimalList animalList = new AnimalList();

    @Override
    public synchronized Animal retrieve(long id) {
        for (Animal animal : animalList.getAnimals()) {
            if (Long.valueOf(id).equals(animal.getId())) {
                // breaks out of for loop and returns animal for id if found
                return animal;
            }
        }
        return null;
    }

    @Override
    public synchronized Animal updateOrSave(Animal animal) {
        Animal update = null;
        Long id = animal.getId();

        // check if trying to update existing animal
        if (id != null) {
            update = retrieve(animal.getId());

            // cannot update non existing id
            if (update == null) {
                throw new RuntimeException("cannot retrieve animal with unknown id=" + animal);
            }

        } else {
            // new animal so add to list
            update = animal;
            id = animalList.getCurrentMaxId();
            id++;
            animalList.setCurrentMaxId(id);
            update.setId(id);
            animalList.getAnimals().add(update);
        }

        // update only using fields which are not null in animal
        if (animal.getAddress() != null) {
            update.setAddress(animal.getAddress());
        }
        if (animal.getName() != null) {
            update.setName(animal.getName());
        }
        if (animal.getAnimalType() != null) {
            update.setAnimalType(animal.getAnimalType());
        }

        return update;
    }

    @Override
    public synchronized boolean delete(long id) {
        ListIterator<Animal> animalIterator = animalList.getAnimals().listIterator();
        while (animalIterator.hasNext()) {
            Animal a = animalIterator.next();
            if (id == a.getId()) {
                // value is removed
                animalIterator.remove();
                return true;
            }
        }
        // does not contain value
        return false;
    }

    @Override
    public synchronized List<Animal> retrieve(Animal animalTemplate) {

        List<Animal> returnedAnimals = new ArrayList<Animal>();

        for (Animal animal : animalList.getAnimals()) {
            boolean match = true;
            // search only using fields which are not null in animal
            if (animalTemplate.getAddress() != null && !animal.getAddress().equals(animalTemplate.getAddress())) {
                match = false;
            }
            if (animalTemplate.getName() != null && !animal.getName().equals(animalTemplate.getName())) {
                match = false;
            }
            // note we use an equals method created in object)
            if (animalTemplate.getAnimalType() != null && !animal.getAnimalType().equals(animalTemplate.getAnimalType())) {
                match = false;
            }
            if (match) {
                returnedAnimals.add(animal);
            }
        }

        return returnedAnimals;

    }

    @Override
    public synchronized List<Animal> retrieveAll() {
        return new ArrayList<Animal>(animalList.getAnimals());
    }

    // no need to synchronize
    @Override
    public Animal create(AnimalType animalType) {
        Animal animal = new Animal();
        animal.setAnimalType(animalType);
        return animal;
    }

}
