pipeline {
    agent {label 'ubuntu-18.04 && x64 && hw'}

    environment {
        GRADLE_OPTS = '-Dorg.gradle.daemon=false'
        ARTIFACTORY_HOST='http://maven.v4core.os-n3.hw:8081/artifactory'
    }

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
                withCredentials([
                    usernamePassword(
                        credentialsId: 'buildserver-v4core', 
                        usernameVariable: 'ARTIFACTORY_USERNAME', 
                        passwordVariable: 'ARTIFACTORY_PASSWORD')
                ]) { 

                    sh './gradlew mapsdk:artifactoryPublish'
                }
            }
        }
    }
}