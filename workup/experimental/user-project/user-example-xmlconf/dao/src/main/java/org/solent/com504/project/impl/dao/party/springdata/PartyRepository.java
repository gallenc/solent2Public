/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.party.springdata;

import java.util.List;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gallenc
 */
@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    
    @Query("select p from Party p LEFT JOIN FETCH p.users where p.partyRole = :partyRole")
    public List<Party> findByPartyRole(@Param("partyRole") PartyRole partyRole);

    @Query("select p from Party p LEFT JOIN FETCH p.users where p.firstName = :firstName and p.secondName = :secondName")
    public List<Party> findByName(@Param("firstName") String firstName, @Param("secondName") String secondName);
    
    @Query("select p from Party p LEFT JOIN FETCH p.users where p.uuid = :uuid")
    public List<Party> findByUuid(@Param("uuid") String uuid);
    
    @Query("select p from Party p LEFT JOIN FETCH p.users")
    public List<Party> findAll();
    
}
