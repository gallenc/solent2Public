/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author cgallen
 */
public class AnimalDaoJpaImpl implements AnimalDao {

    final static Logger LOG = LogManager.getLogger(AnimalDaoJpaImpl.class);

    private EntityManager entityManager;

    public AnimalDaoJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Animal retrieve(long id) {
        Animal animal = entityManager.find(Animal.class, id);
        return animal;
    }

    @Override
    public Animal updateOrSave(Animal animal) {
        entityManager.getTransaction().begin();
        entityManager.persist(animal);  // NOTE merge(animal) differnt semantics
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

        
        Map<String, String> paramMap = new HashMap<String, String>();
   
        String queryString = "select a from Animal a WHERE TRUE=TRUE  "; // WHERE TRUE=TRUE masn WHERE always has a predicate ";
        if (animalTemplate.getName() != null) {
            queryString = queryString + "AND a.name LIKE :name "; //':name' ";
            paramMap.put("name", animalTemplate.getName());
        }
        if (animalTemplate.getAddress() != null) {
            queryString = queryString + "AND a.address LIKE :address ";
            paramMap.put("address", animalTemplate.getAddress());
        }
        if (animalTemplate.getAnimalType()!=null && animalTemplate.getAnimalType().getType() != null) {
            queryString = queryString + "AND a.animalType.type LIKE :type ";
            paramMap.put("type", animalTemplate.getAnimalType().getType());
        }
        
        // prevents running this section if not printing debug log
        if (LOG.isDebugEnabled()) {
            LOG.debug("queryString string built: " + queryString + "using parameters: ");
            for (String key : paramMap.keySet()) {
                LOG.debug("key: "+key + " value:"+paramMap.get(key));
            }
        }

        TypedQuery<Animal> query = entityManager.createQuery(queryString, Animal.class);
        for (String key : paramMap.keySet()) {
            query.setParameter(key, paramMap.get(key));
        }

        List<Animal> animalList = query.getResultList();
        return animalList;
    }

    @Override
    public List<Animal> retrieveAll() {
        TypedQuery<Animal> q = entityManager.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animalList = q.getResultList();
        return animalList;
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Animal ").executeUpdate();
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
