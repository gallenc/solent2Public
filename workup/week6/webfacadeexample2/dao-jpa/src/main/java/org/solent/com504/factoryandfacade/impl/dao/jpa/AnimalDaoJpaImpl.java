/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        Animal usr = entityManager.find(Animal.class, id);
        return usr;
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
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Animal a WHERE a.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();

        return true;
    }

    @Override
    public List<Animal> retrieve(Animal animalTemplate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose To
    }

    @Override
    public List<Animal> retrieveAll() {
        TypedQuery<Animal> q = entityManager.createQuery("SELECT a FROM Animal ", Animal.class);
        List<Animal> animalList = q.getResultList();
        return animalList;
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Animals").executeUpdate();
        entityManager.getTransaction().commit();
    }

    // no need to synchronize - same as simple dao
    @Override
    public Animal create(AnimalType animalType) {
        Animal animal = new Animal();
        animal.setAnimalType(animalType);
        return animal;
    }

}
