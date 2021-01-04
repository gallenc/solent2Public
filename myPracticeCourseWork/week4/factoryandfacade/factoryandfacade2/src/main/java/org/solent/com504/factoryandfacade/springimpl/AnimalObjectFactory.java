package org.solent.com504.factoryandfacade.springimpl;

import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.Cat;
import org.solent.com504.factoryandfacade.model.Cow;
import org.solent.com504.factoryandfacade.model.Dog;
import org.solent.com504.factoryandfacade.model.FarmFacade;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnimalObjectFactory {
    // for more info on spring see https://www.mkyong.com/spring3/spring-3-hello-world-example/

    // loads at startup and is never changed
    // applicationContext.xml must be on the class path
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static Animal createCat() {
        // nb Cat is a prototype bean - a new one is created on every call to the context
        Cat cat = (Cat) context.getBean("cat");
        return cat;
    }

    public static Animal createDog() {
        // nb Dog is a prototype bean - a new one is created on every call to the context
        Dog dog = (Dog) context.getBean("dog");
        return dog;
    }

    public static Animal createCow() {
        // nb Cow is a prototype bean - a new one is created on every call to the context
        Cow cow = (Cow) context.getBean("cow");
        return cow;
    }

    public static FarmFacade getFarmFacade() {
        // nb farmFacade is a singleton bean - there is only ever one and this gets the facade
        FarmFacade farmFacade = (FarmFacade) context.getBean("farmFacade");
        return farmFacade;
    }
}
