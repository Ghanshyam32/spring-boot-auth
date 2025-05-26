# spring-boot-auth

A full-featured authentication service built with Spring Boot, Spring Security, JPA, and PostgreSQL (Supabase). This project supports user registration, email verification, JWT-based authentication, and secure login.

## Features
- User registration with email verification
- JWT-based authentication and authorization
- Secure password hashing (BCrypt)
- PostgreSQL (Supabase) integration
- Email sending via Gmail SMTP
- Easily extensible for roles, permissions, and more

## Technologies Used
- Java 17+
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- PostgreSQL (Supabase)
- Lombok
- Jakarta Mail
- JWT (io.jsonwebtoken)
- Maven

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/Ghanshyam32/spring-boot-auth.git
cd spring-boot-auth
```

### 2. Configure Environment Variables
Create a `.env` file in the project root or set the following environment variables (recommended for secrets):

```
SPRING_DATASOURCE_URL=jdbc:postgresql://<supabase_host>:5432/<database>
SPRING_DATASOURCE_USERNAME=<your_db_username>
SPRING_DATASOURCE_PASSWORD=<your_db_password>
JWT_SECRET_KEY=<your_jwt_secret>
SUPPORT_EMAIL=<your_gmail_address>
APP_PASSWORD=<your_gmail_app_password>
```

**Tip**: For Gmail, generate an App Password if 2FA is enabled.

### 3. Edit `application.properties` if Needed
Check `src/main/resources/application.properties` for:

```
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

security.jwt.secret-key=${JWT_SECRET_KEY}
security.jwt.expiration-time=3600000

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${SUPPORT_EMAIL}
spring.mail.password=${APP_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

spring.config.import=optional:file:.env[.properties]
```

### 4. Install Dependencies
This project uses Maven Wrapper. You can use the included scripts:

```bash
./mvnw clean install
```

or

```bash
mvn clean install
```

### 5. Run the Application
```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080` by default.

## API Endpoints

### Authentication
- **POST /auth/signup**  
  Register a new user.  
  **Request body**:
  ```json
  {
    "username": "yourUsername",
    "email": "your@email.com",
    "password": "yourPassword"
  }
  ```

- **POST /auth/login**  
  Authenticate and receive a JWT.  
  **Request body**:
  ```json
  {
    "email": "your@email.com",
    "password": "yourPassword"
  }
  ```

- **POST /auth/verify**  
  Verify email with code sent to your inbox.  
  **Request body**:
  ```json
  {
    "email": "your@email.com",
    "verificationCode": "123456"
  }
  ```

- **POST /auth/resend-verification**  
  Resend verification code to your email.  
  **Request body**:
  ```json
  {
    "email": "your@email.com"
  }
  ```

## Database (Supabase PostgreSQL) Setup
1. Create a project at [Supabase](https://supabase.com).
2. Go to **Project Settings > Database** to find your connection details.
3. Use these details in your `.env` or `application.properties`.

## Email Setup
- Uses Gmail SMTP by default.
- For production, consider using a transactional email service (SendGrid, Mailgun, etc.).

## Customization
- **Add roles/permissions**: Extend the `User` entity and security config.
- **Change database**: Update `application.properties` and dependencies.
- **Change email provider**: Update `spring.mail.*` properties.

## Development Notes
- Uses Lombok for boilerplate reduction (install Lombok plugin for your IDE).
- Uses Spring Boot's auto-configuration for most settings.
- JWT secret must be strong and kept secret.

## Troubleshooting
- **Database connection errors**: Double-check your Supabase credentials and network access.
- **Email not sending**: Ensure your Gmail account allows SMTP and you are using an App Password.
- **Schema errors**: Ensure your entity fields and database columns match (especially after migrations).

## License
This project is for educational/demo purposes. Feel free to fork and adapt for your own use!

## Author
Ghanshyam Mishra