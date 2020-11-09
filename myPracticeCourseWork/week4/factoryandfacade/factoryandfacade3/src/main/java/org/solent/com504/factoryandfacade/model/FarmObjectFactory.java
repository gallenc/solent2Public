package org.solent.com504.factoryandfacade.model;

import java.util.List;

public interface FarmObjectFactory {

    public Animal createAnimal(String animalType);

    public List<String> getSupportedAnimalTypes();

    public FarmFacade createFarmFacade();
}
