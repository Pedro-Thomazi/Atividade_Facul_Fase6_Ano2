FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV PROFILE=prd
EXPOSE 8080

# Script de inicialização que aguarda o banco
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=${PROFILE} -jar app.jar"]