/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.dao.jaxb.test;

import org.junit.Before;
import org.junit.Test;
import org.solent.com528.project.impl.dao.jaxb.PriceCalculatorDAOJaxbImpl;
import org.solent.com528.project.impl.dao.jaxb.StationDAOJaxbImpl;
import org.solent.com528.project.model.dao.StationDAO;

/**
 *
 * @author cgallen
 */
public class StationDAOJaxbImplTest {
    
    StationDAO stationDAOjaxb;
    
        // runs before every test
    @Before
    public void init() {
        String fileName = "target/stationDAOJaxbImplFile.xml";
        stationDAOjaxb = new StationDAOJaxbImpl(fileName);
        System.out.println("deleting all station items");
        stationDAOjaxb.deleteAll();
    }

    @Test
    public void testInit() {
        // just runs init method
    }
}
