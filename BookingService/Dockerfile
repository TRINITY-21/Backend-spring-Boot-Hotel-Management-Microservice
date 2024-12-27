# Use the official OpenJDK base image for Java 21
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory
WORKDIR /app
# Copy the built jar file from the build stage
COPY build/libs/*.jar /app/BookingService-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 4050

# Run the jar file
ENTRYPOINT ["java", "-jar", "BookingService-0.0.1-SNAPSHOT.jar"]