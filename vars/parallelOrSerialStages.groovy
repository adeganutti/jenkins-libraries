def call(runParallel, stagesMap){
    if (runParallel) {
        parallel(stagesList)
    } else {
        for (stage in stagesList.values()) {
            stage.call()
        }
    }
}
