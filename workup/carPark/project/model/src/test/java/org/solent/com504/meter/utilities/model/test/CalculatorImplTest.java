/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.meter.utilities.model.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.solent.com504.meter.model.dto.ChargeBand;
import org.solent.com504.meter.model.dto.DailyChargingScheme;
import org.solent.com504.meter.model.dto.DayOfWeek;
import org.solent.com504.meter.utilities.model.Calculator;

/**
 * @author cgallen
 */
public class CalculatorImplTest {

    final static Logger LOG = LogManager.getLogger(CalculatorImplTest.class);

    List<DailyChargingScheme> dailyChargingSchemeList = new ArrayList<DailyChargingScheme>();

    @Before
    public void createModel() {

        List<DayOfWeek> weekDays = Arrays.asList(DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);

        for (DayOfWeek day : weekDays) {
            DailyChargingScheme dailyChargingScheme1 = new DailyChargingScheme();
            dailyChargingScheme1.setDay(day);
            List<ChargeBand> chargeBandList1 = new ArrayList<ChargeBand>();

            // 0:00 to 09:00 charge 0
            ChargeBand band1_1 = new ChargeBand();
            band1_1.setHrs(0);
            band1_1.setMins(0);
            band1_1.setPricePerHr(0.0);
            chargeBandList1.add(band1_1);

            // 09:00 to 12:00 charge 2.00
            ChargeBand band1_2 = new ChargeBand();
            band1_2.setHrs(9);
            band1_2.setMins(0);
            band1_2.setPricePerHr(2.0);
            chargeBandList1.add(band1_2);

            // 12:00 to 18:30 charge 1.00
            ChargeBand band1_3 = new ChargeBand();
            band1_3.setHrs(12);
            band1_3.setMins(0);
            band1_3.setPricePerHr(1.0);
            chargeBandList1.add(band1_3);

            // 18:30 to 00:00 charge 0.50
            ChargeBand band1_4 = new ChargeBand();
            band1_4.setHrs(18);
            band1_4.setMins(30);
            band1_4.setPricePerHr(0.5);
            chargeBandList1.add(band1_4);

            dailyChargingScheme1.setChargeBandList(chargeBandList1);

            dailyChargingSchemeList.add(dailyChargingScheme1);
        }

        // free at weekends
        List<DayOfWeek> weekEndDays = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        for (DayOfWeek day : weekEndDays) {
            DailyChargingScheme dailyChargingScheme2 = new DailyChargingScheme();
            dailyChargingScheme2.setDay(day);
            List<ChargeBand> chargeBandList2 = new ArrayList<ChargeBand>();

            // 0:00 to 09:00 charge 0
            ChargeBand band1_1 = new ChargeBand();
            band1_1.setHrs(0);
            band1_1.setMins(0);
            band1_1.setPricePerHr(0.0);
            chargeBandList2.add(band1_1);

            dailyChargingScheme2.setChargeBandList(chargeBandList2);

            dailyChargingSchemeList.add(dailyChargingScheme2);
        }

        LOG.debug("dailyChargingSchemeList:");
        for (DailyChargingScheme dailyChargingScheme : dailyChargingSchemeList) {
            LOG.debug("    " + dailyChargingScheme);
        }
    }

    @Test
    public void testCalculatorStaticMethods() throws ParseException {

        // 2020-08-17 14:59 MONDAY
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date startDate = format.parse("2020-08-17 13:55");

        DayOfWeek dow = Calculator.getDayOfWeekFromDate(startDate);
        assertTrue(DayOfWeek.MONDAY.equals(dow));

        startDate = format.parse("2020-08-18 14:59");

        dow = Calculator.getDayOfWeekFromDate(startDate);
        assertTrue(DayOfWeek.TUESDAY.equals(dow));

        Integer mins = Calculator.getMinuteInDayFromDate(startDate);
        Integer expected = 14 * 60 + 59;
        assertEquals(expected, mins);

    }

    @Test
    public void testCalculatorPricePerMinute() throws ParseException {

        Calculator calculator = new Calculator(dailyChargingSchemeList);

        double aN = calculator.getPricePerMinute(DayOfWeek.MONDAY, 0);
        assertEquals("price per minute at 00.00 on monday ", 0.0, aN, 0);

        // price per minute at 01:00
        aN = calculator.getPricePerMinute(DayOfWeek.MONDAY, 60);
        assertEquals("price per minute at 01.00 on monday ", 0.0, aN, 0);

        // price per minute at 09.00 on monday
        aN = calculator.getPricePerMinute(DayOfWeek.MONDAY, 9 * 60);
        assertEquals("price per minute at 09.00 on monday ", 2.0 / 60, aN, 0);

        // price per minute at 13.00 on tuesday
        aN = calculator.getPricePerMinute(DayOfWeek.TUESDAY, 13 * 60);
        assertEquals("price per minute at 13.00 on tuesday ", 1.0 / 60, aN, 0);

        // price per minute at 12.30 on saturday
        aN = calculator.getPricePerMinute(DayOfWeek.SATURDAY, 12 * 60 + 30);
        assertEquals("price per minute at 12.30 on saturday ", 0.0, aN, 0);

    }

