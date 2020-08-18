/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.meter.utilities.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.solent.com504.meter.model.dto.ChargeBand;
import org.solent.com504.meter.model.dto.DailyChargingScheme;
import org.solent.com504.meter.model.dto.DayOfWeek;
import org.solent.com504.meter.model.dto.WeeklyChargingScheme;

/**
 *
 * @author cgallen
 */
public class Calculator1 {

    public static Integer getMinutesForMoney(Date timeNow, WeeklyChargingScheme weeklyChargingScheme, Double parkingCharge) {
        Integer parkingMinutes = 0;
        Double actualCharge = 0.0;
        Double aN = 0.0;
        Double chargeIncrement = 0.0;

        Integer tStart = getTimeInWeekFromDate(timeNow);

        Map<Integer, Double> chargingSchemeMap = chargingSchemeMap(weeklyChargingScheme);

        Iterator<Integer> chargingSchemeIterator = chargingSchemeMap.keySet().iterator();

        // find the start of the charge band when the ticket is to be issued
        Integer tn0 = 0;
        Integer tn1 = chargingSchemeIterator.next();
        while (tn1 < tStart && chargingSchemeIterator.hasNext()) {
            tn0 = tn1;
            tn1 = chargingSchemeIterator.next();
        }
        // find starting charge
        aN = chargingSchemeMap.get(tn0) / 60; // charge per hour to charge per minute
        chargeIncrement = aN * (tStart - tn0);

        while (actualCharge <= parkingCharge) {
            if ((actualCharge + chargeIncrement) < parkingCharge) {
                actualCharge = actualCharge + chargeIncrement;
                parkingMinutes = parkingMinutes + tn1 - tn0;
                if (chargingSchemeIterator.hasNext()) {
                    tn1 = chargingSchemeIterator.next();
                    tn0 = tn1;
                    aN = chargingSchemeMap.get(tn0) / 60; // charge per hour to charge per minute
                    chargeIncrement = aN * (tn1 - tn0);
                } else {
                    break;
                }
            } else {
                Double diff = parkingCharge - actualCharge;
                Integer diffTime = Math.toIntExact(Math.round(diff / aN));
                parkingMinutes = parkingMinutes + diffTime;
                break;
            }

        }
        return parkingMinutes;

    }

    // T1............T2............T3............T4............T5 
    // |.............|.............|.............|.............|
    // .................|.............................|
    // .................Tstart........................Tend
    // .................<---------->
    // .................(T3-Tstart)*T2.a
    // ............................<------------->
    // ............................(T3-T4)*T3.a
    // ...........................................<--->
    // ...........................................(Tend-T4)*T3.a
    /**
     * @param timeNow
     * @param weeklyChargingScheme
     * @param parkingMinutes
     * @return
     */
    public static Double getMoneyForMinutes(Date timeNow, WeeklyChargingScheme weeklyChargingScheme, Integer parkingMinutes) {
        Double charge = 0.0;
        //60 * 60 * 24 * 7 = 604800 minutes

        Integer tStart = getTimeInWeekFromDate(timeNow);
        Integer tEnd = tStart + parkingMinutes;

        Map<Integer, Double> chargingSchemeMap = chargingSchemeMap(weeklyChargingScheme);

        if (chargingSchemeMap.isEmpty()) {
            return 0.0;
        }

        Iterator<Integer> chargingSchemeIterator = chargingSchemeMap.keySet().iterator();

        Double chargeIncrement = 0.0;
        Double aN = 0.0;
        Integer tn0 = chargingSchemeIterator.next();
        Integer tn1 = tn0;

        // find the start of the charge band when the ticket is to be issued
        while (tn1 <= tStart && chargingSchemeIterator.hasNext()) {
            tn0 = tn1;
            tn1 = chargingSchemeIterator.next();
        }

        // calculate charge from tStart to next charge band
        aN = chargingSchemeMap.get(tn0);
        chargeIncrement = aN / 60 * (tn1 - tStart);
        charge = charge + chargeIncrement;

        // move through charge bands until in last time band which contains tEnd 
        while (chargingSchemeIterator.hasNext() && tn1 <= tEnd) {
            tn0 = tn1;
            tn1 = chargingSchemeIterator.next();
            aN = chargingSchemeMap.get(tn0);
            chargeIncrement = aN / 60 * (tn1 - tn0);
            charge = charge + chargeIncrement;
        }

        aN = chargingSchemeMap.get(tn0);
        chargeIncrement = aN /60 * (tEnd - tn0);
        charge = charge + chargeIncrement;

        return charge;

    }

    /**
     * number ranges from 1 (Sunday) to 7 (Saturday)
     * @param date
     * @return
     */
    public static int getDayNumberFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    static DayOfWeek getDayOfWeekFromDate(Date date) {
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

    /**
     * gives the time in the week from a given date (0 to 60 * 60 * 24 * 7 = 604800 minutes)
     *
     * @param date
     * @return
     */
    public static int getTimeInWeekFromDate(Date date) {
        int dayBaseMins = getDayBaseMinutes(getDayOfWeekFromDate(date));

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        int timeInWeek = dayBaseMins + hours *60 + mins;

        return timeInWeek;

    }

    /**
     * gives the minutes passed since sunday 00:00 to current day of week
     *
     * @param day DayOfWeek
     * @return minutes passed since sunday 00:00 to current day of week
     */
    public static Integer getDayBaseMinutes(DayOfWeek day) {
        Integer dayBaseHrs = 0;
        switch (day) {
            case SUNDAY:
                dayBaseHrs = 0;
                break;
            case MONDAY:
                dayBaseHrs = 24 * 1;
                break;
            case TUESDAY:
                dayBaseHrs = 24 * 2;
                break;
            case WEDNESDAY:
                dayBaseHrs = 24 * 3;
                break;
            case THURSDAY:
                dayBaseHrs = 24 * 4;
                break;
            case FRIDAY:
                dayBaseHrs = 24 * 5;
                break;
            case SATURDAY:
                dayBaseHrs = 24 * 6;
                break;
        }
        return dayBaseHrs * 60;
    }

    /**
     * Returns a map of minutes in week against all charges in given weeklyChargingScheme sorted by time ascending
     *
     * @param weeklyChargingScheme
     * @return Map Minutes in week (0 to 60 * 60 * 24 * 7 = 604800 minutes), charge per hour
     */
    public static Map<Integer, Double> chargingSchemeMap(WeeklyChargingScheme weeklyChargingScheme) {
        Map<Integer, Double> weeklyTimeMap = new TreeMap<Integer, Double>();

        weeklyTimeMap.put(0, 0.0); // always have at least a zero charge in the map - may be overwritten

        List<DailyChargingScheme> dailyChargingSchemeList = weeklyChargingScheme.getDailyChargingSchemeList();
        for (DailyChargingScheme dailyScheme : dailyChargingSchemeList) {
            DayOfWeek day = dailyScheme.getDay();
            int dayBaseMinutes = getDayBaseMinutes(day);

            List<ChargeBand> chargeBandList = dailyScheme.getChargeBandList();
            for (ChargeBand chargeBand : chargeBandList) {
                Integer bandStart = dayBaseMinutes + chargeBand.getHrs() * 60 + chargeBand.getMins();
                weeklyTimeMap.put(bandStart, chargeBand.getPricePerHr());
            }
        }

        return weeklyTimeMap;

    }

}
