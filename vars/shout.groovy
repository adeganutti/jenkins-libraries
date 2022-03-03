def call(command) {
	def res = sh script: command, returnStdout: true
	return res
}
