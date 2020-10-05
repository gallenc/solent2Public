package org.solent.com504.meter.model.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MeterReplyMessage {

    private Integer code;

    private String debugMessage;

    private ParkingMeter parkingMeterConfig;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public ParkingMeter getParkingMeterConfig() {
        return parkingMeterConfig;
    }

    public void setParkingMeterConfig(ParkingMeter parkingMeterConfig) {
        this.parkingMeterConfig = parkingMeterConfig;
    }

    @Override
    public String toString() {
        return "MeterReplyMessage{" + "code=" + code + ", debugMessage=" + debugMessage + ", parkingMeterConfig=" + parkingMeterConfig + '}';
    }
    
    
}
