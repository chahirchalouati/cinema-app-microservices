FROM openjdk:11-jdk-slim
MAINTAINER chahir-chalouati
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} config-service.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/config-service.jar"]