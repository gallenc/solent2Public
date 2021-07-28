
package org.solent.java.tutorial.a;

// Java encourages developers to document their classes using comments.
// comments are not read by the compiler and so can be used to provide in line comments on your code 
// for other developmeers or 'comment out' code for testing

// comments can be double slashes // which prevent the compiler reading code to the end of the line

/* or as in this case you can use block comments beginning with /*
*/

/**
 * block comments beginning with /** can be used to automatically generate html 'javadoc' 
 * documentation web sites.
 * @author cgallen
 */
public class JavaDocumentationExamples {
    
    /**
     * javadoc comments can document fields or variables
     */
    private Double mynumber = null;
    
    /**
     * javadoc comments can contain additional parameters to allow you to describe methods.
     * here we are documenting that this method converts a Double to a double primitive
     * @param mynumber the number to convert to a 
     * @return 
     */
    public double getDouble(Double aDoubleNumber){
        return aDoubleNumber;
    }

    /**
     * 
     * @return the mynumber variable
     */
    public Double getMynumber() {
        return mynumber;
    }

    /**
     * 
     * @param mynumber the mynumber variable to set
     */
    public void setMynumber(Double mynumber) {
        this.mynumber = mynumber;
    }


    
    
    
    
}
