def call(serverUrl, repositoryName, version, protocol, credentialId) {
  pom = readMavenPom file: "pom.xml"
  filesByGlob = findFiles(glob: "target/*.${pom.packaging}")
  echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
  artifactPath = filesByGlob[0].path
  artifactExists = fileExists artifactPath
  if (artifactExists) {
      echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}"
      nexusArtifactUploader(
              nexusVersion: version,
              protocol: protocol,
              nexusUrl: serverUrl,
              groupId: pom.groupId,
              version: pom.version,
              repository: repositoryName,
              credentialsId: credentialId,
              artifacts: [
                      [artifactId: pom.artifactId,
                       classifier: '',
                       file      : artifactPath,
                       type      : pom.packaging],
                      [artifactId: pom.artifactId,
                       classifier: '',
                       file      : "pom.xml",
                       type      : "pom"]
              ]
      )
  } else {
      error "*** File: ${artifactPath}, could not be found"
  }
}
