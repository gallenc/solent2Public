package org.solent.com528.project.model.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PricingDetails {

    private Double peakPricePerZone;

    private Double offpeakPricePerZone;

    @XmlElementWrapper(name = "priceBandList")
    @XmlElement(name = "priceBand")
    private List<PriceBand> priceBandList;

    public Double getPeakPricePerZone() {
        return peakPricePerZone;
    }

    public void setPeakPricePerZone(Double peakPricePerZone) {
        this.peakPricePerZone = peakPricePerZone;
    }

    public Double getOffpeakPricePerZone() {
        return offpeakPricePerZone;
    }

    public void setOffpeakPricePerZone(Double offpeakPricePerZone) {
        this.offpeakPricePerZone = offpeakPricePerZone;
    }

    public List<PriceBand> getPriceBandList() {
        return priceBandList;
    }

    public void setPriceBandList(List<PriceBand> priceBandList) {
        this.priceBandList = priceBandList;
    }

    @Override
    public String toString() {
        return "PricingDetails{" + "peakPricePerZone=" + peakPricePerZone + ", offpeakPricePerZone=" + offpeakPricePerZone + ", priceBandList=" + priceBandList + '}';
    }
    
}
