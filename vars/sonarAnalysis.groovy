def call(String SonarEnv){
  withSonarQubeEnv(SonarEnv){
     sh 'mvn clean verify sonar:sonar'
  }
}
