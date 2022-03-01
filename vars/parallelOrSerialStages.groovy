def call(runParallel, stagesMap){
    if (runParallel) {
        parallel(stagesMap)
    } else {
        for (analysisStage in stagesMap.values()) {
            analysisStage.call()
        }
    }
}
