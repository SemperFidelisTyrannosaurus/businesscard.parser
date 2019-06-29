# businesscard.parser

A command line program to read in a a text file resembling a business card and return a contact information object 
displaying the name, phone number, and email address on the card.

Requires gradle 4.9+ and Java JDK 8.

To run this program, simply clone the code from github and run [gradle build] in the businesscard.parser folder.
Then you can run [gradle run --args=-f="location of text file here"] to see the output on the command line.
[gradle test] can also be run to confirm unit tests pass.

A program run should look something like this:

user@ubuntu:~/IdeaProjects/businesscard.parser$ gradle run --args=-f=/home/user/IdeaProjects/businesscard.parser/src/main/resources/MikeSmith

> Task :run
Proper Name: Mike Smith
Phone Number: 4105551234
Email: msmith@asymmetrik.com

BUILD SUCCESSFUL in 0s
3 actionable tasks: 2 executed, 1 up-to-date

