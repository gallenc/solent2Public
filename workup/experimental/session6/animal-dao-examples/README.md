
# Project using Spring MVC  and SpringDATA

This version of the project is begining to introduce SpringData as a framework on top of JPA.

The project now has 4 seperate DAO's which can work with the model
```
dao-jaxb
dao-jdbc
dao-jpa
dao-springdata-jpa
```
We now introduce dao-springdata-jpa and this is wired into the higher layers. (dao-jaxb,dao-jdbc and dao-jpa are still built but not used as depencencies in the service layer)

We looked at stand alone SpringData dao's in week10.
This project simply uses the technology previously introduced within the service layer.
Eclipselink is the JPA provider and Derby is used as the embedded database.

The SpringMVC code introduced earlier still coexists with the original JSP's and this requires that we still use factories throughout.

This project illustates a partial migration to Spring where elements of the legacy code still remain but are integrated with the new code using Spring.

## running the application
As usual, build the whole project using 
```
mvn clean install
```
Then open the project and all its subprojects in Netbeans and run both the webfacadeexample2-web and webfacadeexample2-web-client projects in the embedded tomcat server.

## exercise

Run the project in netbeans and see that it works similarly to the previous version

compare this project with the previous version in Week9













