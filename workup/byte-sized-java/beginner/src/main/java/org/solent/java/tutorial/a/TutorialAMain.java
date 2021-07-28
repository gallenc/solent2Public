/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.java.tutorial.a;

/**
 *
 * @author cgallen
 */
public class TutorialAMain {
    
    public static void main(String[] args) {
        
       // The eight primitives defined in Java are int, byte, short, long, float, double, boolean, and char
       // – those aren't considered objects and represent raw values.
       int intA = 10;
       short shortA = 255;
       long longA= Long.MAX_VALUE; // MAX_VALUE = 9223372036854775807
       float floatA = Float.MAX_VALUE; // 3.4028235E38
       double doubleA = Double.MAX_VALUE; // 1.7976931348623157E308
       boolean booleanA = true; // true or false
       char charA = 'A';
       
       // each primative has a corressponding Object which uses more memory but has additional behaviour methods
       Integer intB = 10;
       Short shortB = 255;
       Long longB= Long.MAX_VALUE; // MAX_VALUE = 9223372036854775807
       Float floatB = Float.MAX_VALUE; // 3.4028235E38
       Double doubleB = Double.MAX_VALUE; // 1.7976931348623157E308
       Boolean booleanB = true; // true or false
       Character charB = 'B';

    }
}
