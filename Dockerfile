FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD

ARG SPRING_ACTIVE_PROFILE
MAINTAINER Bruno

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean install -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE && mvn package -B -e -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE
FROM openjdk:11-slim
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/portaldeestagios-api-*.jar /app/portaldeestagios-api.jar
ENTRYPOINT ["java", "-jar", "portaldeestagios-api.jar"]