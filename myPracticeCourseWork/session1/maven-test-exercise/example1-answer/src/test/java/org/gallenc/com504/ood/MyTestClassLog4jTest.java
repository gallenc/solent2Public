/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gallenc.com504.ood;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gallenc
 */
public class MyTestClassLog4jTest {

    public MyTestClassLog4jTest() {
    }

    @Test
    public void aboutMeTest() {

        MyTestClassLog4j testclass1 = new MyTestClassLog4j();
        testclass1.writeAboutMe();

    }
    
    @Test
    public void aboutMeTest2() {

        MyTestClass testclass1 = new MyTestClass();
        testclass1.writeAboutMe();

    }
}
