#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package}.model;

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
public class EntityList {

    // only used by persistance layer
    private Integer lastEntityId = null;

    @XmlElementWrapper(name = "entities")
    @XmlElement(name = "entity")
    private List<Entity> entities = new ArrayList<Entity>();

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    
    
    public Integer getLastEntityId() {
        return lastEntityId;
    }

    public void setLastEntityId(Integer lastEntityId) {
        this.lastEntityId = lastEntityId;
    }

    @Override
    public String toString() {
        return "EntityList{" + "lastEntityId=" + lastEntityId + ", entities=" + entities + '}';
    }



}
