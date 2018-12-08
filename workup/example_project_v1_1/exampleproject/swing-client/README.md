# Swing Client
This project contains an example swing client which contacts the ReST service to get its data. 
The data retrieved is stored locally and can be searched on the display.

![alt text](../swing-client/images/SwingClient.png "Figure SwingClient.png")

## Building and Running

The client can be run directly from Netbeans by running the MainApp.java class


To run the client standalone use:
```
mvn clean install
cd target
java -jar exampleproject-swing-client-0.2-SNAPSHOT.one-jar.jar

```

You can also supply a local properties file 
```
java -jar exampleproject-swing-client-0.2-SNAPSHOT.one-jar.jar -p <path to config.properties>
```
config.properties is a location reference to a properties file to be loaded at startup. 
This can be placed in the same folder as the one-jar

The following contents should be in the properties file

```
# config.properties configuration file for Entity app

# the url of the data service to which this client must connect
baseUrl=http://localhost:8680/

# if schedule enabled = true the data service is contacted on a schedule 
# defined in the following properties
scheduleEnabled=false

# if scheduleEnabled= true, scheduleIntervalSeconds determines how
# often the client attempts to reload its schedule
scheduleIntervalSeconds=125

# if the client failed to contact the service, it will retry a number of times
# set by retryAttempts times before waiting scheduleIntervalSeconds to try again
retryAttempts=5

# if a retry attempt fails, the system will wait retryIntervalSeconds between retrys
retryIntervalSeconds=11

```


