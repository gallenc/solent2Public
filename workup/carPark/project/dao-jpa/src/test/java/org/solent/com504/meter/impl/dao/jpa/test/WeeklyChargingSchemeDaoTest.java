/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.meter.impl.dao.jpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.meter.impl.dao.jpa.MeterDAOFactoryJpaImpl;
import org.solent.com504.meter.model.dao.MeterDAOFactory;
import org.solent.com504.meter.model.dao.WeeklyChargingSchemeDao;
import org.solent.com504.meter.model.dto.ChargeBand;
import org.solent.com504.meter.model.dto.DailyChargingScheme;
import org.solent.com504.meter.model.dto.DayOfWeek;
import org.solent.com504.meter.model.dto.WeeklyChargingScheme;

/**
 *
 * @author cgallen
 */
public class WeeklyChargingSchemeDaoTest {

    final static Logger LOG = LogManager.getLogger(WeeklyChargingSchemeDaoTest.class);

    private WeeklyChargingSchemeDao weeklyChargingSchemeDao = null;

    private MeterDAOFactory daoFactory = new MeterDAOFactoryJpaImpl();

    @Before
    public void before() {
        try {
            weeklyChargingSchemeDao = daoFactory.getWeeklyChargingSchemeDAO();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        assertNotNull(weeklyChargingSchemeDao);
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
        
        // start by removing all data
        weeklyChargingSchemeDao.deleteAll();
         List<WeeklyChargingScheme> emptyList = weeklyChargingSchemeDao.findAll();
        assertTrue(emptyList.isEmpty());
        
        // create multiple charging schemes for different areas
        List<String> chargingSchemeNames = Arrays.asList("central", "east", "west", "north", "south", "extended stay");

        for (String chargingSchemeName : chargingSchemeNames) {

            WeeklyChargingScheme weeklyChargingScheme = new WeeklyChargingScheme();

            weeklyChargingScheme.setChargingSchemeName(chargingSchemeName);
            List<DailyChargingScheme> dailyChargingSchemeList = new ArrayList();
            weeklyChargingScheme.setDailyChargingSchemeList(dailyChargingSchemeList);

            // create charging scheme for all days
            for (DayOfWeek day : DayOfWeek.values()) {
                // set up for monday
                DailyChargingScheme dailyChargingScheme1 = new DailyChargingScheme();
                dailyChargingScheme1.setDay(day);
                List<ChargeBand> chargeBandList1 = new ArrayList<ChargeBand>();
                ChargeBand band1_1 = new ChargeBand();
                band1_1.setHrs(12);
                band1_1.setMins(59);
                band1_1.setPricePerHr(1.15);
                chargeBandList1.add(band1_1);
                ChargeBand band1_2 = new ChargeBand();
                band1_2.setHrs(15);
                band1_2.setMins(59);
                band1_2.setPricePerHr(2.0);
                chargeBandList1.add(band1_2);
                ChargeBand band1_3 = new ChargeBand();
                band1_3.setHrs(23);
                band1_3.setMins(59);
                band1_3.setPricePerHr(3.0);
                chargeBandList1.add(band1_3);
                dailyChargingScheme1.setChargeBandList(chargeBandList1);
                dailyChargingSchemeList.add(dailyChargingScheme1);
            }

            weeklyChargingScheme = weeklyChargingSchemeDao.save(weeklyChargingScheme);
            WeeklyChargingScheme newWeeklyChargingScheme = weeklyChargingSchemeDao.findById(weeklyChargingScheme.getId());
            assertNotNull(newWeeklyChargingScheme);
            // simply compares contents of 2 objects
            assertTrue(weeklyChargingScheme.toString().equals(newWeeklyChargingScheme.toString()));
        }

        // test find all
        List<WeeklyChargingScheme> foundWeeklyChargingSchemeList = weeklyChargingSchemeDao.findAll();
        assertEquals(chargingSchemeNames.size(), foundWeeklyChargingSchemeList.size());

        // test find by name
        for (String chargingSchemeName : chargingSchemeNames) {
            WeeklyChargingScheme foundWeeklyChargingScheme = weeklyChargingSchemeDao.findBychargingSchemeName(chargingSchemeName);
            assertNotNull(foundWeeklyChargingScheme);
            assertTrue(chargingSchemeName.equals(foundWeeklyChargingScheme.getChargingSchemeName()));
        }

        LOG.debug("end of createWeeklyChargingSchemeTest");
    }
}
