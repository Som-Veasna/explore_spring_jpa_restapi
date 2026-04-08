package com.sna.jpa.spring_jpa_restapi.service.impl;
import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Instructor;
import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import com.sna.jpa.spring_jpa_restapi.model.request.CourseRequest;
import com.sna.jpa.spring_jpa_restapi.repository.CourseRepository;
import com.sna.jpa.spring_jpa_restapi.repository.InstructorRepository;
import com.sna.jpa.spring_jpa_restapi.service.CourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                              InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Course> getAllCourse(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("courseId").descending());
        return courseRepository.findAll(pageable).getContent();
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course saveCourse(CourseRequest request) {
        Instructor instructor = instructorRepository.findById(request.getInstructorId()).orElse(null);
        if (instructor == null) return null;
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Integer id, CourseRequest request) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) return null;
        Instructor instructor = instructorRepository.findById(request.getInstructorId()).orElse(null);
        if (instructor == null) return null;
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudentByCourseId(Integer courseId) {
        return courseRepository.findAllStudentByCourseId(courseId);
    }
}