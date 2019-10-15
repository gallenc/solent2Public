package org.solent.com504.factoryandfacade.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AnimalList {

    private List<Animal> animals = new ArrayList<Animal>();

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
