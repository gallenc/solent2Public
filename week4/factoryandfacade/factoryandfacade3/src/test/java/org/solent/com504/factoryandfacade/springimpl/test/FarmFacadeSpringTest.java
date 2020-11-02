/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.springimpl.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.factoryandfacade.springimpl.impl.FarmObjectFactorySpringImpl;
import org.solent.com504.factoryandfacade.model.FarmObjectFactory;
import org.solent.com504.factoryandfacade.test.FarmFacadeTest;

/**
 *
 * @author gallenc
 */
public class FarmFacadeSpringTest {
    
    String testApplicationContext = "applicationContext.xml";

    FarmObjectFactory farmObjectFactory = null;
    FarmFacadeTest farmFacadeTest = null;

    @Before
    public void loadFactory() {
        farmObjectFactory = new FarmObjectFactorySpringImpl(testApplicationContext);
        farmFacadeTest = new FarmFacadeTest();
        farmFacadeTest.setFarmObjectFactory(farmObjectFactory);
    }

    @Test
    public void testFactory() {
        System.out.println("FarmFacadeSpringTest testFactory");
        farmFacadeTest.testFactory();
    }

    @Test
    public void testFarmFacade() {
        System.out.println("FarmFacadeSpringTest testFarmFacade");
        farmFacadeTest.testFarmFacade();
    }
}
