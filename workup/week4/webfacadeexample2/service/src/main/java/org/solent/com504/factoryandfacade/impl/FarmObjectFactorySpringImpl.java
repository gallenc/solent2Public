package org.solent.com504.factoryandfacade.impl;

import java.util.List;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.FarmFacade;
import org.solent.com504.factoryandfacade.model.FarmObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FarmObjectFactorySpringImpl implements FarmObjectFactory {

    private static final String DEFAULT_APPLICATION_CONTEXT = "applicationContext.xml";

    private String applicationContextFile = DEFAULT_APPLICATION_CONTEXT;

    public FarmObjectFactorySpringImpl() {
    }

    public FarmObjectFactorySpringImpl(String appContextFile) {
        applicationContextFile = appContextFile;
    }

    // loads at startup and is never changed
    // applicationContext.xml must be on the class path
    private final ApplicationContext context = new ClassPathXmlApplicationContext(applicationContextFile);

    @Override
    public Animal createAnimal(String animalType) {
        Animal animal = (Animal) context.getBean(animalType);
        return animal;
    }

    @Override
    public List<String> getSupportedAnimalTypes() {
        List<String> supportedAnimalTypes = (List) context.getBean("supportedAnimalTypes");
        return supportedAnimalTypes;
    }

    @Override
    public FarmFacade createFarmFacade() {
        FarmFacadeImpl farmFacadeImpl = (FarmFacadeImpl)  context.getBean("farmFacade");
        farmFacadeImpl.setFarmObjectFactory(this);
        return  farmFacadeImpl ;
    }

 
}
