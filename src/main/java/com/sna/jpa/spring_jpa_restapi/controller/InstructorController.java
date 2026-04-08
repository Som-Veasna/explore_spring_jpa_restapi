package com.sna.jpa.spring_jpa_restapi.controller;

import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Instructor;
import com.sna.jpa.spring_jpa_restapi.model.request.InstructorRequest;
import com.sna.jpa.spring_jpa_restapi.model.response.ApiResponse;
import com.sna.jpa.spring_jpa_restapi.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Operation(summary = "Get all instructors")
    @GetMapping
    public ResponseEntity<?> getAllInstructors(
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.ok(ApiResponse.<List<Instructor>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get All Instructors Successfully")
                .payload(instructorService.getAllInstructors(size, page))
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Get instructor by id")
    @GetMapping("/{instructor-id}")
    public ResponseEntity<?> getInstructorById(@PathVariable("instructor-id") Integer id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .status(HttpStatus.NOT_FOUND.value())
                            .message("Instructor Not Found with id: " + id)
                            .payload(null)
                            .timestamp(Instant.now())
                            .build());
        }
        return ResponseEntity.ok(ApiResponse.<Instructor>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get Instructor Successfully")
                .payload(instructor)
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Create instructor")
    @PostMapping
    public ResponseEntity<?> saveInstructor(@RequestBody InstructorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Instructor>builder()
                        .success(true)
                        .status(HttpStatus.CREATED.value())
                        .message("Save Instructor Successfully")
                        .payload(instructorService.saveInstructor(request))
                        .timestamp(Instant.now())
                        .build());
    }

    @Operation(summary = "Update instructor by id")
    @PutMapping("/{instructor-id}")
    public ResponseEntity<?> updateInstructor(
            @PathVariable("instructor-id") Integer id,
            @RequestBody InstructorRequest request) {
        ResponseEntity<?> check = getInstructorById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        return ResponseEntity.ok(ApiResponse.<Instructor>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Update Instructor Successfully")
                .payload(instructorService.updateInstructor(id, request))
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Delete instructor by id")
    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable("instructor-id") Integer id) {
        ResponseEntity<?> check = getInstructorById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        instructorService.deleteInstructorById(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Delete Instructor Successfully")
                .payload(null)
                .timestamp(Instant.now())
                .build());
    }

    @Operation(summary = "Get all courses by instructor id")
    @GetMapping("/{instructor-id}/courses")
    public ResponseEntity<?> getAllCourseByInstructorId(@PathVariable("instructor-id") Integer id) {
        ResponseEntity<?> check = getInstructorById(id);
        if (check.getStatusCode() == HttpStatus.NOT_FOUND) {
            return check;
        }
        return ResponseEntity.ok(ApiResponse.<List<Course>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get All Courses By Instructor Successfully")
                .payload(instructorService.getAllCourseByInstructorId(id))
                .timestamp(Instant.now())
                .build());
    }
}