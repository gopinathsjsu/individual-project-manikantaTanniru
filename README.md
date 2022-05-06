# CMPE 202 Individual Project
Name: Manikanta Tanniru
ID:015947425



## Instructions to run the project

* Go to repo manikantatanniru and clone the repository or download the zip file.
* You need Intellij IDE to run the code.
* Open the zipped folder or the entire folder in Intellij using Open Project.
* After opening the project go to Billing.java and run the project.
* If the java version is not compatible please download the correct JDK version ( Oracle JDK 17)
* Build and run the code next and you will get a "Enter a file name" prompt in the terminal in that enter Input-File.csv or Input-File-2.csv
* Output is displayed in the terminal and the error file or output CSV file is generated and can check the same in the project folder under Output.csv     or log.txt


## Design Patterns

*Singleton Pattern:*
I have tried to implement the singleton design pattern to keep the cardNumber private to the order. This is to instill
security and avoid duplication code smell.

*Chain of Responsibilities:*
Chain of Responsibility is the design pattern that is also used in the above implementations
as it works that lets the order method, upon receiving a request, each handler decides either to process the request
or to pass it to the next handler in the chain. Upon receiving a request, each handler decides either to process the
request or to pass it to the next handler in the chain. 

## UML CLASS DIAGRAM

![UML Class Diagram](uml.png)
