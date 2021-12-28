FROM openjdk:8-jdk-alpine

ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar

RUN mkdir -p ./cert
ARG CERT_FILE=./cert/keystore.p12
COPY ${CERT_FILE} ./cert/keystore.p12

EXPOSE 443
 
ENTRYPOINT ["java","-jar","app.jar"]