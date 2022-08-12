
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

Click on Update Film to bring up a pop up form and provide a Film ID and attribute values for the film you'd like to update

![image](https://user-images.githubusercontent.com/37335919/184320854-022d58e5-848a-4df9-884e-e3a340240d6c.png)

Click on Delete Film to provide the ID of the Film you want to delete.

![image](https://user-images.githubusercontent.com/37335919/184320992-22e77f39-565f-47cd-ba1a-8ae4cb83a9f4.png)

Click on Search Film to search for films by name. 

![image](https://user-images.githubusercontent.com/37335919/184321128-4bcf1486-338e-48c5-b5a4-68167d531318.png)

Click on Filter Films to filter films by director, genre, or year of release.

![image](https://user-images.githubusercontent.com/37335919/184321345-3f852d13-b7a3-4089-8aac-efb4745aa053.png)

Click on Add Film to My List to provide a Film ID and status (Complete, Watching, Planning) of the film you want to add to your list.

![image](https://user-images.githubusercontent.com/37335919/184321516-06120e75-97ec-4dc3-b9dd-3dd69a208363.png)

### TV Shows

On the navigation bar click on "TV Shows" to go to the TV Shows page

Upon loading the page it will automatically display all TV shows stored in the database

![image](https://user-images.githubusercontent.com/37335919/184322540-9b7d4a1b-a192-4f51-94e5-dd1105d43ac4.png)

Click on Add TV Show to provide the details of the show you want to add.

![image](https://user-images.githubusercontent.com/37335919/184322623-8e6d36fd-20f4-4533-8f43-63e30c5a2b90.png)

Click on Update TV Show to provide the ID and new details of the show you want to update.

![image](https://user-images.githubusercontent.com/37335919/184322725-e1402bce-cd0f-498a-afc8-cb4fd3d718c8.png)

Click on Delete TV Show to provide the ID of the show you want to delete.

![image](https://user-images.githubusercontent.com/37335919/184322805-43ca93b0-4356-4e2b-a63f-fef86f2617ef.png)

Click on Search TV Show to search for a show by name.

![image](https://user-images.githubusercontent.com/37335919/184323510-64cc06b2-a1a4-4873-8277-074e26efdf22.png)

Click on Filter TV Shows to filter shows by episodes, seasons, genre, and year began.

![image](https://user-images.githubusercontent.com/37335919/184323586-756bcf95-8bc6-4e76-bff8-6522d594d3d1.png)

Click on Add TV Show to My List to provide the ID, Status(Complete, Watching, Planning), and Progress (episodes watched) of the show you want to add to your list.

![image](https://user-images.githubusercontent.com/37335919/184323746-0ac2f24c-3103-47f5-9a02-6d66266cabb0.png)

### My Watch List

Click on My Watch List on the navigation bar to see films and TV shows in your list.

![image](https://user-images.githubusercontent.com/37335919/184323947-3eb6f5cb-7a93-4d20-8d58-2e7bed783185.png)

Click on Update Entry to provide an entry ID and update the status, progress, and rating of an entry.

![image](https://user-images.githubusercontent.com/37335919/184324671-8f55cb51-6ecf-41be-8013-91aad6bd58ba.png)

Click on Delete Entry to provide the ID of the entry you want to delete from your watch list.

![image](https://user-images.githubusercontent.com/37335919/184324908-4b602070-e387-4a77-b78a-4b9fd9c4f3fe.png)

Click on Search Watch List to search for an entry on your watch list by name.

![image](https://user-images.githubusercontent.com/37335919/184325004-7075e11e-7da3-44c7-81ff-7352560322dd.png)

Click on Filter Watch List to filter your list by status, genre, or rating.

![image](https://user-images.githubusercontent.com/37335919/184325219-35982421-2486-47fe-b6f6-29ad80498012.png)

## Testing

### Setup

In Eclipse, in src/main/resources open application.properties and change profile to test. 

All tests are within src/test/java.

### Unit Test

There are two packages with unit tests, com.qa.filmtvtracker.repo and  com.qa.filmtvtracker.services.

Select them and Coverage As -> JUnit test.

![image](https://user-images.githubusercontent.com/37335919/184361950-e1cf180f-00c3-487f-baf5-85e1726be83c.png)

![image](https://user-images.githubusercontent.com/37335919/184362275-f1b5f600-3a72-4974-b6a1-b6224949ce9b.png)

### Integration Test

There are integration tests for the controller in the com.qa.filmtvtracker.controllers package.

Select the package and Coverage As -> JUnit Test

![image](https://user-images.githubusercontent.com/37335919/184374702-ff32c308-926d-4a78-969d-deef1faf9880.png)

### Selenium Test

In the com.qa.filmtvtracker.main package there is a test called SeleniumFilmsTest.java that tests the Films page on the website.

First run filmtvtracker as a spring boot app. Then select SeleniumFilmsTest.java and Run As -> JUnit Test
![image](https://user-images.githubusercontent.com/37335919/184333094-33f09eaa-a9ae-4ce7-a8cb-83dee2ba16d7.png)

