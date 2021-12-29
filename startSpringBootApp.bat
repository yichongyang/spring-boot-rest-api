@echo off

start /B docker run -d -p 443:443 --name yyang-spring-boot-app yyang/spring-boot-app > nul 2>&1

exit 0
