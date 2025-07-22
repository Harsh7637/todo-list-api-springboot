package com.harsh.todo.todo_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("🚀 Todo List API")
                        .version("v1.0")
                        .description(buildDescription())
                        .contact(new Contact()
                                .name("Harsh Sharma")
                                .email("Sharmahs812004@gmail.com")
                                .url("https://github.com/Harsh7637/todo-list-api-springboot"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                )
                .servers(List.of(
                        new Server()
                                .url("http://todo-list-api-env.eba-kmgsg9ty.eu-north-1.elasticbeanstalk.com")
                                .description("🌐 Production Server (AWS Elastic Beanstalk)"),
                        new Server()
                                .url("http://localhost:5000")
                                .description("🔧 Development Server (Local)")
                ))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("🔐 Enter your JWT token here (without 'Bearer ' prefix)")
                ));
    }

    private String buildDescription() {
        return """
                ## 📋 Welcome to Todo List API!
                
                A **secure** and **feature-rich** REST API for managing your personal todo lists, built with Spring Boot and JWT authentication.
                
                ---
                
                ### 🚀 Quick Start Guide
                
                **Step 1: Register a new account**
                ```
                POST /register
                {
                  "name": "Your Name",
                  "email": "your@email.com", 
                  "password": "your-password"
                }
                ```
                
                **Step 2: Login to get your JWT token**
                ```
                POST /login
                {
                  "email": "your@email.com",
                  "password": "your-password"
                }
                ```
                
                **Step 3: Copy the token and click "Authorize" 🔒 button above**
                - Paste your JWT token (without 'Bearer ' prefix)
                - Now you can test all protected endpoints!
                
                **Step 4: Start managing your todos! ✅**
                - Create, read, update, and delete your personal todos
                - All operations are secure and user-specific
                
                ---
                
                ### 🔧 Features & Capabilities
                - 🔐 **Secure Authentication**: JWT-based user registration and login
                - 📋 **Personal Todo Management**: Each user sees only their own todos
                - 🔄 **Full CRUD Operations**: Create, read, update, delete todos
                - 📄 **Pagination Support**: Handle large todo lists efficiently
                - 🛡️ **Security First**: All endpoints protected, passwords hashed
                - ☁️ **Production Ready**: Deployed on AWS with PostgreSQL
                
                ---
                
                ### 📚 Documentation & Resources
                
                **📖 Complete Setup Guide**: [Readme.md](https://github.com/Harsh7637/todo-list-api-springboot/blob/main/Readme.md)
                
                **🔗 GitHub Repository**: [https://github.com/Harsh7637/todo-list-api-springboot](https://github.com/Harsh7637/todo-list-api-springboot)
                
                **📮 Postman Collection**: Detailed testing guide available in README
                
                **🌐 Live Demo**: Try the API directly using this Swagger interface!
                
                ---
                
                ### ⚡ Pro Tips
                - **Tokens expire after 24 hours** - login again if you get 401 errors
                - **Use the Authorize button** 🔒 above to avoid manually adding headers
                - **Check the README** for complete Postman setup and deployment guides
                - **Each user can only see their own todos** - this is by design for security
                
                ---
                
                ### 🤝 Need Help?
                - 📧 **Email**: Sharmahs812004@gmail.com
                - 🐙 **GitHub Issues**: [Report bugs or request features](https://github.com/Harsh7637/todo-list-api-springboot/issues)
                - 📖 **Documentation**: Complete setup guide in README
                
                ---
                
                **Made with ❤️ by Harsh Sharma | Built with Spring Boot & JWT**
                """;
    }
}