package org.solent.com504.factoryandfacade.impl;

import org.solent.com504.factoryandfacade.model.Cow;
import org.solent.com504.factoryandfacade.model.Duck;

public class DuckImpl extends AbstractAnimal implements Cow {

    @Override
    public String getSound() {
        return Duck.SOUND;
    }

    @Override
    public String getAnimalType() {
        return Duck.ANIMAL_TYPE;
    }
}
