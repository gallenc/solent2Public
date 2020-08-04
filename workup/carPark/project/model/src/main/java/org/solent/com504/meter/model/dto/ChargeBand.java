package org.solent.com504.meter.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class ChargeBand {
    
    private Long id;

    private Integer hrs;

    private Integer mins;

    private Double pricePerHr;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHrs() {
        return hrs;
    }

    public void setHrs(Integer hrs) {
        this.hrs = hrs;
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Double getPricePerHr() {
        return pricePerHr;
    }

    public void setPricePerHr(Double pricePerHr) {
        this.pricePerHr = pricePerHr;
    }

    @Override
    public String toString() {
        return "ChargeBand{" + "id=" + id + ", hrs=" + hrs + ", mins=" + mins + ", pricePerHr=" + pricePerHr + '}';
    }


 
    
}
