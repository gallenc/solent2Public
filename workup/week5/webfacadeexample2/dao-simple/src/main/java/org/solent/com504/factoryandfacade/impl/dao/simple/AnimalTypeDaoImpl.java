package org.solent.com504.factoryandfacade.impl.dao.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

public class AnimalTypeDaoImpl implements AnimalTypeDao {

    // default values in simple dao
    List<AnimalType> supportedAnimalTypes = new ArrayList(Arrays.asList(
            new AnimalType("Dog", "woof"),
            new AnimalType("Cat", "meow"),
            new AnimalType("Cow", "moo")
    ));

    @Override
    public AnimalType getAnimalType(String type) {
        for (AnimalType animalType : supportedAnimalTypes) {
            if (animalType.getType().equals(type)) {
                return animalType;
            }
        }
        return null;

    }

    // this is not sunchronized so only call at initialisation
    public void setSupportedAnimalTypes(List<AnimalType> supportedAnimalTypes) {
        this.supportedAnimalTypes = supportedAnimalTypes;
    }

    @Override
    public List<AnimalType> getSupportedAnimalTypes() {
        return supportedAnimalTypes;
    }

}
