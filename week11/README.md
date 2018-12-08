# Week 11 Starting your project using an archetype

Following from last week, the example project and maven archetype have now been extended to also generate a swing client similar to what you will need in your project.

You can choose to use swing or if you wish javaFX to create your client.

If you really arent confident with this, you also have the option to create a second web app which acts as a client in a seperate web container. (Running on a different port)

The key to doing all of this is the scheduling class which updates the local DAO from he ReST api on a regular basis.

An example of this is in the class EntityClientLoader.java

Both of these projects can be found here:

[week11/example_project_v1_1](../week11/example_project_v1_1)