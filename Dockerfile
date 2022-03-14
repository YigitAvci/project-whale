FROM openjdk:17
WORKDIR /app
ENTRYPOINT ["java", "-jar", "movei_review_project-0.0.1-SNAPSHOT.jar"]