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

    private Integer hr;

    private Integer min;

    private Double pricePerHr;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHr() {
        return hr;
    }

    public void setHr(Integer hr) {
        this.hr = hr;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Double getPricePerHr() {
        return pricePerHr;
    }

    public void setPricePerHr(Double pricePerHr) {
        this.pricePerHr = pricePerHr;
    }

    @Override
    public String toString() {
        return "ChargeBand{" + "hr=" + hr + ", min=" + min + ", pricePerHr=" + pricePerHr + '}';
    }

 
    
}
