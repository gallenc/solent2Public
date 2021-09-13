# Representational State Transfer ReST API exercise

## Introduction

You have created a simple JSP which responds to a request to validate a credit card. 
Now we are going to extend this to create an Application Programming Interface which will allow another program to ask for a credit card validation.

The [ReST architectural style](https://en.wikipedia.org/wiki/Representational_state_transfer) was first proposed in  2000 by Roy Fielding in his Phd thesis.
ReST stands for Representational State Transfer and it essentially requires that the http verbs GET, POST, PUT, DELETE etc are applied to change the state of resources on a ReST end point.
A ReST end point is simply a url but instead of pointing to a html web page, it points to a set of resources which represent a resource in your program. 
These are usually rendered as an XML or Json document.

Although most API's dont strictly follow the ReST model, the use of GET and POST to send XML or Json messages is very widely used.
HTTP and its varients are the protocols most widely found on the internet. 
This means that any interface based on HTTP is likely to be able to traverse a company firewall cleanly. 
For this reason interfaces designed using ReST principles and which use http as the transport protocol have become very popular.

In this exercise we will be using the [Jersey 2.0 library](https://eclipse-ee4j.github.io/jersey/) which is an implementation of the java [JAX-RS JSR370](https://jcp.org/en/jsr/detail?id=370) standard for coding ReST interfaces. 

Jersey can run within a web container as a servelet and can easily sit along side the JSPs in the war file we have already created in Tomcat.

We are going to create two end points one using GET and one using POST. 

## GET example

The first end point will simply respond to a GET request with a url parameter containing the card number e.g.
```
GET http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard?cardNo=4444444444444448
Headers
Accept : application/json
```
It will return a reply message containing a CardValidationResult encoded as javascript object notation (json)
```
{
    "message": null,
    "code": 200,
    "cardValidationResult": {
        "valid": true,
        "cardType": "VISA",
        "error": null,
        "cardNo": "4444444444444448",
        "message": "4444444444444448    >>    card: VISA"
    }
}
```

## POST example

The second end point will use the same URL but in this case we will POST a json representation of a CreditCard.
This will return the same format of reply message as shown above 
```
POST http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard
Headers
Content-type : application/json
Accept : application/json
body:
{
    "name": "test user1",
    "endDate": "11/21",
    "cardnumber": "5133880000000012",
    "cvv": "123",
    "issueNumber": "01"
}
```

## Creating a ReST APP with Jersey

Some code has been provided as a starting point which you need to extend to complete the exercise.

When a Jersey ReST application is run in a web container like tomcat, it automatically registers itself as a servelet within the container.

The starting point for a jersey application is a class extending ResourceConfig which implements javax.ws.rs.core.Application interface. 
This is as illustrated in the [RestApp.java](../creditcardtest/creditcardchecker/web/src/main/java/org/solent/ood/creditcardchecker/rest/RestApp.java) class below.

```
package org.solent.ood.creditcardchecker.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/rest")
public class RestApp extends ResourceConfig {
    public RestApp() {
        packages("org.solent.ood.creditcardchecker.rest");
    }
}

```
In this example, when the RestApp class is discovered by Tomcat, Tomcat reads the annotation @ApplicationPath("/rest") and registers the ReST application such that all URL's beginning /rest are forwarded to this ReST application.

In this example, when the RestApp class is started, jersey scans all of the classes found in the package "org.solent.ood.creditcardchecker.rest" for Rest Service implementation classes. 


Now look at the [RestService.java](../creditcardtest/creditcardchecker/web/src/main/java/org/solent/ood/creditcardchecker/rest/RestService.java) class which is scanned within the "org.solent.ood.creditcardchecker.rest" package. 

This class is annotated with @Path("/api-v1") which tells jersey the next segment of the url.

The first method will simply return a text message "Hello, rest! in response to the url http://localhost:8080/creditcardchecker-web/rest/api-v1

```
@Path("/api-v1")
public class RestService {
    final static Logger LOG = LogManager.getLogger(RestService.class);

    @GET
    public String test() {
        LOG.debug("rest test called");
        return "Hello, rest!";
    }
```

The second method is more useful and implements the GET request discussed above.
Here we are going to validate a card number supplied as a url paramater.

Note the annotations in the start of the method

@GET tells jersey that this method should only respond to a GET request.

@Path("/validateCard") gives the next url segment which will call this method i.e. http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard

@Produces({MediaType.APPLICATION_JSON}) sets the html reply headers to tell the client that the response is json.

@QueryParam("cardNo") String cardNo will extract the url paramater ?cardNo=4444444444444448 into the String cardNo

```
   /**
     * http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard?cardNo=4444444444444448
     * @return 
     */
    @GET
    @Path("/validateCard")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getValidateCard(@QueryParam("cardNo") String cardNo) {
        try {
            LOG.debug("GET /validateCard called cardNo:" + cardNo);
            ReplyMessage replyMessage = new ReplyMessage();
            CardValidationResult result = RegexCardValidator.isValid(cardNo);
            replyMessage.setCardValidationResult(result);
            if (result.isValid()) {
                replyMessage.setCode(Response.Status.OK.getStatusCode());
                return Response.status(Response.Status.OK).entity(replyMessage).build();
            } else {
                replyMessage.setCode(Response.Status.BAD_REQUEST.getStatusCode());
                return Response.status(Response.Status.BAD_REQUEST).entity(replyMessage).build();
            }

        } catch (Exception ex) {
            LOG.error("error calling GET /validateCard ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setMessage("error calling GET /validateCard " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }
```
The ReST method always replies with a Response object which contains the HTTP reponse code and possibly an entity class encoded as as Json.

You can see the possible http response codes here
https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

And their corresponding Response.Status constants are documented here
https://docs.oracle.com/javaee/7/api/index.html?javax/ws/rs/core/Response.Status.html

Within this example method you will see that a number of responses are possible all of which will be sent back with the ReplyMessage class as a data transfer object.
```
public class ReplyMessage {
    
    String message;
    
    int code;
    
    CardValidationResult cardValidationResult;

    // getters and setters ...
}
```
The body of the method is within a try-catch block. 
If an exception is thrown the catch block will respond with a ReplyMessage containing some error information and a http code indicating Internal Server Error (500).

Within the try block we check the card using the RegexCardValidator and depending on the result of the validation, we might send back a Response.Status.OK (http code 200) or Response.Status.BAD_REQUEST (400)
and also a ReplyMessage containing a CardValidationResult json object. 

You can run the web web application and use the url http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard?cardNo=4444444444444448 to test the app.
Try also putting in incorrect card numbers and see what you get.

## ReSTER app.
You can easily test GET operations using a browser url (which always uses GET).
To test POST operations, you will need to use a tool such as the Rester application which runs in firefox or crome. 
See the [example Rester configurations](../creditcardtest/creditcardchecker/rester/) 

## Exercise 1 Complete the POST service .
You have a partly completed method which implements the POST action.
 
Can you complete this method so that it validates the card and can you test it using Rester ?

Remember you will be sending the JSON credit card representation to the service in the body of the HTML request.

## Jersey ReST Client

Having created a ResT application, we now need to be able to access it programatically using a java ReST client. 
In this case we are using the Jersey Client library to impliment the client. 
You have been provided an example client but there are many tutorials on line to learn more if you wish.

The example code is in [CardCheckerClient.java](../creditcardtest/creditcardchecker/web/src/main/java/org/solent/ood/creditcardchecker/client/CardCheckerClient.java)

Tests for this code are in [CardCheckerClientTest.java](../creditcardtest/creditcardchecker/web/src/test/java/org/solent/ood/creditcardchecker/client/test/manual/CardCheckerClientTest.java)

### Jersey Client GET call
The GET method from this class corresponding to 

GET http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard?cardNo=4444444444444448

is is given below 

```
public boolean getCheckCard(String cardNo) {
        LOG.debug("GET getCheckCard called");

        // sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        // allows client to decode json
        client.register(JacksonJsonProvider.class);

        WebTarget webTarget = client.target(urlStr).path("/rest/api-v1/validateCard").queryParam("cardNo", cardNo);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        return replyMessage.getCardValidationResult().isValid();

    }
```
You will see some code to set up the logging in the client. 
This allows us to see the requests and responses received by the client as it is used.
```
// sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));
```
Jersey is using the [Jackson library](https://github.com/FasterXML/jackson) to serialise json and we tell the client to use jackson with the line
```
client.register(JacksonJsonProvider.class);
```
The following line sets up a WebTarget which reperesents the URL you are sending the ReST request to.
Note how we can use this client with differnt servers running this interface by changing the base urlStr 
```
 WebTarget webTarget = client.target(urlStr).path("/rest/api-v1/validateCard").queryParam("cardNo", cardNo);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
```
The query paramater is set using .queryParam("cardNo", cardNo) which becomes /rest/api-v1/validateCard?cardNo=xxx

The invocationBuilder allows us to set the request headers to tell the server we need a json reply.
```
       Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
```
Now we can make the GET call using
```
        Response response = invocationBuilder.get();
```
This call is synchronous, which means that we wait for the reply.

Finally, when we get a reply, we can de-serialise the response into the expected class i.e. ReplyMessage.
```
        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
```
If the response is not serialisable into ReplyMessage.class, the call will throw an exception which you will need to handle in your code.

### Jersey Client POST call

If you look at the second method, postCheckCard(CreditCard creditCard) you will see that in this case you actually post the json representation of the CreditCard using
```
        Response response = invocationBuilder.post(Entity.entity(creditCard, MediaType.APPLICATION_JSON));
```

## Exercise 1. Adding to POST test.
There is a test class to test this client 
[CardCheckerClientTest.java](../creditcardtest/creditcardchecker/web/src/test/java/org/solent/ood/creditcardchecker/client/test/manual/CardCheckerClientTest.java)

Note that in the [pom.xml](../creditcardtest/creditcardchecker/web/pom.xml)
, we have configured surefire so that the CardCheckerClientTest.java is not run as part of the build but only manually. 
This is because the test will only pass if the tomcat server is running.
```
           <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M3</version>
                <configuration>
                    <excludes>
                        <!-- prevent tests running in this package. These are manual one off development tests -->
                        <exclude>**/manual/*.java</exclude>
                    </excludes>
                </configuration>
            </plugin>
```
Start the web application and run this test manually. 
Only the first test method which tests the GET service will do anything.

Add code to the POST test in CardCheckerClientTest.java so that you can test the client posting requests to the server. 

## Congratulations

Once you have completed this exercise, you will understand the basics of multi module builds and how to use a library in your web application.

You will also understand how to create a client which makes calls against a ReST API
