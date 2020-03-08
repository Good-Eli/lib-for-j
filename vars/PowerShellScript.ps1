function geturlstat ($url) {
    Write-Host -NoNewline ' >>> Connecting to a URL...'
    if ((Test-Connection -computer $url -quiet) -eq $True)
    {$result = "success"
    Write-Host $result}
    Else {$result = "failure"}
    return $result
    }
Write-Output " >>> PowerShell function successfully connected from GitHub!"
