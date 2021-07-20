/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jpa.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.solent.com528.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com528.project.model.dao.DAOFactory;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dto.Station;

/**
 *
 * @author cgallen
 */
public class StationDAOJpaImplTest {

    final static Logger LOG = LogManager.getLogger(StationDAOJpaImplTest.class);

    private StationDAO stationDao = null;

    private DAOFactory daoFactory = new DAOFactoryJPAImpl();

    @Before
    public void before() {
        stationDao = daoFactory.getStationDAO();
        assertNotNull(stationDao);
    }

    public List<Station> createDummyStations() {
        // STATION LIST
        List<Station> stationList = new ArrayList();
        Station station = new Station();
        station.setName("Waterloo");
        station.setZone(1);
        stationList.add(station);
        Station station2 = new Station();
        station2.setName("Abbey Road");
        station2.setZone(2);
        stationList.add(station2);
        Station station3 = new Station();
        station3.setName("Acton Town");
        station3.setZone(3);
        stationList.add(station3);

        return stationList;
    }

    @Test
    public void createStationDAOJpaImplTest() {
        LOG.debug("start of createStationDAOJpaImplTest(");
        // this test simply runs the before method
        LOG.debug("end of createStationDAOJpaImplTest(");
    }

    @Test
    public void stationDAOJpaImplTest() {
        LOG.debug("start of stationDAOJpaImplTest");
        
        stationDao.deleteAll();
        List<Station> testStationList = stationDao.findAll();
        assertNotNull(testStationList);
        assertTrue(testStationList.isEmpty());

        // create 3 stations
        List<Station> dummyStationList = createDummyStations();

        stationDao.saveAll(dummyStationList);
        testStationList = stationDao.findAll();
        assertEquals(dummyStationList.size(),testStationList.size() );
        
        testStationList = stationDao.findByZone(2);
        assertEquals(1,testStationList.size());
        assertTrue("Abbey Road".equals(testStationList.get(0).getName()));
        
        testStationList = stationDao.findByZone(3);
        assertEquals(1,testStationList.size());
        assertTrue("Acton Town".equals(testStationList.get(0).getName()));
        
        Station station = stationDao.findByName("Abbey Road");
        assertNotNull(station);
        assertEquals(Integer.valueOf(2),station.getZone());
        
        station = stationDao.findByName("xxxx");
        assertNull(station);
        
        Set<Integer> zones = stationDao.getAllZones();
        assertEquals(3,zones.size());

        LOG.debug("end of stationDAOJpaImplTest");
    }

}
