pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'sh \'mvn clean install -f sample-repo/pom.xml\''
      }
    }

  }
}