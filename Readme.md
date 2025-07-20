# Todo List API

A simple REST API for managing todo lists, built with Spring Boot and secured with JWT authentication. Perfect for learning or as a starting point for larger projects.

## Features

- **User Registration & Login** - Create accounts and authenticate with JWT tokens
- **Personal Todo Lists** - Each user can only see and manage their own todos
- **Full CRUD Operations** - Create, read, update, and delete todos
- **Pagination Support** - Handle large todo lists efficiently
- **Secure by Default** - Passwords are hashed, endpoints are protected
- **Easy Setup** - Uses H2 in-memory database, no external dependencies

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security 6
- JWT for authentication
- JPA/Hibernate for database operations
- H2 Database (in-memory)
- Maven for dependency management

## API Endpoints

### Authentication
- `POST /register` - Create a new user account
- `POST /login` - Login and get JWT token

### Todo Management (Authentication Required)
- `GET /todos` - Get your todos with pagination
- `POST /todos` - Create a new todo
- `GET /todos/{id}` - Get a specific todo by ID
- `PUT /todos/{id}` - Update an existing todo
- `DELETE /todos/{id}` - Delete a todo

## Quick Start

### What you need
- Java 17 or newer
- Maven 3.6+
- Postman (for testing the API)

### Running the project

1. **Clone and navigate to the project**
```bash
git clone https://github.com/Harsh7637/todo-api.git
cd todo-api
```

2. **Start the application**
```bash
mvn spring-boot:run
```

3. **API is now running at** `http://localhost:8080`

The H2 database console is available at `http://localhost:8080/h2-console` if you need to check the data.

## Testing with Postman

### Setting up your environment

First, create a new environment in Postman:
- Environment name: `Todo API`
- Add these variables:
    - `baseUrl` = `http://localhost:8080`
    - `token` = (leave empty for now)

### 1. Register a new user

**POST** `{{baseUrl}}/register`

Headers:
```
Content-Type: application/json
```

Body:
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

### 2. Login to get your token

**POST** `{{baseUrl}}/login`

Headers:
```
Content-Type: application/json
```

Body:
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

**Important:** After login, copy the token from the response and save it in your Postman environment variable `token`. Or add this script to the Tests tab of your login request:

```javascript
if (pm.response.code === 200) {
    const response = pm.response.json();
    pm.environment.set("token", response.token);
}
```

### 3. Create your first todo

**POST** `{{baseUrl}}/todos`

Headers:
```
Content-Type: application/json
Authorization: Bearer {{token}}
```

Body:
```json
{
  "title": "Buy groceries",
  "description": "Need to get milk, eggs, and bread from the store"
}
```

### 4. Get all your todos

**GET** `{{baseUrl}}/todos?page=0&size=10`

Headers:
```
Authorization: Bearer {{token}}
```

### 5. Get a specific todo

**GET** `{{baseUrl}}/todos/1`

Headers:
```
Authorization: Bearer {{token}}
```

### 6. Update a todo

**PUT** `{{baseUrl}}/todos/1`

Headers:
```
Content-Type: application/json
Authorization: Bearer {{token}}
```

Body:
```json
{
  "title": "Buy groceries - Updated",
  "description": "Get milk, eggs, bread, and some fruits too",
  "completed": true
}
```

### 7. Delete a todo

**DELETE** `{{baseUrl}}/todos/1`

Headers:
```
Authorization: Bearer {{token}}
```

## Project Structure

```
src/main/java/com/harsh/todo/todo_api/
├── config/          # Security and app configuration
├── controller/      # REST API endpoints
├── dto/            # Request/Response objects
├── model/          # Database entities (User, Todo)
├── repository/     # Data access layer
├── security/       # JWT and authentication logic
└── service/        # Business logic
```

## API Responses

### Successful registration
```json
{
  "message": "User registered successfully",
  "userId": 1
}
```

### Login response
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

### Todo creation
```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Need to get milk, eggs, and bread from the store",
  "completed": false,
  "createdAt": "2025-01-20T10:30:00",
  "updatedAt": "2025-01-20T10:30:00"
}
```

### Paginated todos
```json
{
  "content": [
    {
      "id": 1,
      "title": "Buy groceries",
      "description": "Need to get milk, eggs, and bread",
      "completed": false,
      "createdAt": "2025-01-20T10:30:00",
      "updatedAt": "2025-01-20T10:30:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1
}
```

## Status Codes

- `200` - Request successful
- `201` - Resource created successfully
- `400` - Bad request (check your data)
- `401` - Unauthorized (login required or token expired)
- `403` - Forbidden (you can't access this resource)
- `404` - Resource not found
- `500` - Something went wrong on the server

### Error responses look like this:
```json
{
  "message": "Todo not found",
  "status": 404,
  "timestamp": "2025-01-20T10:30:00"
}
```

## Configuration

The app uses these default settings in `application.properties`:

```properties
# Server
server.port=8080

# Database (H2 in-memory)
spring.datasource.url=jdbc:h2:mem:todoapi
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

# JWT
jwt.secret=mySecretKey
jwt.expiration=86400000
```

## Important Notes

⚠️ **Data persistence**: Since we're using H2's in-memory database, all your data will be lost when you restart the application. This is perfect for development and testing.

⚠️ **Token expiration**: JWT tokens expire after 24 hours. You'll need to login again to get a fresh token.

⚠️ **Security**: This setup is great for development. For production, you'd want to use a real database like PostgreSQL and stronger JWT secrets.

## Common Issues & Solutions

**"Token expired" errors**: Login again to get a new token

**"Access denied" errors**: Make sure you're including the Authorization header with a valid token

**Can't see other users' todos**: This is by design! Each user can only see their own todos

**Want to check the database**: Visit `http://localhost:8080/h2-console` and use the connection details from application.properties

## Deployment

This API works well on:
- Railway (easiest option)
- Heroku
- Render
- Any cloud platform that supports Java applications

For production deployment, consider switching to PostgreSQL and updating the JWT secret.

## Author

Built by **Harsh** - [GitHub](https://github.com/Harsh7637)

If you found this helpful, give it a star! ⭐