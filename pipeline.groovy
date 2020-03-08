//волшебная функция, которая берет команды из аргументов и запускает с ними powershell напрямую по .exe
//простая команда типа "powershell script: 'script'" ни в какую не хотела кушать функцию из файла, а потом запускать ее по обращению
def PowerShell(psCmd) {
    psCmd=psCmd.replaceAll("%", "%%")
    bat "powershell.exe -NonInteractive -ExecutionPolicy Bypass -Command \"\$ErrorActionPreference='Stop';$psCmd;EXIT \$global:LastExitCode\""}
    
pipeline {
 //параметры джобы определил в коде, удобно, чо
 parameters {
  string(name: 'url_var', defaultValue: 'google.com', description: 'URL for checking for availability')
  string(name: 'email_var', defaultValue: 'v.sioffo@gmail.com', description: 'Email for the report')
 }
 agent any
 stages {
  stage ('Stage A: Connecting to git'){
   steps{
    //конектимся к гитхабу. Репозитарий открытый, креденшионалы не прописывал
    git 'https://github.com/Good-Eli/lib-for-j.git'
    echo " >>> Repository connected!"
   }
  }
  stage ('Stage B: Run script'){
   steps{
    echo " >>> Now we will check ${env.url_var}"
    //запускаем немножко кода в декларативном конвейере, надо переменную считать
    script {
    //вызываем функцию из шапки, пишем ответ в темп-файл. Как ответ функции PS напрямую в переменную записать? Пока не нашел ответ...
    PowerShell(". '.\\vars/PowerShellScript.ps1'; geturlstat '${env.url_var}' | Out-File usefulfile.txt -Encoding UTF8")
    //стринг из файлика пишем в переменную. Файлик после самоуничтожется, но этот костыль мне очень не нравится
    env.logs = readFile encoding: 'UTF8', file: 'usefulfile.txt'
    }
   }
  }
  stage ('Stage C: Sending mail'){
   steps{
    echo " >>> Sending a report to ${env.email_var}"
    //шлем мыло, тут все понятно
    mail body: "Project: ${env.JOB_NAME}<br>Build Number: ${env.BUILD_NUMBER}<br>URL de build: ${env.BUILD_URL}<br>Connection with ${env.url_var}: <b>${env.logs}</b>", charset: 'UTF-8', mimeType: 'text/html', subject: "${env.JOB_NAME}: job done", to: "${env.email_var}"; 
   }
  }
 }
}
