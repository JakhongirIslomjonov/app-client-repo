# Base image
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the build artifact from the target directory
COPY target/app.jar   app.jar

# Expose the port the app runs on
EXPOSE 8080
EXPOSE 5433
# Run the application
CMD ["java", "-jar","app.jar"]
