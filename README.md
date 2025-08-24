# 💰 Secure Finance & User Management System

A **Spring Boot-based full-stack application** that provides secure **finance management** and **user authentication/authorization** using **Spring Security, JWT, and Vault**.  
This project is designed to help users **track expenses, manage users, and secure sensitive configurations** with **HashiCorp Vault**.

---

## 📖 Extended Description

The **Secure Finance & User Management System** is a **Spring Boot-based full-stack application** that combines **finance tracking** with **robust security practices** to provide a production-grade solution for managing expenses and users.  

This project is built with **Java, Spring Boot, Spring Security, and JWT** for authentication and authorization, ensuring that only authorized users can access and manage resources. It also integrates with **HashiCorp Vault**, a powerful secrets management tool, to securely store and manage sensitive data such as database credentials, JWT secret keys, and API tokens.  

With a **role-based access control (RBAC)** system, the application distinguishes between **Admin** and **User** roles:
- **Admins** can manage users and expenses (CRUD operations for all users).
- **Users** can securely manage their own expense records.  

The **expense management module** provides CRUD operations that allow users to:
- Create new expense entries with amount, category, and description.
- Retrieve a list of all their expenses with pagination and filtering.
- Update existing expenses to reflect accurate data.
- Delete expenses if needed.

This project is designed as a **reference architecture** for building:
- Finance/expense management applications.
- Secure user management systems.
- Applications that integrate with **Vault for secrets management**.  

Additionally, it follows **best practices** for:
- ✅ Layered architecture (Controller → Service → Repository).
- ✅ Centralized exception handling with `@ControllerAdvice`.
- ✅ Validation using `javax.validation` annotations.
- ✅ Testing using **JUnit 5** and **Mockito**.
- ✅ CI/CD-ready structure (Dockerized for containerized deployment).

Whether you’re looking to **learn Spring Security with JWT**, **understand Vault integration**, or **build a secure finance management system**, this project offers a comprehensive starting point.


## 📌 Features

- 🔐 **User Authentication & Authorization** (JWT + Spring Security)
- 👥 **Role-Based Access Control** (Admin, User)
- 💸 **Expense Management** (CRUD operations for expenses)
- 🛡️ **Secure Secret Management** using **HashiCorp Vault**
- 📊 **RESTful APIs** for integration with frontend
- 🗄️ **Database Integration** with MySQL
- 📝 **Centralized Exception Handling**
- ✅ **Unit & Integration Testing** with JUnit & Mockito
- 🚀 **Dockerized Deployment** (Optional)

---

## 🏗️ Tech Stack

**Backend**
- Java 8+  
- Spring Boot 3+  
- Spring Security  
- JWT Authentication  
- Spring Data JPA  
- MySQL  

**DevOps / Security**
- HashiCorp Vault (for storing secrets like DB credentials & JWT secrets)  
- Docker (for containerization)  

**Tools**
- Maven  
- Git & GitHub  
- Postman (API Testing)  

---

## 📂 Project Structure

```bash
secure-finance-user-management/
│── src/
│   ├── main/
│   │   ├── java/com/example/expense
│   │   │   ├── controller/        # REST Controllers (User, Expense)
│   │   │   ├── DTO/             # Entity classes (User, Expense)
│   │   │   ├── repository/        # Spring Data JPA Repositories
│   │   │   ├── service/           # Business logic layer
│   │   │   ├── config/            # JWT configurations
│   │   │   ├── security/          # Security, Vault
│   │   │   └── ExpenseTrackerApplication.java
│   │   └── resources/
│   │       ├── application.properties  # Vault + DB + JWT configs
│   │       └── schema.sql / data.sql   # DB initialization scripts
│   └── test/                           # JUnit + Mockito Tests
│
├── .gitignore
├── pom.xml
├── README.md
└── Dockerfile (optional)

## Postman Collection
A ready-to-use Postman collection is included in the repo under the `/postman` folder.  
Simply import it into Postman and start testing APIs with sample requests.  
