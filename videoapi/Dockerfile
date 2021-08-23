FROM openjdk:11

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} videoapi.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "videoapi.jar"]