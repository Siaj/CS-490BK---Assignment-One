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
        echo "The build Id was: ${BUILD_ID}"
      }
    }

    stage('Bye Now') {
      steps {
        echo "Jenkins Agent for this build was: ${JENKINS_AGENT_NAME}"
        echo "Git commit hash: ${GIT_COMMIT}"
      }
    }
  }
}
