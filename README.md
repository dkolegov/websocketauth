##Packaging and deploying
###Command to create an executable jar:
$ mvn package
###Command to run the executable jar created on the previous step(with embedded Tomcat):
$ java -jar target/websocketauth-0.0.1-SNAPSHOT.jar
##Preconfguration
###The application configured with predefined users:
	NAME(email)		PASSWORD
	fpi@bk.ru 		123123
	user 				123123
	dog@gmail.com	qwerty
	mice@mail.ru		qwerty123
##About
Servlet container: Tomcat
DB: H2 (in-memory mode)
Frameworks: Spring Framework(Boot, JPA, MVC, Core, WebSocket)
##Testing
### After deploying application(see Packaging and deploying) you can open a test page. Default application url for testing:
localhost:8080