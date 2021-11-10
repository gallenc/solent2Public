package org.solent.com504.factoryandfacade.impl;

import java.util.Arrays;
import java.util.List;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.Cat;
import org.solent.com504.factoryandfacade.model.Cow;
import org.solent.com504.factoryandfacade.model.Dog;
import org.solent.com504.factoryandfacade.model.Duck;
import org.solent.com504.factoryandfacade.model.FarmFacade;
import org.solent.com504.factoryandfacade.model.FarmObjectFactory;

public class FarmObjectFactoryImpl implements FarmObjectFactory {

    private static final List supportedAnimalTypes = Arrays.asList(Cat.ANIMAL_TYPE, Dog.ANIMAL_TYPE, Cow.ANIMAL_TYPE, Duck.ANIMAL_TYPE);

    @Override
    public Animal createAnimal(String animalType) {
        
        Animal animal = null;
        // could use if / else etc
        switch (animalType) {
            case Cat.ANIMAL_TYPE:
                animal = new CatImpl();
                break;
            case Dog.ANIMAL_TYPE:
                animal = new DogImpl();
                break;
            case Cow.ANIMAL_TYPE:
                animal = new CowImpl();
                break;
            case Duck.ANIMAL_TYPE:
                animal = new DuckImpl();
                break;
            default:
                throw new IllegalArgumentException("unknown animalType=" + animalType);
        }
        return animal;

    }

    @Override
    public List<String> getSupportedAnimalTypes() {
        return supportedAnimalTypes;
    }

    @Override
    public FarmFacade createFarmFacade() {
        FarmFacadeImpl farmFacade= new FarmFacadeImpl();
        farmFacade.setFarmObjectFactory(this);
        return farmFacade;
    }
}
