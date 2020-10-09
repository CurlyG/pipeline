#!/bin/bash

echo "*************************"
echo "** Testing the code *****"
echo "*************************"

# WORKSPACE=/home/jenkins/jenkins-data/jenkins_home/workspace/irecipePipeline

docker run --rm -v $PWD/java-app:/app -v /root/.m2/:/root/.m2/ -w /app maven:3-alpine "$@"

