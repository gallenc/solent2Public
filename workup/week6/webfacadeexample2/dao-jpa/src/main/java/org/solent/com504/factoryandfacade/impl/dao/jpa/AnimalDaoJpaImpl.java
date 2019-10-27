/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

/**
 *
 * @author cgallen
 */
public class AnimalDaoJpaImpl implements AnimalDao {

    private EntityManager entityManager;

    public AnimalDaoJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Animal retrieve(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal updateOrSave(Animal animal) {
        entityManager.getTransaction().begin();
        entityManager.persist(animal);  // nore merge(animal) differnt semantics
        // entityManager.flush() could be used
        entityManager.getTransaction().commit();
        return animal;
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> retrieve(Animal animalTemplate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> retrieveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // no need to synchronize - same as simple dao
    @Override
    public Animal create(AnimalType animalType) {
        Animal animal = new Animal();
        animal.setAnimalType(animalType);
        return animal;
    }

}
