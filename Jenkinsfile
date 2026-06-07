pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/reshmaperween-qa/selenium-automation-framework.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}