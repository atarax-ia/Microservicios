#
# Build stage
#
FROM openjdk:17-jdk-alpine

ENV APP_NAME provincias

CMD  cd ${APP_NAME}/ && ./mvnw package

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG PORT=8100
EXPOSE ${PORT}

ARG JAR_FILE=${APP_NAME}/target/*.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]