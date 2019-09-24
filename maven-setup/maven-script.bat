echo off
ECHO script to set up maven
ECHO ----------------------
ECHO this script sets up maven temporarily in an account where maven does not exist on the computer
ECHO keep the command window open and run maven commands once this script is run
ECHO ideally you should permanently install maven following these instructions
ECHO https://maven.apache.org/install.html
ECHO https://www.mkyong.com/maven/how-to-install-maven-in-windows/
ECHO ----------------------

SET mavenDownloadUrl=http://mirror.ox.ac.uk/sites/rsync.apache.org/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.zip
SET mavenZip=apache-maven-3.5.4-bin.zip

IF NOT EXIST %cd%\maven (

ECHO maven folder %cd%\maven doesnt exist. Trying to download or extract maven zip
ECHO mavenDownloadUrl=%mavenDownloadUrl%
ECHO mavenZip=%mavenZip%

IF EXIST %cd%\%mavenZip% (
ECHO file %cd%\%mavenZip% exists
) ELSE (
ECHO file %cd%\%mavenZip% does not exist trying to download
bitsadmin.exe /transfer "downloadMaven" /priority FOREGROUND %mavenDownloadUrl% %cd%\%mavenZip%
)

REM https://stackoverflow.com/questions/21704041/creating-batch-script-to-unzip-a-file-without-additional-zip-tools

echo unzipping   %cd%\maven\ from %cd%\%mavenZip% 
Call :UnZipFile "%cd%\maven\" "%cd%\%mavenZip%"

)

REM THIS IS VALUE SET BY SOLENT
SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_202
echo setting JAVA_HOME to %JAVA_HOME%  you may need to change this if wrong JDK referenced
SET PATH=%JAVA_HOME%\bin;%PATH%;

echo check that javac on classpath
javac -version

SET M2_HOME=%cd%\maven\apache-maven-3.5.4
SET MAVEN_HOME=%M2_HOME%
SET MAVEN_OPTS=-XX:MaxPermSize=256m -Xmx1024m
SET PATH=%M2_HOME%\bin;%PATH%;

ECHO ----------------------
ECHO setting environment variable to local maven installation for this cmd.exe session
ECHO M2_HOME=%M2_HOME%
ECHO MAVEN_HOME=%MAVEN_HOME%
ECHO MAVEN_OPTS=%MAVEN_OPTS%
ECHO PATH=%PATH%;
ECHO ----------------------

ECHO testing local maven with mvn -version
ECHO ----------------------
mvn -version
ECHO ----------------------
ECHO if you see maven output then setup is complete

EXIT /b

:UnZipFile <ExtractTo> <newzipfile>
set vbs="%temp%\_.vbs"
if exist %vbs% del /f /q %vbs%
>%vbs%  echo Set fso = CreateObject("Scripting.FileSystemObject")
>>%vbs% echo If NOT fso.FolderExists(%1) Then
>>%vbs% echo fso.CreateFolder(%1)
>>%vbs% echo End If
>>%vbs% echo set objShell = CreateObject("Shell.Application")
>>%vbs% echo set FilesInZip=objShell.NameSpace(%2).items
>>%vbs% echo objShell.NameSpace(%1).CopyHere(FilesInZip)
>>%vbs% echo Set fso = Nothing
>>%vbs% echo Set objShell = Nothing
cscript //nologo %vbs%
if exist %vbs% del /f /q %vbs%

