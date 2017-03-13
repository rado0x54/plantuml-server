PlantUML Server 
===============
Master Branch: [![Build Status](https://travis-ci.org/rado0x54/plantuml-server.png?branch=master)](https://travis-ci.org/rado0x54/plantuml-server)

PlantUML Server is a web application to generate UML diagrams on-the-fly.

![](https://raw.githubusercontent.com/ftomassetti/plantuml-server/readme/screenshots/screenshot.png)
 
To know more about PlantUML, please visit http://plantuml.sourceforge.net/.

Requirements
============

 * jre/jdk 1.8.0 or above

How to run the server
=====================

Just run:

```
gradle appRun
```

The server is now listing to [http://localhost:8080/](http://localhost:8080/).
In this way the server is run on an embedded jetty (or tomcat) server. 


How to run the server with Docker
=================================

```
docker build -t plantuml .
docker run -d -p 8080:8080 plantuml
```

The server is now listing to [http://localhost:8080](http://localhost:8080).

You may specity the port in `-p` Docker command line argument.


How to generate the war
=======================

To build the war, just run:

```
gradle war
```

at the root directory of the project to produce plantuml.war in the `build/libs/` directory.
