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
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {   
                    withMaven(maven : 'maven_3_6_3'){
                        bat 'mvn test'
                    }

                    
                }
                bat 'mvn surefire-report:report'

                publishHTML target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/site',
                    reportFiles: 'surefire-report.html',
                    reportName: 'RCov Report'
               ]
            }
        }
        stage('Deploy') {
            when {
                branch 'production'
            }
            steps {
                echo 'Deploying....'
                bat 'mvn exec:java'
            }
        }
    }
}
