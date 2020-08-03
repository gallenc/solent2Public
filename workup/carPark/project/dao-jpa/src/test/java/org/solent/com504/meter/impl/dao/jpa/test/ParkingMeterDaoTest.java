/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.meter.impl.dao.jpa.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.meter.model.dao.ParkingMeterDao;
import org.solent.com504.meter.impl.dao.jpa.MeterDAOFactoryJpaImpl;
import org.solent.com504.meter.model.dao.MeterDAOFactory;

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
        LOG.debug("start of createParkingMeterDaoTest(");
        // this test simply runs the before method
        LOG.debug("end of createParkingMeterDaoTest(");
    }

    @Test
    public void createParkingMeterTest() {
        LOG.debug("createParkingMeterTest(");

        LOG.debug("end of createParkingMeterDaoTest(");
    }
}
