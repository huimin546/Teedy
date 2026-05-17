pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" clean'
            }
        }

        stage('Compile') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" compile'
            }
        }

        stage('Test') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" test -Dmaven.test.failure.ignore=true'
            }
        }

        stage('PMD') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" pmd:pmd'
            }
        }

        stage('JaCoCo') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" jacoco:report'
            }
        }

        stage('Javadoc') {
            steps {
                // 使用更加兼容 Windows bat 的高级 doclint 参数格式，并且强制指定即使错也忽略
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" javadoc:javadoc -Ddoclint=none -DfailOnError=false'
            }
        }

        stage('Site') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" site'
            }
        }

        stage('Package') {
            steps {
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" package -DskipTests'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}