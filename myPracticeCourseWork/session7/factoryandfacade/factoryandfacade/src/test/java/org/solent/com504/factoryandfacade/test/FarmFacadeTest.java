package org.solent.com504.factoryandfacade.test;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.impl.AnimalObjectFactory;
import org.solent.com504.factoryandfacade.model.FarmFacade;

/**
 *
 * @author cgallen
 */
public class FarmFacadeTest {
   

    @Test
    public void FarmFacadeTest() {

        FarmFacade farmFacade = AnimalObjectFactory.getFarmFacade();
        assertNotNull(farmFacade);
        
        List<Animal> animals = farmFacade.getAllAnimals();
        assertTrue(animals.isEmpty());
        
        // test create cat
        String catName = "fluffy";
        farmFacade.addCat(catName);
        
        animals = farmFacade.getAllAnimals();
        assertEquals(1, animals.size());
        
        assertEquals(catName, animals.get(0).getName());
  
        // WHAT OTHER TESTS WOULD YOU CREATE HERE TO SET UP AND TEST THE FARM FACADE?
        
        String dogName = "doggy";
        farmFacade.addDog(dogName);
        animals = farmFacade.getAllAnimals();
        assertEquals(2, animals.size());
        
        assertEquals("doggy", animals.get(1).getName());
        
        String cowName = "moo moo";
        farmFacade.addCow(cowName);
        animals = farmFacade.getAllAnimals();
        assertEquals(3, animals.size());
        
        assertEquals(cowName, animals.get(2).getName());
        
        String duckName = "quackers";
        farmFacade.addDuck(duckName);
        animals = farmFacade.getAllAnimals();
        assertEquals(4, animals.size());
        
        assertEquals(duckName, animals.get(3).getName());
    }
}
