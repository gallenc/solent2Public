package org.solent.com528.project.model.dto;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Station {

    // stops xml binding of id
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // default zone is zero
    private Integer zone =0;

    // stops xml binding of ticket machines - not needed in messages
    @XmlTransient
    
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
//    @JoinColumn(name = "ticketMachine_id")
//    private Set<TicketMachine> ticketMachines;
//    
//    // see https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//    // use these methods to add / remove ticket machines from stations
//    public void addTicketMachine(TicketMachine ticketMachine) {
//        ticketMachines.add(ticketMachine);
//        ticketMachine.setStation(this);
//    }
// 
//    public void removeTicketMachine(TicketMachine ticketMachine) {
//        ticketMachines.remove(ticketMachine);
//        ticketMachine.setStation(null);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

//    public Set<TicketMachine> getTicketMachines() {
//        return ticketMachines;
//    }
//
//    public void setTicketMachines(Set<TicketMachine> ticketMachines) {
//        this.ticketMachines = ticketMachines;
//    }

//    @Override
//    public String toString() {
//        return "Station{" + "id=" + id + ", name=" + name + ", zone=" + zone + ", ticketMachines=" + ticketMachines + '}';
//    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", name=" + name + ", zone=" + zone + '}';
    }


}
