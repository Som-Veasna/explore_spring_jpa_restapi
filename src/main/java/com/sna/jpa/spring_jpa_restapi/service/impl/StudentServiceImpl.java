package com.sna.jpa.spring_jpa_restapi.service.impl;

import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import com.sna.jpa.spring_jpa_restapi.model.request.StudentRequest;
import com.sna.jpa.spring_jpa_restapi.repository.CourseRepository;
import com.sna.jpa.spring_jpa_restapi.repository.StudentRepository;
import com.sna.jpa.spring_jpa_restapi.service.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                               CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> getAllStudent(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("studentId").descending());
        return studentRepository.findAll(pageable).getContent();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    private List<Course> getCourses(StudentRequest request) {
        List<Course> courses = new ArrayList<>();
        for (Integer courseId : request.getCourseId()) {
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course == null) return null;
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Student saveStudent(StudentRequest request) {
        List<Course> courses = getCourses(request);
        if (courses == null) return null;
        Student student = new Student();
        student.setStudentName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCourses(courses);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Integer id, StudentRequest request) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) return null;
        List<Course> courses = getCourses(request);
        if (courses == null) return null;
        student.setStudentName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCourses(courses);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}