def call(sonarqubeEnv, credentialsId) {
	echo "Scanning with SonarQube env : ${sonarqubeEnv}"
	
	withSonarQubeEnv(sonarqubeEnv) {
		withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'SONAR_USERNAME', passwordVariable: 'SONAR_PASSWORD')]) {
			sh "mvn clean verify sonar:sonar -Dsonar.login=$SONAR_USERNAME -Dsonar.password=$SONAR_PASSWORD"
		}
	}
}
