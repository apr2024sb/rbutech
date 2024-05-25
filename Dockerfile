# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/bms.jar /app/bms.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/bms.jar"]