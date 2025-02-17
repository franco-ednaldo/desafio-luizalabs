# Stage 1: Build the Maven application
FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

# Copy only the pom.xml to download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy and build the application code
COPY src/ src/
RUN mvn package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the compiled JAR from the builder stage
COPY --from=builder /app/target/order-reader-0.0.1-SNAPSHOT.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]

