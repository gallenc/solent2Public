/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jaxb.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.solent.com528.project.impl.dao.jaxb.StationDAOJaxbImpl;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dto.Station;

/**
 *
 * @author cgallen
 */
public class StationDAOJaxbLondonTest {

    StationDAO stationDAOjaxb;

    // runs before every test
    @Before
    public void init() throws URISyntaxException {

        // loads from model/src/main/resources on class path
        URL res = getClass().getClassLoader().getResource("londonStations.xml");
        String fileName = res.getPath();
        System.out.println("loading from london underground fileName:   " + fileName);
        stationDAOjaxb = new StationDAOJaxbImpl(fileName);
    }

    @Test
    public void testInit() {
        // just runs init method
    }

    @Test
    public void testFindLondonStationsByZone() {
        List<Station> stationList = stationDAOjaxb.findAll();
        System.out.println("all stations:" + stationList.size());
        assertEquals(304, stationList.size());

        stationList = stationDAOjaxb.findByZone(1);
        System.out.println("zone1:" + stationList.size());
        assertEquals(62, stationList.size());

        stationList = stationDAOjaxb.findByZone(2);
        System.out.println("zone2:" + stationList.size());
        assertEquals(90, stationList.size());

        stationList = stationDAOjaxb.findByZone(3);
        System.out.println("zone3:" + stationList.size());
        assertEquals(57, stationList.size());

        stationList = stationDAOjaxb.findByZone(4);
        System.out.println("zone4:" + stationList.size());
        assertEquals(45, stationList.size());

        stationList = stationDAOjaxb.findByZone(5);
        System.out.println("zone5:" + stationList.size());
        assertEquals(23, stationList.size());

        stationList = stationDAOjaxb.findByZone(6);
        System.out.println("zone6:" + stationList.size());
        assertEquals(20, stationList.size());

        stationList = stationDAOjaxb.findByZone(7);
        System.out.println("zone7:" + stationList.size());
        assertEquals(4, stationList.size());

        stationList = stationDAOjaxb.findByZone(8);
        System.out.println("zone8:" + stationList.size());
        assertEquals(1, stationList.size());

        stationList = stationDAOjaxb.findByZone(9);
        System.out.println("zone9:" + stationList.size());
        assertEquals(2, stationList.size());

        Set<Integer> zones = stationDAOjaxb.getAllZones();
        assertEquals(9, zones.size());
    }

    @Test
    public void testFindZoneByLondonStations() {
        Station station = stationDAOjaxb.findByName("Waterloo");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(1));

        station = stationDAOjaxb.findByName("Abbey Road");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(2));

        station = stationDAOjaxb.findByName("Acton Town");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(3));

        station = stationDAOjaxb.findByName("Alperton");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(4));

        station = stationDAOjaxb.findByName("Becontree");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(5));

        station = stationDAOjaxb.findByName("Elm Park");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(6));

        station = stationDAOjaxb.findByName("Watford");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(7));

        station = stationDAOjaxb.findByName("Chalfont and Latimer");
        System.out.println("Station Found :" + station);
        assertTrue(station.getZone().equals(8));
    }
}
