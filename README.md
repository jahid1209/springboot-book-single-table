#### Guide

* This REST API based project is built with spring boot version 2.4.0 Java Runtime Environment 8 is 
required to run the application as well.
* The API respnses back some data associated with book in JSON array. 
#### Prerequisite
* jdk-1.8.0
* maven 3.6.3 (Optional. Requires, if you don't use mvnw/docker)
* docker (Optional. Requires, if run with docker)
* docker-compose (Optional. Requires, if run with docker-compose)

#### Build the application with Maven
It's a maven based application, To build the application following command need to be run from command line.

```
mvn package
```
or

```
mvnw package
```
Again you will need to have minimum JDK8 available at you PATH variable. 
If you don't have JDK8 installed please follow the docker build section 2.

#### Run the application

```aidl
java -jar target/book-api.jar
```
As it is a spring boot application, to run with spring-boot-maven plug-in
```aidl
./mvnw spring-boot:run
```

#### Build the Docker image
   
* S1.

To build a docker image with the package, that has been generated at the previous step following command is necessary from command line.

```aidl
docker build -t book-api .
```

* S2

#### [Multi-stage](https://docs.docker.com/develop/develop-images/multistage-build/) Docker build: (Everything using docker)

It's pretty helpful if there is no JDK is installed in the system and dependency is docker.
Command to run with docker-
```
docker pull  j1209/book-api.jar
```

**Run the Docker image**

To run the newly created image, the command is 
```
docker run -p 9090:8080 j1209/book-api.jar
```

**Make sure 9090 port is free**

#### Access the application from browser

Assuming the application is running on local machine, if so then the URL to access the swagger defination will be -

```aidl
http://localhost:9090/swagger-ui.html
```