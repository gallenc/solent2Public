/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.springdata;

import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gallenc
 */
@Repository
public interface AnimalDAOSpringData extends JpaRepository<Animal, Long> {
    
}

