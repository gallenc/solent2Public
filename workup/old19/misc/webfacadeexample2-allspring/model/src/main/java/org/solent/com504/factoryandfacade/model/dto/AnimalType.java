package org.solent.com504.factoryandfacade.model.dto;

import java.util.Objects;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * AnimalType is a java bean which is used as a data transfer object (dto) and also
 * for persisting types of animals. This bean has been annotated with JAXB annotations
 * so that it can be used in ReST calls and also in the jaxb-dao
 *
 * @author gallenc
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
public class AnimalType {

    private String sound;

    private String type;
    
    // default constructor
    public AnimalType(){
    }

    // added constructor
    public AnimalType(String type, String sound ) {
        this.sound = sound;
        this.type = type;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setType(String type) {
        this.type = type;
    }

    // getter and setters
    public String getSound() {
        return sound;
    }
  
    public String getType() {
        return type;
    }

    
    // additional methods
    @Override
    public String toString() {
        return "AnimalType{" + "sound=" + sound + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sound);
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnimalType other = (AnimalType) obj;
        if (!Objects.equals(this.sound, other.sound)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }


}
