pipeline {
agent any


stages {

    stage('Checkout') {
        steps {
            git branch: 'main',
            url: 'https://github.com/reshmaperween-qa/selenium-automation-framework.git'
        }
    }

    stage('Build') {
        steps {
            sh 'mvn clean compile'
        }
    }

    stage('Test') {
        steps {
            sh 'mvn test -Dsurefire.suiteXmlFiles=grouping.xml'
        }
    }
}

post {
    always {
        publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'reports',
            reportFiles: 'Test-Report*.html',
            reportName: 'Extent Report'
        ])
    }
}


}
