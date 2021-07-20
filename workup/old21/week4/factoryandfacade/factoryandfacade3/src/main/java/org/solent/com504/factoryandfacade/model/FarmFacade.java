package org.solent.com504.factoryandfacade.model;

import java.util.List;

public interface FarmFacade {

    public List<Animal> getAllAnimals();

    public boolean addAnimal(String animalType, String name);

    public List<Animal> getAnimalsOfType(String animalType);
    
    public Animal getAnimal(String name);
    
    public void removeAnimal(String name);
}
