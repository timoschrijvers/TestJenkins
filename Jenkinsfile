pipeline {
    agent{
        docker {
            image 'maven:3-alpine'
        }
    }
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
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
