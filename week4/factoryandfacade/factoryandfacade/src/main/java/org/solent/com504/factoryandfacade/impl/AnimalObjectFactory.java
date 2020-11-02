package org.solent.com504.factoryandfacade.impl;

import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.Cat;
import org.solent.com504.factoryandfacade.model.Cow;
import org.solent.com504.factoryandfacade.model.Dog;
import org.solent.com504.factoryandfacade.model.FarmFacade;

public class AnimalObjectFactory{
    
    // note there is only oe of these ever
    private static FarmFacade farmFacade = new FarmFacadeImpl();

    public static Animal createCat() {
        return new Cat();
    }

    public static Animal createDog() {
        return new Dog();
    }

    public static Animal createCow() {
        return new Cow();
    }

    public static FarmFacade getFarmFacade() {
        return farmFacade;
    }
}
