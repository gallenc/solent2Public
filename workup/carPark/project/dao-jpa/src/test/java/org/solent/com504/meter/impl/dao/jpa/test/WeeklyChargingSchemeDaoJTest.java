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
import org.solent.com504.meter.impl.dao.jpa.MeterDAOFactoryJpaImpl;
import org.solent.com504.meter.model.dao.MeterDAOFactory;
import org.solent.com504.meter.model.dao.WeeklyChargingSchemeDao;

/**
 *
 * @author cgallen
 */
public class WeeklyChargingSchemeDaoJTest {

    final static Logger LOG = LogManager.getLogger(WeeklyChargingSchemeDaoJTest.class);

    private WeeklyChargingSchemeDao chargingSchemeDao = null;

    private MeterDAOFactory daoFactory = new MeterDAOFactoryJpaImpl();

    @Before
    public void before() {
        try {
            chargingSchemeDao = daoFactory.getWeeklyChargingSchemeDAO();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        assertNotNull(chargingSchemeDao);
    }

    @Test
    public void createWeeklyChargingSchemeDaoTest() {
        LOG.debug("start of createWeeklyChargingSchemeDaoTest");
        // this test simply runs the before method
        LOG.debug("end of createWeeklyChargingSchemeDaoTest");
    }

        @Test
    public void createWeeklyChargingSchemeTest() {
        LOG.debug("start of createWeeklyChargingSchemeTest");
        
        
        
        
        // this test simply runs the before method
        LOG.debug("end of createWeeklyChargingSchemeTest");
    }
}
