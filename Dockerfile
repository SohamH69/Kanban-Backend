# Use official OpenJDK image as base
FROM eclipse-temurin:25-jdk

# Set working directory inside container
WORKDIR /app

# Copy the JAR file into the container
COPY target/kanban-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]