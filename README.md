# MONEY
Money transfer between accounts REST API

This is a [Spring Boot](http://projects.spring.io/spring-boot/) application.
It exposes two APIs (accounts and transfer).
It helps the user to list the accounts and make money transfer between accounts.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 5](https://gradle.org/)

## Running the application locally

You may download the jar file build/libs/money-1.0-SNAPSHOT.jar and run the command;
```shell
java -jar money-1.0-SNAPSHOT.jar
```
If you download the source code to your local computer, there are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `uk.co.incircity.Application` class from your IDE.

Alternatively you can use the [Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html#using-boot-running-with-the-gradle-plugin) like so:

```shell
gradle bootRun
```

Default port is 8080.

## Building the application and run the unit tests locally

```shell
gradle clean build
```

## API Details
- Name: List Accounts<br/>
  Description: This end point prints the accounts list in JSON format.<br/>
  Http Method: GET<br/>
  URL: localhost:8080/accounts<br/>

- Name: Make Money Transfer<br/>
  Description: This end ponit perform some checks to make sure whether the transfer is same the make the transfer.<br/>
  Http Method: POST<br/>
  URL: localhost:8080/transfer<br/>
  Content-Type: application/json<br/>
  Request Body Example: 
```shell
{
	"fromAccountNumber":50002,
	"toAccountNumber":50004,
	"amount":5110.50
}
```

## In Memory Test Data:
You've got two repository already created and stored in memory when start up. 

Account Repository:
```shell
Account Number | Owner Id  | Balance       | Currency
=====================================================
50001            1001        10000.00        GBP
50002            1002        10000.00        GBP
50003            1003        10000.00        GBP
50004            1004        10000.00        GBP
50005            1005        10000.00        USD
```

User Repository: 
```shell
User Id  |  Name
=============================
1001        Darlene J Halter
1002        Leroy Shaw
1003        Mario Burkett
1004        Sharon Turner
1005        Carol M Bickford
```
