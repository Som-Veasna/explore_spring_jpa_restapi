package com.sna.jpa.spring_jpa_restapi.service.impl;

import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Instructor;
import com.sna.jpa.spring_jpa_restapi.model.request.InstructorRequest;
import com.sna.jpa.spring_jpa_restapi.repository.CourseRepository;
import com.sna.jpa.spring_jpa_restapi.repository.InstructorRepository;
import com.sna.jpa.spring_jpa_restapi.service.InstructorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository,
                                  CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("instructorId").descending());
        return instructorRepository.findAll(pageable).getContent();
    }

    @Override
    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public Instructor saveInstructor(InstructorRequest request) {
        Instructor instructor = new Instructor();
        instructor.setInstructorName(request.getName());
        instructor.setEmail(request.getEmail());
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor updateInstructor(Integer id, InstructorRequest request) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor == null) return null;
        instructor.setInstructorName(request.getName());
        instructor.setEmail(request.getEmail());
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructorById(Integer id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCourseByInstructorId(Integer instructorId) {
        return courseRepository.findByInstructor_InstructorId(instructorId);
    }
}