function geturlstat ($url) {
    if ((Test-Connection -computer $url -quiet) -eq $True)
    {$result = "success"}
    Else {$result = "failure"}
    write-log "$result"
    return $result
    }
Write-Output " >>> PowerShell function successfully connected from GitHub!"
