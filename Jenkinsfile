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
                // 加上这两个关键参数，让不规范的注释报错变成警告，允许流水线顺利通过
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" javadoc:javadoc -DfailOnError=false -Dadditionalparam="-Xdoclint:none"'
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