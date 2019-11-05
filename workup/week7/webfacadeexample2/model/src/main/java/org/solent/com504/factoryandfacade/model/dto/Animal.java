package org.solent.com504.factoryandfacade.model.dto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Animal is a java bean which is used as a data transfer object (dto) and also for persisting Animals. 
 * This bean has been annotated with JAXB annotations so
 * that it can be used in ReST calls and also in the jaxb-dao
 * Animal also contains JPA annotations so that it can be saved to a database by JPA
 *
 * @author gallenc
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Animal {

    private Long id;

    private String name;

    private String address;

    private AnimalType animalType;

    // getter and setters
    
    // id is used as key for the table corresponding to this JPA object
    // Generate value sets the database to automatically increase the ID ofr every new saved entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Embedded
    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    // additional methods
    @Override
    public String toString() {
        return "Animal{" + "name=" + name + ", address=" + address + ", animalType=" + animalType + ", id=" + id + '}';
    }

}
