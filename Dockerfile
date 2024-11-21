
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/alarm-0.0.1-SNAPSHOT.jar alarm-service.jar

EXPOSE 10300

ENTRYPOINT ["java", "-jar", "auth-service.jar"]

ENV TZ=Asia/Seoul