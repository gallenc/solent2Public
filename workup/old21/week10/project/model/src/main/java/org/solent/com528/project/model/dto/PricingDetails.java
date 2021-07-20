package org.solent.com528.project.model.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PricingDetails {

    private Double peakPricePerZone = 0.0;

    private Double offpeakPricePerZone = 0.0;

    @XmlElementWrapper(name = "priceBandList")
    @XmlElement(name = "priceBand")
    private List<PriceBand> priceBandList;

    public PricingDetails() {
        // set default price band so always a band present
        priceBandList = new ArrayList<PriceBand>();
        PriceBand defaultPriceBand = new PriceBand();
        defaultPriceBand.setRate(Rate.OFFPEAK);
        defaultPriceBand.setHour(0);
        defaultPriceBand.setMinutes(0);
        priceBandList.add(defaultPriceBand);
    }

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
        StringBuffer sb = new StringBuffer("PricingDetails{" + "peakPricePerZone=" + peakPricePerZone + ", offpeakPricePerZone=" + offpeakPricePerZone + "\n priceBandList:\n");

        for (PriceBand priceBand : priceBandList) {
            sb.append("   " + priceBand.toString() + "\n");
        }

        sb.append("}");

        return sb.toString();

    }

}
