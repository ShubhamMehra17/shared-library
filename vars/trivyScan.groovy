def call(String imageName, String tag) {
    sh """
        trivy image --exit-code 0 --severity HIGH,CRITICAL ${imageName}:${tag}
    """
}
