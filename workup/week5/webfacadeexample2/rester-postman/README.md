# Rester app

Tis folder contains configuration files for Rester

## chrome plugin
https://chrome.google.com/webstore/detail/rester/eejfoncpjfgmeleakejdcanedmefagga?hl=en

## firefox plugin
https://addons.mozilla.org/en-US/firefox/addon/rester/


## Web Services 

Web Services are API's implemented using HTTP which are intended for machine to machine communications. 

Representational State Transfer [ReST](https://en.wikipedia.org/wiki/Representational_state_transfer)  is a style of web service interface which uses HTTP Get, Post and Update operations to implement an API which transfers messages about resource objects using either XML or Json payloads.

Strictly  speaking most ReST interfaces (and indeed the ones we will make) do not completely following the ReST pattern for describing resources but they do use the HTTP commands with XML or Json messages.
The advantage of the ReST style is that is is relatively simple to implement and test and it will work through most firewalls.

The following diagram illustrates how we can extend our web application server to offer web services to stand alone clients.

![alt text](../week6/drawio/JerseyRest.png "Figure JerseyRest.png")


## ReST and the Jersey library

Java provides a standard for implementing ReST interfaces called [JSR-339 JavaTM API for RESTful Web Services or more simply JAX-RS](https://download.oracle.com/otndocs/jcp/jaxrs-2_0-fr-eval-spec/index.html)
The most commonly used reference implementation of JAX-RS is [Jersey](https://jersey.github.io/) and we will use this library in our examples.

Lots of tutorials are available on line both on Jersey and ReST; for example see [Vogella's Jersey tutorial](http://www.vogella.com/tutorials/REST/article.html).
However as usual, we only need a subset of the standard to do what we need to do and the examples we will use are sufficient for you to understand what is going on. 

## JAXB and Jackson

XML is a standard for passing or saving information between systems as a machine readable documents. 
XML is very like HTML but has a much more precise definition. 
Since our ReST messages are going to be in XML, we need a library which can convert java objects into XML (called marshalling) or convert XML documents into java objects (called unmarshalling). 

The standard for manipulating XML in Java is called the Java Architecture for XML Binding or [JAXB](https://docs.oracle.com/javase/8/docs/technotes/guides/xml/jaxb/index.html).
JAXB is included with the JDK and is used by Jersey to create messages for ReST services. 

In addition to JAXB, if we include the Jackson library on our class path it allows Jersey to also marshal and unmarshal JAXB XML objects as Json messages.

