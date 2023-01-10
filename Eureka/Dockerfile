#
# Build stage
#
FROM openjdk:17-jdk-alpine

ENV APP_NAME eureka

CMD  cd ${APP_NAME}/ && ./mvnw package

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG PORT=8300
EXPOSE ${PORT}

ARG JAR_FILE=${APP_NAME}/target/*.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]