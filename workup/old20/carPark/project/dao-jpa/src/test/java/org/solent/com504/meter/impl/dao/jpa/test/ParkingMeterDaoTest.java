/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.meter.impl.dao.jpa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.meter.model.dao.ParkingMeterDao;
import org.solent.com504.meter.impl.dao.jpa.MeterDAOFactoryJpaImpl;
import org.solent.com504.meter.model.dao.MeterDAOFactory;
import org.solent.com504.meter.model.dto.Location;
import org.solent.com504.meter.model.dto.ParkingMeter;

/**
 *
 * @author cgallen
 */
public class ParkingMeterDaoTest {

    final static Logger LOG = LogManager.getLogger(ParkingMeterDaoTest.class);

    private ParkingMeterDao parkingMeterDao = null;

    private MeterDAOFactory daoFactory = new MeterDAOFactoryJpaImpl();

    @Before
    public void before() {
        parkingMeterDao = daoFactory.getParkingMeterDAO();
        assertNotNull(parkingMeterDao);
    }

    @Test
    public void createParkingMeterDaoTest() {
        LOG.debug("start of createParkingMeterDaoTest");
        // this test simply runs the before method
        LOG.debug("end of createParkingMeterDaoTest");
    }

    @Test
    public void createParkingMeterTest() {
        LOG.debug("createParkingMeterTest");
        
        // delete all previous meters and check isEmpty
        parkingMeterDao.deleteAll();
        assertTrue(parkingMeterDao.findAll().isEmpty());

        // add 5 meters with random serial numbers
        List<String> parkingMeterSerialNumbers = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            parkingMeterSerialNumbers.add(UUID.randomUUID().toString());
        }

        // create parking meters
        for (String parkingMeterSerialNumber : parkingMeterSerialNumbers) {
            ParkingMeter parkingMeter = new ParkingMeter();

            Location location = new Location();
            location.setAddress("10 Downing Street SW1");
            location.setCarParkName("Prime Mininser Park");
            //51.503497, -0.127577
            location.setLatitude(51.503497);
            location.setLongitude(-0.127577);
            parkingMeter.setLocation(location);
            parkingMeter.setSerialNumber(parkingMeterSerialNumber);

            parkingMeterDao.save(parkingMeter);
        }
        
        // check 5 meters have been added
        List<ParkingMeter> foundMeters = parkingMeterDao.findAll();
        assertEquals(5, foundMeters.size());

        LOG.debug("end of createParkingMeterTest");
    }
}
