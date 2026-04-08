package com.sna.jpa.spring_jpa_restapi.controller;

import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import com.sna.jpa.spring_jpa_restapi.model.request.StudentRequest;
import com.sna.jpa.spring_jpa_restapi.model.response.ApiResponse;
import com.sna.jpa.spring_jpa_restapi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public ResponseEntity<?> getAllStudents(
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get All Students Successfully")
                .payload(studentService.getAllStudent(size, page))
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Get student by id")
    @GetMapping("/{student-id}")
    public ResponseEntity<?> getStudentById(@PathVariable("student-id") Integer id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Student Not Found with id: " + id)
                            .payload(null)
                            .timestamp(Instant.now())
                            .build());
        }
        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get Student Successfully")
                .payload(student)
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Create student")
    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequest request) {
        Student student = studentService.saveStudent(request);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Course Not Found")
                            .payload(null)
                            .timestamp(Instant.now())
                            .build());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Student>builder()
                        .success(true)
                        .status(HttpStatus.CREATED.value())
                        .message("Save Student Successfully")
                        .payload(student)
                        .timestamp(Instant.now())
                        .build());
    }

    @Operation(summary = "Update student by id")
    @PutMapping("/{student-id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable("student-id") Integer id,
            @RequestBody StudentRequest request) {
        ResponseEntity<?> check = getStudentById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        Student student = studentService.updateStudent(id, request);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Course Not Found")
                            .payload(null)
                            .timestamp(Instant.now())
                            .build());
        }
        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Update Student Successfully")
                .payload(student)
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Delete student by id")
    @DeleteMapping("/{student-id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("student-id") Integer id) {
        ResponseEntity<?> check = getStudentById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Delete Student Successfully")
                .payload(null)
                .timestamp(Instant.now())
                .build());
    }
}