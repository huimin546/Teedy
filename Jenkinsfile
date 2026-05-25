pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'dockerhub_credentials'
        DOCKER_IMAGE           = 'traccytian/teedy_jenkins'
        DOCKER_TAG             = "${env.BUILD_NUMBER}"
        MVN_CMD                = '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd"'
    }

    stages {
        stage('Clean & Package') {
            steps {
                bat "${env.MVN_CMD} clean package -B -DskipTests -Dmaven.test.skip=true -Dmaven.javadoc.skip=true"
            }
        }

        stage('Build Image') {
            steps {
                bat "docker build -t ${env.DOCKER_IMAGE}:${env.DOCKER_TAG} ."
            }
        }

        stage('Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials',
                                                  usernameVariable: 'DOCKER_USER',
                                                  passwordVariable: 'DOCKER_PASS')]) {
                    bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                    bat "docker push ${env.DOCKER_IMAGE}:${env.DOCKER_TAG}"
                    bat "docker tag  ${env.DOCKER_IMAGE}:${env.DOCKER_TAG} ${env.DOCKER_IMAGE}:latest"
                    bat "docker push ${env.DOCKER_IMAGE}:latest"
                    bat "docker logout"
                }
            }
        }

        stage('Run Containers') {
            steps {
                // 停止并删除旧容器（忽略报错）
                bat script: "docker stop teedy8082 teedy8083 teedy8084", returnStatus: true
                bat script: "docker rm   teedy8082 teedy8083 teedy8084", returnStatus: true

                // 启动 3 个新容器
                bat "docker run --name teedy8082 -d -p 8082:8080 ${env.DOCKER_IMAGE}:${env.DOCKER_TAG}"
                bat "docker run --name teedy8083 -d -p 8083:8080 ${env.DOCKER_IMAGE}:${env.DOCKER_TAG}"
                bat "docker run --name teedy8084 -d -p 8084:8080 ${env.DOCKER_IMAGE}:${env.DOCKER_TAG}"

                bat 'docker ps --filter "name=teedy"'
            }
        }
    }
}
