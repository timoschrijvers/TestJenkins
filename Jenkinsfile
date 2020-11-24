pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                "c:\Program Files\Java\jdk-1.8.0_25\bin\javac" src/myfirstapp/*.java -d classes
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