def call(repositoryUrl, filePath, credentials) {
	try
	{
		withCredentials([usernamePassword(credentialsId: credentials, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')])
		{
			def scriptTempName = new Date().getTime().toString()
			def b64 = json(shout("curl -u ${USERNAME}:${PASSWORD} -H 'Accept: application/vnd.github.v3+json' '${repositoryUrl}/${filePath}'"))?.content?.replace("\n","")
			if(b64 == null)
				error "ERROR getFileFromGitHub: invalid groovy file."
			writeFile text: b64, file: scriptTempName, encoding: "Base64"
			def res = load scriptTempName
			return res
		}
	}
	catch(e)
	{
		println "ERROR loadFromGitHub: ${e.message}"
		return null
	}
}
