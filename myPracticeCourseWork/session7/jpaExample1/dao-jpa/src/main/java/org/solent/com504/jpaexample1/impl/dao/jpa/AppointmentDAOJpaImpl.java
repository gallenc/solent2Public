/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.jpaexample1.model.dao.AppointmentDAO;
import org.solent.com504.jpaexample1.model.dto.Appointment;
import org.solent.com504.jpaexample1.model.dto.Person;

/**
 *
 * @author cgallen
 */
public class AppointmentDAOJpaImpl implements AppointmentDAO {

    final static Logger LOG = LogManager.getLogger(PersonDAOJpaImpl.class);

    private EntityManager entityManager;

    public AppointmentDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Appointment findById(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        return appointment;
    }

    @Override
    public Appointment save(Appointment appointment) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(appointment);  // NOTE merge(animal) differnt semantics
            // entityManager.flush() could be used
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("problem saving entity:", ex);
            entityManager.getTransaction().rollback();
        }
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a", Appointment.class);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;
    }

    @Override
    public Appointment delete(Appointment appointment) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Appointment a WHERE a.id=:id");
        q.setParameter("id", appointment.getId());
        q.executeUpdate();
        entityManager.getTransaction().commit();
        return appointment;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Appointment a WHERE a.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        Query q1 = entityManager.createQuery("DELETE FROM Appointment a");
        q1.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Appointment> findByPersonA(Person personA) {
        Query q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.personA = :personA");
        q.setParameter("personA", personA);
        List<Appointment> appointments =  q.getResultList();
        return appointments;
    }

    @Override
    public List<Appointment> findByPersonB(Person personB) {
        Query q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.personB = :personB");
        q.setParameter("personB", personB);
        List<Appointment> appointments =  q.getResultList();
        return appointments;
    }

    @Override
    public List<Appointment> findByDate(Integer year, Integer month, Integer hour, Integer minutes) {
        Query q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.yr = :year AND a.mth = :month AND a.hr = :hour AND a.durationMinutes = :minutes");
        q.setParameter("year", year);        
        q.setParameter("month", month);        
        q.setParameter("hour", hour);        
        q.setParameter("minutes", minutes);
        q.executeUpdate();
        List<Appointment> appointments =  q.getResultList();
        return appointments;
    }

}
