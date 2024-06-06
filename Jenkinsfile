pipeline {
	agent any
	tools {
                maven 'maven-3' 
		jdk 'jdk-11'
	}

	stages {
                stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Git-Checkout') {
            steps {
                checkout scm
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
       
                stage("code review to check quality of code"){
                       steps{
                             bat "mvn pmd:pmd"
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