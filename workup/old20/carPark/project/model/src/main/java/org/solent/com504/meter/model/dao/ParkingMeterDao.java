package org.solent.com504.meter.model.dao;

import java.util.List;
import org.solent.com504.meter.model.dto.ParkingMeter;

public interface ParkingMeterDao {

    public ParkingMeter findById(Long id);

    public ParkingMeter save(ParkingMeter parkingMeter);

    public List<ParkingMeter> findAll();

    public void deleteById(Long id);

    public void deleteAll();

    public ParkingMeter findBySerialNumber(String serialNumber);
    
    
    
    
}
