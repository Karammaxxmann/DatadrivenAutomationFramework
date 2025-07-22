pipeline {
    agent any
    tools {
        maven 'Maven'
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
                script {
                    try {
                        sh 'mvn test -DsuiteXmlFile=testng.xml'
                    } catch (Exception e) {
                        echo "Tests failed, but continuing to generate reports: ${e.getMessage()}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
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
            script {
                // Check if test results exist before trying to publish
                if (fileExists('**/target/surefire-reports/*.xml')) {
                    junit '**/target/surefire-reports/*.xml'
                } else {
                    echo 'No test results found to publish'
                }
            }
        }
        
        failure {
            script {
                echo 'Build failed, but mail is not configured.'
                // Uncomment and configure the mail step below when SMTP is set up
                /*
                mail to: 'your-email@example.com',
                     subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                     body: "Check Jenkins for details: ${env.BUILD_URL}"
                */
            }
        }
    }
}
