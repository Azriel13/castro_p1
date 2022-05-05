# Castro_p1

## Project Description

This application can be used to create an employee and allow the submission of expenses.
The expenses cannot be changed or deleted after they have been approved or denied.

## Technologies used:

1. JAVA
2. PostgreSQL
3. Maven
4. AWS RDs
5. Postman

### Features

* Ability to create an employee and add them to the database
* Ability to create an expense for the employee with the default of Pending
* Expense and Employee cannot be deleted once an expense has been approved or denied

### TO-DO List
* Get the deployment working with Elastic Beanstalk or EC2
* Implement logger more efficiently

## Getting Started:
1. Download JDK with Java 8 or later
2. Download Postman to send HTTP requests
3. Download Maven and follow instructions for setup to be able to build the project for any changes that are made.
4. Create system environment on host for database and name the environment as shown in green:
![System Environment name: p1_Postgres](https://github.com/Azriel13/castro_p1/blob/master/Screenshots/Screenshot_2022-05-04_184847.png)
Or change the environment name in the connectionsutil file to one that is setup on the host.
5. If any changes were made use maven to build the project into an executeable jar file
6. Open the command prompt and cd into target file
7. Execute the .jar file

# Usage

Postman will be used to pass in JSON arguments to communicate with the database.

# Contributors

### Michael Castro

# Licenses 

### None Required
