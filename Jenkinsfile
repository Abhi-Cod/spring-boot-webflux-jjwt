pipeline {
    agent any
    tools { 
        maven 'maven-3' 
        jdk 'jdk-11' 
    }
    stages {
         stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}