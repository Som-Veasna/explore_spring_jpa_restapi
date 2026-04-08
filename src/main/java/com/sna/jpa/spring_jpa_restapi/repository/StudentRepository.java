package com.sna.jpa.spring_jpa_restapi.repository;

import com.sna.jpa.spring_jpa_restapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Integer countByEmail(String email);
}