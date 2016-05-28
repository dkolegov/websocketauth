###Packaging and deploying
####Configurations
Open the application.properties file and set your own configurations.
####Prerequisites
Java 8
Maven > 3.0
####Command to create an executable jar:
$ mvn package
####Command to run the executable jar created on the previous step(with embedded Tomcat):
$ java -jar target/websocketauth-0.0.1-SNAPSHOT.jar
After this command the application should be accessable by 'localhost:8080'

###Preconfiguration
####This application configured with predefined users:
	NAME(email)		PASSWORD
	fpi@bk.ru		123123
	user			123123
	dog@gmail.com	qwerty
	mice@mail.ru	qwerty123

### Usage
- Run the application and go on http://localhost:8080/
- Use name/password from the preconfiguration list or submit a non-exist user. See results.
### Requirements
See ./requirements/JAVA EE.odt