/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.java.tutorial.a.a1;

/**
 *
 * @author cgallen
 */
public class Car {

    /**
     * object orientation implies that information on how a class works and the data inside a class is hidden from the external users of the class. Usually we
     * declare data which is used by several methods in a class as 'private'. Private means you cannot access this data externally without using a method
     * provided by a class
     *
     * fields can also be declared: public The code is accessible for all classes private The code is only accessible within the declared class default The code
     * is only accessible in the same package. This is used when you don't specify a modifier. or protected only available to classes which inherit from this
     * class
     *
     * mostly we use public and private. fields or properties are data objects which are 'scoped' in the class to be available to all methods within the class.
     */
    private String numberPlate = null;
    private String type = null;

    /**
     * all classes implicitly have 'no variable Constructors' which are used to create a new 
     * 'empty class' however you can also explicitly declare a constructor. 
     * If we defined this constructor as 'private' then the only way to create the class would 
     * be through the constructors declared public
     */
    public Car() {
    }

    /**
     * classes can also have constructors with variables which pre-initialise a class when it is created Note the use of 'this' which refers to the class in
     * which the constructor is present. This allows us to distinguish between the parameters of the constructor and the fields in the class
     */
    public Car(String numberPlate, String type) {
        this.numberPlate = numberPlate;
        this.type = type;
    }

    /**
     * Getters and setters are used here to access and change the fields in the class.
     * @return 
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    /**
     * All classes implicitly have toString() methods but by default the toString() 
     * only returns internal object information. We often 'override' the default toString
     * method to return more useful information.
     * @return 
     */
    
    @Override
    public String toString() {
        return "Car{" + "numberPlate=" + numberPlate + ", type=" + type + '}';
    }
    
    

}
