def call(String imageName, String buildNumber){
  echo "Building image >> ${imageName}:${buildNumber}"
  sh "docker build ${imageName}:${buildNumber} ."
}
