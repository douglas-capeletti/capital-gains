FROM openjdk:11
COPY . /app
WORKDIR /app
CMD ["./gradlew", "jar"]
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar","./app.jar"]
