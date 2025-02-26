# Prime Service

A microservice application that checks if a number is prime. The application consists of a backend service built with Spring Boot and a frontend built with HTML, CSS, and JavaScript.

## Features

- User registration and authentication
- JWT-based security
- Prime number checking
- PostgreSQL database for user storage
- Docker Compose for easy deployment

## Prerequisites

- Docker and Docker Compose
- Java 17 or higher (for development)
- Maven (for development)

## Running the Application

### Using Docker Compose

1. Clone the repository:

   ```bash
   git clone https://github.com/your-organization/prime-service.git
   cd prime-service
   ```

2. Start the application using Docker Compose:

   ```bash
   cd manage-containers
   ./reboot.sh  # On Unix/Mac
   # OR
   reboot.bat  # On Windows
   ```

3. Access the frontend:
   - Open the `practicum3/index.html` file in your browser
   - Or serve it using a simple HTTP server:
     ```bash
     cd practicum3
     python -m http.server 8000
     ```
     Then open http://localhost:8000 in your browser

### Manual Setup (Development)

1. Start the PostgreSQL database:

   ```bash
   docker run -d --name primes-database -p 5001:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_DB=primes postgres:alpine
   ```

2. Build and run the Spring Boot application:

   ```bash
   mvn clean package
   java -jar target/prime-service-0.0.1-SNAPSHOT.jar
   ```

3. Access the frontend as described above.

## API Endpoints

### Authentication

- **Register**: `POST /auth/register`

  - Request body: `{ "username": "user", "password": "password" }`
  - Response: User object

- **Login**: `POST /auth/login`
  - Request body: `{ "username": "user", "password": "password" }`
  - Response: JWT token

### Prime Checking

- **Check Prime**: `GET /primes/{number}`
  - Requires authentication (JWT token in Authorization header)
  - Response: `{ "isPrime": true/false }`

## Architecture

- **Backend**: Spring Boot with Spring Security and JWT authentication
- **Database**: PostgreSQL for user storage
- **Frontend**: HTML, CSS, and JavaScript
- **Containerization**: Docker and Docker Compose

## Development

### Backend Structure

- `model`: Data models (Customer)
- `repository`: Data access layer
- `service`: Business logic
- `controller`: API endpoints
- `security`: Security configuration

### Frontend Structure

- `index.html`: Main HTML file
- `styles.css`: CSS styles
- `script.js`: JavaScript code for API interaction

## Troubleshooting

- If you encounter database connection issues, ensure PostgreSQL is running and accessible
- Check the logs for detailed error messages
- Ensure the correct environment variables are set in Docker Compose

## License

This project is licensed under the MIT License - see the LICENSE file for details.
