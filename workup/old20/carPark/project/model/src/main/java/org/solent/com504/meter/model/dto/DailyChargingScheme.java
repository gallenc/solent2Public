package org.solent.com504.meter.model.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class DailyChargingScheme {
    
    Long id;

    private DayOfWeek day;

    @XmlElementWrapper(name = "chargeBandList")
    @XmlElement(name = "chargBand")
    private List<ChargeBand> chargeBandList;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<ChargeBand> getChargeBandList() {
        return chargeBandList;
    }

    public void setChargeBandList(List<ChargeBand> chargeBandList) {
        this.chargeBandList = chargeBandList;
    }

    @Override
    public String toString() {
        return "DailyChargingScheme{" + "day=" + day + ", chargeBandList=" + chargeBandList + '}';
    }
    
    
}
