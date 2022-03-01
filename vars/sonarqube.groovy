def call(sonarqubeEnv, credentialsId) {
	//echo sonarqubeEnv
	//echo credentialsId
	
	withSonarQubeEnv(sonarqubeEnv) {
		withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'SONAR_USERNAME', passwordVariable: 'SONAR_PASSWORD')]) {
			sh "mvn clean verify sonar:sonar -Dsonar.login=$SONAR_USERNAME -Dsonar.password=$SONAR_PASSWORD"
		}
	}
}
