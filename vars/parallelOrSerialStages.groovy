def call(runParallel, stagesMap){
    def stagesList = []
    stagesList.add(stagesMap)

    if (runParallel) {
        parallel(stagesList)
    } else {
        for (stage in stagesList.values()) {
            stage.call()
        }
    }
}
