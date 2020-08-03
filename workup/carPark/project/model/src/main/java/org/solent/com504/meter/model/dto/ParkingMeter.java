package org.solent.com504.meter.model.dto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ParkingMeter {

    private Long id;

    private String serialNumber;


    private Location location;

    private WeeklyChargingScheme weeklyChargingScheme;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Embedded
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToOne
    public WeeklyChargingScheme getWeeklyChargingScheme() {
        return weeklyChargingScheme;
    }

    public void setWeeklyChargingScheme(WeeklyChargingScheme weeklyChargingScheme) {
        this.weeklyChargingScheme = weeklyChargingScheme;
    }

    @Override
    public String toString() {
        return "ParkingMeter{" + "id=" + id + ", serialNumber=" + serialNumber + ", location=" + location + ", weeklyChargingScheme=" + weeklyChargingScheme + '}';
    }
    
    
}
