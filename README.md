# Simple Email Service

Demonstrating the simple email sending client using multiple mail service providers - Email service fail over has been implemented.

### Prerequisites

What things you need to install the software and how to setup your system

```
* For backend project
	Java 8 - Latest
	Maven 3.x
	Eclipse IDE / Spring Tool Suit (STS)
	MailGun email service credentials
	ManDrill email service credentials
	SendGrid email service credentials
	
* For frontend project
  	Node JS Latest version 7.x
  	Angular CLI
  	IntelliJ Idea / Webstorm IDE
```

## Open projects in IDEs

	Main project is backend system.It can be open using Eclipse IDE / Spring Tool Suit (STS) / IntelliJ Idea.
	If you need to open frontend project separately got to the Email-Service\src\main folder.
	Open that EmailServiceFrontend project using IntelliJ Idea / Webstorm IDE.

## Getting Started

	Run Total system -
	(1) Clone the project (Backend project).
	(2) Open the project using Eclipse IDE.
	(3) Fill up necessary email service credentials (MailGun,ManDrill,SendGrid)
	(2) Go to the Email-Service folder using command prompt.
	(3) To clean project , run command      mvn clean
	(4) To build project , run command      mvn package
	(5) After successful build, go to the target folder.
	(6) run command    java -jar Email-Service-0.0.1-SNAPSHOT.jar
	(7) Load any browser and fire up http://localhost:8080/
	
	Run only backend system (REST API) -
	(1) Go to the Email-Service\src\main\resources\static folder and delete all the contents manually.
	(2) Go to the Email-Service folder using command prompt.
	(3) To clean project , run command      mvn clean
	(4) To build project , run command      mvn package
	(5) After successful build, go to the target folder.
	(6) run command    java -jar Email-Service-0.0.1-SNAPSHOT.jar
	
	Run frontend system - 
	(1) Go to the Email-Service\src\main\EmailServiceFrontend folder using command prompt.
	(2) Run command    npm install
	(3) Run command    ng serve --proxy-config proxy-conf.json
	(4) Load any browser and fire up http://localhost:4200 (port can change manually - check the configurations)
	
## Development and further improvements
	
	Backend and frontend projects can run separately and can develop them separately.
	To test both the systems together first need to build frontend project with ng build -prod command.
	After successful build copy generated /dist folder contents and replace backend project static folder contents.

## Running the tests

	At the moment backend system is not fully implemented and include only two REST API test cases.
	Frontend project has not implemented test cases yet.

### TODO List

```
Frontend - Improvements of UI , email client interface
Frontend - Add email validations
Frontend - Add attachments with images - multipart email
Backend - add tracking emails
Backend - add tracking and monitoring email services
Backend - Implement JMS
```

## Author

	Sampath Thennakoon
	 - Java Development experience - 6 years
	 - Spring boot development experience - 4 months
	 - Angular 2/4 development experience - 2 months


