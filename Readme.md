# Todo List API

A RESTful API built with Spring Boot that allows users to manage their to-do lists with JWT authentication.

## 🚀 Features

- **User Authentication**: JWT-based registration and login
- **CRUD Operations**: Complete todo management
- **User Authorization**: Users can only access their own todos
- **Pagination**: Efficient data retrieval with pagination support
- **Secure**: Password hashing with BCrypt and proper authentication
- **Database**: JPA/Hibernate with H2 (development) and PostgreSQL support

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security 6**
- **JWT (JSON Web Tokens)**
- **JPA/Hibernate**
- **H2 Database** (development)
- **Maven**

## 📋 API Endpoints

### Authentication
- `POST /register` - Register a new user
- `POST /login` - Login user

### Todo Management (Requires Authentication)
- `GET /todos` - Get paginated todos
- `POST /todos` - Create a new todo
- `GET /todos/{id}` - Get specific todo
- `PUT /todos/{id}` - Update todo
- `DELETE /todos/{id}` - Delete todo

## 🔧 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/todo-api.git
cd todo-api
```

2. **Run the application**
```bash
mvn spring-boot:run
```

3. **Access the API**
```
http://localhost:8080
```

## 🧪 Usage Examples

### 1. Register a User
```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 3. Create a Todo (with token)
```bash
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "title": "Buy groceries",
    "description": "Buy milk, eggs, and bread"
  }'
```

### 4. Get Todos with Pagination
```bash
curl -X GET "http://localhost:8080/todos?page=1&limit=10" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🏗️ Project Structure

```
src/main/java/com/harsh/todo/todo_api/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── model/          # JPA entities
├── repository/     # Data access layer
├── security/       # Security configuration
└── service/        # Business logic
```

## 🔒 Security Features

- JWT token-based authentication
- Password encryption using BCrypt
- User authorization (users can only access their own todos)
- Protected endpoints with proper HTTP status codes
- Input validation and error handling

## 🌍 Environment Variables

For production deployment, set these environment variables:

```env
DATABASE_URL=jdbc:postgresql://localhost:5432/todoapi
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
JWT_SECRET=your_super_secret_jwt_key_minimum_256_bits
PORT=8080
```

## 📚 API Response Examples

### Successful Todo Creation
```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Buy milk, eggs, and bread"
}
```

### Paginated Todos Response
```json
{
  "data": [
    {
      "id": 1,
      "title": "Buy groceries",
      "description": "Buy milk, eggs, bread"
    }
  ],
  "page": 1,
  "limit": 10,
  "total": 1
}
```

### Error Response
```json
{
  "message": "Unauthorized"
}
```

## 🚀 Deployment

This API can be deployed on:
- **Railway** (recommended)
- **Heroku**
- **Render**
- **AWS/Google Cloud**

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**Harsh** - [Your GitHub Profile](https://github.com/YOUR_USERNAME)

---

⭐ If this project helped you, please give it a star!