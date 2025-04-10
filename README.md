# Secure User Form 🔒

## Project Overview 🚀

**Secure User Form** is a web application designed as part of a Secure Development course. The project focuses on building a user authentication system that ensures the security of sensitive data such as user credentials. It employs best practices in securing authentication processes and implementing security measures such as token-based authentication, secure password storage, and validation.

This project was developed as a hands-on activity to apply concepts learned in the **Secure Development** course. It provides a comprehensive overview of securing web applications, including but not limited to user authentication, authorization, and data protection.

## Features 🌟

- 📝 **User Registration**: Allows users to create accounts securely.
- 🔐 **User Login**: Supports login functionality with token-based authentication (JWT).
- 💼 **Secure User Dashboard**: After successful authentication, users are granted access to a secure page displaying their details.
- 🔑 **Token-Based Authentication**: Uses JWT (JSON Web Tokens) for secure session management.
- ✅ **Input Validation**: Validates user inputs on both the client-side and server-side to prevent common security vulnerabilities.
- 🔒 **Password Encryption**: User passwords are securely hashed and never stored in plain text.
- 🌐 **CORS Handling**: Properly handles cross-origin requests, allowing only authorized domains to interact with the backend.

## Technologies Used 🛠️

- **Frontend**:
  - HTML, CSS, JavaScript (for front-end interface and user interactions)
  - JSON Web Tokens (JWT) for secure token-based authentication
  - Fetch API for making secure HTTP requests

- **Backend**:
  - Spring Boot (Java) for creating secure backend RESTful APIs
  - JWT for authentication and authorization
  - Spring Security for securing endpoints and managing user sessions

- **Security Measures**:
  - 🛡️ **CSRF Protection**: Disabled for token-based authentication (handled by JWT).
  - 🔐 **Secure Password Storage**: Passwords are hashed using a strong algorithm (e.g., BCrypt).
  - 🌍 **HTTPS**: Ensure all communication between the client and server is done over HTTPS to prevent data interception.
  - 🌍 **CORS Configuration**: Configured to allow cross-origin requests from trusted domains only.

## Setup Instructions ⚙️

To get the project up and running locally, follow these steps:

### 1. Clone the repository:

```bash
git clone https://github.com/tatilimongi/Secure-UserForm.git
cd Secure-UserForm
```

### 2. Install dependencies:

- Make sure you have **Java** and **Maven** installed for the backend (Spring Boot).
- For the front-end, you can open the HTML, CSS, and JavaScript files directly in your browser.

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
- **POST /auth/register**: User registration
- **GET /welcome**: Secure user dashboard (requires JWT token for authentication)

### 5. Testing:

Once the application is running, you can test the endpoints with tools like **Postman** or **cURL** by sending requests to the appropriate API endpoints. Ensure that you include the **Authorization** header with the token in requests that require authentication.

## Security Focus 🛡️

This project serves as an educational demonstration of secure development practices, particularly focusing on:

1. 🔑 **Authentication Security**: Ensuring secure login processes using token-based authentication with JWT.
2. 🔐 **Authorization**: Restricting access to specific resources based on user roles and authentication status.
3. ✅ **Input Validation**: Preventing SQL injection, XSS (Cross-Site Scripting), and other common vulnerabilities.
4. 🌍 **Secure Communication**: Emphasizing the importance of HTTPS and secure communication between clients and the server.
5. 🔒 **Password Security**: Storing passwords securely using strong hashing algorithms.

## Future Improvements 🌱

While this project covers the basics of secure user authentication, there are several areas that could be enhanced in the future:

- 🔌 **OAuth 2.0 Integration**: Implement OAuth 2.0 for third-party login options (Google, Facebook, etc.).
- 📧 **Email Verification**: Implement email verification during the registration process.
- 🧑‍🤝‍🧑 **User Roles and Permissions**: Add a role-based access control (RBAC) system to manage user roles and permissions.

## License 📜

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

*This project is part of the Secure Development course at Centro Universitário Tiradentes. The goal is to understand and implement secure practices in web development.* 🌟
