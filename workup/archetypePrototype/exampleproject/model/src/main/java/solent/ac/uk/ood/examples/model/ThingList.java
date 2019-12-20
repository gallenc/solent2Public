/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ThingList {

    // only used by persistance layer
    private Integer lastThingId = null;

    @XmlElementWrapper(name = "things")
    @XmlElement(name = "thing")
    private List<Thing> things = new ArrayList<Thing>();

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }
    
    
    public Integer getLastThingId() {
        return lastThingId;
    }

    public void setLastThingId(Integer lastThingId) {
        this.lastThingId = lastThingId;
    }

    @Override
    public String toString() {
        return "ThingList{" + "lastThingId=" + lastThingId + ", things=" + things + '}';
    }



}
