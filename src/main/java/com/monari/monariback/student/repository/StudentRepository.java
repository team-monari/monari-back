package com.monari.monariback.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monari.monariback.student.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
