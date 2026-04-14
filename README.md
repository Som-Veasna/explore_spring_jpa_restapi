# Spring JPA REST API

A RESTful API built with Spring Boot, Spring Data JPA, and PostgreSQL for managing courses, instructors, and students in an educational system.

## Features

- **Course Management**: Create, read, update, and delete courses
- **Instructor Management**: Manage course instructors
- **Student Management**: Handle student enrollments and course associations
- **RESTful API**: Well-structured endpoints with consistent response format
- **API Documentation**: Integrated Swagger UI for interactive API documentation
- **Database Integration**: PostgreSQL with Hibernate ORM

## Technologies Used

- **Java 21**
- **Spring Boot 4.0.5**
- **Spring Data JPA**
- **Spring Web MVC**
- **PostgreSQL**
- **Lombok**
- **SpringDoc OpenAPI** (Swagger)
- **Maven**

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker and Docker Compose (for PostgreSQL)

## Database Schema

The application uses three main entities:

### Instructor
- `instructor_id` (Primary Key)
- `instructor_name`
- `email`

### Course
- `course_id` (Primary Key)
- `course_name`
- `description`
- `instructor_id` (Foreign Key)

### Student
- `student_id` (Primary Key)
- `student_name`
- `email`
- `phone_number`

### Student-Course (Many-to-Many)
- `student_id` (Foreign Key)
- `course_id` (Foreign Key)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd spring_jpa_restapi
```

### 2. Database Setup

Start PostgreSQL using Docker Compose:

```bash
docker-compose up -d
```

**Note**: The `compose.yaml` file sets up PostgreSQL with:
- Database: `mydatabase`
- Username: `myuser`
- Password: `secret`
- Port: `5432`

However, the `application.properties` is configured for database name `spring_jpa_restapi`. You may need to update either the compose file or the properties file to match.

### 3. Build the Application

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Course Endpoints
- `GET /api/v1/courses` - Get all courses (paginated)
- `GET /api/v1/courses/{course-id}` - Get course by ID
- `POST /api/v1/courses` - Create a new course
- `PUT /api/v1/courses/{course-id}` - Update a course
- `DELETE /api/v1/courses/{course-id}` - Delete a course
- `GET /api/v1/courses/{course-id}/students` - Get all students enrolled in a course

### Instructor Endpoints
- `GET /api/v1/instructors` - Get all instructors (paginated)
- `GET /api/v1/instructors/{instructor-id}` - Get instructor by ID
- `POST /api/v1/instructors` - Create a new instructor
- `PUT /api/v1/instructors/{instructor-id}` - Update an instructor
- `DELETE /api/v1/instructors/{instructor-id}` - Delete an instructor
- `GET /api/v1/instructors/{instructor-id}/courses` - Get all courses by an instructor

### Student Endpoints
- `GET /api/v1/students` - Get all students (paginated)
- `GET /api/v1/students/{student-id}` - Get student by ID
- `POST /api/v1/students` - Create a new student
- `PUT /api/v1/students/{student-id}` - Update a student
- `DELETE /api/v1/students/{student-id}` - Delete a student
- `GET /api/v1/students/{student-id}/courses` - Get all courses enrolled by a student
- `POST /api/v1/students/{student-id}/courses/{course-id}` - Enroll student in a course
- `DELETE /api/v1/students/{student-id}/courses/{course-id}` - Unenroll student from a course

## API Documentation

Once the application is running, you can access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

## Configuration

The application configuration can be found in `src/main/resources/application.properties`:

- Database URL: `jdbc:postgresql://localhost:5432/spring_jpa_restapi`
- Server Port: `8080`
- Hibernate DDL Auto: `update`
- SQL Logging: Enabled

## Testing

Run the tests using Maven:

```bash
mvn test
```

