pipeline {
	agent any
	tools {
                maven 'maven-3' 
		jdk 'jdk-17'
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
	stage('Sonarqube analysis') {
         steps {
          script {
            scannerHome = tool 'SonarScanner';
          }
            withSonarQubeEnv('sonarqube-10.5.1.90531') {
            bat "${scannerHome}/sonar-scanner.bat" 
           }
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
        
     post {
        always {
            bat 'echo "Cleaning up..."'
        }
        
        success {
            mail to: 'avishekup@gmail.com',
                 subject: 'Pipeline Succeeded',
                 body: 'Your Jenkins Pipeline has completed successfully.'
        }
        
        failure {
            mail to: 'avishekup@gmail.com',
                 subject: 'Pipeline Failed',
                 body: 'Your Jenkins Pipeline has failed. Please investigate.'
        }
    }

}