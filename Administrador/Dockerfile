#
# Build stage
#
FROM openjdk:17-jdk-alpine

ENV APP_NAME administrador

ADD /${APP_NAME} ./

RUN ./mvnw clean package
RUN cp /target/${APP_NAME}-0.0.1-SNAPSHOT.jar /target/app.jar

ARG PORT=8400
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "/target/app.jar"]