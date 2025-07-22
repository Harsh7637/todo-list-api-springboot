# 📝 Todo List API

A simple REST API for managing todo lists, built with Spring Boot and secured with JWT authentication. Perfect for learning or as a starting point for larger projects.

**📋 Project based on:** [roadmap.sh Todo List API Project](https://roadmap.sh/projects/todo-list-api)

## ✨ Features

- **🔐 User Registration & Login** - Create accounts and authenticate with JWT tokens
- **📋 Personal Todo Lists** - Each user can only see and manage their own todos
- **🔄 Full CRUD Operations** - Create, read, update, and delete todos
- **📄 Pagination Support** - Handle large todo lists efficiently
- **🛡️ Secure by Default** - Passwords are hashed, endpoints are protected
- **☁️ Production Ready** - Deployed on AWS Elastic Beanstalk with PostgreSQL
- **⚡ Easy Setup** - Works locally with H2 or in production with PostgreSQL

## 📖 API Documentation with Swagger

**🌐 Live API Documentation:** [http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html](http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html)

For local development, visit `http://localhost:5000/swagger-ui.html` or `http://localhost:5000/swagger-ui/index.html` to explore and test your API with Swagger UI. All endpoints are documented with sample requests and schema annotations.

> Swagger is automatically enabled via SpringDoc OpenAPI.

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security 6
- JWT for authentication
- JPA/Hibernate for database operations
- **Production**: PostgreSQL on AWS RDS
- **Development**: H2 Database (in-memory)
- SpringDoc OpenAPI (Swagger documentation)
- **Deployment**: AWS Elastic Beanstalk
- Maven for dependency management

## 🔌 API Endpoints

### 🔑 Authentication
- `POST /register` - Create a new user account
- `POST /login` - Login and get JWT token

### 📝 Todo Management (Authentication Required)
- `GET /todos` - Get your todos with pagination
- `POST /todos` - Create a new todo
- `GET /todos/{id}` - Get a specific todo by ID
- `PUT /todos/{id}` - Update an existing todo
- `DELETE /todos/{id}` - Delete a todo

## 🚀 Quick Start

### 📋 What you need
- Java 17 or newer ☕
- Maven 3.6+ 🔧
- Postman (for testing the API) 📮

### 🏃‍♂️ Running the project

1. **📁 Clone and navigate to the project**
```bash
git clone https://github.com/Harsh7637/todo-api.git
cd todo-api
```

2. **▶️ Start the application**
```bash
mvn spring-boot:run
```

3. **🌐 API is now running at** `http://localhost:5000`

The H2 database console is available at `http://localhost:5000/h2-console` if you need to check the data during local development. 💾

**📖 Access Swagger UI at** `http://localhost:5000/swagger-ui.html` to explore the API documentation interactively.

## 🌐 Live Production API

The API is deployed and running live on AWS Elastic Beanstalk:
- **🔗 Live API Base URL**: `http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com`
- **📖 Live Swagger Documentation**: [http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html](http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html)

You can test the live API directly using the production URLs!

## 📮 Testing with Postman

### ⚙️ Setting up your environment

First, create a new environment in Postman:
- Environment name: `Todo API Local` 🌍
- Add these variables:
  - `baseUrl` = `http://localhost:5000` 🔗
  - `token` = (leave empty for now) 🎫

**For testing the live production API, create another environment:**
- Environment name: `Todo API Production` 🌍
- Add these variables:
  - `baseUrl` = `http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com` 🔗
  - `token` = (leave empty for now) 🎫

### 1. 📝 Register a new user

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

### 2. 🔐 Login to get your token

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

**⚠️ Important:** After registration or login, **the server returns only a JWT token**. Copy this token from the response and save it in your Postman environment variable `token`. Or add this script to the Tests tab of your login/register request:

```javascript
if (pm.response.code === 200 || pm.response.code === 201) {
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
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Login response
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
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

### Local Development Configuration

The app uses these settings in `application.properties` for local development:

```properties
# Server
server.port=5000

# Database (H2 in-memory for local development)
spring.datasource.url=jdbc:h2:mem:todoapi
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

# JWT
jwt.secret=mySecretKey
jwt.expiration=86400000

# Swagger/OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### AWS Production Configuration

For production deployment on AWS Elastic Beanstalk with PostgreSQL:

```properties
# === Server Config ===
server.port=5000

# === PostgreSQL Config for AWS Deployment ===
# These values are provided by Elastic Beanstalk environment variables
spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_DATABASE_USERNAME}
spring.datasource.password=${JDBC_DATABASE_PASSWORD}

# === JPA Settings ===
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# === H2 Disabled ===
spring.h2.console.enabled=false

# === JWT ===
# The secret can be overridden by an environment variable in AWS
jwt.secret=${JWT_SECRET:mySecretKey}
jwt.expiration=86400000

# === Swagger/OpenAPI ===
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## AWS Deployment Guide

This API is successfully deployed on AWS using Elastic Beanstalk with PostgreSQL RDS. Here's how you can deploy your own instance:

### Prerequisites for AWS Deployment

- AWS Account with appropriate permissions
- AWS CLI installed and configured
- PostgreSQL dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Steps to Deploy on AWS Elastic Beanstalk

1. **Create RDS PostgreSQL Database**
  - Go to AWS RDS Console
  - Create a new PostgreSQL database
  - Note down the endpoint, username, and password

2. **Prepare Your Application**
  - Update your `application.properties` with the AWS configuration shown above
  - Create a JAR file: `mvn clean package`

3. **Deploy to Elastic Beanstalk**
  - Create a new Elastic Beanstalk application
  - Choose Java platform
  - Upload your JAR file
  - Configure environment variables:
    - `JDBC_DATABASE_URL`: Your RDS endpoint URL
    - `JDBC_DATABASE_USERNAME`: Your database username
    - `JDBC_DATABASE_PASSWORD`: Your database password
    - `JWT_SECRET`: A strong JWT secret for production

4. **Test Your Deployment**
  - Access your Swagger UI at: `http://your-app-url.elasticbeanstalk.com/swagger-ui/index.html`
  - Test the API endpoints

### Environment Variables Required

Set these environment variables in your Elastic Beanstalk configuration:

- `JDBC_DATABASE_URL`: `jdbc:postgresql://your-rds-endpoint:5432/your-database-name`
- `JDBC_DATABASE_USERNAME`: Your PostgreSQL username
- `JDBC_DATABASE_PASSWORD`: Your PostgreSQL password
- `JWT_SECRET`: A secure secret key for JWT token generation

## Adding Swagger to Your Project

To add Swagger documentation to your Todo API, you need to:

### 1. Add SpringDoc OpenAPI dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

### 2. Create OpenAPI configuration (optional but recommended):

```java
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Todo List API")
                        .version("1.0")
                        .description("A simple REST API for managing todo lists with JWT authentication"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
```

### 3. Add annotations to your controllers (examples):

```java
@RestController
@RequestMapping("/todos")
@Tag(name = "Todo Management", description = "Operations related to todo management")
public class TodoController {

    @PostMapping
    @Operation(summary = "Create a new todo", description = "Creates a new todo item for the authenticated user")
    @ApiResponse(responseCode = "201", description = "Todo created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        // implementation
    }
}
```

## Important Notes

⚠️ **Data persistence**:
- **Local Development**: Uses H2 in-memory database, data is lost on restart
- **Production**: Uses PostgreSQL on AWS RDS, data persists permanently

⚠️ **Token expiration**: JWT tokens expire after 24 hours. You'll need to login again to get a fresh token.

⚠️ **Token-only authentication**: Both registration and login endpoints return **only a JWT token**. No user details are returned in the response for security reasons.

⚠️ **Environment-specific behavior**: The app automatically switches between H2 (local) and PostgreSQL (AWS) based on environment variables.

⚠️ **Security**: Production deployment uses environment variables for sensitive data like database credentials and JWT secrets.

## Common Issues & Solutions

**"Token expired" errors**: Login again to get a new token

**"Access denied" errors**: Make sure you're including the Authorization header with a valid token

**Can't see other users' todos**: This is by design! Each user can only see their own todos

**Want to check the database**:
- **Local**: Visit `http://localhost:5000/h2-console` and use the connection details from application.properties
- **Production**: Access your AWS RDS PostgreSQL database through AWS console or database clients

**Swagger UI not loading**: Make sure the SpringDoc dependency is added and the application is running

**AWS deployment issues**: Check your environment variables are properly set in Elastic Beanstalk configuration

## Deployment

This API is **currently live and deployed** on AWS Elastic Beanstalk! 🚀

**🌐 Live Production API**: [http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html](http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html)

### Other deployment options:
- **AWS Elastic Beanstalk** ⭐ (currently deployed here)
- Railway
- Heroku
- Render
- Any cloud platform that supports Java applications

### Production Features:
- ✅ PostgreSQL database on AWS RDS
- ✅ Environment-based configuration
- ✅ Secure JWT token handling
- ✅ Auto-scaling capabilities
- ✅ Professional-grade hosting

## About This Project

This project implements the [Todo List API challenge from roadmap.sh](https://roadmap.sh/projects/todo-list-api), which is designed to help developers practice building REST APIs with authentication and CRUD operations.

## Author

Built by **Harsh** - [GitHub](https://github.com/Harsh7637)

If you found this helpful, give it a star! ⭐