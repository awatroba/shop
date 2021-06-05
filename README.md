# Shop
Simple spring boot app 

The shop application is an application containing products from different categories. 
The user has the option of adding and removing a product from his basket, and can also display all products and products by category. 
The administrator can, however, view all products and products by category, as well as add and remove products from the store. 
The application also has registration and login functionality. All entries are checked and a corresponding message is displayed in the event of any errors. 

# Implementation

The application contains a backend part created in the REST architecture. It is written using Spring Boot. Login is done via SpringSecurity. 
Data is saved and retrieved from a SQL database. The application is written using the MVC design pattern. Spring mechanisms run views written with html, 
css, js and ajax. The Bootstrap library was used to improve the visual part of the project. Custom exceptions are also created in the application, e.g. 
in the case of incorrect data or performing an incorrect action. The application distinguishes between two user roles: admin, which is 
only one, and user, which each registering user has. 
