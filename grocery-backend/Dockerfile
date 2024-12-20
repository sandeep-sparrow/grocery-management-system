# Use a lightweight OpenJDK 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your build output into the container
COPY target/grocery-backend-*.jar app.jar

# Expose the port on which the Spring Boot app runs
EXPOSE 8080

# Set the entry point to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]