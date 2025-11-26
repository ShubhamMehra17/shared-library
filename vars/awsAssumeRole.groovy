def call(String roleArn, String region) {
    sh """
        CREDS=\$(aws sts assume-role \
            --role-arn ${roleArn} \
            --role-session-name jenkins-session \
            --region ${region} \
            --query "Credentials" \
            --output json)

        export AWS_ACCESS_KEY_ID=\$(echo \$CREDS | jq -r .AccessKeyId)
        export AWS_SECRET_ACCESS_KEY=\$(echo \$CREDS | jq -r .SecretAccessKey)
        export AWS_SESSION_TOKEN=\$(echo \$CREDS | jq -r .SessionToken)
    """
}
