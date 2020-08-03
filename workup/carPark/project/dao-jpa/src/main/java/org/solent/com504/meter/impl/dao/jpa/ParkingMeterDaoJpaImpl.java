package org.solent.com504.meter.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.meter.model.dao.ParkingMeterDao;
import org.solent.com504.meter.model.dto.ParkingMeter;

public class ParkingMeterDaoJpaImpl implements ParkingMeterDao {

    final static Logger LOG = LogManager.getLogger(ParkingMeterDaoJpaImpl.class);

    private EntityManager entityManager;

    public ParkingMeterDaoJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public ParkingMeter findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ParkingMeter save(ParkingMeter parkingMeter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ParkingMeter> findAll() {
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
    public ParkingMeter findBySerialNumber(String serialNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
