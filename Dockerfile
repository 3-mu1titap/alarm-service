
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/alarm-service-0.0.1-SNAPSHOT.jar alarm-service.jar

EXPOSE 10300

ENTRYPOINT ["java", "-jar", "alarm-service.jar"]

ENV TZ=Asia/Seoul