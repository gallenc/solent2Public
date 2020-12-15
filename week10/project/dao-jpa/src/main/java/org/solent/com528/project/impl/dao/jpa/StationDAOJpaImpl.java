/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jpa;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dto.Station;

/**
 *
 * @author cgallen
 */
public class StationDAOJpaImpl implements StationDAO {

    final static Logger LOG = LogManager.getLogger(StationDAOJpaImpl.class);

    private EntityManager entityManager;

    public StationDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Station findById(Long id) {
        Station station = entityManager.find(Station.class, id);
        return station;
    }

    // this assumes station names are unique
    @Override
    public Station findByName(String name) {
        TypedQuery<Station> q = entityManager.createQuery("SELECT s FROM Station s WHERE s.name=:name", Station.class);
        q.setParameter("name", name);
        List<Station> stationList = q.getResultList();
        if (stationList.isEmpty()) {
            return null;
        } else {
            return stationList.get(0);
        }
    }

    @Override
    public Station save(Station station) {
        entityManager.getTransaction().begin();
        entityManager.persist(station);
        entityManager.getTransaction().commit();
        return station;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Station s WHERE s.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Station ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Station station) {
        entityManager.getTransaction().begin();
        entityManager.remove(station);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Station> findAll() {
        TypedQuery<Station> q = entityManager.createQuery("SELECT s FROM Station s ORDER BY s.name ASC", Station.class);
        List<Station> stationList = q.getResultList();
        return stationList;
    }

    @Override
    public List<Station> findByZone(Integer zone) {
        TypedQuery<Station> q = entityManager.createQuery("SELECT s FROM Station s WHERE s.zone=:zone ORDER BY s.name ASC", Station.class);
        q.setParameter("zone", zone);
        List<Station> stationList = q.getResultList();
        return stationList;
    }

    @Override
    public void saveAll(List<Station> stationList) {
        entityManager.getTransaction().begin();
        for (Station station : stationList) {
            entityManager.persist(station);
        }
        entityManager.getTransaction().commit();
    }

    /**
     * returns a set of all zones in list of stations    
    */
    @Override
    public Set<Integer> getAllZones() {
        List<Station> stationList = this.findAll();
        Set<Integer> zones = new TreeSet();
        for (Station st : stationList) {
            zones.add(st.getZone());
        }
        return zones;
    }

}
