package org.solent.com504.factoryandfacade.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Animal is a java bean which is used as a data transfer object (dto) and also
 * for persisting Animals. This bean has been annotated with JAXB annotations
 * so that it can be used in ReST calls and also in the jaxb-dao
 * @author gallenc
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Animal {

    private String name;

    private String address;

    private AnimalType animalType;

    private Long id;

    // getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // additional methods
    @Override
    public String toString() {
        return "Animal{" + "name=" + name + ", address=" + address + ", animalType=" + animalType + ", id=" + id + '}';
    }

}
