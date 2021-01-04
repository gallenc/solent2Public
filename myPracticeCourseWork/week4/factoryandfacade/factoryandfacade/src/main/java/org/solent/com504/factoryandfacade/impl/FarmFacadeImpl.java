/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl;

import java.util.ArrayList;
import java.util.List;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.FarmFacade;

/**
 *
 * @author cgallen
 */
public class FarmFacadeImpl implements FarmFacade {

    // how would you implement this class
    private List<Animal> animalList = new ArrayList<Animal>();

    @Override
    public List<Animal> getAllAnimals() {
        // returns a copy of the ArrayList
        return new ArrayList<Animal>(animalList);
    }

    @Override
    public void addDog(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCat(String name) {
        Animal animal = AnimalObjectFactory.createCat();
        animal.setName(name);
        animalList.add(animal);
    }

    @Override
    public void addCow(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
