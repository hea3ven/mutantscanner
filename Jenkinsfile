pipeline {
    agent any

    tools {
        gradle "gradle"
    }

    stages {
        stage("Checkout") {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[credentialsId: 'GITHUB_CREDENTIALS', url: 'https://github.com/hea3ven/mutantscanner.git']]])
            }
        }
        stage("Compile") {
            steps {
                sh 'gradle compileJava --info'
            }
        }
        stage("Package") {
            steps {
                sh 'gradle build --info'
            }
        }
        stage("Unit test") {
            steps {
                sh 'gradle test --info'
            }
        }
        stage("Docker build") {
            steps {
                sh "docker build -t mutantscanner ."
            }
        }
        stage("Docker run test environment") {
            steps {
                sh "docker stop mutantscanner-test-env || true"
                sh "docker run -d --rm -p 10100:8080 --name mutantscanner-test-env -e SPRING_DATASOURCE_URL=jdbc:postgresql://hea3ven2.cnhtqlmz8xzt.us-east-2.rds.amazonaws.com:5432/mutantscanner_test -e SPRING_DATASOURCE_USERNAME=mutantscanner_test -e SPRING_DATASOURCE_PASSWORD=mutantscanner_test mutantscanner"
            }
        }
        stage("Integration test") {
            steps {
                sleep 20
                sh 'gradle integrationTest -DbaseUrl="http://localhost:10100/" --info'
            }
        }
        stage("Docker run production environment") {
            steps {
                sh "docker stop mutantscanner-prod-env || true"
                sh "docker run -d --rm -p 8080:8080 --name mutantscanner-prod-env -e SPRING_DATASOURCE_URL=jdbc:postgresql://hea3ven2.cnhtqlmz8xzt.us-east-2.rds.amazonaws.com:5432/mutantscanner mutantscanner"
            }
        }
    }
}
