# Week 5 - Using JAXB for model persistance

# Logging
One of the important wasys in whc you can check that your program is running correctly is logging error and debug messages. 
This is often done using a logging framework - a library designed to help with the task.
Going forwards I will be using Log4j 2 which is a widely used library.
Logging is implemented using a static factory which is involked to create a logger for each class.
Once you have a logger for the class, you an log mesages with different severities, error, info, debug, trace 
```
public class RestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.factoryandfacade.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(RestService.class);
    
    ...
    LOG.debug("this is a debug message");
    LOG.error("this is a error message");
    
```
The output is determined by a log4j2 config file on the class path which is placed in the /src/main/resources folder
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO">
    <!-- Logging Properties -->
    <Properties>
        <!-- this sets the output style properties for the log messages -->
        <Property name="LOG_PATTERN">%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n</Property>
        <!--<Property name="APP_LOG_ROOT" >target</Property>-->
        
        <!-- this creates log files in the catalina base directory -->
        <!-- e.g. in netbeans C:\Users\<your username>\AppData\Roaming\NetBeans\8.2\apache-tomcat-8.0.27.0_base -->
        <Property name="APP_LOG_ROOT" >${sys:catalina.base}/logs/app</Property>
    </Properties>
    <Appenders>
        
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${LOG_PATTERN}"/>
        </Console>
        
        <!-- a roling file appender limits the size of log files -->
        <RollingFile name="appLog" fileName="${APP_LOG_ROOT}/app-perf.log"
                     filePattern="${APP_LOG_ROOT}/app-perf-%d{yyyy-MM-dd}-%i.log" >
            <PatternLayout pattern="${LOG_PATTERN}"/>
            <Policies>
                <SizeBasedTriggeringPolicy size="19500KB" />
            </Policies>
            <DefaultRolloverStrategy max="1"/>
        </RollingFile>
        
    </Appenders>
    <Loggers>
        
        <!-- The name org.solent specifies that log names beginning with this string are dealt with by the following appenders -->
        <!-- i.e. in our config, classes in package names beginnign org.solent -->
        <Logger name="org.solent"  additivity="false" level="DEBUG">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="appLog"/>
        </Logger>

        <!-- this logs to  the Consol (System.out) -->
        <Root level="DEBUG">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```




# Exercises for week 4 Using JAXB for model persistance

## Set up
1. remember to merge your project with the upstream project
2. create a new 'week4' folder under your myPracticeCouseWork folder.
3. copy the contents of week4 into this folder and modify or add to the code here as needed. (NB only change any code in myPracticeCouseWork).

## Exercise 1 webfacadeexample2

In this exercise you will look at how the facade and factory you created last week can be used as the back end of a web site. 

Try the  [webfacadeexample2](../week4/webfacadeexample2) exercises.

