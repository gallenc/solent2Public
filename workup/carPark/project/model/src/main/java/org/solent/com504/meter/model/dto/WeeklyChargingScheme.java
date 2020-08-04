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
public class WeeklyChargingScheme {
    
    Long id;
    
    @XmlElementWrapper(name = "dailyChargingSchemeList")
    @XmlElement(name = "dailyChargingScheme")
    private List<DailyChargingScheme> dailyChargingSchemeList;

    private String chargingSchemeName;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<DailyChargingScheme> getDailyChargingSchemeList() {
        return dailyChargingSchemeList;
    }

    public void setDailyChargingSchemeList(List<DailyChargingScheme> dailyChargingSchemeList) {
        this.dailyChargingSchemeList = dailyChargingSchemeList;
    }

    public String getChargingSchemeName() {
        return chargingSchemeName;
    }

    public void setChargingSchemeName(String chargingSchemeName) {
        this.chargingSchemeName = chargingSchemeName;
    }

    @Override
    public String toString() {
        return "WeeklyChargingScheme{" + "dailyChargingSchemeList=" + dailyChargingSchemeList + ", chargingSchemeName=" + chargingSchemeName + '}';
    }
    
    
}
