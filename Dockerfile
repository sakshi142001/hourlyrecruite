# Step 1: Use Java base image
FROM openjdk:17-jdk-slim
# Step 2: App.jar copy 
COPY target/hr-backend.jar app.jar
# Step 3: App run
ENTRYPOINT ["java", "-jar", "/app.jar"]
