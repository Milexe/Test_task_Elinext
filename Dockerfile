FROM maven:3.6.0-jdk-11-slim AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:11-jre-slim
COPY --from=build /target/test_task-0.0.1-SNAPSHOT.jar test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test.jar"]