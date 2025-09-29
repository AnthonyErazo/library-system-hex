FROM openjdk:17-jdk-slim

LABEL maintainer="biblioteca-hexagonal"

WORKDIR /app

# Copiar el JAR compilado (debe compilarse previamente con mvn clean package)
COPY target/biblioteca-hexagonal-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]