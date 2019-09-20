# Setting Up Maven and java class path

## Working on your own pc
If you are following this class on your own pc or laptop then you should install the java 8 jdk and maven using the procedures outlined here

download java 8 jdk here 
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

set windows class path

https://www.mkyong.com/java/how-to-set-java_home-on-windows-10/

install maven

https://maven.apache.org/install.html

https://www.mkyong.com/maven/how-to-install-maven-in-windows/

You will also need to install Netbeans 8.2 (not 9.0) (Ideally you should install the Jave EE version)
https://netbeans.org/


## Working on class pc's

The class PC's should have Netbeans 8.2 already installed.

PLEASE NOTE that the class PC's should now have maven and JDK 8 pre-installed on the class path  so these steps are not necessary on all machines. 
To check if it is set up this type open a command window (cmd.exe) and type
```
mvn clean install
```

If mvn is not found, you are working in a machine without maven but you can follow this procedure.

The class PC's have JDK 8 installed but do not have the java JDK class path correctly set up so you cannot call javac from the command line. 
Unfortunately by default the class PC's also do not all have apache maven installed. 

We need access to the JDK and maven for this class, so I have provided a script to help.
This script will download a copy of maven to the maven directory and include it with the java JDK on your class path.

Open a windows command prompt in the maven-setup directory and run the batch file.

```
cd maven-setup
maven-script.bat
```
If maven is not already downloaded, this script will download and unpack it and include it in the class path.

To check it has worked type 
```
mvn -version
```
also type 
```
javac -version
```
For all of the class exercises you will need to open a command prompt and run this script first. 
Then use this command window to run the exercises. (i.e. run the script in this folder and then cd to the folder where you are doing the exercise).

(Please note that if Solent IT update the JDK, the script may need to be changed to reference the correct JDK folder on the class path).

# Using Powershell
if you are usign powershell instead of cmd.exe, the maven-script.bat doesn't change the environment after it completes.

In this case use the following command which will leave the environment variables changed
```
 .\Invoke-CmdScript maven-script.bat

```
Again to check it has worked type 
```
mvn -version
```
also type 
```
javac -version
```