def call(oktetoServerUrl, oktetoToken, oktetoGithubRepositoryName, oktetoGithubRepositoryUrl, oktetoDeploymentName){
  echo "Deploying on Okteto server : ${oktetoServerUrl} - ${oktetoGithubRepositoryName} - ${oktetoGithubRepositoryUrl} - ${oktetoDeploymentName}"
  
  sh "okteto context use ${oktetoServerUrl} --token ${oktetoToken}"
  sh "okteto kubeconfig"
  sh "okteto pipeline destroy -p ${oktetoGithubRepositoryName} -w -l info"
  sh "okteto pipeline deploy -r ${oktetoGithubRepositoryUrl} L -b main -w -l info"
  sh "kubectl rollout restart deployment ${oktetoDeploymentName}"
}
