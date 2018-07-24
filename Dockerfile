FROM openjdk:8-jdk-alpine
COPY mutantscanner-service/build/libs/mutantscanner-service-1.0.0.jar mutantscanner-service-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/mutantscanner-service-1.0.0.jar"]
