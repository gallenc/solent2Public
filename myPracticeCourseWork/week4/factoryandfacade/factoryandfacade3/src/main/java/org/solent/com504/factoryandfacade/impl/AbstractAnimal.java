package org.solent.com504.factoryandfacade.impl;

import org.solent.com504.factoryandfacade.model.Animal;

public abstract class AbstractAnimal implements Animal {
    
    String name = null;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Abstract methods
    
    @Override
    public abstract String getSound();
    
    @Override
    public abstract String getAnimalType();

    @Override
    public String toString() {
        return "AbstractAnimal{" + "name=" + name 
                + " type="+getAnimalType()
                + " sound="+getSound()
                + "}";
    }
    
    
    
 
    
}
