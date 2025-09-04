FROM openjdk:21-jdk-slim
COPY target/dockerized.mongodb-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java","-jar","java-app.jar"]
