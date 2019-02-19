This is Web Application with the following technologies and frameworks:

-Spring Framework

-Thymeleaf template 

-MySQL

-Spring Data/ Hibernate 

-AJAX

-Bootstrap



Eventures

Eventures Inc. is a fast-rising newly made Start-Up Company, which specializes in Event Tickets Sales. It is said to be the killer of systems like Eventim, Eventbride, etc.
You have been appointed as the developer of the main web application. This is a great responsibility, so do your best and do not dissapoint your employers. The application functionality is not that complex, and it will be separated into several parts, each part consisting of several tasks. 
Your current task is to extend the business logic of the application, implementing, validation, error handling, and pagination, so get started.
1. Validation
Implement data validation for the data models which are affected by user input (User, Event).

User
The following data constraints should be present:
Username
Should NOT be NULL.
Should be at least 3 symbols long.

Password
Should NOT be NULL.
Should be at least 5 symbols long.
May contain any character.

Email
Should NOT be NULL.
Should be a valid email. (implement a basic email validation)

Unique Citizen Number (UCN)
Should NOT be NULL.
Should consist of exactly 10 numbers.
Event

Name
Should NOT be NULL.
Should be at least 10 symbols long.
May contain any character.

Place
Should NOT be NULL.
Should NOT be empty.
May contain any character.

Start
Should NOT be NULL.
Should be a valid date.

End
Should NOT be NULL.
Should be a valid date.
Total Tickets
Should NOT be NULL.
Should be a non-zero integer number.
Price per Ticket
Should NOT be NULL.
Should be a decimal number.

2. Error Handling
Implement basic error handling in the Eventures application. 
Instead of handling binding errors or any other request-connected errors in the Controllers, throw exceptions and handle them with error handlers.
Implement custom view pages for the errors, so that you could provide the client with a suitable response.

3. Pagination
Implement a basic pagination for the tables of the Events and the Orders. But don’t make it too basic … Make it have at least previous, next and indexed pages functionalities.

