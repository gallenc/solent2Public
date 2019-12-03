/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.spring;

import java.util.List;
import java.util.Optional;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.dao.springdata.AnimalDAOSpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class AnimalDaoImplSpring implements AnimalDao {

    final static Logger LOG = LogManager.getLogger(AnimalDaoImplSpring.class);

    @Autowired
    private AnimalDAOSpringData animalDAOSpringData = null;

    @Override
    public Animal retrieve(long id) {
        // note new Optional value used in java 8
        Optional<Animal> found = animalDAOSpringData.findById(id);
        return (found.isPresent()) ? found.get() : null;
    }

    @Override
    public Animal updateOrSave(Animal animal) {
        return animalDAOSpringData.save(animal);
    }

    @Override
    public boolean delete(long id) {
        animalDAOSpringData.deleteById(id);
        return true;
    }

    @Override
    public List<Animal> retrieve(Animal animalTemplate) {
        // using QueryByExampleExecutor<Animal>
        // see https://www.logicbig.com/tutorials/spring-framework/spring-data/query-by-example.html

        Example<Animal> employeeExample = Example.of(animalTemplate);
        //calling QueryByExampleExecutor#findAll(Example)
        List<Animal> animals = animalDAOSpringData.findAll(employeeExample);
        return animals;

    }

    @Override
    public List<Animal> retrieveAll() {
        return animalDAOSpringData.findAll();
    }

    /**
     * Note that this Create DOES NOT SAVE ANIMAL
     * @param animalType
     * @return 
     */
    @Override
    public Animal create(AnimalType animalType) {
        Animal animal = new Animal();
        animal.setAnimalType(animalType);
        return animal;
    }

    @Override
    public void deleteAll() {
        animalDAOSpringData.deleteAll();
    }

}
