/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.meter.utilities.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.solent.com504.meter.model.dto.ChargeBand;
import org.solent.com504.meter.model.dto.DailyChargingScheme;
import org.solent.com504.meter.model.dto.DayOfWeek;

/**
 * This calculator class calculates the charge for a parking period based upon the dailyChargingSchemeList
 * 
 * You must initialize the class  using a dailyChargingSchemeList in the constructor.
 * The two key business methods are:
 * 
 * Double getMoneyForMinutes(Date startTime, Integer parkingMinutes)
 * calculates the charge for parkingMinutes spent at meter from Date startTime
 * 
 * Integer getMinutesForMoney(Date startTime, Double parkingPayment)
 * calculates the number of parking minutes allowed for a given parking payment given a meter startTime
 * 
 * @author cgallen
 */
public class Calculator {

    final static Logger LOG = LogManager.getLogger(Calculator.class);

    // Map<DayOfWeek,Map<Time in mins ,Double price per minute>>
    private Map<DayOfWeek, SortedMap<Integer, Double>> weekTimeMap = new HashMap();

    /**
     * Constructor. You must supply a validly populated list of dailyChargingSchemes
     * @param dailyChargingSchemeList 
     */
    public Calculator(List<DailyChargingScheme> dailyChargingSchemeList) {

        // fill weekTimeMap with empty days as default
        for (DayOfWeek day : DayOfWeek.values()) {
            TreeMap<Integer, Double> dailyChargeMap = new TreeMap();
            dailyChargeMap.put(0, 0.0);
            weekTimeMap.put(day, dailyChargeMap);
        }

        // fill weekTimeMap with actual values if set
        if (dailyChargingSchemeList == null) {
            throw new IllegalArgumentException("dailyChargingSchemeList cannot be null");
        }

        for (DailyChargingScheme dailyScheme : dailyChargingSchemeList) {
            DayOfWeek day = dailyScheme.getDay();
            if (day == null) {
                throw new IllegalArgumentException("day cannot be null in DailyChargingScheme");
            }
            TreeMap<Integer, Double> dailyChargeMap = new TreeMap();
            List<ChargeBand> chargeBandList = dailyScheme.getChargeBandList();
            for (ChargeBand chargeBand : chargeBandList) {
                if (chargeBand.getHrs() == null || chargeBand.getMins() == null || chargeBand.getPricePerHr() == null) {
                    throw new IllegalArgumentException("cannot have null value in chargeBand: " + chargeBand);
                }
                Double pricePerMinute = chargeBand.getPricePerHr() / 60;
                dailyChargeMap.put(chargeBand.getHrs() * 60 + chargeBand.getMins(), pricePerMinute);
            }
            weekTimeMap.put(day, dailyChargeMap);
        }
    }

    /**
     * calculates the charge for parkingMinutes spent at meter from Date startTime
     * @param startTime
     * @param parkingMinutes
     * @return 
     */
    public Double getMoneyForMinutes(Date startTime, Integer parkingMinutes) {
        Double charge = 0.0;
        long startTimeLong = startTime.getTime();

        for (long min = 0; min <= parkingMinutes; min++) {
            long t = startTimeLong + min * 60000; // 60000ms = 1 minute
            Date date = new Date(t);
            DayOfWeek day = getDayOfWeekFromDate(date);
            int minuteInDay = getMinuteInDayFromDate(date);
            charge = charge + getPricePerMinute(day, minuteInDay);
        }
        return charge;
    }

    /**
     * calculates the number of parking minutes allowed for a given parking payment given a meter startTime
     * @param startTime
     * @param parkingPayment
     * @return 
     */
    public Integer getMinutesForMoney(Date startTime, Double parkingPayment) {
        Integer parkingMinutes = 0;
        Double currentCharge = 0.0;
        long startTimeLong = startTime.getTime();

        while (parkingPayment > currentCharge) {
            parkingMinutes++;
            long t = startTimeLong + parkingMinutes * 60000; // 60000ms = 1 minute
            Date date = new Date(t);
            DayOfWeek day = getDayOfWeekFromDate(date);
            int minuteInDay = getMinuteInDayFromDate(date);
            currentCharge = currentCharge + getPricePerMinute(day, minuteInDay);
        }
        return parkingMinutes;
    }

    public double getPricePerMinute(DayOfWeek day, Integer minuteInDay) {
        SortedMap<Integer, Double> dailyChargeMap = weekTimeMap.get(day);
        return getPricePerMinute(dailyChargeMap, minuteInDay);
    }

    private double getPricePerMinute(SortedMap<Integer, Double> dailyChargeMap, Integer minuteInDay) {
        Iterator<Integer> chargingSchemeIterator = dailyChargeMap.keySet().iterator();

        if (dailyChargeMap.isEmpty()) {
            return 0.0;
        }
        
        Double aN =0.0;
        Integer tn0 = chargingSchemeIterator.next();
        Integer tn1 = tn0;
        while (tn1 <= minuteInDay) {
            tn0 = tn1;
            aN = dailyChargeMap.get(tn0);
            if (!chargingSchemeIterator.hasNext()) {
                break;
            }
            tn1 = chargingSchemeIterator.next();
        }
        
        return aN;
    }

    public static int getMinuteInDayFromDate(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        int ninuteInDay = hours * 60 + mins;

        return ninuteInDay;

    }

    /**
     * number ranges from 1 (Sunday) to 7 (Saturday)
     *
     * @param date
     * @return
     */
    public static int getDayNumberFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static DayOfWeek getDayOfWeekFromDate(Date date) {
        int dayNum = getDayNumberFromDate(date);

        switch (dayNum) {
            case 1:
                return DayOfWeek.SUNDAY;
            case 2:
                return DayOfWeek.MONDAY;
            case 3:
                return DayOfWeek.TUESDAY;
            case 4:
                return DayOfWeek.WEDNESDAY;
            case 5:
                return DayOfWeek.THURSDAY;
            case 6:
                return DayOfWeek.FRIDAY;
            case 7:
                return DayOfWeek.SATURDAY;
            default:
                throw new IllegalArgumentException("illegal day number");
        }

    }

}
