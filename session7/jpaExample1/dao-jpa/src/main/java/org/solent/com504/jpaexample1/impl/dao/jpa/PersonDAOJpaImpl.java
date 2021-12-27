/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.jpaexample1.model.dao.PersonDAO;
import org.solent.com504.jpaexample1.model.dto.Person;
import org.solent.com504.jpaexample1.model.dto.Role;

/**
 *
 * @author cgallen
 */
public class PersonDAOJpaImpl implements PersonDAO {

    final static Logger LOG = LogManager.getLogger(PersonDAOJpaImpl.class);

    private EntityManager entityManager;

    public PersonDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Person findById(Long id) {
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public Person save(Person person) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(person);  // NOTE merge(animal) differnt semantics
            // entityManager.flush() could be used
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("problem saving entity:", ex);
            entityManager.getTransaction().rollback();
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> q = entityManager.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> personList = q.getResultList();
        return personList;
    }

    @Override
    public void deleteById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person delete(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        try {
            entityManager.createQuery("DELETE FROM Person ").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("problem deleting all entities:", ex);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Person> findByRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findByName(String firstName, String secondName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
