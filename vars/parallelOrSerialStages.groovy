def call(runParallel, stagesMap){
    if (runParallel) {
        parallel(stagesMap)
    } else {
        for (stage in stagesMap.values()) {
            stage.call()
        }
    }
}
