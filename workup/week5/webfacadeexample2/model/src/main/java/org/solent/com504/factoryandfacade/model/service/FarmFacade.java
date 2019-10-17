package org.solent.com504.factoryandfacade.model.service;

import java.util.List;
import org.solent.com504.factoryandfacade.model.dto.Animal;

public interface FarmFacade {

    public List<Animal> getAllAnimals();

    public Animal addAnimal(String animalType, String name);

    public List<Animal> getAnimalsOfType(String animalType);

    public Animal getAnimal(String name);

    public boolean removeAnimal(String name);

    public List<String> getSupportedAnimalTypes();
}
