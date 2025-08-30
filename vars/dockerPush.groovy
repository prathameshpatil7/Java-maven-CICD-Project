def call(String imageName, String buildNumber, String dockerhubCreds){
  withDockerRegistry(credentialsId: dockerhubcreds, url: 'https://index.docker.io/v1/', toolname: 'docker){
      sh "docker -t ${imageName}:${buildNumber} ${imageName}:latest"
      sh "docker push ${imageName}:${buildNumber}"
      sh "docker push ${imageName}:latest"
  }
}
