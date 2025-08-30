@Library('Shared-Lib') _
pipeline {
    agent any
    triggers {
        githubPush()
    }
    tools {
        jdk 'jdk21'
        maven 'maven3'
    }
    environment {
        DOCKERHUB_CREDS = credentials('dockerhub-creds')
        DOCKER_IMAGE_NAME = "patilprathamesh/petclinic"
        GITOPS_REPO_URL = "https://github.com/prathameshpatil7/petclinic-gitops.git"
        GITOPS_REPO_CREDS = 'gitops-repo-creds'
    }

    stages {
        stage('Git Checkout') {
            steps {
                gitCheckout('docker', 'https://github.com/prathameshpatil7/Java-maven-CICD-Project.git')
            }
        }

        stage('Maven Build') {
            steps {
                mavenBuild()
            }
        }

        stage('SonarQube Analysis & Quality Gate') {
            steps {
                sonarAnalysis('MySonar')
            }
			post{
				always{
					sonarQualityGate()
				}
			}
        }

        stage('OWASP Dependency-Check') {
            steps {
                owaspDependencyCheck('NVD_API_KEY')
            }
        }

        stage('Docker Build') {
            steps {
                dockerBuild("${DOCKER_IMAGE_NAME}", "${BUILD_NUMBER}")
            }
        }

        stage('Run Docker Container for Testing') {
            steps {
                runDockerContainer("${DOCKER_IMAGE_NAME}", "${BUILD_NUMBER}")
            }
        }

        stage('Vulnerability Scan with Trivy') {
            steps {
                trivyScan("${DOCKER_IMAGE_NAME}", "${BUILD_NUMBER}")
            }
        }

        stage('Push to DockerHub') {
            steps {
                dockerPush("${DOCKER_IMAGE_NAME}", "${BUILD_NUMBER}", 'dockerhub-creds')
            }
        }

        stage('Update GitOps Repository') {
            steps {
                updateGitOps( 'main', "${GITOPS_REPO_URL}",  'gitops-repo-creds', "${DOCKER_IMAGE_NAME}", "${BUILD_NUMBER}","github.com/prathameshpatil7/petclinic-gitops.git" )
            }
        }
    }
}
