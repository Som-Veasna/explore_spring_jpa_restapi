package com.sna.jpa.spring_jpa_restapi.controller;

import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import com.sna.jpa.spring_jpa_restapi.model.request.CourseRequest;
import com.sna.jpa.spring_jpa_restapi.model.response.ApiResponse;
import com.sna.jpa.spring_jpa_restapi.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Get all courses")
    @GetMapping
    public ResponseEntity<?> getAllCourse(
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.ok(ApiResponse.<List<Course>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get All Courses Successfully")
                .payload(courseService.getAllCourse(size, page))
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Get course by id")
    @GetMapping("/{course-id}")
    public ResponseEntity<?> getCourseById(@PathVariable("course-id") Integer id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Course Not Found with id: " + id)
                            .payload(null)
                            .timestamp(Instant.now())
                            .build());
        }
        return ResponseEntity.ok(ApiResponse.<Course>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get Course Successfully")
                .payload(course)
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Create course")
    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody CourseRequest request) {
        Course course = courseService.saveCourse(request);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Instructor Not Found with id: " + request.getInstructorId())
                            .payload(null)
                            .timestamp(Instant.now())
                            .build());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Course>builder()
                        .success(true)
                        .status(HttpStatus.CREATED.value())
                        .message("Save Course Successfully")
                        .payload(course)
                        .timestamp(Instant.now())
                        .build());
    }

    @Operation(summary = "Update course by id")
    @PutMapping("/{course-id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable("course-id") Integer id,
            @RequestBody CourseRequest request) {
        ResponseEntity<?> check = getCourseById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        Course course = courseService.updateCourse(id, request);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Instructor Not Found with id: " + request.getInstructorId())
                            .payload(null)
                            .timestamp(Instant.now())
                            .build());
        }
        return ResponseEntity.ok(ApiResponse.<Course>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Update Course Successfully")
                .payload(course)
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Delete course by id")
    @DeleteMapping("/{course-id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("course-id") Integer id) {
        ResponseEntity<?> check = getCourseById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        courseService.deleteCourse(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Delete Course Successfully")
                .payload(null)
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Get all students by course id")
    @GetMapping("/{course-id}/students")
    public ResponseEntity<?> getAllStudentByCourseId(@PathVariable("course-id") Integer id) {
        ResponseEntity<?> check = getCourseById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get All Students By Course Successfully")
                .payload(courseService.getAllStudentByCourseId(id))
                .timestamp(Instant.now())
                .build());
    }
}