package org.solent.com504.factoryandfacade.model.service;

public interface FarmFacade {

    public List<Animal> getAllAnimals();

    public Animal addAnimal(String animalType, String name);

    public List<Animals> getAnimalsOfType(String animalType);

    public Animal getAnimal(String name);

    public boolean removeAnimal(String name);

    public List<String> getSupportedAnimalTypes();
}
