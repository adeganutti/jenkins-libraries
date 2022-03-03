def call(command) {
	echo "Shout : ${command}"
	def res = sh script: command, returnStdout: true
	return res
}
