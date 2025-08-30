def call(){
  timeout(time: 10,unit:'MINUTE'){
    waitForQualityGate abortPipeline: false
  }
}
