def call(String imageName, String tag, String accountId, String region) {

    sh """
        aws ecr get-login-password --region ${region} \
        | docker login --username AWS \
          --password-stdin ${accountId}.dkr.ecr.${region}.amazonaws.com
    """

    sh """
        docker tag ${imageName}:${tag} \
        ${accountId}.dkr.ecr.${region}.amazonaws.com/${imageName}:${tag}

        docker push ${accountId}.dkr.ecr.${region}.amazonaws.com/${imageName}:${tag}
    """
}
