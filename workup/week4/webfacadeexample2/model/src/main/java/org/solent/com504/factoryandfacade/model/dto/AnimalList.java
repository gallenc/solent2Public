package org.solent.com504.factoryandfacade.model.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animalList")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalList {

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "animal")
    private List<Animal> animals = new ArrayList<Animal>();
    
    private Long currentMaxId = 0L;

    public Long getCurrentMaxId() {
        return currentMaxId;
    }

    public void setCurrentMaxId(Long currentMaxId) {
        this.currentMaxId = currentMaxId;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
