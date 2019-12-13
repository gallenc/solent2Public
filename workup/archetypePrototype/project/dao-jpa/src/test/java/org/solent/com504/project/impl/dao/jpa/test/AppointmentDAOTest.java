/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.jpa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dto.Appointment;

/**
 *
 * @author cgallen
 */
public class AppointmentDAOTest {

    final static Logger LOG = LogManager.getLogger(AppointmentDAOTest.class);

    private AppointmentDAO appointmentDao = null;

    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();

    @Before
    public void before() {
        appointmentDao = daoFactory.getAppointmentDAO();
        assertNotNull(appointmentDao);

        // delete all appointments before each test
        appointmentDao.deleteAll();

    }

    @Test
    public void createAppointmentDAOTest() {
        LOG.debug("start of createAppointmentDAOTest(");
        // this test simply runs the before method
        LOG.debug("end of createAppointmentDAOTest(");
    }

    @Test
    public void findBetweenDatesTest() {
        LOG.debug("start of findBetweenDates(");

        // e.g. (2009-12-31 23:59)
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date startDate = null;
        Date endDate = null;

        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        Appointment appointment3 = new Appointment();
        
        try {

            appointment1.setStartDate(format.parse("2020-12-31 14:59"));
            appointment1.setDurationMinutes(30);
            appointment2.setStartDate(format.parse("2020-12-31 15:59"));
            appointment2.setDurationMinutes(30);
            appointment3.setStartDate(format.parse("2020-11-30 15:59"));
            appointment3.setDurationMinutes(30);

            startDate = format.parse("2020-12-31 13:59");
            endDate = format.parse("2020-12-31 23:59");
            LOG.debug("start date = " + format.format(startDate));
            LOG.debug("end date = " + format.format(startDate));
        } catch (ParseException ex) {
            LOG.error("problem parsing date", ex);
        }

        appointmentDao.save(appointment1);
        appointmentDao.save(appointment2);

        List<Appointment> appointments = appointmentDao.findBetweenDates(startDate, endDate);
        assertEquals(2,appointments.size());
        LOG.debug("found appointments " + appointments.size());

        for (Appointment appointment : appointments) {
            LOG.debug(" " + appointment);
        }

        LOG.debug("end of findBetweenDates");
    }

}
