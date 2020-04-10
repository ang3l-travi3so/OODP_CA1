####Design Patterns
===================

As we have been taught Design Patterns are some kind of general template that have been proposed to solve some common issues referring to
the design of programs. They are based on general ideas that usually fit on particular problems, according to refactoring.guru (2020).On
another hand, sourcemarking.com (2020), points out that designs patterns are "general repeatable solutions to commonly occurring problems
in software design."

As it was presented by its four authors, the book Design Patterns: Elements of Reusable Object-Oriented Software, launched in 1994, shows
23 initial patterns which could be applied to te main programming designs problems. Among them, it can be found the three which were
implemented in this project: Singlenton, Builder and the the Data Access Object, include later for the J2EE.

The reasons why mentioned above were implemented in this project are following detailed:

1.- Based on the information provided by Refactoring.Guru (2020), the Singelton Pattern was implemented because this one allows to make sure that a
class, in this case MainOODP_CA1, will have only one instance of the object "db", that gives us only one connection to the database, avoiding
in this case to saturate the database generating many connections at the same time from the same application.

2.- Newly, taking into account the references from Refactoring.Guru (2020), the Builder Pattern was applied on the class Country to create
different complex objects, in this case countries, which contain different attributes such as code, name, continent, surface area and head of
state, by reusing the same construction code.

3.- Th Data Access Object Patterns is explained by GeeksForGeeks.org (2020), as the pattern that "separate low level data accessing API 
or operations from high level business services. Following are the participants in Data Access Object Pattern." Which in simple words
means that this pattern is in charge of dealing with different levels of connecting to the data base, based on the principles of 
encapsulation and abstraction, by taking abstracting the idea or general information from business and encapsulating it to be able to 
connect it with different layers used by for elements: BusinessObject(CliInterface), DataAccessObject (MySQLCountryDAO), DataSource
(Same name)and TransferObject (MainOODP_CA1).

## Some Links for more in depth
https://refactoring.guru/design-patterns/book
https://www.geeksforgeeks.org/data-access-object-pattern/
https://javarevisited.blogspot.com/2013/01/data-access-object-dao-design-pattern-java-tutorial-example.html

##Description of the Program
=============================

In order to run the program it is necessary to run first the server which in this case it is represented by MainOODP_CA1, to stablish the
connection with the data base, and then it should be ran the client represented by CliInterface, which will be in charge of interacting
to the user and the program.


