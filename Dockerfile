FROM maven:3.6.3 AS maven
ARG ACCESS_KEY
ARG SECRET_KEY
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn -DskipTests=true package


FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
ARG JAR_FILE=/usr/src/app/target/komorebi-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
