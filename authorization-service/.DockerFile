FROM openjdk:11-jdk-slim
MAINTAINER chahir-chalouati
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} authorization-service.jar
EXPOSE 9191
ENTRYPOINT ["java","-jar","/authorization-service.jar"]