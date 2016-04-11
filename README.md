##Packaging and deploying
####1. Command to create an executable jar:
$ mvn package
####2. Command to run the executable jar created on the previous step(with embedded Tomcat):
$ java -jar target/websocketauth-0.0.1-SNAPSHOT.jar
####3. After step 2 the application should be accessable by 'localhost:8080'
##Preconfiguration
####This application configured with predefined users:
	NAME(email)		PASSWORD
	fpi@bk.ru		123123
	user			123123
	dog@gmail.com	qwerty
	mice@mail.ru	qwerty123
##Main technologies used in this project
####Java 8
####Servlet container: Tomcat
####DB: H2 (in-memory mode)
####Frameworks: Spring Framework(Boot, JPA, MVC, Core, WebSocket)
##Testing
After deploying this application(see Packaging and deploying) you can open a test page. Default application URL for testing is 'localhost:8080'
##Requirements
####See ./requirements/JAVA EE.odt