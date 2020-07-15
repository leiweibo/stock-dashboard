FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=stockdata-0.0.1-SNAPSHOT.jar

COPY build/libs/${JAR_FILE} data-server.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/data-server.jar"]