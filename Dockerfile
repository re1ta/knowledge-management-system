FROM openjdk:17-jdk-slim
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ./target/knowledgeManagementSystem-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]