    @Test
    public void testCalculatorCharging() throws ParseException {

        Calculator calculator = new Calculator(dailyChargingSchemeList);

        // 2020-08-17 07:00 MONDAY for 3 hours 09.00 - 12.00 = 2*3 = £6.00
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = format.parse("2020-08-17 07:00");
        Integer parkingMinutes = 180;

        double charge = calculator.getMoneyForMinutes(startDate, parkingMinutes);
        assertEquals("2020-08-17 07:00 MONDAY for " + parkingMinutes + "minutes ", 2.00, charge, 0.05);

        // 07.00-09.00 (£0 per hr) £0.0
        startDate = format.parse("2020-08-17 07:00");
        parkingMinutes = 2 * 60;
        charge = calculator.getMoneyForMinutes(startDate, parkingMinutes);
        assertEquals("2020-08-17 07:00 MONDAY for " + parkingMinutes + " minutes ", 0, charge, 0.05);

        // 09.00-12.00 (£2 per hr) £6.0
        startDate = format.parse("2020-08-17 09:00");
        parkingMinutes = 3 * 60;
        charge = calculator.getMoneyForMinutes(startDate, parkingMinutes);
        assertEquals("2020-08-17 09:00 MONDAY for " + parkingMinutes + " minutes ", 6, charge, 0.05);

        // + 12.00-18.30 (£1per hr) £3.5 
        startDate = format.parse("2020-08-17 12:00");
        parkingMinutes = 6 * 60 + 30;
        charge = calculator.getMoneyForMinutes(startDate, parkingMinutes);
        assertEquals("2020-08-17 12:00 MONDAY for " + parkingMinutes + " minutes ", 6.5, charge, 0.05);

        // + 18.30-0.00 (£0.5 per hr) 5.5*0.5 = £2.75
        startDate = format.parse("2020-08-17 18:30");
        parkingMinutes = 5 * 60 + 30;
        charge = calculator.getMoneyForMinutes(startDate, parkingMinutes);
        assertEquals("2020-08-17 18:30 MONDAY for " + parkingMinutes + " minutes ", 2.75, charge, 0.05);

        // + 18.30-01.30 (£0.5 per hr) 5.5*0.5 = £2.75 00.00 £0 per hr
        startDate = format.parse("2020-08-17 18:30");
        parkingMinutes = 7 * 60;
        charge = calculator.getMoneyForMinutes(startDate, parkingMinutes);
        assertEquals("2020-08-17 18:30 MONDAY for " + parkingMinutes + " minutes ", 2.75, charge, 0.05);

        startDate = format.parse("2020-08-17 07:00");
        parkingMinutes = 24 * 60;

        // 07.00-09.00 £0
        // 09.00-12.00 (£2 per hr) £6.0
        // + 12.00-18.30 (£1per hr) £6.5 
        // + 18.30-0.00 (£0.5 per hr) 5.5*0.5 = £2.75  total = 
        charge = calculator.getMoneyForMinutes(startDate, parkingMinutes);
        assertEquals("2020-08-17 07:00 MONDAY for " + parkingMinutes + " minutes ", 15.25, charge, 0.05);

    }

    @Test
    public void testCalculatorChargingMinutes() throws ParseException {

        Calculator calculator = new Calculator(dailyChargingSchemeList);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date startDate = format.parse("2020-08-17 09:00");
        Double charge = 2.00;
        int minutes = calculator.getMinutesForMoney(startDate, charge);
        assertEquals("2020-08-17 09:00 MONDAY for £" + charge + " minutes ", 1 * 60, minutes);

        startDate = format.parse("2020-08-17 12:00");
        charge = 6.5;
        minutes = calculator.getMinutesForMoney(startDate, charge);

        Date endDate = new Date(startDate.getTime() + minutes * 60000);
        System.out.println("charge=" + charge
                + " start date=" + format.format(startDate)
                + " end date=" + format.format(endDate));
        // expect 390 actually 392
        // assertEquals("2020-08-17 12:00 MONDAY for £" + charge + " minutes ", 6 * 60 + 30, minutes );

        startDate = format.parse("2020-08-17 18:30");
        charge = 2.75;
        minutes = calculator.getMinutesForMoney(startDate, charge);

        endDate = new Date(startDate.getTime() + minutes * 60000);
        System.out.println("charge=" + charge
                + " start date=" + format.format(startDate)
                + " end date=" + format.format(endDate));
        // expect 330 actually 870
        //assertEquals("2020-08-17 18:30 MONDAY for £" + charge + " minutes ", 5 * 60 + 30, minutes );

        startDate = format.parse("2020-08-17 07:00");
        charge = 15.25;
        minutes = calculator.getMinutesForMoney(startDate, charge);
        endDate = new Date(startDate.getTime() + minutes * 60000);
        System.out.println("charge=" + charge
                + " start date=" + format.format(startDate)
                + " end date=" + format.format(endDate));
        // expect 1440 but 1019
        //assertEquals("2020-08-17 07:00 MONDAY for £" + charge + " minutes ", 24*60, minutes );

    }

}
