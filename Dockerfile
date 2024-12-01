# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's JAR file into the container
COPY target/checkout-0.0.1-SNAPSHOT.jar app.jar

# Set the entry point to run the Spring Boot application in command-line mode
ENTRYPOINT ["java", "-jar", "app.jar"]
