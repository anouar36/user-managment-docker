FROM maven:3.9.11-eclipse-temurin-17 AS builder
WORKDIR /build

COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests package

FROM tomcat:10.1.46-jdk17
WORKDIR /usr/local/tomcat

RUN rm -rf webapps/*

COPY --from=builder /build/target/*.war webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
