function geturlstat ($url) {
    Write-Host -NoNewline ' >>> Connecting to a URL...'
    if ((Test-Connection -computer $url -quiet) -eq $True)
    {$result = "success"}
    Else {$result = "failure"}
    Write-Host $result
    return $result
    }
Write-Output " >>> PowerShell function successfully connected from GitHub!"
