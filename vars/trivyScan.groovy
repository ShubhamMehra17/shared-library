def call(Map config = [:]) {

    def imageName = config.imageName ?: error("imageName is required")
    def imageTag  = config.imageTag ?: "latest"
    def severity  = config.severity ?: "CRITICAL,HIGH"
    def failBuild = config.failBuild ?: true
    def trivyUrl  = config.trivyUrl ?: "http://host.docker.internal::4954"

    echo "🔍 Trivy Image Scan "
    echo "Image    : ${imageName}:${imageTag}"
    echo "Server   : ${trivyUrl}"
    echo "Severity : ${severity}"

    def exitCode = failBuild ? "1" : "0"

    sh """
      docker run --rm \
        -v /var/run/docker.sock:/var/run/docker.sock \
        aquasec/trivy:latest image \
        --server ${trivyUrl} \
        --severity ${severity} \
        --exit-code ${exitCode} \
        ${imageName}:${imageTag}
    """

    echo "✅ Trivy scan completed"
}
