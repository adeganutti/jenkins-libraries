def call(inputFile) {
	def res = readJSON file: inputFile
	return res
}
