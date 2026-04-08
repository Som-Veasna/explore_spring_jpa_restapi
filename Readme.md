properties# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Server Port
server.port=8080

# Swagger UI
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs

✅ Replace your_db_name, your_username, and your_password with your actual PostgreSQL credentials.
now give readme about this project4:04 PMREADME.md
markdown# 📚 Student Course Management System

A RESTful API built with **Spring Boot** and **Spring Data JPA** for managing students, courses, and instructors.

---

## 🛠️ Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.x |
| Spring Data JPA | 3.x |
| PostgreSQL | 15+ |
| Lombok | Latest |
| Swagger (SpringDoc) | Latest |

---

## 📁 Project Structure
src/main/java/com/sna/homework002/
├── controller/
│   ├── InstructorController.java
│   ├── CourseController.java
│   └── StudentController.java
├── model/
│   ├── entity/
│   │   ├── Instructor.java
│   │   ├── Course.java
│   │   └── Student.java
│   ├── request/
│   │   ├── InstructorRequest.java
│   │   ├── CourseRequest.java
│   │   └── StudentRequest.java
│   └── response/
│       └── ApiResponse.java
├── repository/
│   ├── InstructorRepository.java
│   ├── CourseRepository.java
│   └── StudentRepository.java
└── service/
├── InstructorService.java
├── CourseService.java
├── StudentService.java
└── impl/
├── InstructorServiceImpl.java
├── CourseServiceImpl.java
└── StudentServiceImpl.java

---

## 🗄️ Database Schema

```sql
CREATE TABLE instructors (
    instructor_id   SERIAL       PRIMARY KEY,
    instructor_name VARCHAR(100) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE courses (
    course_id     SERIAL       PRIMARY KEY,
    course_name   VARCHAR(150) NOT NULL,
    description   TEXT,
    instructor_id INT          NOT NULL REFERENCES instructors(instructor_id) ON DELETE CASCADE
);

CREATE TABLE students (
    student_id   SERIAL       PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email        VARCHAR(150),
    phone_number VARCHAR(20)
);

CREATE TABLE student_course (
    student_id INT NOT NULL REFERENCES students(student_id) ON DELETE CASCADE,
    course_id  INT NOT NULL REFERENCES courses(course_id)   ON DELETE CASCADE,
    PRIMARY KEY (student_id, course_id)
);
```

---

## ⚙️ Setup & Installation

### 1. Clone the project
```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo
```

### 2. Configure database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the project
```bash
./mvnw spring-boot:run
```

### 4. Access Swagger UI
http://localhost:8080/swagger-ui.html

---

## 📌 API Endpoints

### 👨‍🏫 Instructor `/api/v1/instructors`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Get all instructors |
| GET | `/{instructor-id}` | Get instructor by id |
| POST | `/` | Create instructor |
| PUT | `/{instructor-id}` | Update instructor |
| DELETE | `/{instructor-id}` | Delete instructor |
| GET | `/{instructor-id}/courses` | Get all courses by instructor |

### 📚 Course `/api/v1/courses`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Get all courses |
| GET | `/{course-id}` | Get course by id |
| POST | `/` | Create course |
| PUT | `/{course-id}` | Update course |
| DELETE | `/{course-id}` | Delete course |
| GET | `/{course-id}/students` | Get all students by course |

### 👨‍🎓 Student `/api/v1/students`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Get all students |
| GET | `/{student-id}` | Get student by id |
| POST | `/` | Create student |
| PUT | `/{student-id}` | Update student |
| DELETE | `/{student-id}` | Delete student |

---

## 📝 Request & Response Examples

### Create Instructor
**POST** `/api/v1/instructors`
```json
{
  "name": "John Doe",
  "email": "john@email.com"
}
```
**Response `201`:**
```json
{
  "success": true,
  "status": 201,
  "message": "Save Instructor Successfully",
  "payload": {
    "instructorId": 1,
    "instructorName": "John Doe",
    "email": "john@email.com"
  },
  "timestamp": "2026-01-01T00:00:00Z"
}
```

### Create Course
**POST** `/api/v1/courses`
```json
{
  "courseName": "Spring Boot",
  "description": "Learn Spring Boot",
  "instructorId": 1
}
```
**Response `201`:**
```json
{
  "success": true,
  "status": 201,
  "message": "Save Course Successfully",
  "payload": {
    "courseId": 1,
    "courseName": "Spring Boot",
    "description": "Learn Spring Boot",
    "instructor": {
      "instructorId": 1,
      "instructorName": "John Doe",
      "email": "john@email.com"
    }
  },
  "timestamp": "2026-01-01T00:00:00Z"
}
```

### Create Student
**POST** `/api/v1/students`
```json
{
  "name": "Sara",
  "email": "sara@email.com",
  "phoneNumber": "012-111-001",
  "courseId": [1, 2]
}
```
**Response `201`:**
```json
{
  "success": true,
  "status": 201,
  "message": "Save Student Successfully",
  "payload": {
    "studentId": 1,
    "studentName": "Sara",
    "email": "sara@email.com",
    "phoneNumber": "012-111-001",
    "courses": [
      {
        "courseId": 1,
        "courseName": "Spring Boot",
        "description": "Learn Spring Boot",
        "instructor": { ... }
      }
    ]
  },
  "timestamp": "2026-01-01T00:00:00Z"
}
```

### Not Found Response `404`
```json
{
  "success": false,
  "status": 404,
  "message": "Student Not Found with id: 99",
  "payload": null,
  "timestamp": "2026-01-01T00:00:00Z"
}
```

---

## 🔗 Entity Relationships
instructors ──< courses ──< student_course >── students
One Instructor → Many Courses
One Course     → Many Students (via student_course)
One Student    → Many Courses  (via student_course)

---

## 👨‍💻 Author

- **Name:** SNA
- **Project:** Homework 002
- **Framework:** Spring Boot + Spring Data JPA