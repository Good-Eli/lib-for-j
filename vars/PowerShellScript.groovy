#!/usr/bin/env groovy

def call(body) {
    echo "Start Deploy"

    echo "Deployed"
    currentBuild.result = 'SUCCESS' //FAILURE to fail

    return this
}
