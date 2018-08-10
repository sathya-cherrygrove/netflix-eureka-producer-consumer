FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY /target/eureka-service-discovery-0.0.1-SNAPSHOT.jar eureka-service-discovery.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-service-discovery.jar"]