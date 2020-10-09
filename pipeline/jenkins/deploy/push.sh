#!/bin/bash

echo "*********************************"
echo "**** Pushing image **************"
echo "*********************************"

IMAGE="irecipe"

echo "*** Logging in ***"

docker login -u ruzanna1 -p $PASS
echo "***** Tagging Image *****"
docker tag $IMAGE:$BUILD_TAG ruzanna1/$IMAGE:$BUILD_TAG
echo "**** Pushing Image ****"
docker push ruzanna1/$IMAGE:$BUILD_TAG

