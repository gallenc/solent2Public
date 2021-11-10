# JSON File DAO implementation

This module implements the AnimalDao using the Jackson library to serialise jsva objects as Json files.

If you build the dao-json module, you will see that the tests persist the list of animals as a file in the target directory

```
/target/testDaoFile.json

{
  "animals" : [ {
    "id" : 1,
    "name" : "name_1",
    "address" : "address_1",
    "animalType" : {
      "sound" : "woof",
      "type" : "Dog"
    }
  }, {
    "id" : 2,
    "name" : "name_2",
    "address" : "address_2",
    "animalType" : {
      "sound" : "meow",
      "type" : "Cat"
    }
  }, {
    "id" : 3,
    "name" : "name_3",
    "address" : "address_3",
    "animalType" : {
      "sound" : "moo",
      "type" : "Cow"
    }
  } ],
  "currentMaxId" : 3
}
```

The Jackson project is documented here https://github.com/FasterXML/jackson-docs

For a simple tutorial on Jackson see https://www.baeldung.com/jackson-object-mapper-tutorial


