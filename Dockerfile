FROM maven:3.6.3 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn -DskipTests=true package


FROM adoptopenjdk/openjdk11:alpine-jre
ARG ACCESS_KEY_ARG
ARG SECRET_KEY_ARG
ENV ACCESS_KEY=$ACCESS_KEY_ARG
ENV SECRET_KEY=$SECRET_KEY_ARG
EXPOSE 8080
ARG JAR_FILE=/usr/src/app/target/komorebi-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
