# Use an official Maven image to perform the build
FROM maven:3.9.7-amazoncorretto-22 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests=true

# Use an official Java runtime as a parent image
FROM openjdk:22-oracle

ARG SPRING_PORT
ENV SPRING_PORT ${SPRING_PORT}

# Copy the executable jar file from the build stage
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE ${SPRING_PORT}

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
