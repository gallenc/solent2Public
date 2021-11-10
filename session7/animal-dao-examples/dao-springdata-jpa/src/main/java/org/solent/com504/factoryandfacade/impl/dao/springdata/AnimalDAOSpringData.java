/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.springdata;

import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * see  https://www.logicbig.com/tutorials/spring-framework/spring-data/query-by-example.html
 * @author gallenc
 */
@Repository
public interface AnimalDAOSpringData extends JpaRepository<Animal, Long>, QueryByExampleExecutor<Animal>  {

}

