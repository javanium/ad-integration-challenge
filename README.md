# AppDirect  integration challenge
This is a functional prototype done for AppDirect Backend Coding Challenge  
The goal of this challenge was to demonstrate the ability to integrate with App Direct APIs, flow and security guidelines  

## CI/CD
*(This repository has CI/CD running for every commit)*  
Used **Travis** to verify that the code pushed builds and unit tests are passing.  
Used **Heroku** to deploy the application on every successful build.  
The app is deployed at http://mighty-retreat-17602.herokuapp.com
## Event Notification API
Event notifications API is unified in one end point :  
`mighty-retreat-17602.herokuapp.com/api/v1/notifications?eventUrl={eventUrl}`
####Integration completed
- Subscription Create
- Subscription Change
- Subscription Cancel  

####Integration handled
(Return a failed event notification response saying that the event type is valid but not integrated yet)  
- Subscription Notice
- User Assignment
- User Unassignment
- User Updated  

## Technologies used
- Gradle
- Java 8
- Spring Boot
- Spring Security OAuth
- Spring Data Rest (HATEOAS)
- MongoDB
- HALBrowser: Accessible at http://mighty-retreat-17602.herokuapp.com, An API browser for the hal+json media type of the ISV)
- JUnit
