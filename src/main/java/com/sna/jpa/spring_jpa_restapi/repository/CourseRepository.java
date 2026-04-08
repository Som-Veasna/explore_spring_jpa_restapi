package com.sna.jpa.spring_jpa_restapi.repository;

import com.sna.jpa.spring_jpa_restapi.model.entity.Course;
import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByInstructor_InstructorId(Integer instructorId);

    @Query("""
        SELECT s FROM Student s
        JOIN s.courses c
        WHERE c.courseId = :courseId
    """)
    List<Student> findAllStudentByCourseId(Integer courseId);
}