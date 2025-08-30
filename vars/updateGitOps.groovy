def call(){
  dir('gitops-repo'){
    git branch: branch, url: repoUrl, credentialsId: gitopsCred
    sh "sed -i 's|image: .*|image: ${imageName}:${buildNumber}|g' deployment.yaml"

    withCredentials([string(credentialsId: gitopsCred, usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]){
      sh """
        git config --global user.email 'jenkins@example.com'
        git config --global user.name 'Jenkins CI'
        git add . && git commit -m 'updated image version of deployment to ${imageName}:${buildNumber}'
        git remote set-url origin https://${GIT_USER}:${GIT_TOKEN}@${projectUrl}
        git push origin ${branch}
      """
    }
  }
}
