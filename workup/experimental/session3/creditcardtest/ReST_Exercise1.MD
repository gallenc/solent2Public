# Representational State Transfer ReST API exercise

## Introduction

You have created a simple JSP which responds to a request to validate a credit card. 
Now we are going to extend this to create an Application Programming Interface which will allow another program to ask for a credit card validation.

The [ReST architectural style](https://en.wikipedia.org/wiki/Representational_state_transfer) was first proposed in  2000 by Roy Fielding in his Phd thesis.
ReST stands for Representational State Transfer and it essentially requires that the http verbs GET, POST, PUT, DELETE etc are applied to change the state of resources on ReST end point.
A ReST end point is simply a url but instead of pointing to a html web page, it points to a set of resources which are usually represented as XML or Json.

Although most API's dont strictly follow the ReST model, the use of GET and POST to send XML or Json messages is very widely used.
HTTP and its varients are the protocols most widely used on the internet. 
This means that any interface based on http is likely to be able to traverse a firewall cleanly. 
For this reason interfaces designed using ReST principles and which use http as the transport protocol have become very popular.

In this exercise we will be using the [Jersey 2.0 library](https://eclipse-ee4j.github.io/jersey/) which is an implementation of the java [JAX-RS JSR370](https://jcp.org/en/jsr/detail?id=370) standard for coding ReST interfaces. 

Jersey can run within a web container as a servelet and can easily sit along side the JSPs in the war file we have already created in Tomcat.

We are going to create two end points one using GET and one using POST

## GET example

The first end point will simply respond to a GET request with a url parameter containing the card number e.g.
```
GET http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard?cardNo=4444444444444448
Headers
Accept : application/json
```
It will return a reply containing a CardValidationResult encoded as javascript object notation (json)
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

The second end point will use the same RL but in this case we will POST a json representation of a CreditCard 
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







## Congratulations

Once you have completed this exercise, you will understand the basics of multi module builds and how to use a library in your web application.

Now you can go on and try the ReST API Exercises based on this project
You can now go on and try the  [shoppingcart](../../session3/shoppingcart ) exercise