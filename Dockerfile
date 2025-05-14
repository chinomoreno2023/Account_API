FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/AccountAPI-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
