package org.solent.com504.factoryandfacade.impl;

import org.solent.com504.factoryandfacade.model.Cat;

public class CatImpl extends AbstractAnimal implements Cat {

    @Override
    public String getSound() {
        return Cat.SOUND;
    }

    @Override
    public String getAnimalType() {
        return Cat.ANIMAL_TYPE;
    }
    
}
