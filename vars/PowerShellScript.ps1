function geturlstat ($url) {
    if ((Test-Connection -computer $url -quiet) -eq $True)
    {$result = "success"}
    Else {$result = "failure"}
    return $result
    }
Write-Output "Func get!"
