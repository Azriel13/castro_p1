# Castro_p1

## Project Description

This application can be used to create an employee and allow the submission of expenses.
The expenses cannot be changed or deleted after they have been approved or denied.

## Technologies used:

1. JAVA
2. PostgreSQL
3. Maven
4. Dbeaver, or other database IDE
5. Postman, or other app to send HTTP requests

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
4. Create database or have database ready, then download dbeaver or other database IDE.
5. Create system environment on host for database and name the environment as shown in green:
![System Environment name: p1_Postgres](https://github.com/Azriel13/castro_p1/blob/master/Screenshots/Screenshot_2022-05-04_184847.png)
Or change the environment name in the connectionsutil file to one that is setup on the host.
6. If any changes were made use maven to build the project into an executeable jar file
7. Open the command prompt and cd into target file
8. Execute the .jar file

# Usage

This app can be used for expense reimbursements. It will allow someone to add an employee, add an expense, and approve or deny an expense.

# Contributors

### Michael Castro

# Licenses 

### None Required
