/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dao.springdata;

import java.util.List;
import org.solent.com504.project.model.dto.Actor;
import org.solent.com504.project.model.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gallenc
 */
@Repository
public interface ActorDAOSpringData extends JpaRepository<Actor, Long> {

    @Query("select p from Actor p where p.role = :role")
    public List<Actor> findByRole(@Param("role") Role role);

    @Query("select p from Actor p where p.firstName = :firstName and p.secondName = :secondName")
    public List<Actor> findByName(@Param("firstName") String firstName, @Param("secondName") String secondName);

}
