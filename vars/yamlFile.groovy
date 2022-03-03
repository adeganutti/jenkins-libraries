def call(inputFile) {
	def res = readYaml file: inputFile
	return res
}
