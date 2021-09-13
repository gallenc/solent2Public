
# Credit Card Test

So far we have looked only at a simple maven project which contains a single module.
In real projects, we often need to build underlying services or libraries which our web application can import.
In this exercise we will begin looking at a more complex multi-module maven project where we create a library which are used by our web application.

We want to use a library which can validate a credit card number.  
The library can be used in point of sales terminals and in shopping cart web sites to pre check credit cards before forwarding a transaction request to a bank.

## Credit Card fields
Credit cards have a number of fields
* Card number (PAN)
* Expiry Date (MMYY)
* Name
* CVV ( 3 or 4 digit number printed on card as additional check)

## Credit Card Numbers
The structure of all industry standard credit cards are described here
https://en.wikipedia.org/wiki/Payment_card_number

The main credit card number, also called a primary account number (PAN) consists of:
* A six-digit Issuer Identification Number (IIN), the first digit of which is the major industry identifier (MII).  (This is also called bank identification numbers if the card is a bank card)
* A variable length (up to 12 digits)  primary account number (PAN) (also called an individual account identifier)
* A single check digit calculated using the Luhn algorithm
Ranges of Issuer Identification Numbers are associate with the major credit card systems, Visa, Mastercard etc. 

Individual banks are allocated specific IIN/BIN numbers. Some examples are provided below

* List of IIN numbers https://stevemorse.org/ssn/List_of_Bank_Identification_Numbers.html
* Bank of Scotland BIN numbers https://www.creditcardvalidator.org/bank-of-scotland

We need to build an app which will check the luhn code and also find the issuer of a credit card. 

## Credit card validator

Two projects have been provided, 
[creditcardchecker](../creditcardtest/creditcardchecker ) is for you to use in this exercise and
[creditcardchecker-answer](../creditcardtest/creditcardchecker-answer ) contains an answer. 
Try the exercises before you look at the answer package. 

### 1. copy this session3 and build the project
copy this session3 into your myPracticeCourseWork folder and build the project using the maven command line.
```
cd myPracticeCourseWork/session3/creditcardtest/creditcardchecker

mvn clean install
```
After a few minutes, you should see 
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

### 2. Import into Netbeans
Now import [creditcardchecker](../creditcardtest/creditcardchecker ) into Netbeans

To do this 
1. Select File > Import Project which will open the import project dialog. 
2. Navigate to your copy at myPracticeCourseWork/session3/creditcardchecker
3. Make sure you select 'open required projects'

![alt text](../creditcardtest/images/importccproject1.jpg "Figure importccproject1.jpg" )

This should open 3 'modules' in netbeans; the parent project 'credicardchecker' and the two sub projects 'card-checker' and creditcardchecker-web.
The image below shows the projects and the files view of the project.

![alt text](../creditcardtest/images/multimodule1.png "Figure multimodule1.png" )

### 3. Study at the project you have imported

Maven multi module projects are organised hierarchically with a parent project and nested sub projects. 

The parent creditcardchecker pom.xml contains a list of the order in which sub-projects or 'modules' should be built.
Because this project simply calls the build of its children and has no output it's packaging is pom

```
    <groupId>org.solent.ood</groupId>
    <artifactId>creditcardchecker</artifactId>
    <version>0.1-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>card-checker</module>
        <module>web</module>
    </modules>
```
The sub modules inherit but can override the settings from the parent project

card-checker is a simple library for checking the Luhn code of cards.
It was mostly written by a third party on Github and packaged in this project by your tutor. 

It's pom describes it's parent and also that the output is a jar.
```
    <parent>
        <groupId>org.solent.ood</groupId>
        <artifactId>creditcardchecker</artifactId>
        <version>0.1-SNAPSHOT</version>
    </parent> 

    <modelVersion>4.0.0</modelVersion>
    <artifactId>card-checker</artifactId>
    <packaging>jar</packaging>

```

cardchecker-web is a simple web app which is packaged as a war

One of it's dependencies is the card-checker library which must be built before the web app is built.
Note that the <version>${project.version}</version> makes sure we pull in the library with the same 0.1-SNAPSHOT project version
```
    <parent>
        <groupId>org.solent.ood</groupId>
        <artifactId>creditcardchecker</artifactId>
        <version>0.1-SNAPSHOT</version>
    </parent>

    <artifactId>creditcardchecker-web</artifactId>
    <packaging>war</packaging>

 ...
    
    <dependencies>

        <dependency>
            <groupId>org.solent.ood</groupId>
            <artifactId>card-checker</artifactId>
            <version>${project.version}</version>
        </dependency>
        ...
     <dependencies>
```

### 4. examine the card-checker
Look carefully at the source code and the tests in the card-checker.
You dont need to know everything about how this code works but you do need to know how to use it.
The [cardcheck tests](../creditcardtest//creditcardchecker/card-checker/src/test/java/solent/ac/uk/ood/examples/cardcheck/ ) should show you how to use the library in your own code to check a credit card number.

### 5. Create a JSP to check a card
Now try to use this little library in a jsp which checks a credit card number. 
You simply need to use a form for filling in the number, 
a button to submit and provide user feedback on the validity of the card number.

An example JSP output is shown below

![alt text](../creditcardtest/images/validcard.png "Figure validcard.png" )

## Congratulations

Once you have completed this exercise, you will understand the basics of multi module builds and how to use a library in your web application.

Now you can go on and try the [ReST API Exercises based on this project](../creditcardtest/ReST_Exercise1.MD ) exercise





