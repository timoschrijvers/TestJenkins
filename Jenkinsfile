pipeline {
    agent any

    stages {
        stage('Build') {
                steps{
                    withMaven(maven : 'maven_3_6_3'){
                        bat 'mvn clean compile'
                    }
                }
        }
        stage('Test') {
            steps{
                withMaven(maven : 'maven_3_6_3'){
                    bat 'mvn test'
                }
                
                bat 'mvn surefire-report:report'
                
                publishHTML target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'TEST-MethodsTest.xml',
                    reportName: 'RCov Report'
               ]
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
