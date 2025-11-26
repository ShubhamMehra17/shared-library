def call() {
    sh """
        mkdir -p owasp-report
        dependency-check.sh \
            --project owasp-scan \
            --scan . \
            --format HTML \
            --out owasp-report
    """

    publishHTML(target: [
        reportDir: 'owasp-report',
        reportFiles: 'dependency-check-report.html',
        reportName: 'OWASP Dependency Check'
    ])
}
