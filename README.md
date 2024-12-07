# Kotlin API Example

## Requirements:
- JDK 21
- Maven

## Setup Instructions:

1. Clone the repository.
2. Run `mvn clean install` to build the application.
3. Run `mvn spring-boot:run` to start the server.

## Endpoints:
- **POST /signup**: Create a new user.
- **POST /login**: Login and get a JWT token.
- **POST /creator/course**: Create a course.
- **GET /creator/courses**: Get all courses by creator.
- **GET /stats**: Fetch statistics for all bought courses.

## Docker Setup:
1. Build the Docker image: `docker build -t kotlin-api .`
2. Run the Docker container: `docker run -p 8080:8080 kotlin-api`
