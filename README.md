
# Software Core -Practical Project: Film & TV Tracker

A Film & TV tracking website for QA Academy built using springboot and tested using JUnit, Mockito, and Selenium.

This website allows you to perform CRUD functionality with a database of films and TV shows as well as add them to your personal watch list.

https://tasdiq.atlassian.net/jira/software/projects/FTT/boards/5

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Have the latest [Java SE Development Kit version 17 installed.](https://www.oracle.com/java/technologies/downloads/#java17)

Have the latest [Apache Maven version installed.](https://maven.apache.org/download.cgi)

### Installing

Step 1: Clone the repository to your local device.


#### Editing and Running in Eclipse

Open Film-TV-Tracker-Project in Eclipse as a Springboot project. Ensure the Java Build Path is set to JavaSE-17. Open application.properties and set profile to dev.

In Eclipse click Run As -> Spring Boot App

The terminal within Eclipse should show this

![image](https://user-images.githubusercontent.com/37335919/184318539-48c543db-29f9-4f12-9942-9575b61d05fe.png)

## Using the Film & TV Tracker Website

After starting the springboot app in Eclipse, on your web browser go to localhost:8081/index.html. This is the page you should see.

![image](https://user-images.githubusercontent.com/37335919/184319155-97a18acf-edbc-4675-8adb-51764f87eebd.png)

### Films page

On the navigation bar click on "Films" to go to the Films page

Upon loading the page it will automatically display all films stored in the database

![image](https://user-images.githubusercontent.com/37335919/184319627-6956b914-26f0-4de4-9cae-184ddaab9dfb.png)

Click on Add Film to bring up a pop up form to submit information on the new film you'd like to add.

![image](https://user-images.githubusercontent.com/37335919/184319806-aa895950-0594-4f4c-945a-bc1003e95783.png)


