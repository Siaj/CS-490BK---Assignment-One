pipeline {
  agent any
  stages {
    stage('Hello') {
      steps {
        echo "Hello from Organization Jenkinsfile - I am from ${env.BRANCH_NAME}"
      }
    }

    stage('Hello Again') {
      steps {
        echo "Hello from Organization - all environment variable are:"
        sh 'printenv'
      }
    }
  }
}
