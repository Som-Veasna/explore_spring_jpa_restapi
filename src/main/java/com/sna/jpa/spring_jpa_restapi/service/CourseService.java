package com.sna.jpa.spring_jpa_restapi.service;

import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import com.sna.jpa.spring_jpa_restapi.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse(Integer size, Integer page);
    Course getCourseById(Integer id);
    Course saveCourse(CourseRequest request);
    Course updateCourse(Integer id, CourseRequest request);
    void deleteCourse(Integer id);
    List<Student> getAllStudentByCourseId(Integer courseId);
}