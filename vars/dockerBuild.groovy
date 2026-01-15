def call(Map config = [:]) {

    def imageName = config.imageName ?: error("imageName is required")
    def imageTag  = config.imageTag ?: "latest"
    def context   = config.context ?: "."
    def buildArgs = config.buildArgs ?: ""
    def push      = config.push ?: false

    echo "🐳 Building Docker image"
    echo "Image   : ${imageName}:${imageTag}"
    echo "Context : ${context}"

    sh """
        docker build \
          -t ${imageName}:${imageTag} \
          ${buildArgs} \
          ${context}
    """

    echo "✅ Docker build completed"
}
