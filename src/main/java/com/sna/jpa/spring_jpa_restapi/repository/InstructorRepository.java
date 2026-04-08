package com.sna.jpa.spring_jpa_restapi.repository;

import com.sna.jpa.spring_jpa_restapi.model.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}