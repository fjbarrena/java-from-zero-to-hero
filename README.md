# Tecnogochis!
Like tamagochis but without copyright (and a bit more shabby :D)

This is a funny 'use case' for the course 'Java: From Zero to Hero' ;)

## Run

First, create an schema named 'technogochi' in your local MySQL database

Then, change the persistence.xml file and change the user and password values to yours

Then, do in technogochi-spring-solution:

```
mvn clean install
```

Finally, copy the .war inside the webapps folder of a tomcat 7/8 server and enjoy at http://localhost:8080/tecnogochi-spring/ (or the port you're using)