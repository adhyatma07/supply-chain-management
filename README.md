Supply Chain Management System

A backend-driven Supply Chain Management System built using Spring Boot to manage suppliers, products, customers, and orders efficiently through RESTful APIs.
This project demonstrates real-world backend development practices including layered architecture, database integration, and exception handling.

🚀 Features

Supplier, Product, Customer, and Order management

RESTful APIs with proper HTTP methods

CRUD operations using Spring Data JPA

Centralized exception handling

PostgreSQL database integration

Clean layered architecture (Controller → Service → Repository)

Easily extendable for frontend integration (React / Angular)

🛠️ Tech Stack

Backend: Java, Spring Boot

ORM: Spring Data JPA (Hibernate)

Database: PostgreSQL

Build Tool: Maven

API Testing: Postman

IDE: Eclipse (Enterprise Java & Web Developers)

Version Control: Git & GitHub

📂 Project Structure
SupplyChainManagement
│
├── src/main/java
│   └── org.jsp.SupplyChainManagement
│       ├── Controller
│       ├── Service
│       ├── Repository
│       ├── Entity
│       └── Exception
│
├── src/main/resources
│   ├── application-example.properties
│
├── pom.xml
└── .gitignore

⚙️ Configuration
Database Configuration

This project uses PostgreSQL.
For security reasons, the actual application.properties file is ignored.

Steps:

Navigate to:

src/main/resources


Rename:

application-example.properties → application.properties


Update with your local database credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ How to Run the Project

Clone the repository:

git clone https://github.com/adhyatma07/supply-chain-management.git


Open the project in Eclipse

Update database details in application.properties

Run the main Spring Boot application class

Application will start on:

http://localhost:8080

🔍 API Testing

You can test APIs using Postman.

Example endpoints:

POST /customers

GET /customers/{id}

POST /products

GET /orders

DELETE /suppliers/{id}

🧠 Learning Outcomes

Hands-on experience with Spring Boot and REST APIs

Understanding of layered backend architecture

Practical use of Spring Data JPA with PostgreSQL

Proper exception handling and clean code practices

Real-world Git & GitHub workflow

👨‍💻 Author

Adhyatma Hawale
GitHub: adhyatma07

📌 Future Enhancements

Frontend integration using React

Role-based authentication (Spring Security)

Swagger API documentation

Docker support
