# simple-spring-webapp-monolith

Pre-requisites:
1. Java 17 installed

Intent - Demonstrate a simple spring web application with embedded tomcat

<h4>How to execute the App?</h4>

Option 1 - run the Java class AppLauncher<br/>
Option 2 - Bundle the Jar file and execute it - java -jar simple-spring-webapp-0.0.1-SNAPSHOT.jar<br/>

<h4>How to verfiy?</h4>
Open the browser and hit the URL - http://localhost:8080/

<h4>About App</h4>
This web application provides 2 functionalities
a. user management - user registration, login/logout and user listing <br/>
b. invoice management - search/create the invoice for the user and download the invoice copy(pdf format)<br/>

<h4>Tech stack</h4>
Java 17, spring mvc, spring security, thymleaf, h2 database, JPA, hibernate-validator, flying-saucer library for pdf generation ( check pom.xml for more details)

This application stores invoice copies in ${user.home}/invoices directory on the local machine.
h2 database is created in ${user.home} directory with name as myFirstH2Database

<h4>Connnect to H2?</h4>
Incase, you want to connect to h2 database thoroug UI interface, go to the h2 jar location, typical location can be - $[user.home}/.m2/repository/com/h2database/h2/1.4.200. run the command to explore the h2 options -> java -cp h2-1.4.200.jar org.h2.tools.Server -help

To start web console run the command -> java -cp h2-1.4.200.jar org.h2.tools.Server -web

open the browser and hit the url -> http://localhost:8082, this will open up the login window like below 
![image](https://user-images.githubusercontent.com/93635967/224973449-38da70b4-8103-422d-84a0-44dc5fc8f254.png)

password - sa


