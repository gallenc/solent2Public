/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.solent.com528.project.model.dao.TicketMachineDAO;
import org.solent.com528.project.model.dto.Station;
import org.solent.com528.project.model.dto.TicketMachine;

/**
 *
 * @author cgallen
 */
public class TicketMachineDAOJpaImpl implements TicketMachineDAO {

    final static Logger LOG = LogManager.getLogger(TicketMachineDAOJpaImpl.class);

    private EntityManager entityManager;

    public TicketMachineDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public TicketMachine findById(Long id) {
        TicketMachine ticketMachine = entityManager.find(TicketMachine.class, id);
        return ticketMachine;
    }

    @Override
    public TicketMachine findByUuid(String uuid) {
        TypedQuery<TicketMachine> q = entityManager.createQuery("SELECT t FROM TicketMachine t WHERE t.uuid=:uuid", TicketMachine.class);
        q.setParameter("uuid", uuid);
        List<TicketMachine> ticketMachineList = q.getResultList();
        if (ticketMachineList.isEmpty()) {
            return null;
        } else {
            return ticketMachineList.get(0);
        }
    }

    @Override
    public TicketMachine save(TicketMachine ticketMachine) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(ticketMachine);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("problem saving entity:", ex);
            entityManager.getTransaction().rollback();
        }
        return ticketMachine;
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        try {
            entityManager.createQuery("DELETE FROM TicketMachine ").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("problem creating entity:", ex);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        try {
            Query q = entityManager.createQuery("DELETE FROM TicketMachine t WHERE t.id=:id");
            q.setParameter("id", id);
            q.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("problem deleting entity by id:", ex);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(TicketMachine ticketMachine) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(ticketMachine);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("problem deleting entity:", ex);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<TicketMachine> findByStationName(String stationName) {
        TypedQuery<TicketMachine> q
                = entityManager.createQuery("SELECT t FROM TicketMachine t JOIN t.station s WHERE s.name=:stationName ORDER BY t.uuid ASC", TicketMachine.class);
        q.setParameter("stationName", stationName);
        List<TicketMachine> ticketMachineList = q.getResultList();
        return ticketMachineList;
    }

    @Override
    public List<TicketMachine> findAll() {
        TypedQuery<TicketMachine> q = entityManager.createQuery("SELECT t FROM TicketMachine t ORDER BY t.uuid ASC", TicketMachine.class);
        List<TicketMachine> ticketMachineList = q.getResultList();
        return ticketMachineList;
    }

}
