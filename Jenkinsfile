pipeline {
    agent any

    triggers {
        githubPush()
    }

    parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/MuTalKing/QA_Otus_HomeWork09', description: 'The target git url')
    }

    stages {
        stage('Pull from GitHub') {
            steps {
                git ([
                    url: "${params.GIT_URL}",
                    branch: "${params.branch}"
                    ])
            }
        }
        stage('Run gradle clean test') {
            steps {
                bat 'gradlew clean test '
            }
        }
        stage('Backup and Reports') {
            steps {
                archiveArtifacts artifacts: '**/build/', fingerprint: true
            }
        }
    }
    post {
                always {

                  script {
                      def summary = junit '**/build/test-results/test/*.xml'
                    println("summary generated")
                    if (currentBuild.currentResult == 'SUCCESS') {
                    emailext (
                        subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                        body: """${currentBuild.currentResult}: Job ${env.JOB_NAME}, build ${env.BUILD_NUMBER}, branch - ${branch}\nTest Summary - ${summary.totalCount}, Failures: ${summary.failCount}, Skipped: ${summary.skipCount}, Passed: ${summary.passCount}\nMore info at: ${env.BUILD_URL}""",
                        to: "mutalking@gmail.com"
                    )

                    } else {
                    emailext (
                        subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                        body: """${currentBuild.currentResult}: Job ${env.JOB_NAME}, build ${env.BUILD_NUMBER}, branch - ${branch}\nTest Summary - ${summary.totalCount}, Failures: ${summary.failCount}, Skipped: ${summary.skipCount}, Passed: ${summary.passCount}\nMore info at: ${env.BUILD_URL}""",
                        to: "mutalking@gmail.com"
                    )
                    }
                  }
                }
            }

}