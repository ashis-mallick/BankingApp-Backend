 #Banking Application

A backend banking application built using **Java, Spring Boot, Spring Data JPA, Spring Security, and MySQL**.

The application provides basic banking operations for customers and administrative operations for admins, with authentication, role-based access, transaction management, validation, and exception handling.

## Features

### Customer

* Customer registration
* Basic Authentication
* Deposit money
* Withdraw money
* Check account balance
* View transaction statement
* Transfer money between accounts
* Password management
* Account validation

### Admin

* View all customers
* Search customer by account number
* Change customer active/inactive status
* Change customer password
* Delete customer
* View all transactions

## Tech Stack

* **Java**
* **Spring Boot**
* **Spring Security**
* **Spring Data JPA**
* **Hibernate**
* **MySQL**
* **Maven**
* **Lombok**
* **Bean Validation**

## Architecture

```text
Client / Postman
       |
       v
Controller Layer
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
MySQL Database
```

Spring Security is used to authenticate users and control access based on roles.

```text
ADMIN
  |
  +---- /admin/**

CUSTOMER
  |
  +---- /banking/**
```

## Project Structure

```text
src/main/java/com/ashis
│
├── controllers
│   ├── AdminController.java
│   └── CustomerController.java
│
├── services
│   ├── AdminService.java
│   ├── CustomerService.java
│   ├── AuthService.java
│   └── DataBaseUser.java
│
├── repositories
│
├── entities
│   ├── User.java
│   ├── Customer.java
│   ├── Account.java
│   └── Transactions.java
│
├── dto
│   ├── RegisterDto.java
│   ├── TransactionDto.java
│   ├── TransferBalanceDto.java
│   └── ...
│
└── utils
    └── Roles.java
```

## API Endpoints

### Authentication

```text
POST /register
```

Used to register a new customer.

### Customer APIs

```text
POST /banking/credit
POST /banking/debit
POST /banking/check
POST /banking/statement
POST /banking/transfer
```

### Admin APIs

```text
GET  /admin/view
GET  /admin/search
POST /admin/change-status
POST /admin/change-password
POST /admin/delete-customer
GET  /admin/view-transactions
```

## Validation

Request DTOs use Jakarta Bean Validation.

Examples:

``` java
@NotBlank
@NotNull
@Email
@Pattern
@Size
@Positive

```

Invalid requests are rejected before reaching the service layer.

Example:

```text
TransactionDto
      |
      v
   @Valid
      |
      v
Validation
      |
   +--+--+
   |     |
 Valid  Invalid
   |     |
   v     v
Service  Error
```

## Security

The application currently uses **Spring Security Basic Authentication**.

Users are loaded from the database using a custom `UserDetailsService`.

```text
Login Request
      |
      v
Spring Security
      |
      v
UserDetailsService
      |
      v
UserRepository
      |
      v
Database User
```

Passwords are stored using a password encoder rather than plain text.

## Database Relationships

The main relationships are:

```text
User
  |
  | OneToOne
  v
Customer
  |
  | OneToOne
  v
Account
  |
  | OneToMany
  v
Transactions
```

A customer can have an account and multiple transactions.

## Transaction Flow

### Deposit

```text
Customer
   |
   v
/credit
   |
   v
Validate request
   |
   v
Verify account ownership
   |
   v
Create transaction
   |
   v
Update account balance
   |
   v
Save transaction
```

### Withdrawal

The application verifies the account, checks the available balance, creates the withdrawal transaction, and updates the account balance.

### Transfer

```text
Sender Account
      |
      v
Validate sender
      |
      v
Check balance
      |
      v
Debit sender
      |
      v
Credit receiver
      |
      v
Save transaction
```

## Validation and Exception Handling

The application validates incoming request data using Jakarta Validation.

Business-level errors such as:

* Account not found
* Customer not found
* Insufficient balance
* Invalid transaction
* Unauthorized account access

are handled separately from request validation.

## Running the Application

### 1. Clone the repository

```bash
git clone <repository-url>
```

### 2. Configure MySQL

Create a database and configure the database credentials in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## Testing

API endpoints can be tested using **Postman**.

Recommended testing flow:

```text
Register Customer
       ↓
Authenticate
       ↓
Credit
       ↓
Debit
       ↓
Check Balance
       ↓
Statement
       ↓
Transfer
       ↓
Admin Operations
```

## Future Improvements

* JWT authentication
* Global exception handler
* Pagination for transaction statements
* Transaction history filtering
* API documentation using Swagger/OpenAPI
* Unit and integration testing
* React frontend
* Docker support
* CI/CD pipeline

## Author

**Ashis Mallick**

Java / Spring Boot Developer
