# Role-Based Authentication (RBAC) Backend with Spring Boot

This is a Spring Boot application demonstrating Role-Based Authentication (RBAC). It provides a set of APIs for handling user registration, login, role management, and token-based authentication using JWT. The application uses an H2 database to manage user data and roles, and JWT tokens for authentication and authorization.

## Prerequisites
- Java 11 or higher
- Spring Boot
- H2 Database (used for development)
- Postman or similar API testing tool

## Running the Application
1. Download the JAR file for the application.
2. Open a terminal and run the following command to start the application which runs at port 3000:

```bash
java -jar <path_to_the_jar_file>
```

## Initial Setup
- The `CommandLineRunner` will automatically initialize the data in the H2 database on startup.
- Three tokens will be generated for different roles: `admin`, `manager`, and `user`. These tokens are used to test the functionality of different roles and permissions.

## API Routes

### 1. Demo Route
- **Endpoint:** `/api/demo`
- **Method:** `GET`
- **Authorization:** Bearer Token (access token in the authorization header)
- **Description:** Demonstrates role-based access to data. Depending on the token used, different sets of data are returned:
  - **Admin:** Access to all category data.
  - **Manager:** Access to only Category A data.
  - **User:** Access to only Category B data.

### 2. User Registration
- **Endpoint:** `/api/auth/register`
- **Method:** `POST`
- **Request Body:**

```json
{
  "firstname": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "password": "password123",
  "role": "USER"  // or "MANAGER" / "ADMIN"
}
```

- **Response:** A set of access and refresh tokens for the registered user.

### 3. User Login
- **Endpoint:** `/api/auth/authenticate`
- **Method:** `POST`
- **Request Body:**

```json
{
  "email": "john.doe@example.com",
  "password": "password123"
}
```

- **Description:** Logs in a user by checking the provided email and password against the encoded password in the database.
- **Response:** Returns the access and refresh tokens. If valid credentials are provided, the tokens will be updated in the database.

### 4. Admin Route
- **Endpoint:** `/api/admin`
- **Method:** `GET`
- **Authorization:** Bearer Token (Admin Role)
- **Description:** Returns details of all users, managers, and admins. Only accessible by users with the `ADMIN` role.

### 5. Manager Route
- **Endpoint:** `/api/manager`
- **Method:** `GET`
- **Authorization:** Bearer Token (Manager Role)
- **Description:** Returns details of all users and managers. Accessible only by users with the `MANAGER` role. Admin details will not be shown.

### 6. User Route
- **Endpoint:** `/api/user`
- **Method:** `GET`
- **Authorization:** Bearer Token (User Role)
- **Description:** Returns details of users only. Accessible only by users with the `USER` role. Managers and admins will not be shown.

### 7. Logout
- **Endpoint:** `/auth/logout`
- **Method:** `POST`
- **Description:** Logs out the user by removing the token from the system.

### 8. Get Access Token
- **Endpoint:** `/auth/accesstoken`
- **Method:** `GET`
- **Description:** Retrieves a new access token from the stored refresh token.

### 9. Get Refresh Token
- **Endpoint:** `/auth/refreshToken`
- **Method:** `GET`
- **Description:** Retrieves a new access token using a refresh token.

## Data Initialization on First Run
Upon the first run of the application, the following default data will be initialized:

- **Admin** user with a role of `ADMIN`
- **Manager** user with a role of `MANAGER`
- **User** user with a role of `USER`

Each user will receive their respective access tokens for testing role-based access.

## Technology Stack
- **Spring Boot** - Backend framework
- **H2 Database** - In-memory database for development
- **JWT (JSON Web Tokens)** - For user authentication and authorization
- **Spring Security** - For securing the APIs

## Conclusion
This application provides a simple demonstration of Role-Based Authentication (RBAC) using Spring Boot and JWT. It helps in understanding how roles can be assigned to users and how permissions can be managed via API endpoints.

