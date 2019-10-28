/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.model.dao;

/**
 *
 * @author cgallen
 */
public interface DAOFactory {

    public AnimalTypeDao getAnimalTypeDao();

    public AnimalDao getAnimalDao();

}
