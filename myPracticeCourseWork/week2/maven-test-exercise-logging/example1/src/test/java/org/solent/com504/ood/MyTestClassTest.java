/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.ood;

import org.solent.com504.ood.MyTestClassLog4j;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author cgallen
 */
public class MyTestClassTest {

    @Test
    public void shouldAnswerAboutMe() {
        MyTestClassLog4j myTest = new MyTestClassLog4j();
        myTest.writeAboutMe();
    }
    
    @Test
    public void shouldAnswerWithName() {
        MyTestClassLog4j myTest = new MyTestClassLog4j();
        String result = myTest.talkAboutMe("Craig");
        System.out.println(result);
        
        assertTrue("talking about Craig".equals(result));
    }

}
