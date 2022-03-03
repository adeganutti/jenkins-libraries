
  
def call(inputText) {
	echo "Json : ${inputText}"

	def res = readJSON text: inputText
	return res
}
