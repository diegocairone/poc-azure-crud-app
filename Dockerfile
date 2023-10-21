FROM eclipse-temurin:17.0.8.1_1-jre-ubi9-minimal
MAINTAINER diegocairone@gmail.com
ARG JAR_FILE=target/poc-crud-app-az-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
