# Secure User Form 🔒

## Project Overview 🚀

**Secure User Form** is a web application designed to demonstrate best practices in secure web development. This project focuses on building a user authentication system with a strong emphasis on security, using JWT (JSON Web Tokens) for session management, secure password storage, and input validation.

Developed as part of a **Secure Development** course, this application aims to implement key security concepts, including **RBAC** (Role-Based Access Control) and **PBAC** (Policy-Based Access Control), to control user access. The project serves as a foundation for building secure and scalable web applications.

## Features 🌟

- 📝 **User Registration**: Secure user registration with validation and role assignment.
- 🔐 **User Login**: Secure login functionality using JWT-based authentication.
- 💼 **Secure User Dashboard**: After successful login, users can access a dashboard displaying their details.
- 🔑 **Token-Based Authentication**: JWT tokens are used for secure session management.
- ✅ **Input Validation**: Both client-side (JavaScript) and server-side (Spring Boot) validation to prevent vulnerabilities.
- 🔒 **Password Encryption**: User passwords are hashed using **BCrypt**, ensuring secure storage.
- 🌐 **CORS Handling**: Proper handling of cross-origin requests, restricting access to authorized domains only.
- 🔑 **RBAC (Role-Based Access Control)**: User roles (**ADMIN**, **USER**) define access rights to resources.
- 🛠️ **PBAC (Policy-Based Access Control)**: Custom JWT claims are used for managing user permissions.
- ⏳ **JWT Expiration**: Tokens expire after a set period, enhancing session security.

## Technologies Used 🛠️

- **Frontend**:
  - HTML, CSS, JavaScript (for creating the user interface and interactions)
  - **JWT** for secure authentication and authorization
  - **Fetch API** to make secure HTTP requests

- **Backend**:
  - **Spring Boot** (Java) for building the backend RESTful API
  - **JWT** for token-based authentication
  - **Spring Security** for securing endpoints and managing user sessions

- **Security Features**:
  - 🛡️ **CSRF Protection**: Disabled for token-based authentication, handled by JWT.
  - 🔐 **Password Security**: Passwords are hashed using **BCrypt** for secure storage.
  - 🌍 **HTTPS**: Ensures secure communication between the client and server.
  - 🌍 **CORS Configuration**: Allows cross-origin requests only from trusted domains.

- **Session Management**:
  - JWT tokens with expiration to ensure secure and controlled sessions.

- **Logging and Monitoring**:
  - Basic audit logs for monitoring authentication and access events.

- **Docker**:
  - **Dockerfile**: Configured to build and run the application in a container.
  - **Docker Compose**: Defines and manages services needed for the app.

## Setup Instructions ⚙️

To get the project running locally, follow these steps:

### 1. Clone the repository:

```bash
git clone https://github.com/tatilimongi/Secure-UserForm.git
cd Secure-UserForm
```

### 2. Install dependencies:

- Ensure that you have **Java** and **Maven** installed for the backend (Spring Boot).
- The frontend can be run directly from your browser by opening the HTML, CSS, and JavaScript files.

### 3. Run the application:

- **Backend**:
    - Navigate to the backend directory and run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

- **Frontend**:
    - Open the `index.html` in your browser to interact with the application.

### 4. API endpoints:

- **POST /auth/login**: User login (returns JWT token on success)
- **POST /auth/register**: User registration (you need to register first)
- **GET /welcome**: Secure user dashboard (requires JWT token for authentication)

### 5. Testing:

Use tools like **Postman** or **Insomnia** to test the API endpoints.

#### **User Registration**:

Before logging in, you need to register a user. Send a **POST** request to `http://localhost:8080/register` with the following JSON body:

**Example 1** (Admin User):
```json
{
  "name": "Tatiana",
  "email": "tatiana@example.com",
  "password": "Password1",
  "role": "ADMIN"
}
```

**Example 2** (Regular User):
```json
{
  "name": "Tatiana",
  "email": "tatiana2@example.com",
  "password": "Password1",
  "role": "USER"
}
```

#### **User Login**:

After registering, send a **POST** request to `http://localhost:8080/auth/login` with the following JSON body:

```json
{
  "username": "tatiana@example.com",
  "password": "Password1"
}
```

Upon success, you will receive a JWT token.

#### **Access the Secure Dashboard**:

Once logged in, you can access the secure user dashboard by sending a **GET** request to `http://localhost:8080/welcome` with the **Authorization** header:

```
Bearer <JWT_TOKEN>
```
Replace `<JWT_TOKEN>` with the token you received during login.

#### Docker Setup:

1. **Build the Docker image**:
   - From the project directory, run:
   ```bash
   docker-compose build
   ```

2. **Start the application with Docker**:
   - To start the application in a container, use:
   ```bash
   docker-compose up
   ```

3. **Access the application**:
   - Once the containers are running, you can access the frontend at `http://localhost:8080`.

## Security Focus 🛡️

This project emphasizes the following security aspects:

1. 🔑 **Authentication Security**: Ensures secure login with token-based authentication using JWT.
2. 🔐 **Authorization**: Restricts access to resources based on user roles (RBAC) and custom policies (PBAC) defined in JWT claims.
3. ✅ **Input Validation**: Prevents common security vulnerabilities such as SQL injection and XSS by validating user inputs on both the client and server side.
4. 🌍 **Secure Communication**: Enforces HTTPS to protect data during transmission.
5. 🔒 **Password Security**: Implements **BCrypt** to securely hash and store passwords.
6. 🔐 **Session Management**: Manages sessions using JWT tokens with expiration to enhance security.

## Future Improvements 🌱

While the application covers the essentials of secure authentication, there are several potential areas for future enhancement:

- 🔌 **OAuth 2.0 Integration**: Allow users to authenticate via third-party services (Google, Facebook, etc.).
- 📧 **Email Verification**: Implement email verification during the registration process to ensure valid user emails.
- 🧑‍🤝‍🧑 **Advanced User Roles**: Expand the RBAC system to support more granular roles and permissions.
- 📊 **Comprehensive Logging and Monitoring**: Integrate advanced logging and monitoring tools (e.g., ELK stack, Prometheus) to track and analyze application behavior.

## License 📜

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

*This project is part of the Secure Development course at Centro Universitário Tiradentes and aims to apply secure coding practices in web applications.* 🌟
