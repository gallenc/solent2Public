
# Simple JPA Project

This model for this JPA project can be seen in the [UML Model](../jpaExample1/UMLmodel) 

## Exercise  jpaExample1

This simple project shows two JPA DAO's, one for a Person object and one for an Appointment object.

1. complete the DAO implementations and tests (if they are not already completed).

You should be able to see how to do this by looking at the [JPA dao code](../webfacadeexample2/dao-jpa) in the [webfacadeexample2](../webfacadeexample2) project.

2. complete a use case and robustness diagram for the following scenario. 

A doctors surgery has a number of doctors and patients. 
The receptionist needs to be able to add, delete and modify patients in the system
She also needs need to be able to book modify and cancel appointments with particular doctors for particular patients.

Given the two Patient and Appointment DAO's you now have, what methods should a serviceFacade provide to enable the upper layers?

3. The model classes have some JAXB annotations.
Write a test class for the model to ensure objects can be marshalled and unmarshalled using JAXB

You can see an example of this here
[ModelJaxbTest.java](../animal-dao-examples/model/src/test/java/org/solent/com504/factoryandfacade/test/dto/jaxb/ModelJaxbTest.java) in the in the [animal-dao-examples](../animal-dao-examples) project

Remember you will need a jaxb.index file in each package.


