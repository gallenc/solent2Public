package org.solent.com528.project.model.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketMachineConfig {

    private String uuid;

    private String stationName;
    
    private Integer stationZone;

    @XmlElementWrapper(name = "stationList")
    @XmlElement(name = "station")
    private List<Station> stationList;

    private PricingDetails pricingDetails;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }

    public PricingDetails getPricingDetails() {
        return pricingDetails;
    }

    public void setPricingDetails(PricingDetails pricingDetails) {
        this.pricingDetails = pricingDetails;
    }

    public Integer getStationZone() {
        return stationZone;
    }

    public void setStationZone(Integer stationZone) {
        this.stationZone = stationZone;
    }

    @Override
    public String toString() {
        return "TicketMachineConfig{" + "uuid=" + uuid + ", stationName=" + stationName + ", stationZone=" + stationZone + ", stationList=" + stationList + ", pricingDetails=" + pricingDetails + '}';
    }
   

}
