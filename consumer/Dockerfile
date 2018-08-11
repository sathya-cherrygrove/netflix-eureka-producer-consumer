FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY /target/consumer-0.0.1-SNAPSHOT.jar consumer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/consumer.jar"]