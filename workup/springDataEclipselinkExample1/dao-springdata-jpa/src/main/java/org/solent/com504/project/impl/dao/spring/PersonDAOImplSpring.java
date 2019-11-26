/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.spring;

import java.util.List;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.dao.springdata.PersonDAOSpringData;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author gallenc
 */
@Component
public class PersonDAOImplSpring implements PersonDAO {
    
    @Autowired
    private PersonDAOSpringData personDAOspring = null;

    @Override
    public Person findById(Long id) {
        return personDAOspring.getOne(id);
    }

    @Override
    public Person save(Person person) {
        return personDAOspring.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personDAOspring.findAll();
    }

    @Override
    public void deleteById(long id) {
        personDAOspring.deleteById(id);
    }

    @Override
    public Person delete(Person person) {
        personDAOspring.delete(person);
        return null; // TODO SHOULD CHANGE INTERFACE TO RETURN NULL
    }

    @Override
    public void deleteAll() {
        personDAOspring.deleteAll();
    }

    @Override
    public List<Person> findByRole(Role role) {
        return personDAOspring.findByRole(role);
    }

    @Override
    public List<Person> findByName(String firstName, String secondName) {
        return personDAOspring.findByName(firstName, secondName);
    }
    
}
