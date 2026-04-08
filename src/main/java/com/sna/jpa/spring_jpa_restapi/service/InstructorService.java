package com.sna.jpa.spring_jpa_restapi.service;

import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Instructor;
import com.sna.jpa.spring_jpa_restapi.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Integer size, Integer page);
    Instructor getInstructorById(Integer id);
    Instructor saveInstructor(InstructorRequest request);
    Instructor updateInstructor(Integer id, InstructorRequest request);
    void deleteInstructorById(Integer id);
    List<Course> getAllCourseByInstructorId(Integer instructorId);
}