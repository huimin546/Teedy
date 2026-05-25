pipeline {
    agent any

    environment {
        // 1. Jenkins 凭据管理里配置的 Docker Hub 凭据 ID
        DOCKER_HUB_CREDENTIALS = 'dockerhub_credentials'

        // 2. 更改为你的 DockerHub 账户名/仓库名
        DOCKER_IMAGE = 'traccytian/teedy_jenkins'

        // 3. 使用 Jenkins 构建号作为 Tag
        DOCKER_TAG = "${env.BUILD_NUMBER}"

        // 4. 你本地 Windows 上的 Maven 绝对路径
        MVN_CMD = '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd"'
    }

    stages {
        stage('Package') {
            steps {
                // 使用本地 Maven 路径进行清理和快速打包（跳过测试）
                bat "${env.MVN_CMD} clean package -B -DskipTests"
            }
        }

        stage('Building image') {
            steps {
                script {
                    // 调用根目录下的 Dockerfile 构建镜像
                    docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
                }
            }
        }

        stage('Upload Image') {
            steps {
                script {
                    // 登录并推送镜像到 Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
                    }
                }
            }
        }

        stage('Run containers') {
            steps {
                script {
                    // 1. 在 Windows 环境下，通过 bat 停止并删除旧的 3 个容器，防止端口和名称冲突
                    bat "docker stop teedy8082 teedy8083 teedy8084 2>nul || cmd /c exit 0"
                    bat "docker rm teedy8082 teedy8083 teedy8084 2>nul || cmd /c exit 0"

                    // 2. 同时启动 3 个新容器，分别映射到实验要求的 8082, 8083, 8084 端口
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy8082 -d -p 8082:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy8083 -d -p 8083:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy8084 -d -p 8084:8080')
                }
                // 3. 打印当前正在运行的 teedy 容器以供 onsite 检查验证
                bat 'docker ps --filter "name=teedy"'
            }
        }
    }
}