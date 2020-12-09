/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jaxb.test;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.solent.com528.project.impl.dao.jaxb.PriceCalculatorDAOJaxbImpl;
import org.solent.com528.project.model.dto.Rate;
import org.solent.com528.project.model.dto.PriceBand;
import org.solent.com528.project.model.dto.PricingDetails;

/**
 *
 * @author cgallen
 */
public class PriceCalculatorDAOJaxbImplTest {

    PriceCalculatorDAOJaxbImpl priceCalculatorDAOJaxb;

    // runs before every test
    @Before
    public void init() {
        String fileName = "target/priceCalculatorDAOJaxbImplFile.xml";
        priceCalculatorDAOJaxb = new PriceCalculatorDAOJaxbImpl(fileName);
        System.out.println("deleting all pricing list items");
        priceCalculatorDAOJaxb.deleteAll();
    }

    @Test
    public void testInit() {
        // just runs init method
    }

    @Test
    public void addDeletePriceBandsTest() {
        PricingDetails pricingDetails;

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        priceCalculatorDAOJaxb.setOffpeakPricePerZone(2.50);
        priceCalculatorDAOJaxb.setPeakPricePerZone(5.00);

        System.out.println("no append:" + pricingDetails);

        assertEquals(1, pricingDetails.getPriceBandList().size());

        PriceBand priceBand1 = new PriceBand();
        priceBand1.setRate(Rate.OFFPEAK);
        priceBand1.setHour(0);
        priceBand1.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand1);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("append 1:" + pricingDetails);

        assertEquals(1, pricingDetails.getPriceBandList().size());

        PriceBand priceBand2 = new PriceBand();
        priceBand2.setRate(Rate.PEAK);
        priceBand2.setHour(7);
        priceBand2.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand2);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("append 2:" + pricingDetails);
        assertEquals(2, pricingDetails.getPriceBandList().size());

        PriceBand priceBand3 = new PriceBand();
        priceBand3.setRate(Rate.OFFPEAK);
        priceBand3.setHour(10);
        priceBand3.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand3);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("append 3:" + pricingDetails);
        assertEquals(3, pricingDetails.getPriceBandList().size());

        PriceBand priceBand4 = new PriceBand();
        priceBand4.setRate(Rate.PEAK);
        priceBand4.setHour(15);
        priceBand4.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand4);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("append 4:" + pricingDetails);
        assertEquals(4, pricingDetails.getPriceBandList().size());

        PriceBand priceBand5 = new PriceBand();
        priceBand5.setRate(Rate.OFFPEAK);
        priceBand5.setHour(19);
        priceBand5.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand5);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("append 5:" + pricingDetails);
        assertEquals(5, pricingDetails.getPriceBandList().size());

        PriceBand priceBandRemove = new PriceBand();
        priceBandRemove.setRate(Rate.OFFPEAK);
        priceBandRemove.setHour(15);
        priceBandRemove.setMinutes(0);
        System.out.println("removing priceband:" + priceBandRemove);
        priceCalculatorDAOJaxb.deletePriceBand(priceBandRemove);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("removed priceband:" + pricingDetails);
        assertEquals(4, pricingDetails.getPriceBandList().size());
    }

    public void setknownSchedule() {
        PricingDetails pricingDetails;

        System.out.println("deleting all pricing list items");
        priceCalculatorDAOJaxb.deleteAll();
        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        assertEquals(1, pricingDetails.getPriceBandList().size());

        priceCalculatorDAOJaxb.setOffpeakPricePerZone(2.50);
        priceCalculatorDAOJaxb.setPeakPricePerZone(5.00);

        PriceBand priceBand1 = new PriceBand();
        priceBand1.setRate(Rate.OFFPEAK);
        priceBand1.setHour(0);
        priceBand1.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand1);

        PriceBand priceBand2 = new PriceBand();
        priceBand2.setRate(Rate.PEAK);
        priceBand2.setHour(7);
        priceBand2.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand2);

        PriceBand priceBand3 = new PriceBand();
        priceBand3.setRate(Rate.OFFPEAK);
        priceBand3.setHour(10);
        priceBand3.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand3);

        PriceBand priceBand4 = new PriceBand();
        priceBand4.setRate(Rate.PEAK);
        priceBand4.setHour(15);
        priceBand4.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand4);

        PriceBand priceBand5 = new PriceBand();
        priceBand5.setRate(Rate.OFFPEAK);
        priceBand5.setHour(19);
        priceBand5.setMinutes(0);
        priceCalculatorDAOJaxb.addPriceBand(priceBand5);

        pricingDetails = priceCalculatorDAOJaxb.getPricingDetails();
        System.out.println("Initialised Pricing Details:" + pricingDetails);

    }

    @Test
    public void testGetPriceforTime() throws ParseException {
        setknownSchedule();

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
//
//        date1 = df.parse("2021-01-20 10:25");
//        rate1 = priceCalculatorDAOJaxb.getRate(date1);
//        assertEquals(Rate.OFFPEAK, rate1);
//        pricePerZone = priceCalculatorDAOJaxb.getPricePerZone(date1);
//        assertEquals(2.50, pricePerZone, 0.0001);
//
//        date1 = df.parse("2029-01-20 17:30");
//        rate1 = priceCalculatorDAOJaxb.getRate(date1);
//        assertEquals(Rate.PEAK, rate1);
//        pricePerZone = priceCalculatorDAOJaxb.getPricePerZone(date1);
//        assertEquals(5.00, pricePerZone, 0.0001);
//
//        date1 = df.parse("2029-01-20 19:30");
//        rate1 = priceCalculatorDAOJaxb.getRate(date1);
//        assertEquals(Rate.OFFPEAK, rate1);
//        pricePerZone = priceCalculatorDAOJaxb.getPricePerZone(date1);
//        assertEquals(2.50, pricePerZone, 0.0001);
    }
}
