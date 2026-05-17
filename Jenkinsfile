pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                // 用双引号包裹全路径（因为路径中带有空格），反斜杠写两个进行转义
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
                bat '"D:\\IDEA\\IntelliJ IDEA 2025.2.5\\plugins\\maven\\lib\\maven3\\bin\\mvn.cmd" javadoc:javadoc'
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