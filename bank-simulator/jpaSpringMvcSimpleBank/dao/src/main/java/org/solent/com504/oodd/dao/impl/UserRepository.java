/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.dao.impl;

import org.solent.com504.oodd.user.model.dao.UserDAO;
import org.solent.com504.oodd.user.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cgallen
 */
@Repository
public interface UserRepository extends UserDAO,  JpaRepository<User,Long>{
    
}
