package org.solent.com504.factoryandfacade.impl;

import org.solent.com504.factoryandfacade.model.Cow;

public class CowImpl extends AbstractAnimal implements Cow {

    @Override
    public String getSound() {
        return Cow.SOUND;
    }

    @Override
    public String getAnimalType() {
        return Cow.ANIMAL_TYPE;
    }
}
