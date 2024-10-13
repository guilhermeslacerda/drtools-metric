pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn -B -DskipTests clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }
        stage('Test Code Coverage') {
            steps {
                echo 'Testing with code coverage..'
                sh 'mvn jacoco:prepare-agent test jacoco:report'
            }
			post {
                always {
                    // Publica o relatório de cobertura do JaCoCo no Jenkins
                    jacoco execPattern: '**/target/jacoco.exec', 
                           classPattern: '**/target/classes', 
                           sourcePattern: '**/src/main/java', 
                           exclusionPattern: '**/target/test-classes'
                }
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Analysing source code..'
                sh 'mvn pmd:pmd'
            }
            //post {
            //    always {
            //        pmd canComputeNew: false, pattern: '**/target/pmd.xml' 
            //    }
            //}

        }
    }
}