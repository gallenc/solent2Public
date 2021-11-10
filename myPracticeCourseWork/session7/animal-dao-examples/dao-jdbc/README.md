# JDBC based DAO 

This file implements the AnimalDAO using JDBC Java Database Connection

In this cases an embedded SQL database called apache derby http://db.apache.org/derby/ is being used to save the data.
You will see the derby database data is created in the /target/tmp directory

JDBC is the core interface on which java depends to connect to SQL databases. 
You can see a tutorial on JDBC here https://www.baeldung.com/java-jdbc


In this example 

[ConnectionFactory.java](../animal-dao-examples/dao-jdbc/src/main/java/org/solent/com504/factoryandfacade/impl/dao/jdbc/ConnectionFactory.java)
Creates a connection to the database.

[AnimalDaoJdbcImpl.java](../animal-dao-examples/dao-jdbc/src/main/java/org/solent/com504/factoryandfacade/impl/dao/jdbc/AnimalDaoJdbcImpl.java)
 implements the DAO.
 
It first creates the database tables and then provides methods to access them.





