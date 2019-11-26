/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dao.springdata;

import java.util.List;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gallenc
 */
@Repository
public interface AppointmentDAOSpringData extends JpaRepository<Appointment, Long> {

    // note in this case we are using the spring method name to define the query !!!
    public List<Appointment> findByPersonA(Person personA);

    public List<Appointment> findByPersonB(Person personB);

}
