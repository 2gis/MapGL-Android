pipeline {
    agent {label 'ubuntu-18.04 && x64 && hw'}

    stages {
        stage("prepare") {
            steps {
                script {
                    def localProperties = '''\
                        sdk.dir=/opt/android-sdk/

                        apiKey=CI_INVALID_API_KEY
                        directionsApiKey=CI_DIRECTIONS_INVALID_KEY
                    '''.stripIndent()

                    writeFile file: "local.properties", text: localProperties
                }
            }
        }

        stage("build") {
            steps {
                sh './gradlew build'
            }
        }

        stage("deploy") {
            steps {
                echo "Deploy SNAPSHOT"
            }
        }
    }
}