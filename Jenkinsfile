pipeline {
	agent any
	tools {
                maven 'maven-3' 
		jdk 'jdk-11'
	}

	stages {
                stage("checkout from github"){
              steps{
                git branch: 'main',
                url:'https://github.com/Abhi-Cod/spring-boot-webflux-jjwt.git'
                echo 'pulled from github successfully'
                }
            }

		stage('Build'){
			steps {
				bat "mvn clean install -DskipTests"
			}
		}

		stage('Test'){
			steps{
				bat "mvn test"
			}
		}
		
		stage('Sanity check') {
            steps {
                input "Does the staging environment look ok?"
               }
            }

		stage('Deploy') {
			steps {
			    bat "mvn jar:jar deploy:deploy"
			}
		}
	}
}