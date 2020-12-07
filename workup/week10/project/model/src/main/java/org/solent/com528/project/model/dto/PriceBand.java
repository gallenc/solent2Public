package org.solent.com528.project.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PriceBand {

    private Integer hour;

    private Integer minutes;

    private Band band;

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        if (hour == null || hour < 0 || hour > 12) {
            throw new IllegalArgumentException("hour out of bounds: " + hour);
        }
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        if (minutes == null || minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("minutes out of bounds: " + minutes);
        }

        this.minutes = minutes;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Integer getTimeInMinutes() {
        int hr = (hour != null) ? hour : 0;
        int min = (minutes != null) ? minutes : 0;
        return hr * 60 + min;
    }

    @Override
    public String toString() {
        return "PriceBand{" + "hour=" + hour + ", minutes=" + minutes + ", band=" + band + '}';
    }
    
}
