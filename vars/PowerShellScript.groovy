#!/usr/bin/env groovy

def call(body) {
    echo "Start Deploy"
    powershell(script: '''function geturlstat ($url){ 
                        if ((Test-Connection -computer $url -quiet) -eq $True) {
                        $result = \"success\"} 
                        Else {$result = \"failure\"} 
                        return $result} geturlstat google.com''')
    echo "Deployed"
    currentBuild.result = 'SUCCESS' //FAILURE to fail

    return this
}
