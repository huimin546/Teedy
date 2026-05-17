pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                // 如果项目根目录下有 mvnw.cmd (Maven Wrapper)，优先使用它
                // 如果没有，直接尝试调用系统命令
                bat 'if exist mvnw.cmd (mvnw.cmd clean) else (mvn clean)'
            }
        }

        stage('Compile') {
            steps {
                bat 'if exist mvnw.cmd (mvnw.cmd compile) else (mvn compile)'
            }
        }

        stage('Test') {
            steps {
                bat 'if exist mvnw.cmd (mvnw.cmd test -Dmaven.test.failure.ignore=true) else (mvn test -Dmaven.test.failure.ignore=true)'
            }
        }

        stage('PMD') {
            steps {
                bat 'if exist mvnw.cmd (mvnw.cmd pmd:pmd) else (mvn pmd:pmd)'
            }
        }

        stage('JaCoCo') {
            steps {
                bat 'if exist mvnw.cmd (mvnw.cmd jacoco:report) else (mvn jacoco:report)'
            }
        }

        stage('Javadoc') {
            steps {
                bat 'if exist mvnw.cmd (mvnw.cmd javadoc:javadoc) else (mvn javadoc:javadoc)'
            }
        }

        stage('Site') {
            steps {
                bat 'if exist mvnw.cmd (mvnw.cmd site) else (mvn site)'
            }
        }

        stage('Package') {
            steps {
                bat 'if exist mvnw.cmd (mvnw.cmd package -DskipTests) else (mvn package -DskipTests)'
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