pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/sandeshMS1996/SportsShoesEcomApp.git'

                sh "mvn clean install -DskipTests"


            post {

                success {
                    archiveArtifacts 'target/*.jar'
                // target - /var/lib/jenkins/workspace/test-pipeline/target
                    sh 'pwd'
                    sh 'who'
                    sh 'scp -v -o StrictHostKeyChecking=no -i /var/lib/jenkins/ProdServer.pem target/*.jar ubuntu@ec2-18-223-134-54.us-east-2.compute.amazonaws.com:/home/ubuntu'
                    sh 'ssh -i /var/lib/jenkins/ProdServer.pem ubuntu@ec2-18-223-134-54.us-east-2.compute.amazonaws.com ./start.sh'
            }
            }

        }
    }
}
