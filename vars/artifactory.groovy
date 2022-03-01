def call(artifactoryId, artifactoryUrl, artifactoryCredentialId, artifactoryRepository, buildName, buildProject) {
        rtServer(
                id: artifactoryId,
                url: artifactoryUrl,
                credentialsId: artifactoryCredentialId,
                timeout: 300
        )

        pom = readMavenPom file: "pom.xml"
        filesByGlob = findFiles(glob: "target/*.${pom.packaging}")
        echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
        artifactPath = filesByGlob[0].path
        artifactExists = fileExists artifactPath
        if (artifactExists) {
            echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}"
        }

        rtUpload(
                serverId: 'artifactory-server-saas',
                spec: '''{
                      "files": [
                        {
                          "pattern": "target/*.jar",
                          "target": "artifactory/example-repo-local/"
                        }
                     ]
                }''',

                buildName: buildName,
                project: buildProject
        )
}
