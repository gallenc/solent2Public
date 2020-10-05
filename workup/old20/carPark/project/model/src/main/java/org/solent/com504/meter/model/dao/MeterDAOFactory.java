package org.solent.com504.meter.model.dao;

public interface MeterDAOFactory {

    public ParkingMeterDao getParkingMeterDAO();

    public WeeklyChargingSchemeDao getWeeklyChargingSchemeDAO();

    public void shutDown();
}
