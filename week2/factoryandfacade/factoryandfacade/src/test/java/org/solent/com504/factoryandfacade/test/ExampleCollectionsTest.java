/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.Cat;
import org.solent.com504.factoryandfacade.model.Dog;

/**
 *
 * @author cgallen
 */
public class ExampleCollectionsTest {

    // This test provides a very simple introduction to Lists, and java Generics
    // For more information and examples see 
    // https://docs.oracle.com/javase/tutorial/collections/
    // To learn more about Generics see
    // https://docs.oracle.com/javase/tutorial/java/generics/index.html

    // in this case we are using an ArrayList implemtation which is a list implementted usign an array
    
     // this is simply a list of objects.
    List unTypedList = new ArrayList();

    // this is a typed list of Animals
    List<Animal> listOfAnimals = new ArrayList<Animal>();

    // you could alternatively use a Linked list impementation to do the same thing
    List unTypedList2 = new LinkedList();
    List<Animal> listOfAnimals2 = new LinkedList<Animal>();

    @Test
    public void unTypedListTest() {
        System.out.println("\n start of unTypedListTest()");

        // You can add any object to an un typed list
        unTypedList.add(new Cat());
        unTypedList.add("any old string");
        unTypedList.add(new Integer(101));

        System.out.println("\nprinting out list of objects using toString()");
        for (Object object : unTypedList) {
            // all objects have a toString method
            // String.toString outputs the actual string
            System.out.println(object.toString());
        }

        System.out.println("\nend of unTypedListTest()");
    }

    @Test
    public void typedListTest() {
        System.out.println("\n start of typedListTest()");

        // You can add any animal to this list
        Cat c1 = new Cat();
        c1.setName("Meme");
        listOfAnimals.add(c1);
        Dog d1 = new Dog();
        d1.setName("Fido");
        listOfAnimals.add(d1);

        // but you cannot add any other object type
        // if you uncomment this line the compiler will throw an error
        // listOfAnimals.add("any old string");
        // we can still access the objects as objects but we have to cast them to animal to access the animal
        System.out.println("\n listing animals with explicit casting:");
        for (Object object : listOfAnimals) {
            // all objects have a toString method
            System.out.println(object.toString());

            // if you know the objects in the list are only Animals 
            // you can cast them to animal
            Animal a = (Animal) object;
            System.out.println("animal '" + a.getName()
                    + "' makes this sound '" + a.getSound()
                    + " because it is a " + a.getClass().getSimpleName()
                    + " implemented by " + a.getClass().getTypeName());

        }

        // but we can also access the animals as types and avoid casting
        // this makes it less likely for the programmer to make errors
        // the compiler automatically casts the object to an animal
        System.out.println("\n listing animals without explicit casting:");
        for (Animal a : listOfAnimals) {

            System.out.println("animal '" + a.getName()
                    + "' makes this sound '" + a.getSound()
                    + "' because it is a '" + a.getClass().getSimpleName()
                    + "' implemented by " + a.getClass().getTypeName());
        }

        System.out.println("end of typedListTest()");
    }
}
