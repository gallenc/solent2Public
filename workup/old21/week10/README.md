# Template Ticket Machine project  - revised

This 1.3 version of the template project has the following features

1. removed all old com406 code not needed

2. added JAXB based daos to load station and charging details

3. added JPA based DAOs to persist Stations and Ticket Machines in controller

4. added jsp's in client and controller to display stations 

5. updated the UML claoss idagram to reflect new classes


you will need to

* write out some clear usecases to understand what you need to implement above hat has been done for you
* look at the tests to see how the charging DAO's are used
* create station controller JSP's to modify stations and add gates to stations
* complete ReST code to generate stationconfig

* create ticket machine code to generate and for a gate to validate a ticket 
* create code for the ticket machine to load it's config - you could use a button on a JSP to force a reload or use a timer to regularly update configuration.

