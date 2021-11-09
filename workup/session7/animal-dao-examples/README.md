
# Project demonstrating various different DAO implementations

The model defines an interface which gives the basic Create Retrieve Update Delete (CRUD) methods implemented by a DAO.

[animalDao.java](../animal-dao-examples/model/src/main/java/org/solent/com504/factoryandfacade/model/dao/animalDao.java)

```
package org.solent.com504.factoryandfacade.model.dao;

import java.util.List;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

public interface AnimalDao {

    /**
     * Returns an animal which has a corresponding id 
     * @param id
     * @return Animal with animal.id = id OR returns null if animal not found.
     */
    public Animal retrieve(long id);

    /**
     * If animal has no id saves animal in the database with a new id 
     * If animal has an id, tries to update animal but if animal with id is not found returns null
     * and does nothing (animal must have no id field set if creating new animal)
     *
     * @param animal
     * @return animal which has been created in the database or updates the existing animal in the  database
     *
     */
    public Animal updateOrSave(Animal animal);

    /**
     * Deletes an item with an id matching animal.id
     *
     * @param id
     * @return true if item with id is found and deleted. False if not found
     */
    public boolean delete(long id);

    /**
     * Uses an Animal object as a template to retrieve a matching animal in the database.
     * Retrieves a list of animals which exactly match the populated fields in the animalTemplate. 
     * Null fields in animalTemplate are ignored.
     * Matching fields are 'ANDed' such that all populated fields in the returned animal must match the corresponding fields in the template.
     *
     * @param animalTemplate
     * @return List of animals or empty list if no matches
     */
    public List<Animal> retrieve(Animal animalTemplate);

    /**
     * Retrieves all animals which have been persisted in a list.
     *
     * @return Lost of all animals in DAO
     */
    public List<Animal> retrieveAll();

    /**
     * Creates a new animal of animal type. 
     * At this point the returned animal is not persisted and does not have an id.
     * It should be persisted using updateOrSave
     *
     * @param animalType type of animal to create
     * @return animal of animalType but with no id set
     */
    public Animal create(AnimalType animalType);
    
    /**
     * Deletes all animals 
     */
    public void deleteAll();

}


```

The project has 4 seperate DAO implementtions which can work with the model
```
dao-simple
dao-json
dao-jaxb
dao-jdbc
dao-jpa
dao-springdata-jpa
```

dao-simple doesn't persist any data but simply keeps the objects in memory.

dao-json persists the animals as json files using the Jackson library

dao-jaxb persists the animals as XML files using the jaxb library

dao-jdbc saves the animal objects in a database but all of he mapping of objects to tables in the database is manual

dao-jpa uses the java persistance architecture (JPA) standard to map objects to database tables. 

Eclipselink is the JPA provider and Derby is used as the embedded database.

dao-springdata-jpa introduces SpringData as a framework on top of JPA which greatly simplifies database interactions.


## running the application
As usual, build the whole project using 
```
mvn clean install
```

and then try running the tests in each of the sub projects















