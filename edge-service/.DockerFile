FROM openjdk:11-jdk-slim
MAINTAINER chahir-chalouati
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} edge-service.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","/edge-service.jar"]