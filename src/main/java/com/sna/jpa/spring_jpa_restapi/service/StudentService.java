package com.sna.jpa.spring_jpa_restapi.service;


import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import com.sna.jpa.spring_jpa_restapi.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent(Integer size, Integer page);
    Student getStudentById(Integer id);
    Student saveStudent(StudentRequest request);
    Student updateStudent(Integer id, StudentRequest request);
    void deleteStudent(Integer id);
}