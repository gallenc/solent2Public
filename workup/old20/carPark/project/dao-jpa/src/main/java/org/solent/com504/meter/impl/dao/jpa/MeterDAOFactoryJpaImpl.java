package org.solent.com504.meter.impl.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.solent.com504.meter.model.dao.MeterDAOFactory;
import org.solent.com504.meter.model.dao.ParkingMeterDao;
import org.solent.com504.meter.model.dao.WeeklyChargingSchemeDao;


public class MeterDAOFactoryJpaImpl implements MeterDAOFactory {
    
        // THIS HAS TO MATCH THE persistance.xml
    private static final String PERSISTENCE_UNIT_NAME = "modelPersistence";
    private static EntityManagerFactory factory;
    private static EntityManager em;
    private static ParkingMeterDao parkingMeterDao;
    private static WeeklyChargingSchemeDao weeklyChargingSchemeDao;

    static {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();

        // note it is important that all DAO's share same entity manafer
        parkingMeterDao = new ParkingMeterDaoJpaImpl(em);
        weeklyChargingSchemeDao = new WeeklyChargingSchemeDaoJpaImpl(em);
    }

    @Override
    public ParkingMeterDao getParkingMeterDAO() {
        return parkingMeterDao;
    }

    @Override
    public WeeklyChargingSchemeDao getWeeklyChargingSchemeDAO() {
        return weeklyChargingSchemeDao;
    }

    @Override
    public void shutDown() {
        if (em!=null) synchronized (this){
            if (em!=null){
                em.close();
            }
        }
    }


}
