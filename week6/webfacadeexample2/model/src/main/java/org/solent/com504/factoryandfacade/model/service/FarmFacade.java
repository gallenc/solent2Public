package org.solent.com504.factoryandfacade.model.service;

import java.util.List;
import org.solent.com504.factoryandfacade.model.dto.Animal;

public interface FarmFacade {

    /**
     * Get all animals on the farm
     * @return list of all Animals
     */
    public List<Animal> getAllAnimals();

    /**
     * Creates an Animal of a given AnimalType with a name and adds the animal to the list of animals stored by this farm.
     * Note that more than one animal can be added with the same name
     * @param animalType type of animal ( which must have been created by the AnimalTypeDao)
     * @param name name to give this
     * animal - does not have to be unique
     * @return an animal of a given type with the supplied name which has been stored. 
     * Animal will also have been given an id.
     * @ throws IllegalArgumentException if animal name is duplicated. All animal names must be unique.
     */
    public Animal addAnimal(String animalType, String name);

    /**
     * 
     * @param animalType type name for the animal type
     * @return returns a list of all animals of a given type
     */
    public List<Animal> getAnimalsOfType(String animalType);

    /**
     * returns the first animal in the list which has a given name
     * @param name
     * @return 
     */
    public Animal getAnimal(String name);

    /**
     * remove the first animal in the list which has a given name
     * @param name
     * @return 
     */
    public boolean removeAnimal(String name);

    /**
     * returns a list of strings describing the supported animal types
     * @return 
     */
    public List<String> getSupportedAnimalTypes();
}
