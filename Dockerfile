FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
# Now you can reference a fixed name in the ENTRYPOINT
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]