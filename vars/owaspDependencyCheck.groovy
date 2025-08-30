def call(String OwaspEnv, String apiKeyCredId){
  withCredentials([string(credentialsId: apiKeyCredId, variable: 'NVD_API')]){
    dependencyCheck additionalArguments: '--scan target/', odcInstallation: OwaspEnv
    dependencyCheckPublisher pattern: "**/dependency-check-report.xml"
  }
}
