@echo off

FOR /F %%i IN ('docker ps -a -q --filter="name=yyang-spring-boot-app"') DO docker rm -f %%i

docker rmi -f yyang/spring-boot-app:latest
docker build --build-arg JAR_FILE=./build/libs/*.jar -t yyang/spring-boot-app .

exit 0
