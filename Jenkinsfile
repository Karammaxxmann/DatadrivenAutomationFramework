pipeline {
    agent any
 
    tools {
        maven 'Maven 3.8.5'
        jdk 'Java 11'
    }
 
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Karammaxxmann/DatadrivenAutomationFramework.git', branch: 'main'
            }
        }
 
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
 
        stage('Run Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }
 
        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    results: [[path: 'allure-results']]
                ])
            }
        }
    }
 
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
 
        failure {
            mail to: 'your-email@example.com',
                 subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Check Jenkins for details: ${env.BUILD_URL}"
        }
    }
}
