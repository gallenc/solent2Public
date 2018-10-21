## Week 5 - Creating a Web UI using JSP

This week we will introduce Java Server Pages and show how they can be used to create a web UI.
To do this we will add a web ui to the doorlock project we worked on as a TCA last week.

# Exercise 1 - try running a JSP in jetty

move week5 code into your myPracticeCourseWork folder.

You will see that there is a new module called lock-web

use mvn clean install on the parent project to build the whole project

now cd to lock-web and run

'mvn jetty:run'

you will see output suggesting that a jetty web server is downloading and starting up

In a browser navigate to http://localhost:8680/

The jetty web server will serve up index.html which is a welcome page

Click on 'To generate a key card click here' to open a new tab which is rendering the java server page ReceptionPage.jsp

Try generating and reading a card using the forms on this page.

# Exercise 2

Using ReceptionPage.jsp as an example, try to add JSP code to the DoorLockPage.jsp to allow you to set the door number and open the door.