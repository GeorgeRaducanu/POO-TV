## Copyright Răducanu George-Cristian 2022-2023 321CA

# Homework no. 3 for OOP class: *POO-TV*

----
### Main objective
The main target of the project is to make a Netflix
like small app with users, movies and various types of actions.

----
### Description of the structure

The implementation contains multiple packages with the following classes:
> checker (given as a model for the class)
>> Checker.java <br/>
>>CheckerConstants.java <br/>
>>Checkstyle.java <br/>
>>Constants.java
> 
> commands
>> BackCommand.java <br/>
>>ChangePage.java <br/>
>>DatabaseOperations.java <br/>
> >OnPage.java <br/>
> >PageSolver.java <br/>
> Recommendation.java <br/>
>> Subscribe.java <br/>
> 
> input
> >ActionsIn.java <br/>
> ContainsIn.java <br/>
> CredentialsIn.java <br/>
> FiltersIn.java <br/>
> Input.java <br/>
> MovieIn.java <br/>
> SortIn.java <br/>
> UserIn.java <br/>
> 
> struct
> > Credentials.java<br/>
> > CurrentPage.java<br/>
> GenreLikes.java<br/>
> Movie.java<br/>
> Notifications.java<br/>
> User.java<br/>
> UserFactory.java <br/>
> UserPremium.java <br/>
> UserStandard.java <br/>
> 
> Main.java
> 
> Test.java
> 

---
### Used OOP principles and patterns

The program incorporates basic OOP patterns such as:
> Singleton - for the currentPage<br/>
> Builder - for Credentials constructors<br/>
> Factory - for a certain type of constructor of the User class<br/>
> Observer - for the notifications received by the users.<br/>

Although I am not too confident in the quality of the Observer pattern,
because of the deprecated API and my strange approach the project does not lack
OOP principles.

Besides patterns, the homework contains inheritance, which the factory pattern
takes advantage of.

---
### Further details of the implementation
The program contains *on page* and *change page* commands, and some bonus commands.

The *change page* commands ensure a proper navigation between the "pages"
of our program, and only allow a correct browsing.

The *on page* commands allow operations on the current page, such as
>searching for a movie <br/>
> filtering by certain criteria <br/>
> buying tokens (necessary for purchasing movies)<br/>
> buying a premium account<br/>
> purchasing movies<br/>
> watching movies<br/>
> rating movies<br/>
> liking movies<br/>
> 
The *change page* commands allow only a correct navigation between the:
> homepage page <br/>
> movies page <br/>
> upgrades page <br/>
> logout page <br/>
> see details page <br/>

Additionally, from the first version of the project, several features have
been added:

> **subscribe action** - It allows a user to subscribe to a genre, in order to
> be notified regarding movies with that genre.<br/>
> 
> **back command** - It gives the user the possibility of going back to the
> previous page. The back command can be applied several times and will
> proceed with the action or print an error <br/>
> 
> **database add** - allows the addition of a movie in a database.
> All the users who are subscribed to the genres of the movies will
> receive notifications. <br/>
> 
> **database remove** - allows the removal of a movie from the database.
> All the users that are subscribed to the genres of the movies will be
> notified. <br/>
> 
> **recommendation** - for the last user that has not logged out, if it's
> a premium user, it will receive a recommendation based on its subscriptions
> and on the numbers of likes. It is a process that takes into account multiple
> data in order to make a correct recommendation
> 

---
### Helpful tool used for checking the results:
https://www.jsondiff.com/
