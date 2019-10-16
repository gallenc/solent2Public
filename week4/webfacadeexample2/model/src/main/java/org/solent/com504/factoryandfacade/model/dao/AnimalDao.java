package org.solent.com504.factoryandfacade.model.dao;

import java.util.List;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

public interface AnimalDao {

    /**
     *
     * @param id
     * @return
     */
    public Animal retrieve(long id);

    /**
     * If animal has no id saves animal in the database with a new id If animal has an id tries to update animal but if animal with id is not found returns null
     * and does nothing (animal must have no id field set if creating new animal)
     *
     * @param animal
     * @return animal which has been creates in data base or updates in database
     *
     */
    public Animal updateOrSave(Animal animal);

    /**
     * Deletes an item with an id matching id
     *
     * @param id
     * @return true if item with id is found and deleted. False if not found
     */
    public boolean delete(long id);

    /**
     * retrieves a list of animals which exactly match the fields in the animal template Fields are 'anded' Null fields are ignored
     *
     * @param animalTemplate
     * @return List of animals or empty list
     */
    public List<Animal> retrieve(Animal animalTemplate);

    /**
     * retrieves all animals in list
     *
     * @return
     */
    public List<Animal> retrieveAll();

    /**
     * creates a new animal of animal type. Animal does not have an id and is not persisted when created.
     *
     * @param animalType type of animal to create
     * @return animal of animalType but with no id set
     */
    public Animal create(AnimalType animalType);

}
