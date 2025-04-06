package com.monari.monariback.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monari.monariback.teacher.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
