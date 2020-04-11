# Introduction to Java

## install and setup 

- install the Java JDK and add it to to your system environment,
 A Java Virtual Machine will be the thing that converts our code into byte code 
 which can be understood by our machine / OS. 
 
- install inteliji community edition a great IDE 

- inside of your source file you can create new classes and files 

- after creating your application you can use ctrl+shift+f10 to make the compiler run which will create the binary .class files 
these files will get put in the output directory 

- to add some structure you can refactor your code and bundle them into packages 

## packaging apps

inside the java sdk we have commands
* `jar cvf TitleCaseConverter.jar .` - create jar and put it inside the current directory
* `java -jar TitleCaseConverter.jar`
* `jar cvmf TC-MANIFEST.MF TitleCaseConverter.jar .` - will tell us where our main class is located how to start our app 
put this file TC-MANIFEST in directory specfying which class to use as a main
* `java -jar TitleCaseConverter.jar` - will now be executable independant of development env 

