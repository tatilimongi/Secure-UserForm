FROM openjdk:22-jdk-slim

RUN apt-get update && apt-get install -y curl

WORKDIR /app

COPY target/userform-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080

CMD tail -f /dev/null