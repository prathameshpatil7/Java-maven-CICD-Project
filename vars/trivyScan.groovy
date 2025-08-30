def call(String imageName, String buildNumber){
  sh "trivy image --exit-code 1 --severity HIGH,CRITICAL ${imageName}:${buildNumber}"
}
