/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com600.example.journeyplanner.jpadao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solent.com600.example.journeyplanner.model.Rideout;
import org.solent.com600.example.journeyplanner.model.RideoutDAO;
import org.solent.com600.example.journeyplanner.model.RideoutState;
import org.solent.com600.example.journeyplanner.model.SysUser;

/**
 *
 * @author cgallen
 */
public class RideoutDAOJpaImpl implements RideoutDAO {

    private static final Logger LOG = LoggerFactory.getLogger(RideoutDAOJpaImpl.class);

    EntityManager entityManager = null;

    public RideoutDAOJpaImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @Override
    public Rideout createRideout(Rideout rideout) {
        entityManager.getTransaction().begin();
        entityManager.persist(rideout);
        // entityManager.flush() could be used
        entityManager.getTransaction().commit();
        return rideout;
    }

    @Override
    public Rideout update(Rideout rideout) {
        entityManager.getTransaction().begin();
        Rideout returnRideout = entityManager.merge(rideout);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return returnRideout;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Rideout r WHERE r.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public Rideout retrieve(Long id) {
        Rideout rideout = entityManager.find(Rideout.class, id);
        return rideout;
    }

    @Override
    public List<Rideout> retrieveAll() {
        TypedQuery<Rideout> q = entityManager.createQuery("SELECT r FROM Rideout r ORDER BY r.startDate,r.title ASC", Rideout.class);
        List<Rideout> rideoutList = q.getResultList();
        return rideoutList;
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Rideout").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Rideout> retrieveLikeMatching(String title) {
        // see https://stackoverflow.com/questions/21456494/spring-jpa-query-with-like

        String queryString = "SELECT r FROM Rideout r WHERE UPPER(r.title) LIKE CONCAT('%',UPPER(:title),'%') ORDER BY r.startDate,r.title  ASC";

        TypedQuery<Rideout> q = entityManager.createQuery(queryString, Rideout.class);
        q.setParameter("title", title);
        List<Rideout> rideoutList = q.getResultList();
        return rideoutList;
    }

    @Override
    public List<Rideout> retrieveLikeMatching(String title, List<RideoutState> rideoutStates) {

        String queryString = "SELECT r FROM Rideout r WHERE UPPER(r.title) LIKE CONCAT('%',UPPER(:title),'%') AND "
                + queryForRideoutState(rideoutStates)+" ORDER BY r.startDate,r.title  ASC";


        TypedQuery<Rideout> q = entityManager.createQuery(queryString, Rideout.class);
        q.setParameter("title", title);
        List<Rideout> rideoutList = q.getResultList();
        return rideoutList;
    }

    @Override
    public List<Rideout> retrieveAllByRideLeader(SysUser rideLeader, List<RideoutState> rideoutStates) {

        String queryString = "SELECT r FROM Rideout r WHERE r.rideLeader = :rideLeader AND "
                + queryForRideoutState(rideoutStates)+" ORDER BY r.startDate,r.title  ASC";

        TypedQuery<Rideout> q = entityManager.createQuery(queryString, Rideout.class);
        q.setParameter("rideLeader", rideLeader);
        List<Rideout> rideoutList = q.getResultList();
        return rideoutList;

    }

    @Override
    public List<Rideout> retrieveAllByRider(SysUser rider, List<RideoutState> rideoutStates) {
        String queryString = "SELECT r FROM Rideout r JOIN r.riders rdr WHERE rdr = :rider AND "
                + queryForRideoutState(rideoutStates)+" ORDER BY r.startDate,r.title  ASC";

        TypedQuery<Rideout> q = entityManager.createQuery(queryString, Rideout.class);
        q.setParameter("rider", rider);
        List<Rideout> rideoutList = q.getResultList();

        //Rideout r;
        // r.getRiders()
        //         r.getWaitlist()
        return rideoutList;

    }

    @Override
    public List<Rideout> retrieveAllWaitListByRider(SysUser rider, List<RideoutState> rideoutStates) {
        String queryString = "SELECT r FROM Rideout r JOIN r.waitlist rdr WHERE rdr = :rider AND "
                + queryForRideoutState(rideoutStates)+" ORDER BY r.startDate,r.title  ASC";

        TypedQuery<Rideout> q = entityManager.createQuery(queryString, Rideout.class);
        q.setParameter("rider", rider);
        List<Rideout> rideoutList = q.getResultList();

        return rideoutList;

    }

    @Override
    public List<Rideout> retrieveAll(List<RideoutState> rideoutStates) {
        String queryString = "SELECT r FROM Rideout r WHERE " + queryForRideoutState(rideoutStates)+" ORDER BY r.startDate,r.title  ASC";
        TypedQuery<Rideout> q = entityManager.createQuery(queryString, Rideout.class);
        List<Rideout> rideoutList = q.getResultList();
        return rideoutList;
    }

    public String queryForRideoutState(List<RideoutState> rideoutStates) {
        if (rideoutStates.isEmpty()) {
            return " (1=1)";
        }
        String query = " (";

        Iterator<RideoutState> it = rideoutStates.iterator();
        while (it.hasNext()) {
            RideoutState state = it.next();
            // this isnt great but it works
            query = query + "r.rideoutstate = " + state.getClass().getName() + "." + state.name();
            if (it.hasNext()) {
                query = query + " OR ";
            }
        }
        query = query + " )";
        return query;
    }

}
