package org.solent.com504.factoryandfacade.model.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * AnimalList is a java bean which is used as a data transfer object (dto) and also
 * for persisting lists of Animals. This bean has been annotated with JAXB annotations
 * so that it can be used in ReST calls and also in the jaxb-dao
 *
 * @author gallenc
 */
@XmlRootElement(name = "animalList")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalList {

    @XmlElementWrapper(name = "alist")
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
