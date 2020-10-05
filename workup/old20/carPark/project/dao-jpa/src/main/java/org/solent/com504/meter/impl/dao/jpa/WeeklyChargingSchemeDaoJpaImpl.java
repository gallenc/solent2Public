package org.solent.com504.meter.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.meter.model.dao.WeeklyChargingSchemeDao;
import org.solent.com504.meter.model.dto.WeeklyChargingScheme;

public class WeeklyChargingSchemeDaoJpaImpl implements WeeklyChargingSchemeDao {

    final static Logger LOG = LogManager.getLogger(WeeklyChargingSchemeDaoJpaImpl.class);

    private EntityManager entityManager;

    public WeeklyChargingSchemeDaoJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public WeeklyChargingScheme findById(Long id) {
        WeeklyChargingScheme weeklyChargingScheme = entityManager.find(WeeklyChargingScheme.class, id);
        return weeklyChargingScheme;
    }

    @Override
    public WeeklyChargingScheme save(WeeklyChargingScheme weeklyChargingScheme) {
        entityManager.getTransaction().begin();
        entityManager.persist(weeklyChargingScheme);
        entityManager.getTransaction().commit();
        return weeklyChargingScheme;
    }

    @Override
    public List<WeeklyChargingScheme> findAll() {
        TypedQuery<WeeklyChargingScheme> q = entityManager.createQuery("SELECT w FROM WeeklyChargingScheme w", WeeklyChargingScheme.class);
        List<WeeklyChargingScheme> chargingSchemeList = q.getResultList();
        return chargingSchemeList;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM WeeklyChargingScheme w WHERE w.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM WeeklyChargingScheme ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public WeeklyChargingScheme findBychargingSchemeName(String chargingSchemeName) {
        TypedQuery<WeeklyChargingScheme> q = entityManager.createQuery(
                "SELECT w FROM WeeklyChargingScheme w WHERE w.chargingSchemeName=:chargingSchemeName",  WeeklyChargingScheme.class);
        q.setParameter("chargingSchemeName", chargingSchemeName);
        List<WeeklyChargingScheme> weeklyChargingSchemeList = q.getResultList();
        if(weeklyChargingSchemeList.isEmpty()) { 
            return null;
        } else{
            return  weeklyChargingSchemeList.get(0);
        }
    }

}
