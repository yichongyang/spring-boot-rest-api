pipeline {
    agent any
    environment{
        CONTAINER_COUNT = 1
    }
    stages{
        stage('Build Spring Boot App Docker Image'){
            steps{
                echo 'Building the spring boot app docker image ...'
                bat 'buildSpringBootAppImage.bat'
            }
        }
        stage('Launch Spring Boot App Docker Containers'){
            steps{
                echo 'Launching the spring boot app docker containers ...'
                bat 'startSpringBootApp.bat ${CONTAINER_COUNT}'
            }
        }
    }
}
