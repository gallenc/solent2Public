package org.solent.com504.project.model.dao;

import java.util.List;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;

public interface AppointmentDAO {

    public Appointment findById(Long id);

    public Appointment save(Appointment appointment);

    public List<Appointment> findAll();

    public Appointment delete(Appointment appointment);

    public void deleteById(Long id);

    public void deleteAll();

    public List<Appointment> findByPersonA(Person personA);

    public List<Appointment> findByPersonB(Person personB);

    public List<Appointment> findByDate(Integer year, Integer month, Integer hour, Integer minutes);
}
