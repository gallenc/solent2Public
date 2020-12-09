/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.service.rest.client.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com528.project.impl.service.rest.client.ClientObjectFactoryImpl;
import org.solent.com528.project.model.dao.PriceCalculatorDAO;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dto.PriceBand;
import org.solent.com528.project.model.dto.PricingDetails;
import org.solent.com528.project.model.dto.Rate;
import org.solent.com528.project.model.dto.Station;
import org.solent.com528.project.model.service.ServiceFacade;
import org.solent.com528.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class RestClientDAOServiceFacadeTest {

    final static Logger LOG = LogManager.getLogger(RestClientDAOServiceFacadeTest.class);

    ServiceObjectFactory serviceObjectFactory = null;
    ServiceFacade serviceFacade = null;

    @Before
    public void loadFactory() {
        serviceObjectFactory = new ClientObjectFactoryImpl();
        assertNotNull(serviceObjectFactory);
        serviceFacade = serviceObjectFactory.getServiceFacade();
        assertNotNull(serviceFacade);
    }

    @Test
    public void testStationDAO() {
        LOG.debug("start of testStationDAO()");

        StationDAO stationDAOjaxb = serviceFacade.getStationDAO();
        assertNotNull(stationDAOjaxb);

        // same tests as in model module
        stationDAOjaxb.deleteAll();
        List<Station> stationList = new ArrayList();
        Station station = new Station();
        station.setName("Waterloo");
        station.setZone(1);
        stationList.add(station);
        stationDAOjaxb.saveAll(stationList);

        Station foundStation = stationDAOjaxb.findByName("Waterloo");
        assertTrue(station.toString().equals(foundStation.toString()));

        LOG.debug("end of testStationDAO()");
    }

    @Test
    public void priceCalculatorSimpleTest() throws ParseException {
        LOG.debug("start of priceCalculatorSimpleTest()");

        PricingDetails pricingDetails;
        PriceCalculatorDAO priceCalculatorDAOJaxb = serviceFacade.getPriceCalculatorDAO();
        assertNotNull(priceCalculatorDAOJaxb);
        priceCalculatorDAOJaxb.deleteAll();

        // these tests same as in model
        priceCalculatorDAOJaxb.setOffpeakPricePerZone(2.50);
        priceCalculatorDAOJaxb.setPeakPricePerZone(5.00);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        // always has at least 00:00 off peak
        assertEquals(1, pricingDetails.getPriceBandList().size());

        PriceBand priceBand2 = new PriceBand();
        priceBand2.setRate(Rate.PEAK);
        priceBand2.setHour(7);
        priceBand2.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand2);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("append 2:" + pricingDetails);
        assertEquals(2, pricingDetails.getPriceBandList().size());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Rate rate1;
        Double pricePerZone;

        Date date1 = df.parse("2020-01-01 00:00");

        rate1 = priceCalculatorDAOJaxb.getRate(date1);
        assertEquals(Rate.OFFPEAK, rate1);
        pricePerZone = priceCalculatorDAOJaxb.getPricePerZone(date1);
        assertEquals(2.50, pricePerZone, 0.0001);

        date1 = df.parse("2020-03-01 08:25");

        rate1 = priceCalculatorDAOJaxb.getRate(date1);
        assertEquals(Rate.PEAK, rate1);
        pricePerZone = priceCalculatorDAOJaxb.getPricePerZone(date1);
        assertEquals(5.00, pricePerZone, 0.0001);

        LOG.debug("end of priceCalculatorSimpleTest()");
    }

}
