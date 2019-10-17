package org.solent.com504.factoryandfacade.model.dao;

import java.util.List;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

public interface AnimalTypeDao {

    public List<AnimalType> getSupportedAnimalTypes();
    
    public AnimalType getAnimalType(String type);
}
