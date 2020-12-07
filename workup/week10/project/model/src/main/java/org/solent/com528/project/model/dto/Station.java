package org.solent.com528.project.model.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {

    // stops xml binding of id
    @XmlTransient 
    private Long id;

    private String name;

    private Integer zone;

    // stops xml binding of ticket machines - not needed in messages
    @XmlTransient 
    private List<TicketMachine> ticketMachines;

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

    public List<TicketMachine> getTicketMachines() {
        return ticketMachines;
    }

    public void setTicketMachines(List<TicketMachine> ticketMachines) {
        this.ticketMachines = ticketMachines;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", name=" + name + ", zone=" + zone + ", ticketMachines=" + ticketMachines + '}';
    }
    
    
    
}
