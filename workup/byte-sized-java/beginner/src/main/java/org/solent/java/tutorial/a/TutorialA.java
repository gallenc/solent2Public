/**
 * Anatomy of a typical java class
 *
 * All Java classes are defined in 'packages'.
 * This helps you structure your project so that classes which are related to a similar function
 * in the program can be kept together in the project.
 */
package org.solent.java.tutorial.a;

/**
 * classes in the same package have visibility of each other and don't need explicitly imported. However classes must explicitly import classes from other
 * packages.
 */
import org.solent.java.tutorial.a.a1.Car;

public class TutorialA {

    // here CarPark classes are in the same package as TutorialA and so don't need explicitly declared
    private CarPark carPark = null;

    // here car classes are not in the same package as TutorialA so need imported as above.
    private Car car = null;

    
      public static void main(String[] args) {

    }
    
    
    
}
