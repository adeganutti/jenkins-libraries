def call(oktetoServerUrl, oktetoGithubRepositoryName, oktetoGithubRepositoryUrl, oktetoDeploymentName, oktetoCredentialId){
  echo "Deploying on Okteto server : ${oktetoServerUrl} - ${oktetoGithubRepositoryName} - ${oktetoGithubRepositoryUrl} - ${oktetoDeploymentName}"
  
  withCredentials([string(credentialsId: oktetoCredentialId, variable: 'TOKEN')]) {
    sh "okteto context use ${oktetoServerUrl} --token $TOKEN"
    sh "okteto kubeconfig"
    sh "okteto pipeline destroy -p ${oktetoGithubRepositoryName} -w -l info"
    sh "okteto pipeline deploy -r ${oktetoGithubRepositoryUrl} -b main -w -l info"
    sh "kubectl rollout restart deployment ${oktetoDeploymentName}"
  }
}
