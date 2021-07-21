FROM openjdk:11.0.11-jdk-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-app.jar
EXPOSE 8080
ENTRYPOINT java -Dspring.profiles.active=prod -jar /spring-app.jar