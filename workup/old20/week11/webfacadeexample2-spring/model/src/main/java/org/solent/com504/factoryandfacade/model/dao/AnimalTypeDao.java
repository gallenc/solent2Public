package org.solent.com504.factoryandfacade.model.dao;

import java.util.List;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

public interface AnimalTypeDao {

    /**
     * @return list of AnimalTypes which this implementation can support
     */
    public List<AnimalType> getSupportedAnimalTypes();
    
    /**
     *  an animalType for a given type string where animalType.type == type
     * @param type type name for type t be returned
     * @return 
     */
    public AnimalType getAnimalType(String type);
}
