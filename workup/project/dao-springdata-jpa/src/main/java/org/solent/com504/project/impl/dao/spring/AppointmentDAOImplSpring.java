/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.spring;

import java.util.List;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.springdata.AppointmentDAOSpringData;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gallenc
 */
@Component
public class AppointmentDAOImplSpring implements AppointmentDAO {

    @Autowired
    private AppointmentDAOSpringData appointmentDAOspring = null;

    @Override
    public Appointment findById(Long id) {
        return appointmentDAOspring.getOne(id);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentDAOspring.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentDAOspring.findAll();
    }

    @Override
    public Appointment delete(Appointment appointment) {
        appointmentDAOspring.delete(appointment);
        return null; // TODO RETURN TYPE SHOULD BE NULL IN INTERFACE
    }

    @Override
    public void deleteById(Long id) {
        appointmentDAOspring.deleteById(id);
    }

    @Override
    public void deleteAll() {
        appointmentDAOspring.deleteAll();
    }

    @Override
    public List<Appointment> findByPersonA(Person personA) {
        return appointmentDAOspring.findByPersonA(personA);
    }

    @Override
    public List<Appointment> findByPersonB(Person personB) {
        return appointmentDAOspring.findByPersonB(personB);
    }

    @Override
    public List<Appointment> findByDate(Integer year, Integer month, Integer hour, Integer minutes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
