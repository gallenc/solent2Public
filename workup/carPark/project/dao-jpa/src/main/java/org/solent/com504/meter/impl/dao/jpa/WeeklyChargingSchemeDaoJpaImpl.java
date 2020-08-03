package org.solent.com504.meter.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
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
        entityManager.persist(weeklyChargingScheme);  // NOTE merge(animal) differnt semantics
        // entityManager.flush() could be used
        entityManager.getTransaction().commit();
        return weeklyChargingScheme;
    }

    @Override
    public List<WeeklyChargingScheme> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WeeklyChargingScheme findBychargingSchemeName(String chargingSchemeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
