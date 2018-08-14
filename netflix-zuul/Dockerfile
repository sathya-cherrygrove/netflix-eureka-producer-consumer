FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY /target/netflix-zuul-0.0.1-SNAPSHOT.jar netflix-zuul.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/netflix-zuul.jar"]