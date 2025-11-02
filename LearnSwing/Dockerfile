# Use OpenJDK 24 as base image
FROM openjdk:24-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Expose port if needed (though Swing apps typically don't need network ports)
# EXPOSE 8080

# Set the main class
ENV MAIN_CLASS=retos.learnswing.LearnSwing

# Run the application
CMD ["sh", "-c", "java -cp target/classes:$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q) $MAIN_CLASS"]