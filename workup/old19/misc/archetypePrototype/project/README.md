
# project with DAO's based on spring MVC, jpa and springdata jpa with Hibernate and HSQLDB

## Comparing time objects and searching by time

Note that the model has now been changed so that you can use a 'java.util.Date startDate' in each appointment

You can compare dates if you use startDate in the model. See example test and DAO below

see the model class

[Appointment.java](../project/model/src/main/java/org/solent/com504/project/model/dto/Appointment.java )
```
...
    private Date startDate  = new Date();  // appointment initialised with date

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

```

The Dao has a new method to compare dates

[AppointmentDAOJpaImpl.java](../project/dao-jpa/src/main/java/org/solent/com504/project/impl/dao/jpa/AppointmentDAOJpaImpl.java )
```
   @Override
    public List<Appointment> findBetweenDates(Date startDate, Date endDate) {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.startDate BETWEEN :startDate AND :endDate", Appointment.class);
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;

    }
```

And the test shows you how to use it

[AppointmentDAOTest.java](../project/dao-jpa/src/test/java/org/solent/com504/project/impl/dao/jpa/test/AppointmentDAOTest.java )
```
   @Test
    public void findBetweenDatesTest() {
        LOG.debug("start of findBetweenDatesTest(");

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
```

