node {

    checkout scm
        
  stage ('Build') {
  def M2_HOME=tool name: 'Maven', type: 'maven'
  sh "/opt/apache-maven-3.6.1/bin/mvn install"
  }
  }