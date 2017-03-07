# workout-logger
A simple web service to help track and log your workouts using a workout manager API.
The focus of the application was to show REST calls between services. Ideally this would be a companion web service for people using a smart soccer shoe to collect and start storing GPS and accelerometer data when people log their workouts.

# Package and Run Spring-Boot
To test, you need to make sure you are in the workout-logger directory where the pom.xml is and run the following maven command to package and launch the Spring-boot Application:
mvn clean install && java -jar target/workout-logger-0.0.1-SNAPSHOT.jar

# Swagger
Once the application is running, you can test out the endpoints by going to:
http://localhost:8080/workout-logger/swagger-ui.html
