/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jpa.test;

import java.net.URL;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com528.project.impl.dao.jaxb.StationDAOJaxbImpl;
import org.solent.com528.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com528.project.model.dao.DAOFactory;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dao.TicketMachineDAO;
import org.solent.com528.project.model.dto.Station;
import org.solent.com528.project.model.dto.TicketMachine;

/**
 *
 * @author cgallen
 */
public class TicketMachineDAOJpaImplTest {

    final static Logger LOG = LogManager.getLogger(TicketMachineDAOJpaImplTest.class);

    private TicketMachineDAO ticketMachineDao = null;
    private StationDAO stationDao;

    private DAOFactory daoFactory = new DAOFactoryJPAImpl();

    @Before
    public void before() {
        ticketMachineDao = daoFactory.getTicketMachineDAO();
        stationDao = daoFactory.getStationDAO();
        assertNotNull(ticketMachineDao);
        assertNotNull(stationDao);
    }

    @Test
    public void createTicketMachinesDAOJpaImplTest() {
        LOG.debug("start of createTicketMachinesDAOJpaImplTest");
        // this test simply runs the before method
        LOG.debug("end of createTicketMachinesDAOJpaImplTest");
    }

    @Test
    public void createTicketMachinesTest() {
        LOG.debug("start of createTicketMachinesTest");

        ticketMachineDao.deleteAll();
        List<TicketMachine> testTicketMachineList = ticketMachineDao.findAll();
        assertTrue(testTicketMachineList.isEmpty());

        List<TicketMachine> dummyTicketMachineList = new ArrayList<TicketMachine>();

        for (Integer i = 0; i < 10; i++) {
            TicketMachine t = new TicketMachine();
            t = ticketMachineDao.save(t);
            assertNotNull(t.getId());
            dummyTicketMachineList.add(t);
        }
        testTicketMachineList = ticketMachineDao.findAll();
        assertEquals(dummyTicketMachineList.size(), testTicketMachineList.size());

        // check ticket machines match
        for (TicketMachine dummyTicketMachine : dummyTicketMachineList) {
            String uuid = dummyTicketMachine.getUuid();
            TicketMachine foundTicketMachine = ticketMachineDao.findByUuid(uuid);
            assertNotNull(foundTicketMachine);
            assertEquals(dummyTicketMachine.getId(), foundTicketMachine.getId());
            assertEquals(dummyTicketMachine.getUuid(), foundTicketMachine.getUuid());
        }

        // check nothing returned if no stations applied
        testTicketMachineList = ticketMachineDao.findByStationName("Waterloo");
        assertTrue(testTicketMachineList.isEmpty());

        LOG.debug("end of createTicketMachinesTest(");
    }

    @Test
    public void createStationsWithTicketMachinesTest() {
        LOG.debug("start of createStationsWithTicketMachinesTest");

        ticketMachineDao.deleteAll();
        List<TicketMachine> testTicketMachineList = ticketMachineDao.findAll();
        assertTrue(testTicketMachineList.isEmpty());

        // this loads a list of stations to use in tests
        // loads from model/src/test/resources on class path
        // NOTE this should but does not load from a file saved in the model jar
        URL res = getClass().getClassLoader().getResource("londonStations.xml");
        String fileName = res.getPath();
        System.out.println("loading from london underground fileName:   " + fileName);
        StationDAOJaxbImpl stationDAOjaxb = new StationDAOJaxbImpl(fileName);
        List<Station> dummyStationList = stationDAOjaxb.findAll();
        
        // create one ticket machine per station
        for(Station dummyStation: dummyStationList){
            dummyStation = stationDao.save(dummyStation);
            TicketMachine exampleTicketMachine = new TicketMachine();
            exampleTicketMachine.setStation(dummyStation);
            ticketMachineDao.save(exampleTicketMachine);
        }
        
        testTicketMachineList = ticketMachineDao.findAll();
        assertEquals(dummyStationList.size(),testTicketMachineList.size());
        
        testTicketMachineList = ticketMachineDao.findByStationName("Acton Town");
        assertEquals(1,testTicketMachineList.size());
        assertTrue("Acton Town".equals(testTicketMachineList.get(0).getStation().getName()));
        assertEquals(Integer.valueOf(3),testTicketMachineList.get(0).getStation().getZone());
        

        LOG.debug("end of createStationsWithTicketMachinesTest");
    }

}
